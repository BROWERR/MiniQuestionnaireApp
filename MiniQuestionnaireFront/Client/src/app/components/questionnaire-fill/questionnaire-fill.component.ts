import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";
import {QuestionnaireService} from "../../service/questionnaire.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {Allwhole} from "../../models/allwhole";
import {UserService} from "../../service/user.service";
import {User} from "../../models/user";
import {User_answer} from "../../models/user_answer";

@Component({
  selector: 'app-questionnaire-fill',
  templateUrl: './questionnaire-fill.component.html',
  styleUrls: ['./questionnaire-fill.component.css']
})
export class QuestionnaireFillComponent implements OnInit {
  allWhole!: Allwhole;
  user!: User;
  userAnswer!: User_answer;
  constructor(private location: Location,
              private questionnaireService: QuestionnaireService,
              private snackbar: MatSnackBar,
              private router: ActivatedRoute,
              private userService: UserService) { }

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

  /*submit():void{
    this.userAnswer = {
      answer:'',
      user: this.user
    }
    console.log(this.userAnswer)
  }*/
}
