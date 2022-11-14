import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AnswerDTO} from "../models/answerDTO";

const ANSWER_API = 'http://localhost:8080/answer/';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(private http: HttpClient) { }

  getAllAnswers() : Observable<any>{
    return this.http.get(ANSWER_API+'all');
  }

  createAnswer(answer: AnswerDTO) : Observable<any>{
    return this.http.post(ANSWER_API+'add',answer);
  }

  updateAnswer(answer: AnswerDTO) : Observable<any>{
    return this.http.post(ANSWER_API+'update',answer);
  }

  getAnswerById(id:number) : Observable<any>{
    return this.http.get(ANSWER_API+id);
  }

  deleteAnswer(id: number | undefined):Observable<any>{
    return this.http.post(ANSWER_API+'delete/'+id,null);
  }
}
