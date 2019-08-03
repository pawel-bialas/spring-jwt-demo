import { Component, OnInit } from '@angular/core';
import {UserService} from "../../shared/service/user.service";
import {Router} from "@angular/router";
import {LoginAuthService} from "../../shared/authentication/login-auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PasswordValidators} from "../../shared/validation/password-validators";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router,
    private authService: LoginAuthService,
    private fb: FormBuilder
  ) {
    this.authService.isLoggedIn();
    this.loginForm = this.createLoginForm();
  }
  public loginForm: FormGroup;
  public user: any = {};

  ngOnInit() {
  }

  loginUser() {
    let user = this.loginForm.value;
    this.userService.loginUser(user).subscribe((response) => {
      console.log(response);
      if (response) {
        if (response['token']) {
          localStorage.setItem('currentUser', JSON.stringify(response));
          if (response['user']['role'] ===  "ADMIN") {
            this.router.navigate(['/admin-panel']);
          }
          if (response['user']['role'] === "USER") {
            this.router.navigate(['/user-panel']);
          }
        }
      }
    })
  }

  createLoginForm(): FormGroup {
    return this.fb.group(
      {
        email: ['', Validators.compose([
          Validators.email,
          Validators.required,
        ])
        ],
        password: ['', Validators.required]
      });
  }
}
