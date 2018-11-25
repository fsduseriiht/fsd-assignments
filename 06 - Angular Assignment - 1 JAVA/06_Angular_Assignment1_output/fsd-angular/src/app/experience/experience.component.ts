import { Component, OnInit } from '@angular/core';

import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  experienceForm: any;
  experienceList = [];

  constructor(private formBuilder: FormBuilder) {
      
    this.experienceForm = this.formBuilder.group({
      'companyName': ['', Validators.required],
      'duration': ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  addExperience() {
    this.experienceList = this.experienceList.concat(this.experienceForm.value);
    this.experienceForm.reset();
  }

  deleteExperience(experienceToDelete) {
    this.experienceList = this.experienceList.filter(experience => experience !== experienceToDelete);
    this.experienceForm.reset();
  }

}
