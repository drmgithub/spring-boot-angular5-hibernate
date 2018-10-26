import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { Router } from '@angular/router';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();

  constructor(private router: Router, private userService: UserService) {

  }

  loginUser(): void {
    this.userService.loginUser(this.user)
        .subscribe( data => {
          alert("User Logged in successfully.");
        });

  };
  ngOnInit() {
  }

}
