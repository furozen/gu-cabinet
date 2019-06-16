import { Component, OnInit } from '@angular/core';
import {DataProviderService} from '../../data-provider.service';
import {IGUServiceData} from '../../Types';
import {Router} from '@angular/router';

@Component({
  selector: 'app-service-list',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.scss']
})
export class ServiceListComponent implements OnInit {

  constructor(private dataProviderService:DataProviderService,  private router: Router) {
    this.getList();
  }

  list:IGUServiceData[];
  getList(showDeleted:boolean=false){
    this.list  = [];
    this.dataProviderService.getList(showDeleted).subscribe(
      (item) => this.list.push(item)
    )
  }

  delete(id:number){
    this.dataProviderService.deleteById(id).subscribe(_=> {this.getList()});
  }


  ngOnInit() {
  }

  step = 0;

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }
// TODO no way use this logic becouse can be holes in indexes
  prevStep() {
    this.step--;
  }

  edit(id:number){
    this.router.navigate(['modify', id])
  }

}
