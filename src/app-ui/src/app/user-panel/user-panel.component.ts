import {Component, OnInit} from '@angular/core';
import {LoginAuthService} from "../authentication/login-auth.service";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {

  public loggedUser: any = {};
  public user: any = {};

  constructor(private authService: LoginAuthService, private userService: UserService) {
    this.authService.isLoggedIn();
    this.loggedUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.userService.getUser(this.loggedUser['token']).subscribe(response => {
      console.log(response);
      this.user = response;
    })

  }
}
