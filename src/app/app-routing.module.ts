import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {FormEditorComponent} from './form-editor/form-editor.component';

const routes: Routes = [
  {path: '', component: DashboardComponent, data:{name: 'Основная'}},
  {path: 'modify/:id', component: FormEditorComponent, data:{name: 'Редактирование'}},
  {path: 'create', component: FormEditorComponent, data:{name: 'Создание формы'} }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
