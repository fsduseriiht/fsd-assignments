import { Component, OnInit } from '@angular/core';

import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {
  projectForm: any;
  projectsList = [];

  constructor(private formBuilder: FormBuilder) {
      
    this.projectForm = this.formBuilder.group({
      'projectName': ['', Validators.required],
      'duration': ['', [Validators.required]],
      'technologies': ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  addProject() {
    this.projectsList = this.projectsList.concat(this.projectForm.value);
    this.projectForm.reset();
  }

  deleteProject(projectToDelete) {
    this.projectsList = this.projectsList.filter(project => project !== projectToDelete);
    this.projectForm.reset();
  }
}
