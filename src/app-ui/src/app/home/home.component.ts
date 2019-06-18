import { Component, OnInit } from '@angular/core';
import {LoginAuthService} from "../authentication/login-auth.service";


@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authService: LoginAuthService) { }

  ngOnInit() {
  }

}
