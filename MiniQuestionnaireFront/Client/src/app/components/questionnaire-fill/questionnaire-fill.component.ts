import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";
import {QuestionnaireService} from "../../service/questionnaire.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {Allwhole} from "../../models/allwhole";
import {UserService} from "../../service/user.service";
import {User} from "../../models/user";
import {User_answer} from "../../models/user_answer";
import {AnswerDTO} from "../../models/answerDTO";
import {UserAnswerService} from "../../service/user-answer.service";

@Component({
  selector: 'app-questionnaire-fill',
  templateUrl: './questionnaire-fill.component.html',
  styleUrls: ['./questionnaire-fill.component.css']
})
export class QuestionnaireFillComponent implements OnInit {
  allWhole!: Allwhole;
  user!: User;
  userAnswer!: User_answer;
  answerD!:AnswerDTO;
  constructor(private location: Location,
              private questionnaireService: QuestionnaireService,
              private snackbar: MatSnackBar,
              private router: ActivatedRoute,
              private userService: UserService,
              private userAnswerService: UserAnswerService) { }

  ngOnInit(): void {
    this.questionnaireService.getQuestionnaireById(this.router.snapshot.params['id_Questionnaire']).
    subscribe(data=> {
      this.allWhole = data;
    })
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
      })
  }
  back():void{
    this.location.back();
  }

  submit():void{
    this.userAnswerService.createUserAnswer(
      this.userAnswer = {
        user: this.user,
        answer: this.answerD
    }).subscribe(data => {
      this.userAnswer = {
        user: this.user,
        answer: this.answerD
      }
    })

    console.log(this.userAnswer)
  }
}
