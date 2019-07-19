import {ErrorHandler, Injectable, Injector} from '@angular/core';
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {ErrorComponent} from "../error/error.component";

@Injectable()
export class GlobalErrorHandlerService implements ErrorHandler {

  constructor(private injector: Injector, private dialog: MatDialog) { }

  handleError(error: any) {
    const router = this.injector.get(Router);
    
    if (error instanceof HttpErrorResponse) {
      console.error('Server returned: ', error.status);
      console.error('Response body: ', error.message);
    } else {
      console.error('An error occurred: ', error.message);
    }

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';

    this.dialog.open(ErrorComponent, dialogConfig);
  }
}
