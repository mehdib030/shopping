import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from "@angular/router";
import {ProductComponent} from "./product.component";
import {DetailComponent} from "./detail/detail.component";
import {CommentComponent} from "./comment-add/comment.component";

const routes:Routes = [
  {path:"product",component:ProductComponent},
  {path:"detail/:productId", component:DetailComponent},
  {path:"add/:productId",component:CommentComponent},
  {path:'edit/:commentId',component:CommentComponent}
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
