import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user.service";
import {LoginAuthService} from "../authentication/login-auth.service";

@Component({
  selector: 'sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  public user: any = {};

  constructor(private userService: UserService, private authService: LoginAuthService) {
    this.authService.isLoggedIn();
  }

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
