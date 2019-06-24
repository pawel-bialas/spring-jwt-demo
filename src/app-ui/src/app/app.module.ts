import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {AdminPanelComponent} from './admin-panel/admin-panel.component';
import { WallComponent } from './wall/wall.component';
import { UserPanelComponent } from './user-panel/user-panel.component';

import {UserService} from "./service/user.service";
import {AuthGuard} from "./authentication/auth.guard";
import {LoginAuthService} from "./authentication/login-auth.service";

import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {ROUTES} from "./routes/app.route";
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


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
    NgbModule
  ],
  providers: [
    UserService,
    AuthGuard,
    LoginAuthService
  ],
  bootstrap: [
    AppComponent,
    SignUpComponent,
    LoginComponent,
    HomeComponent,
    AdminPanelComponent,
    UserPanelComponent,
    WallComponent
  ]
})
export class AppModule { }
