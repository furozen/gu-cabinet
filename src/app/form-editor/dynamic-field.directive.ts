import {
  ComponentFactoryResolver,
    ComponentRef,
    Directive,
    Input,
    OnInit,
    ViewContainerRef
} from "@angular/core";
import { FormGroup } from "@angular/forms";

import { InputComponent } from "./input/input.component";

import { SelectComponent } from "./select/select.component";
import {TGUFields} from '../Types';


const componentMapper = {
  input: InputComponent,
  select: SelectComponent,
};
@Directive({
  selector: "[dynamicField]"
})
export class DynamicFieldDirective implements OnInit {
  @Input() field: TGUFields;

  componentRef: any;
  constructor(
    private resolver: ComponentFactoryResolver,
    private container: ViewContainerRef
  ) {}
  ngOnInit() {
    const factory = this.resolver.resolveComponentFactory(
      // TODO research why this error pop-up on prev commit
      componentMapper[this.field.type as any]
    );
    this.componentRef = this.container.createComponent(factory);
    this.componentRef.instance.field = this.field;

  }
}
