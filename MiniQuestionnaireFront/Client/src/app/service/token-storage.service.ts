import { Injectable } from '@angular/core';
import {User} from "../models/user";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  public saveToken(token : string): void  {
    window.sessionStorage.removeItem(token);
    window.sessionStorage.setItem(TOKEN_KEY,token);
  }

  public getToken(): string|null{
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user:User): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    let url = window.sessionStorage.getItem(USER_KEY)
    if(url !=null){
      return JSON.parse(url);
    }
    return false ;
  }

  logOut(): void {
    window.sessionStorage.clear();
    window.location.reload();
  }
}
