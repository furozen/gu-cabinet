import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DataProviderService} from '../data-provider.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy {

  constructor(private dataProvider:DataProviderService) {
  }
  //TODO onDestroy for something more global
  ngOnDestroy():void {
    this.dataProvider.ngOnDestroy();
  }

  ngOnInit() {
  }

}
