import { Component, OnInit } from '@angular/core';
import {DataProviderService} from '../data-provider.service';
import {ActivatedRoute, Router} from '@angular/router';
import {GUFieldType, IGUComplexField, IGUServiceData} from '../Types';

@Component({
  selector: 'app-form-editor',
  templateUrl: './form-editor.component.html',
  styleUrls: ['./form-editor.component.scss']
})
export class FormEditorComponent implements OnInit {
  private _id:number = -1;
  private data: IGUServiceData={
    id:-1,
    name:'',
    description: '',
    fields: [],
    isApproved: false,
    isPublished:false,
    isDeleted:false
  };

  set id(value:number) {
    this._id = value;
    if(this._id){
      this.getDataById(this._id);
    }
  }

  constructor(private dataProviderService:DataProviderService,private activeRoute:ActivatedRoute, private router:Router) {
    this.activeRoute.params.subscribe(params => this.id = parseInt(params.id));
  }

  ngOnInit() {
  }

  getDataById(id){
    // TODO catch null return
    this.dataProviderService.getDataById(id).subscribe((item)=>{
      this.data = item;
    })
  }

  rawData(){
    return JSON.stringify(this.data,null, 1 );
  }

  saveAndReturnToDashboard(){
    this.save();
    this.returnToDashboard();
  }

  save(){
    this.dataProviderService.saveById(this._id,this.data);
  }

  returnToDashboard(){
    this.router.navigate(['']);
  }

  addInput(){

    this.data.fields.push({
      "type": GUFieldType.input,
      "label": "Название поля",
      "name": "",
      "value": "",
      "validators": [
        {
          "name": "required",
          "validator": "Required",
          "message": "Name Required"
        }
      ],
      "editable": true,
      "description": "описание поля"
    })
  }

  addSelect(){
    this.data.fields.push({
      "type": GUFieldType.select,
      "label": "Название поля",
      "name": "",
      "options": [],
      "validators": [
        {
          "name": "required",
          "validator": "Required",
          "message": "Name Required"
        }
      ],
      "editable": true,
      "description": "описание поля"
    })
  }

  moveFieldEvent(event){
    let data:{direction, item} = event.detail;
    const index=this.data.fields.findIndex(item=>data.item===item);
    if(data.direction === 'up' && index>0){
      this.data.fields.splice(index-1, 2, this.data.fields[index],this.data.fields[index-1]);
    } else if(data.direction === 'down' && index < this.data.fields.length-1) {
      this.data.fields.splice(index, 2, this.data.fields[index+1],this.data.fields[index]);
    } else  if(data.direction === 'hell') {
      this.data.fields.splice(index, 1);
    }
    console.log('found index', index );
  }
}



