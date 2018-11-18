import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ApiService {
  baseUrl: string
  allTasks$: Observable<any>;

  constructor(private http: Http) {
    const appProto = window.location.href.split('/')[0];
    const appDNS = window.location.href.split('/')[2];
    const appPORT = ':8080';

    const locationUrl = appProto + '//' + appDNS.split(':')[0] + appPORT;
    this.baseUrl = locationUrl + '/tasktrackerbackend/';
   }

  /******* Service Call To CREATE New Task Details *******/
  createTask(form) {
    const formVal = form.value;
    const payload = {
        // taskId is not sent from UI it is being created at backend application
        // taskId: 16,
        task: formVal.task,
        parentId: formVal.parentTask,
        startDate: new Date(formVal.sdate).toISOString(),
        endDate: new Date(formVal.edate).toISOString(),
        priority: formVal.priority
    };
    return this.http.post(this.baseUrl + 'task/', payload);
  }


  /******* Service Call To Update Existing Task Details *******/
  updateTask(form, taskId) {
    const formVal = form.value;
    const payload = {
        taskId: taskId,
        task: formVal.task,
        parentId: formVal.parentTask,
        startDate: new Date(formVal.sdate).toISOString(),
        endDate: new Date(formVal.edate).toISOString(),
        priority: formVal.priority
    };
    console.log(payload);
    return this.http.put(this.baseUrl + 'task/'+taskId, payload);
  }

  getParentTasks() {
    return this.http.get(this.baseUrl + 'parent/list').map(res => res.json());
  }

  getAllTasks() {
    this.allTasks$ = this.http.get(this.baseUrl + 'task/list').map(res => res.json());
    return this.allTasks$;
  }

  deleteTask(taskId) {
    return this.http.delete(this.baseUrl + 'task/' + taskId);
  }

  getEditTask() {
    return {
        taskId: 1,
        task: "task1",
        parentId: 105,
        startDate: new Date().toISOString(),
        endDate: new Date().toISOString(),
        priority: 30
    };
  }
}
