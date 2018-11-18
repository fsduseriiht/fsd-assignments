import { Component, Pipe, PipeTransform } from '@angular/core';

import { UtilService } from '../services/util.service';

@Pipe({
  name: 'searchContent'
})
export class SearchContentPipe implements PipeTransform {
  searchValue: string = '';
  
  constructor( private utilService: UtilService) {}

  transform(tasks: any[], taskName?: string, parentTaskName?: string,
    priorityFrom?: string, priorityTo?: string, startDate?: string, endDate?: string): any {
    if (!tasks) return [];
    return tasks
            .filter(task => {
              return task.task.toLowerCase().includes(taskName.trim().toLowerCase());
            })
            .filter(task => {
              return task.parentTask.toLowerCase().includes(parentTaskName.trim().toLowerCase());
            })
            .filter(task => {
              if (!priorityFrom && !priorityTo) {
                return task;
              } else if (priorityFrom && priorityTo) {
                return task.priority >= priorityFrom && parseInt(priorityFrom) && task.priority <= priorityTo && parseInt(priorityTo);
              }
              return task.priority >= priorityFrom && parseInt(priorityFrom) || task.priority <= priorityTo && parseInt(priorityTo);
            })
            .filter(task => {
              if (!startDate && !endDate || startDate.indexOf('NaN') || endDate.indexOf('NaN')) {
                return task;
              } else if (startDate && endDate) {
                return new Date(this.utilService.getDate(task.startDate)) >= new Date(startDate) && new Date(this.utilService.getDate(task.endDate)) <= new Date(endDate);
              }
              return new Date(this.utilService.getDate(task.startDate)) >= new Date(startDate) || new Date(this.utilService.getDate(task.endDate)) <= new Date(endDate);
            });


   








    /*
    
      
    }*/
  }

}
