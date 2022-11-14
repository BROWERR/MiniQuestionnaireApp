import { Injectable } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {TokenStorageService} from "../service/token-storage.service";
import {catchError, Observable, throwError} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptorService implements HttpInterceptor{

  constructor(private tokenService: TokenStorageService,
              private snackbar: MatSnackBar) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError(err => {
      if (err.status === 401) {
        this.tokenService.logOut();
        window.location.reload();
      }

      const error = err.error.message || err.statusText;
      this.snackbar.open(error,"Okay",{
        duration: 2000
      });
      return throwError(error);
    }));
  }
}
export const authErrorInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptorService, multi: true}
];
