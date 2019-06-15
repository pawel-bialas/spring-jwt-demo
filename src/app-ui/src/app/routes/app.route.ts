import {Routes} from "@angular/router";
import {HomeComponent} from "../home/home.component";
import {LoginComponent} from "../login/login.component";
import {SignUpComponent} from "../sign-up/sign-up.component";


export const ROUTES: Routes = [
  {path: "home", component: HomeComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignUpComponent},

  {path: "**", pathMatch: "full", redirectTo: "home"}
];
