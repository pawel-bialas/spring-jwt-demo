import { Component } from '@angular/core';
import {LoginAuthService} from "./authentication/login-auth.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  //Konstruktor stale czuwa nad statusem aktualnego użytkownika
  constructor(private authService: LoginAuthService, private router: Router) {
    this.currentStatus = this.authService.getStatus().subscribe(currentStatus => {
      this.currentStatus = currentStatus;
    })
  }

  public currentStatus: any;

  title = 'app';

  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }
}
