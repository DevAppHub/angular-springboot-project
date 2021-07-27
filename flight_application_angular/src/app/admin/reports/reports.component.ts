import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import jsPDF from 'jspdf';
import { AdminToastService } from '../service/admin-toast.service';
import { ReportService } from '../service/reportService';
import 'jspdf-autotable';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  reportForm: FormGroup;

  head = [['ID', 'Email Id', 'Mobile Number', 'Flight Number']];
  data: any;

  constructor(private toastService: AdminToastService, private reportService: ReportService,) {
    this.reportForm = new FormGroup({
      "flight_no": new FormControl("", Validators.required)
    })
  }

  ngOnInit(): void {
  }

  downloadReport() {
    let flight_no = this.reportForm.get('flight_no')?.value;
    this.reportService.getReport(flight_no).subscribe((res: any) => {
      this.data = JSON.stringify(res);
      const doc = new jsPDF();
      var text = doc.splitTextToSize(JSON.stringify(this.data), 180);
      doc.text(text, 10, 15,);
      doc.setFont("times", "bold");
      doc.save('report.pdf');
    })
  }

}
