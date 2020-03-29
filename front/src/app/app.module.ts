import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent} from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import {MaterialDesignModule} from "./material-design/material-design.module";
import { FormsModule } from '@angular/forms';
import { SideNavigationComponent } from './side-navigation/side-navigation.component';
import {FlexLayoutModule} from '@angular/flex-layout';
import { HttpClientModule } from "@angular/common/http";

import { HTTP_INTERCEPTORS } from "@angular/common/http";
import {AuthInterceptor} from "./authentication/authentication-interceptor";
import {ErrorInterceptor} from "./error-interceptor";
import {ErrorComponent} from "./error/error.component";
import { CategoryComponent } from './category/category.component';



// mongodb:   Ov1g3opgYzq5t8oO

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SideNavigationComponent,
    ErrorComponent,
    CategoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    FlexLayoutModule,
    MaterialDesignModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
  entryComponents: [ErrorComponent]
})
export class AppModule { }
