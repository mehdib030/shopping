import {Component, OnDestroy, OnInit} from '@angular/core';
import {CommentService} from '../comment.service';
import {Comment} from "../comment";
import {Subscription} from "rxjs/internal/Subscription";
import {PageEvent} from "@angular/material";
import {ProductService} from "../product.service";
import {Product} from "../product";
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit , OnDestroy {
  isLoading = false;
  totalComments = 10;
  numberOfComments = 5;
  pageSizeOptions = [1,2,5,10,20];
  currentPage = 1;
  productId:string;
  comments: Comment[] = [];
  private commentsSubscription: Subscription;

  private productsSubscription: Subscription;

  constructor(public commentService: CommentService,public productService: ProductService) { }

  ngOnInit() {
   //this.commentService.getComments(this.numberOfComments,this.currentPage);

   this.isLoading = true;

   //this.productService.getCommentsByProductId()
   this.productsSubscription = this.productService.getProductUpdateListener()
     .subscribe((product: Product) => {
        this.isLoading = false;
        this.productId=""+product.id;
        this.comments = product.comments;
      });
  }

  deleteComment(id: string, productId:string){
    this.commentService.deleteComment(id,this.productId);
    this.commentsSubscription =this.commentService.getCommentUpdateListener()
      .subscribe(comments => {
        this.isLoading = false;
        this.comments = comments;
      });
  }

  pageEventListener(pageEvent:PageEvent){
    this.currentPage = pageEvent.pageIndex+1;
    this.numberOfComments = pageEvent.pageSize;
    this.commentService.getComments(this.numberOfComments,this.currentPage);
  }


  ngOnDestroy() {
    //this.commentsSubscription.unsubscribe();
    this.productsSubscription.unsubscribe();
  }

}
