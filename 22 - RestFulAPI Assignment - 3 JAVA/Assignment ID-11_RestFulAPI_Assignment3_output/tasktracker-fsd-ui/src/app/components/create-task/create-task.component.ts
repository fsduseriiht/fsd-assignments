import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ApiService } from '../../shared/services/api.service';
import { UtilService } from '../../shared/services/util.service';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css'],
  providers: [ApiService]
})
export class CreateTaskComponent implements OnInit {
  parentTasks$: Observable<any>;
  myForm: FormGroup;
  submitted: boolean = false;
  maxPriority: number = 30;
  minPriority: number = 0;
  editedTaskId: number;
  taskName: any;
  parentTask: any;
  startDate: any;
  endDate: any;
  priority: any;

  constructor(
    private apiService: ApiService,
    private utilService: UtilService,
    private router: Router,
    route: ActivatedRoute) {
      this.editedTaskId = route.snapshot.params['id'];
      console.log(route);
    }

  ngOnInit() {
    this.myForm = new FormGroup({
      task: new FormControl('', [ Validators.required, Validators.maxLength(20),  Validators.minLength(2) ]),
      priority: new FormControl(0, [ Validators.required ]),
      parentTask: new FormControl('', [ Validators.required, Validators.maxLength(20),  Validators.minLength(2) ]),
      sdate: new FormControl('', Validators.required),
      edate: new FormControl('', Validators.required)
    });
    this.parentTasks$ = this.apiService.getParentTasks();
    if (this.editedTaskId) {
      this.apiService.getAllTasks().subscribe(tasks => {
        const editedTask = tasks.find(task => task.taskId.toString() === this.editedTaskId);
        this.taskName = editedTask.task;
        this.priority = editedTask.priority;
        this.parentTask = editedTask.parentId;
        this.startDate = this.utilService.getDate(editedTask.startDate);
        this.endDate =  this.utilService.getDate(editedTask.endDate);
        console.log(this.startDate);
      });
    }
  }

  onSubmit() {
    this.submitted = true;
    if (this.editedTaskId) {
      this.apiService.updateTask(this.myForm, this.editedTaskId).subscribe(() => {
        this.router.navigateByUrl('view');
      });
    } else {
      this.apiService.createTask(this.myForm).subscribe(() => {
        this.router.navigateByUrl('view');
      });
    }
  }

  cancelEdit() {
    this.router.navigateByUrl('view');
  }

}
