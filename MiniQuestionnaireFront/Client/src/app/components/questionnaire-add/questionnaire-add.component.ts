import { Component, OnInit } from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {Questionnaire} from "../../models/questionnaire";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Location} from "@angular/common";
import {QuestionnaireService} from "../../service/questionnaire.service";
import {Question} from "../../models/question";
import {QuestionService} from "../../service/question.service";
import {Question_to_server} from "../../models/question_to_server";
import {Allwhole} from "../../models/allwhole";
import {AnswerDTO} from "../../models/answerDTO";
import {UserService} from "../../service/user.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-questionnaire-add',
  templateUrl: './questionnaire-add.component.html',
  styleUrls: ['./questionnaire-add.component.css']
})
export class QuestionnaireAddComponent implements OnInit {
  addForm!: FormGroup;
  addWhole!: Allwhole;
  user!: User;
  constructor(private location: Location,
              private questionnaireService: QuestionnaireService,
              private snackbar: MatSnackBar) {
  }

  ngOnInit() {
    this.addForm = new FormGroup({
      questionnaireName: new FormControl('', Validators.compose([Validators.required])),
      questions: new FormArray([
        this.initQuestion(),
      ]),
    });
    this.addAnswer(0)
  }

  initQuestion() {
    return new FormGroup({
      questionTitle: new FormControl('', Validators.compose([Validators.required])),
      answer_options: new FormControl('', Validators.compose([Validators.required])),
      answers: new FormArray([
        this.initAnswer()
      ])
    });
  }

  initAnswer() {
    return new FormGroup({
      answerTitle: new FormControl('', Validators.compose([Validators.required]))
    });
  }

  addQuestion(i: number) {
    const control = <FormArray>this.addForm.get('questions');
    control.push(this.initQuestion());
    this.addAnswer(i+1)
  }

  addAnswer(j:number) {
    const control = <FormArray>this.addForm.get(['questions',j,'answers']);
    control.push(this.initAnswer());
  }

  getQuestions(form: any) {
    return form.controls.questions.controls;
  }
  getAnswers(form: any) {
    return form.controls.answers.controls;
  }

  removeAnswer(i: number, j: number){
    const control = <FormArray>this.addForm.get(['questions',i,'answers']);
    control.removeAt(j);
  }

  removeQuestion(i: number){
    const control = <FormArray>this.addForm.get('questions');
    control.removeAt(i);
  }

  onSubmit(): void{
    let qq: Question_to_server[] = new Array(0)
    let aa : AnswerDTO[] = new Array(0);
    for (let i = 0; i < this.addForm.value.questions.length; i++) {
      for (let j = 0; j < this.addForm.value.questions.at(i).answers.length; j++) {
        aa.push(this.addForm.value.questions.at(i).answers.at(j).answerTitle);
      }
      qq.push({
        questionTitle: this.addForm.value.questions.at(i).questionTitle,
        answer_options:this.addForm.value.questions.at(i).answer_options,
        answers: aa
      })
      aa = [];
    }
    this.addWhole = {
      questionnaire: {name: this.addForm.value.questionnaireName},
      questions: qq
    }
    this.questionnaireService.createAll({
      questionnaire: {
        name: this.addForm.value.questionnaireName
      },
      questions: qq
    }).subscribe(data=>{
        this.addWhole = data
    })
    this.snackbar.open('The questionnaire was successfully added!','Okay',{
      duration: 2000
    });
  }

  back():void{
    this.location.back();
  }
}
