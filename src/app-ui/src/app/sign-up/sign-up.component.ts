import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  public user: any = {};

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  registerUser(user: any, userForm: any) {
    this.userService.registerUser(user).subscribe((response) => {
      if (response) {
        console.log(response);
        userForm.reset();
      }
    })
  }
}
