import {Directive} from '@angular/core';
import {AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors} from "@angular/forms";
import {Observable} from "rxjs";
import {UserService} from "../service/user.service";


@Directive({
  selector: '[uniqueLogin]',
  providers: [{provide: NG_ASYNC_VALIDATORS, useExisting: UniqueLoginValidatorDirective, multi: true}]
})
export class UniqueLoginValidatorDirective implements AsyncValidator {

  constructor(private userService: UserService) {
  }

   validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {

    return new Promise((resolve, reject) => {
      let result;
      this.userService.checkLogin(control.value).subscribe(response => {
        result = response;
        if (result === false) {
          resolve({uniqueLogin: true})
        } else resolve(null)
      });
    })
  }

}
