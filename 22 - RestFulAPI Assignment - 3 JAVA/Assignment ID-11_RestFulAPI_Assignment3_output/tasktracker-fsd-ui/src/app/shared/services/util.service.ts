import { Injectable } from '@angular/core';

@Injectable()
export class UtilService {

  constructor() { }

  // format Date to "yyyy-MM-dd"
  getDate(isoDate) {
    const date = new Date(isoDate);
    const month = date.getMonth() + 1;
    const taskDate = date.getDate();
    //yyyy-MM-dd
    
    let newDate = '';
    if(taskDate < 10) {
      newDate = '0' + taskDate;
    } else {
      newDate = taskDate + '';
    }

    let newMonth = '';
    if(month < 10) {
      newMonth = '0' + month;
    } else {
      newMonth = month + '';
    }

    return date.getFullYear()+'-' + newMonth + '-' + newDate;
  }
}
