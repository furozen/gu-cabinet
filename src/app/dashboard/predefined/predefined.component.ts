import { Component, OnInit } from '@angular/core';
import {DataProviderService} from '../../data-provider.service';
import {IGUPredefinedServiceData} from '../../Types';
import {NavigationExtras, Router} from '@angular/router';

@Component({
  selector: 'app-predefined',
  templateUrl: './predefined.component.html',
  styleUrls: ['./predefined.component.scss']
})
export class PredefinedComponent implements OnInit {
  list:IGUPredefinedServiceData[] = [];
  constructor(private dataProviderService:DataProviderService,private router:Router ) {

    this.dataProviderService.getPredefinedList().subscribe(item => this.list.push(item))
  }

  ngOnInit() {
  }

  createByTemplate(item:IGUPredefinedServiceData){
    const template = JSON.stringify(item);
    let navigationExtras: NavigationExtras = {
      queryParams: {
        "template": template
      }
    };
    this.router.navigate(["create"],navigationExtras)
  }

}
