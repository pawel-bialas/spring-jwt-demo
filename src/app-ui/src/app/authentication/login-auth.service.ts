import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginAuthService {

  constructor() { }

  private subject = new Subject<any>();

  isLogged() {
    if (localStorage.getItem('currentUser')) {
      this.subject.next({status: true})
    } else {
      this.subject.next({status: false})
    }
  }

  clearStatus() {
    this.subject.next();
  }

  getStatus(): Observable<any> {
    return this.subject.asObservable();
  }
}
