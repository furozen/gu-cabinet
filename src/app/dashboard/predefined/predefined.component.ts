import { Component, OnInit } from '@angular/core';
import {DataProviderService} from '../../data-provider.service';
import {IGUPredefinedServiceData} from '../../Types';

@Component({
  selector: 'app-predefined',
  templateUrl: './predefined.component.html',
  styleUrls: ['./predefined.component.scss']
})
export class PredefinedComponent implements OnInit {
  list:IGUPredefinedServiceData[] = [];
  constructor(private dataProviderService:DataProviderService) {

    this.dataProviderService.getPredefinedList().subscribe(item => this.list.push(item))
  }

  ngOnInit() {
  }

}
