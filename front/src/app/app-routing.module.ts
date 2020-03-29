import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CategoryComponent} from "./category/category.component";

const routes: Routes = [

  {path:"authentication",loadChildren:"./authentication/authentication.module#AuthenticationModule"}, //lazy loading
  {path:"product",loadChildren:"./product/product.module#ProductModule"}, //lazy loading
  {path:"cart",loadChildren:"./cart/cart.module#CartModule"}, //lazy loading
  {path: 'category/:categoryName', component: CategoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
