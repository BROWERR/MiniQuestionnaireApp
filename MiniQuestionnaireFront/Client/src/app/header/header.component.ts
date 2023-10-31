import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";
import {User} from "../models/user";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn = false;
  isDataLoaded = false;
  role!: string;
  user!: User;

  constructor(private tokenService: TokenStorageService,
              private router: Router,
              private userService: UserService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();

    if(this.isLoggedIn) {
      this.userService.getCurrentUser()
        .subscribe(data => {
          this.user = data;
          this.isDataLoaded = true;
        })
    }
  }

  logout():void{
    this.tokenService.logOut();
    this.router.navigate(['/login']);
  }
}
