import {Injectable, OnInit} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {TokenStorageService} from "../service/token-storage.service";
import {delay, Observable} from "rxjs";
import {UserService} from "../service/user.service";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class AuthAdminService implements CanActivate{
  user!: User;
  isAdmin!: boolean;
  constructor(private router: Router,
              private userService: UserService) { }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
    Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.userService.getCurrentUser()
      .subscribe(data => {
        if(data.role == 1){
          this.isAdmin = true;
        }
      })
    if(this.isAdmin) return true;

    this.router.navigate(['/questionnaire']);
    return false;
  }
}
