import {Component, OnDestroy, OnInit} from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Subscription} from "rxjs";
import {CommentListComponent} from "../comment-list/comment-list.component"

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit, OnDestroy {

  prod: Product;

  productId: number;

  isLoading = false;

  private productsSubscription: Subscription;

  constructor(public productService: ProductService, public route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe( (paramMap: ParamMap) => {
      if (paramMap.has('productId')) {
        this.productId = +paramMap.get('productId');
        this.productService.getProductById(this.productId);
        this.isLoading = true;
      }
    });

    this.productsSubscription = this.productService.getProductUpdateListener()
      .subscribe((product: Product) => {
        this.isLoading = false;
        this.prod = product;
      });
  }

  ngOnDestroy() {
    this.productsSubscription.unsubscribe();
  }

}
