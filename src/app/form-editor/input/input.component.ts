import {Component, ElementRef, OnInit} from '@angular/core';
import {Utils} from '../../utils.class';
import {TGUFields} from '../../Types';


@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent implements OnInit {
  field: TGUFields;
  constructor(private el: ElementRef) { }

  ngOnInit() {
  }

  getName(value){
    return Utils.translitate(value,'_');
  }


  move(direction, item){

    this.el.nativeElement
      .dispatchEvent(new CustomEvent('moveItem', {
        detail: {direction, item },
        bubbles: true
      }));

  }

}
