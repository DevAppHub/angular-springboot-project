import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CompanyService } from '../service/company.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  error: any;
  addCompanyForm: FormGroup;
  companySearchForm: FormGroup;
  company_det={
    "company_id": "",
    "company_name": "",
    "company_website":"",
    "company_turnover":"",
    "company_ceo":""
  }
  compSearchResult= {
    "company_id": "",
    "company_name": "",
    "company_website":"",
    "company_turnover":"",
    "company_ceo":""
  };
  companyData= [this.company_det];
  closeResult = '';
  modalcontent:any | undefined;

  constructor(private httpClient: HttpClient,private companyService: CompanyService, private router: Router, private modalService: NgbModal) { 
    this.addCompanyForm = new FormGroup({
      "company_id":  new FormControl("", Validators.required),
      "company_name":  new FormControl("", Validators.required),
      "company_website": new FormControl("", Validators.required),
      "company_turnover": new FormControl("", Validators.required),
      "company_ceo": new FormControl("", Validators.required)
    })
    this.companySearchForm=new FormGroup({
      "company_id": new FormControl("", Validators.required)
    })
  }

  ngOnInit(): void {
    this.companyService.getCompanyList()
      .subscribe((res: any)=>{
        this.companyData = res;
        console.log(this.companyData);
      }, error => {
        this.error = error;
        let errorMessage = this.error.error.error_message;
        window.alert(errorMessage);
      });
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  openModal(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  viewDetails(content: any, comp: any) {
    this.modalcontent = comp;
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  addCompany(){
    this.company_det = {
      company_id: this.addCompanyForm?.get('company_id')?.value,
      company_name: this.addCompanyForm?.get('company_name')?.value,
      company_ceo: this.addCompanyForm?.get('company_ceo')?.value,
      company_turnover: this.addCompanyForm?.get('company_turnover')?.value,
      company_website: this.addCompanyForm?.get('company_website')?.value
    }
    this.companyService.addNewCompany(this.company_det).subscribe(res => {
      if (res != null) {
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        let currentUrl = this.router.url;
        this.router.navigate([currentUrl]);
      }
    }, error => {
      this.error = error;
      let errorMessage = this.error.error.error_message;
      window.alert(errorMessage);
    })

  }
  remove(id: any) {
    this.companyService.deleteCompanyDetails(id).subscribe((res: any) => {
      if (res != null) {
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        let currentUrl = this.router.url;
        this.router.navigate([currentUrl]);
      }
    }, error => {
      this.error = error;
      let errorMessage = this.error.error.error_message;
      window.alert(errorMessage);
    });
  }
  searchCompany(){
    var compCode=this.companySearchForm?.get('company_id')?.value;
    this.companyService.searchCompanyBasedOnCompanyCode(compCode).subscribe((res: any) => {
      console.log(res);
      if (res != null) {
        this.company_det=res;
        this.compSearchResult=this.company_det;
      }
    }, error => {
      this.error = error;
      let errorMessage = this.error.error.error_message;
      window.alert(errorMessage);
    });
  }
}
