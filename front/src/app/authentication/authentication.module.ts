import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialDesignModule} from "../material-design/material-design.module";
import {AuthenticationRoutingModule} from "./authentication-routing.module";
import {FormsModule} from "@angular/forms";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";


@NgModule({
  declarations: [LoginComponent,RegisterComponent],
  imports: [
    CommonModule,
    FormsModule,
    MaterialDesignModule,
    AuthenticationRoutingModule
  ],providers: [],
})
export class AuthenticationModule { }
