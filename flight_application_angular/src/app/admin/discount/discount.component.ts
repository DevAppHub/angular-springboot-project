import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AdminToastService } from '../service/admin-toast.service';
import { DiscountService } from '../service/discount.service';

@Component({
  selector: 'app-discount',
  templateUrl: './discount.component.html',
  styleUrls: ['./discount.component.css']
})
export class DiscountComponent implements OnInit {

  closeResult = '';
  modalcontent: any | undefined;
  addDiscountForm: FormGroup;
  updateDiscountForm: FormGroup;
  error: any;
  discount_det = {
    id: "",
    discount_number: "",
    discount_name: "",
    discount_validity: "",
    discount_price: ""
  }
  discountList = [this.discount_det];

  constructor(private adminToastService: AdminToastService, private discountService: DiscountService, private router: Router, private modalService: NgbModal) {
    this.addDiscountForm = new FormGroup({
      "discount_number": new FormControl("", Validators.required),
      "discount_name": new FormControl("", Validators.required),
      "discount_validity": new FormControl("", Validators.required),
      "discount_price": new FormControl("", Validators.required),
    });

    this.updateDiscountForm = new FormGroup({
      "discount_number": new FormControl("", Validators.required),
      "discount_name": new FormControl("", Validators.required),
      "discount_validity": new FormControl("", Validators.required),
      "discount_price": new FormControl("", Validators.required),
    });
  }

  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
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

  openUpdateDiscountModal(updateScheduleModal: any, discount: any) {

    this.modalcontent = discount;
    this.modalService.open(updateScheduleModal, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  ngOnInit(): void {
    this.discountService.getDiscountList()
      .subscribe((res: any) => {
        var obj = [];
        console.log('res lenth' + res.length);
        for (let i = 0; i < res.length; i++) {
          var flight = {
            id: res[i].id,
            discount_number: res[i].discount_number,
            discount_name: res[i].discount_name,
            discount_validity: res[i].discount_validity,
            discount_price: res[i].discount_price
          }
          obj.push(flight);
        }
        // this.flightData=obj;
        this.discountList = res;
        console.log(this.discountList);
      }, error => {
        this.error = error;
        let errorMessage = this.error.error.error_message;
        window.alert(errorMessage);
      })
  }


  updateDiscountOffer() {
    this.discount_det = {
      id: this.modalcontent.id,
      discount_name: this.modalcontent.discount_name,
      discount_number: this.modalcontent.discount_number,
      discount_price: this.modalcontent.discount_price,
      discount_validity: this.updateDiscountForm?.get('discount_validity')?.value
    }

    if (this.discount_det.discount_validity == null || this.discount_det.discount_validity == "") {
      this.discount_det.discount_validity = this.modalcontent.discount_validity;
    }
    // send request
    this.discountService.updateDiscountDetails(this.modalcontent.id, this.discount_det)
      .subscribe(res => {
        console.log(res);
        let response = res;
        if (response != null) {
          this.adminToastService.show("Discount Updated Successfully", { classname: 'bg-success text-light', delay: 1000 });
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
    console.log('remove discount offer for ' + id)
    this.discountService.removeDiscountOffer(id).subscribe((res: any) => {
      console.log('request to cancel the airline for id' + id);
      if (res != null) {
        this.adminToastService.show("Discount Removed Successfully", { classname: 'bg-success text-light', delay: 1000 });
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

  addNewDiscount() {
    this.discount_det = {
      id: "",
      discount_number: this.addDiscountForm?.get('discount_number')?.value,
      discount_name: this.addDiscountForm?.get('discount_name')?.value,
      discount_price: this.addDiscountForm?.get('discount_price')?.value,
      discount_validity: this.addDiscountForm?.get('discount_validity')?.value
    }
    this.discountService.addNewDiscountOffer(this.discount_det).subscribe(res => {
      if (res != null) {
        this.adminToastService.show("Discount Added Successfully", { classname: 'bg-success text-light', delay: 1000 });
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
