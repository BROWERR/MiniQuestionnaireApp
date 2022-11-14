import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {Observable} from "rxjs";

const AUTH_API = 'http://localhost:8080/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(user: User): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: user.username,
      password: user.password
    });
  }

  public register(user:User): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: user.username,
      password: user.password,
      confirmPassword: user.confirmPassword,
      role: 0
    });
  }

}
