import { Component, OnInit } from '@angular/core';

import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  contactForm: any;

  constructor(private formBuilder: FormBuilder) {
      
    this.contactForm = this.formBuilder.group({
      'name': ['', [Validators.required, Validators.minLength(2)]],
      'mobile': ['', [Validators.required]],
      'email': ['', [Validators.required]],
      'remarks': ['', Validators.required]
    });
  }

  ngOnInit() {
  }

  addMessage() {
    this.contactForm.reset();
    alert('Message submitted successfully!!')
  }
}
