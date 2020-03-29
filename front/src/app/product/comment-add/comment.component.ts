import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import {CommentService} from '../comment.service';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Comment} from "../comment";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  form: FormGroup;

  private action = 'edit';
  private commentId: string;
  private productId:number;
  isLoading = false;
  comment: Comment;

  constructor(public commentService: CommentService, public route: ActivatedRoute) { }

  ngOnInit() {
    this.form = new FormGroup({
      title: new FormControl(null, {
        validators: [Validators.required, Validators.minLength(3)]
      }),
      content: new FormControl(null, { validators: [Validators.required] })
    });

    this.route.paramMap.subscribe( (paramMap: ParamMap) => {
      if(paramMap.has('commentId')) {
        console.log('EDIT');
        this.action = 'edit';
        this.commentId = paramMap.get('commentId');
        this.isLoading = true;
        this.commentService.getComment(this.commentId).subscribe(comment => {
          this.isLoading = false;
          this.productId=+comment.productId;
          this.comment = {
            id: comment.id,
            title: comment.title,
            content: comment.content,
            productId:comment.productId,
            creator: comment.creator
          };
          this.form.setValue({
            title: this.comment.title,
            content: this.comment.content
          });
        });
      } else {
          console.log('CREATE');
          this.action = 'create';
          this.commentId = null;
          this.productId=+paramMap.get('productId')
      }

    });
  }


  saveComment() {
    /* if (this.form.invalid) {
       return;
     }*/
    if (this.action === 'edit') {
        this.commentService.updateComment(this.commentId, this.form.value.title,
        this.form.value.content,""+this.productId);
    }  else {
        this.commentService.createComment(this.form.value.title,
        this.form.value.content,""+this.productId);
    }
    this.form.reset();
  }

}
