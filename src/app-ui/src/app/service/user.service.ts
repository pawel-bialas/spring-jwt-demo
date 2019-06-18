import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  registerUser(user: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    console.log(headers);
    console.log(user);
    return this.http.post('http://localhost:8082/register', user, {headers: headers});
  }

  loginUser(user: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    console.log(headers);
    console.log(user);
    return this.http.post('http://localhost:8082/login', user, {headers: headers});
  }

  getAllUsers(token: any): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    console.log(headers);
    console.log(token);
    return this.http.get('http://localhost:8082/users', {headers: headers});
  }

  getUser(token: any): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    console.log(headers);
    console.log(token);
    return this.http.get('http://localhost:8082/getuser', {headers: headers});
  }

}
