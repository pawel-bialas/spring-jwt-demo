import { Directive } from '@angular/core';
import {AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors} from "@angular/forms";
import {UserService} from "../service/user.service";
import {Observable} from "rxjs";

@Directive({
  selector: '[checkLoginWhileSignIn]',
  providers: [{provide: NG_ASYNC_VALIDATORS, useExisting: CheckLoginWhileSignInDirective, multi: true}]
})
export class CheckLoginWhileSignInDirective implements AsyncValidator{

  constructor(private userService: UserService) { }


  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return new Promise((resolve, reject) => {
      let result;
      this.userService.checkLoginWhileSignIn(control.value).subscribe(response => {
        console.log(response);
        result = response;
        if (result === false) {
          resolve({checkLoginWhileSignIn: true})
        } else resolve(null)
      });
    })

  }

}
