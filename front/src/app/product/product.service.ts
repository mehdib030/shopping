import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { Product} from './product';
import {Comment} from "./comment";
import {Subject} from "rxjs/internal/Subject";
import {ReplaySubject} from "rxjs/internal/ReplaySubject";
import {BehaviorSubject} from "rxjs/internal/BehaviorSubject";
import {Router} from "@angular/router";

const SERVER_URL = environment.url + 'products/';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  product:Product;

  private prods: Product[] = [];

  private productUpdated = new ReplaySubject<Product>();

  private productsUpdated = new ReplaySubject<Product[]>();

  constructor(private http: HttpClient, private router: Router) {}

  getProducts() {

    return this.http.get<Product[]>(SERVER_URL).subscribe(products => {
        this.prods=products;
        this.productsUpdated.next(...[this.prods]);
        this.router.navigate(['/product/product']);
      }
    );
    /*.pipe( map(productData => {
        return {
        prods1 : productData.map(prod  => {
                {
                  description:prod.description,
                  sku:prod.sku,
                  name:prod.name,
                  price:prod.price
                };
            })
        }
      })
    )*/

     /* .pipe( map(
              prod => {
                return
                {
                  description:prod.description,
                    sku:prod.sku,
                    name:prod.name,
                    price:prod.price
                };
              })

        )*/

      /*.pipe( map(productData => {
         return {
           prods1:
             {
               description: productData.description,
               sku: productData.sku,
               name: productData.name,
               price: productData.price
             }
        };

      })
      )*/
      /*.pipe(map( (prods1:any) =>
              prods1.results
        )
      )*/

  }

  getProductById(id:number) {

     this.http.get<Product>(SERVER_URL+'product/'+id).subscribe(product => {
          this.product=product;
          this.productUpdated.next(...[this.product]);
      }
    );
  }

  getProductByName(name:string) {

    this.http.get<Product[]>(SERVER_URL+'productbyname/'+name).subscribe(products => {
        this.prods=products;
        this.productsUpdated.next(...[this.prods]);
        this.router.navigate(['/product/product']);
      }
    );
  }

  getProductsByCategory(categoryName:string){

    return this.http.get(SERVER_URL+name)

  }

  getProductUpdateListener() {
    return this.productUpdated.asObservable();
  }

  getProductsUpdateListener() {
    return this.productsUpdated.asObservable();
  }


}
