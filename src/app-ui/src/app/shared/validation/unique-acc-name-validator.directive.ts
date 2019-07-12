import {Directive} from '@angular/core';
import {AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors} from "@angular/forms";
import {Observable} from "rxjs";
import {UserService} from "../service/user.service";

@Directive({
  selector: '[uniqueAccName]',
  providers: [{provide: NG_ASYNC_VALIDATORS, useExisting: UniqueAccNameValidatorDirective, multi: true}]
})
export class UniqueAccNameValidatorDirective implements AsyncValidator {

  constructor(private userService: UserService) {
  }

  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {

    return new Promise((resolve, reject) => {
      let result;
      this.userService.checkUniqueAccName(control.value).subscribe(response => {
        console.log(response);
        result = response;
        if (result === false) {
          resolve({uniqueAccName: true})
        } else resolve(null)
      });
    })
  }
}
