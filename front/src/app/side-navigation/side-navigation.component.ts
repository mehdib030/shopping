import { Component, OnInit,EventEmitter, Output } from '@angular/core';
import {CategoryService} from "../category/category.service";
import {ProductService} from "../product/product.service";
import {Product} from "../product/product";
import {Category} from "../category/category";

@Component({
  selector: 'app-side-navigation',
  templateUrl: './side-navigation.component.html',
  styleUrls: ['./side-navigation.component.css']
})
export class SideNavigationComponent implements OnInit {

  @Output() closeSidenav = new EventEmitter<void>();

  constructor(public categoryService:CategoryService,public productService:ProductService) { }

  prods : Product[]=[];

  ngOnInit() {
  }

  onClose() {
    this.closeSidenav.emit();
  }

  findProductsByCategory(){

    this.categoryService.findProductsByCategory("Books").subscribe(category => {

        this.prods = category.products;
        console.log( this.prods );

      }

    );

    //this.prods = this.productService.getProductsByCategory("Books");
    this.closeSidenav.emit();
  }

}
