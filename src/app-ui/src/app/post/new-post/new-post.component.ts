import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginAuthService} from "../../authentication/login-auth.service";
import {Router} from "@angular/router";
import {PostService} from "../../shared/service/post.service";

@Component({
  selector: 'new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  public newPostForm: FormGroup;
  public loggedUser: any = {};
  public post: any = {};

  constructor(private authService: LoginAuthService, private router: Router, private fb: FormBuilder, private postService: PostService) {
    this.authService.isLoggedIn();
    this.loggedUser = JSON.parse(localStorage.getItem('currentUser'));
    this.newPostForm = this.createPostForm();
  }

  ngOnInit() {
  }

  createPostForm(): FormGroup {
    return this.fb.group({
      content: ['', Validators.required]
    })
  }

  onClear() {
    this.createPostForm().reset();
    this.initializeForm();
  }

  initializeForm() {
    this.newPostForm.setValue({
      content: ''
    })
  }

  onSubmit() {
    if (this.newPostForm.valid){
      console.log(this.newPostForm.value);
      this.saveNewPost(this.newPostForm.value);
      this.newPostForm.reset();
      this.initializeForm();
    }
  }

  private saveNewPost(post: any) {
    this.postService.saveNewPost(this.loggedUser['token'],post).subscribe(response => {

        if (response === null) {
          this.router.navigate(["/home"]);
        }
    });
  }
}
