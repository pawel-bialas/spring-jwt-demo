import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SignUpComponent} from './users/sign-up/sign-up.component';
import {LoginComponent} from './users/login/login.component';
import {HomeComponent} from './home/home.component';
import {AdminPanelComponent} from './admin/admin-panel/admin-panel.component';
import { WallComponent } from './post/wall/wall.component';
import { UserPanelComponent } from './users/user-panel/user-panel.component';
import { NewPostComponent } from './post/new-post/new-post.component';
import {ErrorComponent} from "./shared/error/error.component";
import { MainNavComponent } from './main-nav/main-nav.component';

import { UniqueLoginValidatorDirective } from './shared/validation/unique-login-validator.directive';
import {UniqueAccNameValidatorDirective} from "./shared/validation/unique-acc-name-validator.directive";


import {UserService} from "./shared/service/user.service";
import {AuthGuard} from "./shared/authentication/auth.guard";
import {LoginAuthService} from "./shared/authentication/login-auth.service";
import {GlobalErrorHandlerService} from "./shared/service/global-error-handler.service";

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {ROUTES} from "./shared/routes/app.route";
import {HttpClientModule} from "@angular/common/http";

import {MaterialModule} from "./material/material.module";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import 'hammerjs';
import { LayoutModule } from '@angular/cdk/layout';
import { CheckLoginWhileSignInDirective } from './shared/validation/check-login-while-sign-in.directive';








@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    LoginComponent,
    HomeComponent,
    AdminPanelComponent,
    UserPanelComponent,
    WallComponent,
    UniqueLoginValidatorDirective,
    UniqueAccNameValidatorDirective,
    NewPostComponent,
    MainNavComponent,
    ErrorComponent,
    CheckLoginWhileSignInDirective

  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(ROUTES),
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    NgbModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    LayoutModule,
  ],
  exports: [
    MaterialModule
  ],
  providers: [
    UserService,
    AuthGuard,
    LoginAuthService,
    GlobalErrorHandlerService,
    { provide: ErrorHandler, useClass: GlobalErrorHandlerService}
  ],
  bootstrap: [
    AppComponent
  ],
  entryComponents: [
    ErrorComponent
  ]
})
export class AppModule { }
