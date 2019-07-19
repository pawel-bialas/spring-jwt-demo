import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {observable, Observable} from "rxjs";
import {observeOn} from "rxjs/operators";
import {parseHttpResponse} from "selenium-webdriver/http";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {}

  getAllPosts (token: any): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': "*"});
    console.log(headers);
    console.log(token);
    return this.http.get('http://localhost:8082/post/get-all',{headers: headers});
  }


  saveNewPost (token: any, post: any): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.post('http://localhost:8082/post/new-post', post,{headers: headers});
  }


}
