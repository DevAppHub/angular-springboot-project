import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { StockService } from '../service/stock.service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {

  addStockForm: FormGroup;
  stockSearchForm: FormGroup;
  comp={
    "id":"",
    "company_id":"",
    "company_name":""
  }
  stock={
    "id":"",
    "stock_code":"",
    "stock_name":""
  }
  companyList=[this.comp];
  stockList=[this.stock];
 
  stock_det={
    "id":"",
    "companyCode": "",
    "companyName":"",
    "stockCode":"",
    "stockName":"",
    "stockPrice":"",
    "validDate":""
  }
  stockData= [this.stock_det];
  stockSearchResult= [this.stock_det];
  closeResult = '';
  error: any;
  modalcontent:any | undefined;

  constructor(private httpClient: HttpClient, private router: Router,private stockService: StockService, private modalService: NgbModal) { 
    this.addStockForm = new FormGroup({
      "companyCode": new FormControl("", Validators.required),
      "stockCode": new FormControl("", Validators.required),
      "validDate": new FormControl("", Validators.required),
      "stockPrice":new FormControl("", Validators.required)
    })
    this.stockSearchForm=new FormGroup({
      "companyCode": new FormControl("", Validators.required)
    })
  }

  ngOnInit(): void {
     this.stockService.getCompanyList()
      .subscribe((res: any)=>{
        this.companyList = res;
      }, error => {
        this.error = error;
        let errorMessage = this.error.error.error_message;
        window.alert(errorMessage);
      });
      this.stockService.getStockList()
      .subscribe((res: any)=>{
        this.stockList = res;
      }, error => {
        this.error = error;
        let errorMessage = this.error.error.error_message;
        window.alert(errorMessage);
      });
      this.stockService.getAvailableStockList()
      .subscribe((res: any)=>{
        this.stockData = res;
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

  remove(id: any) {
    this.stockService.deleteStockDetails(id).subscribe((res: any) => {
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
    var compCode=this.stockSearchForm?.get('companyCode')?.value;
    this.stockService.searchStockBasedOnCompanyCode(compCode).subscribe((res: any) => {
      console.log(res);
      if (res != null) {
        this.stockSearchResult=res;
      }
    }, error => {
      this.error = error;
      let errorMessage = this.error.error.error_message;
      window.alert(errorMessage);
    });
  }
  addStock(){
    this.stock_det = {
      id:"",
      companyCode: this.addStockForm?.get('companyCode')?.value,
      stockCode: this.addStockForm?.get('stockCode')?.value,
      stockPrice: this.addStockForm?.get('stockPrice')?.value,
      validDate: this.addStockForm?.get('validDate')?.value,
      stockName:"",
      companyName:""
    }
    this.stockService.addStock(this.stock_det, this.stock_det.companyCode).subscribe(res => {
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
}
