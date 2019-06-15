
export enum GUFieldType {
  'input' = 'input',
  'select' = 'select',
  'predef' = 'predef'
}

export interface IValidator {
  name: string;
  validator:any;
  message :string;
}

export interface IGUGenericField {
  type: GUFieldType,
  label: string,
  name : string,
  validators: IValidator[],
  description?:string,
  editable?:boolean
}

export interface IGUFieldInput extends IGUGenericField{
  type:GUFieldType.input,
  value:string
}

export interface IGUFieldSelect extends IGUGenericField{
  type:GUFieldType.select;
  options: string[];
}
//TODO add date/date-range[s] fields
export type TGUFields = IGUFieldInput | IGUFieldSelect;

export interface IGUGroupFields{
  name:string;
  type:GUFieldType.predef;
  fields:TGUFields[];
}

export type IGUComplexField = (TGUFields|IGUGroupFields);

export interface IGUServiceData {
  id:number;
  name:string;
  description: string;
  fields: IGUComplexField[];
  // should be status-of-Approval : approved, disparroved, pending
  isApproved:boolean;
  isPublished:boolean;
  // We do not really delete any data just mark it as deleted.
  isDeleted:boolean;
}
