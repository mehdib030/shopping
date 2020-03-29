import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialDesignModule} from "../material-design/material-design.module";
import {FlexLayoutModule} from '@angular/flex-layout';

import {CartComponent} from "./cart.component";
import {CartRoutingModule} from "./cart-routing.module";

@NgModule({
  declarations: [CartComponent],
  imports: [
    CommonModule,
    CartRoutingModule,
    MaterialDesignModule,
    FlexLayoutModule
  ]
})
export class CartModule { }
