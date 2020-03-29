import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import {environment} from '../../environments/environment';

import { HttpHeaders } from '@angular/common/http';
import {Comment} from "./comment";
import {Subject} from "rxjs/internal/Subject";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/x-www-form-urlencoded'
  })
};


const SERVER_URL = environment.url + 'comments/';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  comments: Comment[] = [];

  private commentUpdated = new Subject<Comment[]>();



  constructor(public http: HttpClient, private router: Router) { }

  getComments(numberOfComments: number,currentPage: number) {
    const paginationParams = `?page=${currentPage}&size=${numberOfComments}`;
    this.http.get<Comment[]>(SERVER_URL+'read').subscribe( comments => {
         this.comments = comments;
         this.commentUpdated.next([...this.comments]);
       }
     );

  }

  createComment(title: string, content: string,productId:string) {
    /*const commentData = new FormData();
    commentData.append('title', title);
    commentData.append('content', content);
    commentData.append('creator', 'Mehdi');*/
    const comment = {
      title: title,
      content: content,
      productId:productId,
      creator: 'MB'

    };

    /*const comment = <Comment>({
      title: title,
      content: content,
      creator: 'MB'
  });
*/

    this.http
      .post(
        'http://localhost:8080/comments/add',
        comment
      )
      .subscribe(responseData => {
        console.log(responseData);
        this.getComments(1,2);
        this.router.navigate(['/product/detail/'+productId]);
      });
  }

  updateComment(id: string, title: string, content: string, productId:string) {
    const comment = {
      id: id,
      title: title,
      content: content,
      productId:productId,
      creator: 'MB'
    };
    this.http
      .put(SERVER_URL + 'update', comment)
      .subscribe(response => {
         this.router.navigate(['/product/detail/'+productId]);
      });
  }

  getCommentUpdateListener() {
    return this.commentUpdated.asObservable();
  }

  getComment(id: string) {
    return this.http.get<{id: string; title: string; content: string ; creator: string; productId: string}>(
      SERVER_URL+'read/' + id
    );
  }

  getCommentsByProductId(productId: string) {
    return this.http.get<Comment[]>(
      SERVER_URL+'read1/' + productId
    );
  }

  deleteComment(id: string, productId:string) {
    this.http
      .delete(SERVER_URL + 'delete/'+id)
      .subscribe(response => {
        this.getCommentsByProductId(productId).subscribe(comments =>
        {
          this.comments = comments;
          this.commentUpdated.next([...this.comments]);
          this.router.navigate(['/product/detail/'+productId]);
        });
      });
  }
}
