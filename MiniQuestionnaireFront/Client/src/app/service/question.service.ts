import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Question} from "../models/question";

const QUESTION_API = 'http://localhost:8080/questionnaire/';


@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) { }

  getAllQuestions() : Observable<any>{
    return this.http.get(QUESTION_API+'all');
  }

  createQuestion(question: Question) : Observable<any>{
    return this.http.post(QUESTION_API+'add',question);
  }

  updateQuestion(question: Question) : Observable<any>{
    return this.http.post(QUESTION_API+'update',question);
  }

  getQuestionById(id:number) : Observable<any>{
    return this.http.get(QUESTION_API+id);
  }

  deleteQuestion(id: number | undefined):Observable<any>{
    return this.http.post(QUESTION_API+'delete/'+id,null);
  }

  getAllQuestionById(id: number | undefined):Observable<any> {
    return this.http.get(QUESTION_API+'all/'+id);
  }
}
