import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-search-dialogue',
  template: `<div class="container-fluid">
    <h4 class="modal-title">Hi there!</h4>
  </div>`,
  styleUrls: ['./search-dialogue.component.css']
})
export class SearchDialogueComponent implements OnInit {

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

}
