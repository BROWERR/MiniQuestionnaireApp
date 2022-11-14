import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User_answer} from "../models/user_answer";

const USER_ANSWER_API = 'http://localhost:8080/userAnswer/'

@Injectable({
  providedIn: 'root'
})
export class UserAnswerService {

  constructor(private http: HttpClient) { }

  getAllUserAnswers() : Observable<any>{
    return this.http.get(USER_ANSWER_API+'all');
  }

  createUserAnswer(user_answer: User_answer) : Observable<any>{
    return this.http.post(USER_ANSWER_API+'add',user_answer);
  }

  updateUserAnswer(user_answer: User_answer) : Observable<any>{
    return this.http.post(USER_ANSWER_API+'update',user_answer);
  }

  getUserAnswerById(id:number) : Observable<any>{
    return this.http.get(USER_ANSWER_API+id);
  }

  deleteUserAnswer(id: number | undefined):Observable<any>{
    return this.http.post(USER_ANSWER_API+'delete/'+id,null);
  }
}
