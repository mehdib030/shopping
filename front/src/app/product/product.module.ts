import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialDesignModule} from "../material-design/material-design.module";
import {FlexLayoutModule} from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {ProductRoutingModule} from "./product-routing.module";
import {ProductComponent} from "./product.component";
import { DetailComponent } from './detail/detail.component';
import { CommentComponent } from './comment-add/comment.component';
import { CommentListComponent } from './comment-list/comment-list.component';

@NgModule({
  declarations: [ProductComponent, DetailComponent, CommentComponent, CommentListComponent],
  imports: [
    CommonModule,
    MaterialDesignModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    ProductRoutingModule
  ]
})
export class ProductModule { }
