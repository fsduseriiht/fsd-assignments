import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { CreateTaskComponent } from '../components/create-task/create-task.component';
import { ViewTaskComponent } from '../components/view-task/view-task.component';

const routes: Routes = [
  { path: '', component: CreateTaskComponent, pathMatch: 'full' },
  { path: 'view', component: ViewTaskComponent },
  { path: 'create', component: CreateTaskComponent },
  { path: 'edit/:id', component: CreateTaskComponent }
];


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AppRoutingModule { }
