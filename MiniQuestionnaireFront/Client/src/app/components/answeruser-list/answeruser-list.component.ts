import { Component, OnInit } from '@angular/core';
import {Allwhole} from "../../models/allwhole";
import {User} from "../../models/user";
import {QuestionnaireService} from "../../service/questionnaire.service";
import {UserService} from "../../service/user.service";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {UserAnswerService} from "../../service/user-answer.service";
import {Questionnaire} from "../../models/questionnaire";
import {Question} from "../../models/question";
import {QuestionService} from "../../service/question.service";

@Component({
  selector: 'app-answeruser-list',
  templateUrl: './answeruser-list.component.html',
  styleUrls: ['./answeruser-list.component.css']
})
export class AnsweruserListComponent implements OnInit {
  whole!: Allwhole;
  user!: User[];
  questionnaire!: Questionnaire;
  questions!:Question[];

  constructor(private questionnaireService: QuestionnaireService,
              private questionService: QuestionService,
              private userAnswerService: UserAnswerService,
              private location: Location,
              private snackbar: MatSnackBar,
              private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.questionnaireService.getQuestionnaireAnswerById(this.router.snapshot.params['id_Questionnaire'],
                                            this.router.snapshot.params['idUser']).
    subscribe(data=> {
      this.whole = data;
      console.log(data);
      console.log('------')
    })
    /*this.questionService.getAllQuestionById(this.router.snapshot.params['id_Questionnaire']).
      subscribe(data => {
        this.questions = data;
        console.log(data)
    })*/
  }

  back():void{
    this.location.back();
  }
}
