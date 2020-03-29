import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../authentication.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  isLoading = false;

  constructor(public authenticationService:AuthenticationService ) { }

  ngOnInit() {
  }

  register(form: NgForm){
    if (form.invalid) {
      return;
    }
    this.isLoading = true;
    this.authenticationService.register(form.value.name,form.value.username, form.value.email, form.value.password);
  }

}
