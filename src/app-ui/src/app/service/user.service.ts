import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  saveUser(user: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    console.log(headers);
    console.log(user);
    return this.http.post('http://localhost:8082/register', user, {headers: headers});
  }

}
