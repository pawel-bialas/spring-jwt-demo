import {Routes} from "@angular/router";
import {HomeComponent} from "../../home/home.component";
import {LoginComponent} from "../../users/login/login.component";
import {SignUpComponent} from "../../users/sign-up/sign-up.component";
import {AdminPanelComponent} from "../../admin/admin-panel/admin-panel.component";
import {AuthGuard} from "../../authentication/auth.guard";
import {UserPanelComponent} from "../../users/user-panel/user-panel.component";
import {NewPostComponent} from "../../post/new-post/new-post.component";
import {ErrorComponent} from "../error/error.component";


export const ROUTES: Routes = [
  {path: "home", component: HomeComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignUpComponent},
  {path: "error", component: ErrorComponent},
  {path: "admin-panel", component: AdminPanelComponent, canActivate: [AuthGuard]},
  {path: "user-panel", component: UserPanelComponent, canActivate: [AuthGuard]},
  {path: "new-post", component: NewPostComponent, canActivate: [AuthGuard]},

  {path: "**", pathMatch: "full", redirectTo: "home"}
];
