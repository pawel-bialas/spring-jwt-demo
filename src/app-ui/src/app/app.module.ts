import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SignUpComponent} from './users/sign-up/sign-up.component';
import {LoginComponent} from './users/login/login.component';
import {HomeComponent} from './home/home.component';
import {AdminPanelComponent} from './admin/admin-panel/admin-panel.component';
import { WallComponent } from './post/wall/wall.component';
import { UserPanelComponent } from './users/user-panel/user-panel.component';

import {UserService} from "./service/user.service";
import {AuthGuard} from "./authentication/auth.guard";
import {LoginAuthService} from "./authentication/login-auth.service";

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {ROUTES} from "./shared/routes/app.route";
import {HttpClientModule} from "@angular/common/http";

import {MaterialModule} from "./material/material.module";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';





@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    LoginComponent,
    HomeComponent,
    AdminPanelComponent,
    UserPanelComponent,
    WallComponent
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
  ],
  exports: [
    MaterialModule
  ],
  providers: [
    UserService,
    AuthGuard,
    LoginAuthService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
