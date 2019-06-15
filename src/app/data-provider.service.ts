import {Injectable} from '@angular/core';
import {from, Observable} from 'rxjs';
import {GUFieldType, IGUServiceData} from './Types';
import {filter} from 'rxjs/operators';


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
export class DataProviderService {

  private data:IGUServiceData[];
  constructor() {
    this.data = TestData;

  }

  getList(showDeleted:boolean=false):Observable<IGUServiceData>{
    return from(this.data).pipe(filter(item=>showDeleted || !item.isDeleted));
  }

  getDataById(id:number):Observable<IGUServiceData>{
    return from(this.data).pipe(filter(item=>item.id === id));
  }

  saveById(id:number, newData:IGUServiceData){
    let item = this.data.find( item =>item.id ===id );
    let index = this.data.findIndex( item =>item.id ===id );
    item = newData;
    console.log(' by index', this.data[index], ' value:', item );
  }

}
