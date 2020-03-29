import {Component, OnInit, EventEmitter, Output, OnDestroy} from '@angular/core';
import {ProductService} from "../product/product.service";
import {Subscription} from "rxjs/internal/Subscription";
import {Product} from "../product/product";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit,OnDestroy {
  @Output() sidenavToggle = new EventEmitter<void>();
  value = '';
 //userIsAuthenticated=false;
  visible=false;
  constructor(public productService:ProductService) { }

  private productsSubscription: Subscription;

  prods :Product[] =[];

  ngOnInit() {

  }

  onToggleSidenav() {
   // this.visible = false;
    this.sidenavToggle.emit();
  }

  showContent(){
    this.visible=true;
  }

  search(value:string){
    this.productService.getProductByName(value);
    this.productsSubscription = this.productService.getProductsUpdateListener().subscribe(prods => {
        this.prods = prods;
        console.log( this.prods );
      }
    );
  }

  getAllProducts(){
    this.productService.getProducts();
  }

  ngOnDestroy(){
    this.productsSubscription.unsubscribe();
  }

}
