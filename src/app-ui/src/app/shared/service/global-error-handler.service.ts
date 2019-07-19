import {ErrorHandler, Injectable, Injector} from '@angular/core';
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Injectable()
export class GlobalErrorHandlerService implements ErrorHandler {

  constructor(private injector: Injector) { }

  handleError(error: any) {
    const router = this.injector.get(Router);
    
    if (error instanceof HttpErrorResponse) {
      console.error('Server returned: ', error.status);
      console.error('Response body: ', error.message);
    } else {
      console.error('An error occurred: ', error.message);
    }

    router.navigate(['/error'])
  }
}
