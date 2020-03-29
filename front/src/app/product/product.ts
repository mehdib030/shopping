import {Comment} from "./comment";

export interface Product {
  id:number
  name:string;
  price:string;
  sku:string;
  description:string;
  comments:Comment[];
}
