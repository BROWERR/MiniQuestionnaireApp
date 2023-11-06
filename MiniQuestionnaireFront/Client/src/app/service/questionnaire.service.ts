import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Questionnaire} from "../models/questionnaire";
import {Allwhole} from "../models/allwhole";

const QUESTIONNAIRE_API = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {

  constructor(private http: HttpClient) { }

  getAllQuestionnaires() : Observable<any>{
    return this.http.get(QUESTIONNAIRE_API + 'questionnaire/all');
  }

  /*createQuestionnaire(questionnaire: Questionnaire) : Observable<any>{
    return this.http.post(QUESTIONNAIRE_API+'add',questionnaire);
  }*/

  updateQuestionnaire(questionnaire: Allwhole) : Observable<any>{
    return this.http.post(QUESTIONNAIRE_API+'admin/questionnaire/update',questionnaire);
  }

  getQuestionnaireById(id:number) : Observable<any>{
    return this.http.get(QUESTIONNAIRE_API+id);
  }

  getQuestionnaireAnswerById(id:number, idUser:number) : Observable<any>{
    return this.http.get(QUESTIONNAIRE_API+id+'/'+idUser);
  }

  deleteQuestionnaire(id: number | undefined):Observable<any>{
    return this.http.post(QUESTIONNAIRE_API+'admin/questionnaire/delete/'+id,null);
  }

  createAll(all: Allwhole): Observable<any>{
    return this.http.post(QUESTIONNAIRE_API+'admin/questionnaire/add',all);
  }
}
