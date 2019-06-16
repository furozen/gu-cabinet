import {Injectable, OnDestroy, OnInit} from '@angular/core';
import {from, Observable, of} from 'rxjs';
import {GUFieldType, IGUPredefinedServiceData, IGUServiceData} from './Types';
import {filter} from 'rxjs/operators';
import {PredefinedComponent} from './dashboard/predefined/predefined.component';



const PredefinedTestData:IGUPredefinedServiceData[] = [
  {
    title:"Фестиваль района",
    description:"Госуслуга по привлечению населения района",
    template:{
      id:-1,
      name:'Фестиваль района',
      description: 'Госуслуга по привлечению населения района',
      fields: [],
      isApproved: false,
      isPublished:false,
      isDeleted:false
    }
  },
  {
    title:"Фестиваль района",
    description:"Госуслуга по привлечению населения района",
    template:{
      id:-1,
      name:'Фестиваль района',
      description: 'Госуслуга по привлечению населения района',
      fields: [],
      isApproved: false,
      isPublished:false,
      isDeleted:false
    }
  },
  {
    title:"Фестиваль района",
    description:"Госуслуга по привлечению населения района",
    template:{
      id:-1,
      name:'Фестиваль района',
      description: 'Госуслуга по привлечению населения района',
      fields: [],
      isApproved: false,
      isPublished:false,
      isDeleted:false
    }
  },
  {
    title:"Фестиваль района",
    description:"Госуслуга по привлечению населения района",
    template:{
      id:-1,
      name:'Фестиваль района',
      description: 'Госуслуга по привлечению населения района',
      fields: [],
      isApproved: false,
      isPublished:false,
      isDeleted:false
    }
  },
  {
    title:"Фестиваль района",
    description:"Госуслуга по привлечению населения района",
    template:{
      id:-1,
      name:'Фестиваль района',
      description: 'Госуслуга по привлечению населения района',
      fields: [],
      isApproved: false,
      isPublished:false,
      isDeleted:false
    }
  },
  {
    title:"Фестиваль района",
    description:"Госуслуга по привлечению населения района",
    template:{
      id:-1,
      name:'Фестиваль района',
      description: 'Госуслуга по привлечению населения района',
      fields: [],
      isApproved: false,
      isPublished:false,
      isDeleted:false
    }
  },
  {
    title:"Фестиваль района",
    description:"Госуслуга по привлечению населения района",
    template:{
      id:-1,
      name:'Фестиваль района',
      description: 'Госуслуга по привлечению населения района',
      fields: [],
      isApproved: false,
      isPublished:false,
      isDeleted:false
    }
  }

];

const TestData:IGUServiceData[] = [
  {
    id:1,
    name:'Детский летний лагерь',
    description: ' Запись детей в летний лагерь',
    fields: [],
    isApproved: true,
    isPublished:false,
    isDeleted:false
  },
  {
    id:2,
    name:'Детский летний лагерь',
    description: ' Запись детей в летний лагерь',
    fields: [],
    isApproved: true,
    isPublished:false,
    isDeleted:true
  },
  {
    id:3,
    name:'Детский летний лагерь',
    description: ' Запись детей в летний лагерь',
    fields: [],
    isApproved: true,
    isPublished:false,
    isDeleted:false
  },
  {
    id:4,
    name:'Детский летний лагерь',
    description: ' Запись детей в летний лагерь',
    fields: [],
    isApproved: true,
    isPublished:true,
    isDeleted:false
  },
  {
    id:5,
    name:'Детский летний лагерь',
    description: ' Запись детей в летний лагерь',
    fields: [
      {
      "type": GUFieldType.input,
      "label": "Фамилия",
      "name": "Familiya",
      "value": "",
      "validators": [
        {
          "name": "required",
          "validator": "Required",
          "message": "Name Required"
        },
        {
          "name": "pattern",
          "validator": "^[a-zA-Z]+$",
          "message": "Accept only text"
        }
      ],
      "editable": false,
      "description": "введите фамилию"
    },
      {
      "type": GUFieldType.select,
      "label": "Тип лагеря",
      "name": "Familiya",
      "options": ["Спортивный", 'Языковый', 'Театральный'],
      "validators": [
        {
          "name": "required",
          "validator": "Required",
          "message": "Name Required"
        },
        {
          "name": "pattern",
          "validator": "^[a-zA-Z]+$",
          "message": "Accept only text"
        }
      ],
      "editable": false,
      "description": "выберите тип лагеря"
    }

    ],
    isApproved: false,
    isPublished:false,
    isDeleted:false
  },

]

@Injectable({
  providedIn: 'root'
})
export class DataProviderService implements OnDestroy,OnInit{

  private data:IGUServiceData[];
  private predefined:IGUPredefinedServiceData[];
  private localStorageKey = 'localData';
  constructor() {
    // TODO get data from web
    this.data = TestData;
    this.predefined  = PredefinedTestData;
  }

  getList(showDeleted:boolean=false):Observable<IGUServiceData>{
    return from(this.data).pipe(filter(item=>showDeleted || !item.isDeleted));
  }

  getPredefinedList():Observable<IGUPredefinedServiceData>{
    return from(this.predefined);
  }

  getDataById(id:number):Observable<IGUServiceData>{
    return from(this.data).pipe(filter(item=>item.id === id));
  }

  saveById(id:number, newData:IGUServiceData) {
    if (id >= 0) {
      let item = this.data.find(item => item.id === id);
      item = newData;
      console.log('modify new value:', item);
    } else if (newData.id === -1) {
      // new element
      newData.id = this.getNewIndex();
      this.data.push(newData);

      console.log('create new with id:', id);
    }
  }
  // todo more sophisticated
  getNewIndex():number{
    return this.data.length;
  }

  deleteById(id:number){
    let index = this.data.findIndex(item => item.id ===id);
    if(index !== -1){
      this.data.splice(index,1);
    }
    return of(this.data[0]);
  }
  ngOnDestroy():void {
    localStorage.setItem(this.localStorageKey, JSON.stringify(this.data));
  }

  ngOnInit():void {
    let data = JSON.parse(localStorage.getItem(this.localStorageKey));
    if(data){
      this.data = data;
    }
  }


}
