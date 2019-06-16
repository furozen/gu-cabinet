import {Component, ElementRef, EventEmitter, OnInit, Output} from '@angular/core';
import {Utils} from '../../utils.class';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent implements OnInit {

  @Output() moveFieldEvent:EventEmitter<object> = new EventEmitter<object>();
  constructor(private el: ElementRef) { }

  ngOnInit() {
  }

  getName(value){
    return Utils.translitate(value,'_');
  }

  move(direction, item){
    // this one is not working because dynamic element/
    // TODO consider to remove it
    this.moveFieldEvent.emit({direction, item });
    // use dom event
    this.el.nativeElement
      .dispatchEvent(new CustomEvent('moveItem', {
        detail: {direction, item },
        bubbles: true
      }));

  }
}
