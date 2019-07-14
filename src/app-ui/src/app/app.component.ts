import { Component } from '@angular/core';
import {LoginAuthService} from "./authentication/login-auth.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  //Konstruktor stale czuwa nad statusem aktualnego użytkownika
  constructor(private authService: LoginAuthService, private router: Router, private breakpointObserver: BreakpointObserver) {
    this.currentStatus = this.authService.getStatus().subscribe(currentStatus => {
      this.currentStatus = currentStatus;
    })
  }

  public currentStatus: any;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  title = 'app';

  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }
}
