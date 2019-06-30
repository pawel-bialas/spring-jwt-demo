import { Component, OnInit } from '@angular/core';
import {UserService} from "../../shared/service/user.service";
import {LoginAuthService} from "../../authentication/login-auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  public user: any = {};


  registerForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', Validators.required),
    gender: new FormControl(1),
    uniqueAccName: new FormControl('', Validators.required),
    descAccName: new FormControl('', Validators.required),
    type: new FormControl(1)
  });

  constructor(private userService: UserService, private authService: LoginAuthService, private router: Router) {
    this.authService.isLoggedIn();
  }

  ngOnInit() {
  }

  registerUser(user: any) {
    this.userService.registerUser(user).subscribe((response) => {
      if (response) {
        console.log(response);
        this.router.navigate(['/login']);
      }
    })
  }


  onClear() {
    this.registerForm.reset();
    this.initializeForm();
  };

  initializeForm() {
    this.registerForm.setValue({
      email: '',
      password: '',
      confirmPassword: '',
      gender: 1,
      uniqueAccName:'',
      descAccName: '',
      type: 1
    })
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.registerUser(this.registerForm.value);
      this.registerForm.reset();
      this.initializeForm();
    }
  }
}
