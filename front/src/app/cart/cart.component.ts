import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProductService} from "../product/product.service";
import {Product} from "../product/product";
import {Subscription} from "rxjs/internal/Subscription";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit,OnDestroy {
  prods:Product[] = [];

  productsSubscription:Subscription;

  constructor(public productService:ProductService ) { }

  ngOnInit() {
     //this.productService.getProducts();

      this.productsSubscription = this.productService.getProductsUpdateListener().subscribe(prods => {
      this.prods = prods;
      console.log(this.prods)}
    );
  }

  ngOnDestroy(){
    this.productsSubscription.unsubscribe()
  }

}
