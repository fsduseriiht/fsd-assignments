import { Component, OnInit } from '@angular/core';

import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-biography',
  templateUrl: './biography.component.html',
  styleUrls: ['./biography.component.css']
})
export class BiographyComponent implements OnInit {
  bioForm: any;
  detailsList = [];

  constructor(private formBuilder: FormBuilder) {
      
    this.bioForm = this.formBuilder.group({
      'degree': ['', Validators.required],
      'instituteName': ['', [Validators.required]],
      'institutePlace': ['', [Validators.required]],
      'yearOfPassing': ['', Validators.required],
      'marksObtained': ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  addDetails() {
    this.detailsList = this.detailsList.concat(this.bioForm.value);
    this.bioForm.reset();
  }

  deleteDetails(userToDelete) {
    this.detailsList = this.detailsList.filter(user => user !== userToDelete);
    this.bioForm.reset();
  }

}
