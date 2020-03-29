import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProductService} from './product.service';
import {Product} from './product';
import {Subscription} from "rxjs/internal/Subscription";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit,OnDestroy {

  private productsSubscription: Subscription;

  prods: Product[] = [];

  constructor(public productService: ProductService ) { }

  ngOnInit() {
    this.productsSubscription = this.productService.getProductsUpdateListener().subscribe(prods => {
        this.prods = prods;
        console.log( this.prods );
      }
    );
  }

  ngOnDestroy(){
    this.productsSubscription.unsubscribe()
  }



}


