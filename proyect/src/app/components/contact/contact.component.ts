import { Component, OnInit } from '@angular/core';

import {DatabaseService} from '../../services/database.service';
import {FormControl,FormGroup} from '@angular/forms';
import {Route,RouterModule} from '@angular/router';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  createFormGroup(){
    return new FormGroup({
      name: new FormControl(''),
      email: new FormControl(''),
      message: new FormControl(''),
    })
    
  }

  contactForm: FormGroup;

  constructor(private dbData: DatabaseService) {
    this.contactForm = this.createFormGroup();  
  }

  onReset(){
    this.contactForm.reset();

  }

  onSaveForm(){
    console.log("Envio realizado con exito", this.contactForm.value);
    this.dbData.saveMessage(this.contactForm.value);
  }


  ngOnInit() {
  }

}
