import { Component, OnInit } from '@angular/core';
import {LoginAuthService} from "../shared/authentication/login-auth.service";
import {PostService} from "../shared/service/post.service";
import {MonthFormat} from "../shared/month-format/month-format";


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

    let joinDate: Date = new Date(date);
    let month = MonthFormat.monthFormat(joinDate);
    let year = joinDate.getFullYear();

    return month + ' ' + year;

  }

  formatCreationDate(date: any): String {
    let publicationDate: Date = new Date(date);
    let time: number = Date.now() - publicationDate.getTime();

    if (time > 86400000) {
      return ' ' + publicationDate.getDay() + ' ' + MonthFormat.monthFormat(publicationDate) + ' ' + publicationDate.getFullYear();
    } 
    
    if (time > 3600000) {
      let hours = time / 3600000;
      let hoursFormatted = Math.round(hours);
      return ' ' + hoursFormatted + ' h ago';
    }

    if (time < 3600000) {
      let minutes = time / 60000;
      let minutesFormatted = Math.round(minutes);
      return ' ' + minutesFormatted + ' min ago';
    }
  }
}
