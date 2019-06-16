import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, ActivationEnd, Router} from '@angular/router';
import {DataProviderService} from './data-provider.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnDestroy, OnInit{
  title = 'gu-cabinet';

  constructor(private router:Router, private dataProvider:DataProviderService) {

    this.router.events.subscribe(event=>{
      if(event instanceof ActivationEnd){
        this.title = event.snapshot.data['name'];
      }
    });
  }

  ngOnDestroy():void {
    this.dataProvider.ngOnDestroy();
  }
  ngOnInit():void {
    this.dataProvider.ngOnInit();
  }
}
