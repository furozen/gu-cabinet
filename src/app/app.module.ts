import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormEditorComponent } from './form-editor/form-editor.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ServiceListComponent } from './dashboard/service-list/service-list.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {SharedModule} from './shared/shared.module';
import { DynamicFieldDirective } from './form-editor/dynamic-field.directive';
import { InputComponent } from './form-editor/input/input.component';
import { SelectComponent } from './form-editor/select/select.component';

@NgModule({
  declarations: [
    AppComponent,
    FormEditorComponent,
    DashboardComponent,
    ServiceListComponent,
    DynamicFieldDirective,
    InputComponent,
    SelectComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    NoopAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],

  entryComponents: [
    InputComponent,
    SelectComponent,
  ]
})
export class AppModule { }
