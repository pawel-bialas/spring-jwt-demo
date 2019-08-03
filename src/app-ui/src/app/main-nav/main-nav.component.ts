import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {LoginAuthService} from "../shared/authentication/login-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent {

//Konstruktor stale czuwa nad statusem aktualnego użytkownika

  constructor(private authService: LoginAuthService, private router: Router, private breakpointObserver: BreakpointObserver) {
    this.currentStatus = this.authService.getStatus().subscribe(currentStatus => {
      this.currentStatus = currentStatus;
    })
  }

  public currentStatus: any;

  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );
}
