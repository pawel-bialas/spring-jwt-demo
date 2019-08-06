import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UrlProvider} from "../url/UrlProvider";

@Injectable()
export class UserService {


  constructor(private http: HttpClient) {
    this.http = http;
  }

  form: FormGroup = new FormGroup({
    $key: new FormControl(null),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', [Validators.required, Validators.minLength(8)]),
    gender: new FormControl(1),
    uniqueAccName: new FormControl(''),
    descAccName: new FormControl(''),
    status: new FormControl(1)
  });

  registerUser(user: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    return this.http.post(UrlProvider.SPRING + 'register', user, {headers: headers});
  }

  loginUser(user: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    return this.http.post(UrlProvider.SPRING + 'login', user, {headers: headers});
  }

  getAllUsers(token: any): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get(UrlProvider.SPRING + 'users', {headers: headers});
  }

  getUser(token: any): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get(UrlProvider.SPRING + 'getuser', {headers: headers});
  }

  checkLogin(login: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    return this.http.post(UrlProvider.SPRING + 'register/login', login, {headers: headers});
  }

  checkUniqueAccName(uniqueAccName: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    return this.http.post(UrlProvider.SPRING + 'register/unique-acc-name', uniqueAccName, {headers: headers});
  }

  checkLoginWhileSignIn (login: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    return this.http.post(UrlProvider.SPRING + 'login/check-login', login, {headers: headers});
  }



}
