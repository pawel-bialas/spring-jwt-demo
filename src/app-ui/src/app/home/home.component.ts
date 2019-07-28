import { Component, OnInit } from '@angular/core';
import {LoginAuthService} from "../authentication/login-auth.service";
import {PostService} from "../shared/service/post.service";


@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authService: LoginAuthService, private postService: PostService) { }

  blogPosts: any[];

  ngOnInit() {
    this.postService.getAllPosts().subscribe((response) => {
      this.blogPosts = response
    })
  }

  formatJoinDate(date: any): String {
    let stringDate: String = date;
    let year = stringDate.substr(0,4);
    let month = stringDate.substr(5,2);

    if (month === '01') {
      return ' January ' + year;
    }
    if (month === '02') {
      return ' February ' + year;
    }
    if (month === '03') {
      return ' March ' + year;
    }
    if (month === '04') {
      return ' April ' + year;
    }
    if (month === '05') {
      return ' May ' + year;
    }
    if (month === '06') {
      return ' June ' + year;
    }
    if (month === '07') {
      return ' July ' + year;
    }
    if (month === '08') {
      return ' August ' + year;
    }
    if (month === '09') {
      return ' September ' + year;
    }
    if (month === '10') {
      return ' October ' + year;
    }
    if (month === '11') {
      return ' November ' + year;
    }
    if (month === '12') {
      return ' December ' + year;
    }



  }
}
