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
    title:"Запись на прием",
    description:"Госуслуга к главе района",
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
    title:"Ренген кабинет",
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
    title:"Справка о регистрации",
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
    title:"Патент получение",
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
    'id':6,
    'name':'Оформление загранпаспотра ',
    'description':'Выдача загранпаспортов старого и нового поколения ',
    'fields':[{
      'type':GUFieldType.input,
      'label':'Заявление ',
      'name':'Zayavlenie_',
      'value':'',
      'validators':[{'name':'required', 'validator':'Required', 'message':'Name Required'}],
      'editable':true,
      'description':'Заполните все необходимые поля'
    }, {
      'type':GUFieldType.input,
      'label':'Госпошлина',
      'name':'Gosposhlina',
      'value':'',
      'validators':[{'name':'required', 'validator':'Required', 'message':'Name Required'}],
      'editable':true,
      'description':'Необходимо оплатить госпошлину '
    }],
    'isApproved':false,
    'isPublished':false,
    'isDeleted':false
  },
  {
    "id": 4,
    "name": "Проверка штрафов ",
    "description": "Административные нарушения проверяются в базах ГИБДД  и системы государственных  платежей (ГИС ГМП). ",
    "fields": [
      {
        "type": GUFieldType.input,
        "label": "Паспорт",
        "name": "Pasport",
        "value": "",
        "validators": [
          {
            "name": "required",
            "validator": "Required",
            "message": "Name Required"
          }
        ],
        "editable": true,
        "description": "Введите серию и номер паспорта"
      },
      {
        "type": GUFieldType.input,
        "label": "Водительское удостоверение",
        "name": "Voditelskoe_udostoverenie",
        "value": "",
        "validators": [
          {
            "name": "required",
            "validator": "Required",
            "message": "Name Required"
          }
        ],
        "editable": true,
        "description": "Введите номер своего водительского удостоверения"
      },
      {
        "type": GUFieldType.input,
        "label": "Номер транспортного средства",
        "name": "Nomer_transportnogo_sredstva",
        "value": "",
        "validators": [
          {
            "name": "required",
            "validator": "Required",
            "message": "Name Required"
          }
        ],
        "editable": true,
        "description": "Введите номер своего транспортного средства"
      }
    ],
    "isApproved": false,
    "isPublished": false,
    "isDeleted": false
  },
  {
    "id": 5,
    "name": "Детский летний лагерь",
    "description": "Запись ребенка в летний лагерь",
    "fields": [
      {
        "type": GUFieldType.input,
        "label": "Имя",
        "name": "Imya",
        "value": "",
        "validators": [
          {
            "name": "required",
            "validator": "Required",
            "message": "Name Required"
          }
        ],
        "editable": true,
        "description": "Введите имя ребенка"
      },
      {
        "type": GUFieldType.select,
        "label": "Пол",
        "name": "Pol",
        "options": [
          "мужской",
          "женский"
        ],
        "validators": [
          {
            "name": "required",
            "validator": "Required",
            "message": "Name Required"
          }
        ],
        "editable": true,
        "description": "выберете пол ребенка"
      },
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
          }
        ],
        "editable": true,
        "description": "Введите фамилию ребенка"
      },
      {
        "type": GUFieldType.select,
        "label": "Возраст ребенка ",
        "name": "Vozrast_rebenka_",
        "options": [
          "6",
          "7",
          "8",
          "9",
          "10",
          "11",
          "12"
        ],
        "validators": [
          {
            "name": "required",
            "validator": "Required",
            "message": "Name Required"
          }
        ],
        "editable": true,
        "description": "Выберете возраст"
      },
      {
        "type": GUFieldType.input,
        "label": "телефон",
        "name": "telefon",
        "value": "",
        "validators": [
          {
            "name": "required",
            "validator": "Required",
            "message": "Name Required"
          }
        ],
        "editable": true,
        "description": "введите ваш телефонный номер"
      },
      {
        "type": GUFieldType.select,
        "label": "Тип лагеря",
        "name": "Tip_lagerya",
        "options": [
          "Спортивный",
          "Языковый",
          "Театральный"
        ],
        "validators": [
          {
            "name": "required",
            "validator": "Required",
            "message": "Name Required"
          }
        ],
        "editable": true,
        "description": "Выберете тип лагеря"
      }
    ],
  "isApproved": false,
  "isPublished": false,
  "isDeleted": false
  }

]

@Injectable({
  providedIn: 'root'
})
export class DataProviderService implements OnDestroy,OnInit{

  private data:IGUServiceData[];
  private predefined:IGUPredefinedServiceData[];
  //private localStorageKey = 'localData';
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
    /*if(localStorage) {
      localStorage.setItem(this.localStorageKey, JSON.stringify(this.data));
    }*/
  }

  ngOnInit():void {
    /*if(localStorage) {
      let data = JSON.parse(localStorage.getItem(this.localStorageKey));
      if (data) {
        this.data = data;
      }
    }*/
  }


}
