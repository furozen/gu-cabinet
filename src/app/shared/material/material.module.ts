import { NgModule } from '@angular/core';
import {
  MatCardModule, MatDatepickerModule,
  MatExpansionModule,
  MatListModule,
  MatMenuModule, MatNativeDateModule, MatSelectModule,
  MatSidenavModule, MatSlideToggleModule,
  MatToolbarModule
} from '@angular/material';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material';
import {MatInputModule} from '@angular/material';

const items = [
  MatCardModule,
  MatIconModule,
  MatFormFieldModule,
  MatInputModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatSidenavModule,
  MatListModule,
  MatToolbarModule,
  MatMenuModule,
  MatSlideToggleModule,
  MatExpansionModule,
  MatSelectModule

];


@NgModule({
  declarations: [],
  imports: [
    items
  ],
  exports: [
    items
  ]
})


export class MaterialModule { }
