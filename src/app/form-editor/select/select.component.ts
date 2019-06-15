import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Utils} from '../../utils.class';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent implements OnInit {

  @Output() moveFieldEvent:EventEmitter<object> = new EventEmitter<object>();
  constructor() { }

  ngOnInit() {
  }

  getName(value){
    return Utils.translitate(value,'_');
  }

  move(direction, item){
    this.moveFieldEvent.emit({direction, item })
  }
}
