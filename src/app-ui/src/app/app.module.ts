import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {AdminPanelComponent} from './admin-panel/admin-panel.component';

import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {ROUTES} from "./routes/app.route";
import {UserService} from "./service/user.service";
import {HttpClientModule} from "@angular/common/http";
import { UserPanelComponent } from './user-panel/user-panel.component';
import {AuthGuard} from "./authentication/auth.guard";
import {LoginAuthService} from "./authentication/login-auth.service";

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    LoginComponent,
    HomeComponent,
    AdminPanelComponent,
    UserPanelComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(ROUTES),
    HttpClientModule
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
    UserPanelComponent
  ]
})
export class AppModule { }
