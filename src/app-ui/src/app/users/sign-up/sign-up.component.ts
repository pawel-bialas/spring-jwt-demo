import {Component, OnInit} from '@angular/core';
import {UserService} from "../../shared/service/user.service";
import {LoginAuthService} from "../../shared/authentication/login-auth.service";
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PasswordValidators} from "../../shared/validation/password-validators";


@Component({
  selector: 'sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {


  public registerForm: FormGroup;

  public user: any = {};


  constructor(private userService: UserService, private authService: LoginAuthService, private router: Router, private fb: FormBuilder) {
    this.authService.isLoggedIn();
    this.registerForm = this.createRegisterForm();
  }

  // registerForm: FormGroup = new FormGroup({
  //   email: new FormControl('', [Validators.required, Validators.email]),
  //   password: new FormControl('', [
  //       // 1. Password Field is Required
  //       Validators.required,
  //       // 2. check whether the entered password has a number
  //       patternValidator(/\d/, {hasNumber: true}),
  //       // 3. check whether the entered password has upper case letter
  //       patternValidator(/[A-Z]/, {hasCapitalCase: true}),
  //       // 4. check whether the entered password has a lower-case letter
  //       patternValidator(/[a-z]/, {hasSmallCase: true})
  //     ]
  //   ),
  //   confirmPassword: new FormControl('', Validators.required),
  //   gender: new FormControl(1),
  //   uniqueAccName: new FormControl('', Validators.required),
  //   descAccName: new FormControl('', Validators.required),
  //   type: new FormControl(1)
  // });



  ngOnInit() {
  }

  createRegisterForm(): FormGroup {
    return this.fb.group(
      {
        email: ['', Validators.compose([
          Validators.email,
          Validators.required,
        ])
        ],
        password: ['', Validators.compose([
          // 1. Password Field is Required
          Validators.required,
          // 2. check whether the entered password has a number
          PasswordValidators.patternValidator(/\d/, {hasNumber: true}),
          // 3. check whether the entered password has upper case letter
          PasswordValidators.patternValidator(/[A-Z]/, {hasCapitalCase: true}),
          // 4. check whether the entered password has a lower-case letter
          PasswordValidators.patternValidator(/[a-z]/, {hasSmallCase: true}),
          // 5. check whether the entered password has a special character
          PasswordValidators.patternValidator(/[ !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/, {hasSpecialCharacters: true}),
          // 6. Has a minimum length of 8 characters
          Validators.minLength(8)])
        ],
        confirmPassword: [null, Validators.compose([Validators.required])],
        gender: [1],
        uniqueAccName: ['', Validators.required],
        descAccName: ['', Validators.required],
        type: [1]
      },
      {
        validator: PasswordValidators.passwordMatchValidator
      });
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
    this.router.navigate(['/sign-up'])
  };

  initializeForm() {
    this.registerForm.setValue({
      email: '',
      password: '',
      confirmPassword: '',
      gender: 1,
      uniqueAccName: '',
      descAccName: '',
      type: 1
    });
    this.registerForm.markAsPristine();
    this.registerForm.markAsUntouched();
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.registerUser(this.registerForm.value);
      this.initializeForm();
    }
  }




}

