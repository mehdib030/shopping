import { Component, OnInit } from '@angular/core';
import {Category} from "./category";
import {CategoryService} from "./category.service";
import {Product} from "../product/product";
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  prods: Product[] =[];

  categoryName: string;

  constructor(public categoryService:CategoryService, public route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe( (paramMap: ParamMap) => {
      if(paramMap.has('categoryName')){

        this.categoryName=paramMap.get('categoryName');
        this.categoryService.findProductsByCategory(this.categoryName).subscribe(category => {

            this.categoryName=category.name;
            console.log(this.categoryName);
            this.prods = category.products;
            console.log( this.prods );

          }
        );
      }

    });

  }

}
