import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {LoginAuthService} from "../../authentication/login-auth.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router,
    private authService: LoginAuthService
  ) {
    this.authService.isLoggedIn();
  }

  public user: any = {};

  ngOnInit() {
  }

  loginUser(user: any) {
    this.userService.loginUser(user).subscribe((response) => {
      console.log(response);
      if (response) {
        if (response['token']) {
          localStorage.setItem('currentUser', JSON.stringify(response));
          if (response['user']['role'] === 'ADMIN') {
            this.router.navigate(['/admin-panel']);
          }
          if (response['user']['role'] === 'USER') {
            this.router.navigate(['/user-panel']);
          }
        }
      }
    })
  }
}
