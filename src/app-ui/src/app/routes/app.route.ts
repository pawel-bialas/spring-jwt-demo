import {Routes} from "@angular/router";
import {HomeComponent} from "../home/home.component";
import {LoginComponent} from "../login/login.component";
import {SignUpComponent} from "../sign-up/sign-up.component";
import {AdminPanelComponent} from "../admin-panel/admin-panel.component";
import {AuthGuard} from "../authentication/auth.guard";
import {UserPanelComponent} from "../user-panel/user-panel.component";


export const ROUTES: Routes = [
  {path: "home", component: HomeComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignUpComponent},
  {path: "admin-panel", component: AdminPanelComponent, canActivate: [AuthGuard]},
  {path: "user-panel", component: UserPanelComponent, canActivate: [AuthGuard]},

  {path: "**", pathMatch: "full", redirectTo: "home"}
];
