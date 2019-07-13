import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

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

  saveNewPost (): Observable<any> {
    return null;
  }

}
