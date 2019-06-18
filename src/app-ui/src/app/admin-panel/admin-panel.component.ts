import { Component, OnInit } from '@angular/core';
import {LoginAuthService} from "../authentication/login-auth.service";
import {UserService} from "../service/user.service";

@Component({
  selector: 'admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  public loggedUser: any = {};
  public users: any = [];

  constructor(private authService: LoginAuthService, private userService: UserService) {
    this.authService.isLoggedIn();
    this.loggedUser = JSON.parse(localStorage.getItem('currentUser'));
  }



  ngOnInit() {
    this.userService.getAllUsers(this.loggedUser['token']).subscribe(response => {
      this.users = response;
    })
  }

}
