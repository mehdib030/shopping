import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Category} from "./category";
import {Router} from "@angular/router";

const SERVER_URL = environment.url ;

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient,private router: Router) { }



  public findProductsByCategory(name:string){

    return this.http.get<Category>(SERVER_URL+'category/products/'+name);
  }

}
