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


}
