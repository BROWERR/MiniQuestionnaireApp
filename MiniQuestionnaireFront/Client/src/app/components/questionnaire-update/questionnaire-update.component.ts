import { Component, OnInit } from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {Allwhole} from "../../models/allwhole";
import {User} from "../../models/user";
import {Location} from "@angular/common";
import {QuestionnaireService} from "../../service/questionnaire.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {Question_to_server} from "../../models/question_to_server";
import {AnswerDTO} from "../../models/answerDTO";

@Component({
  selector: 'app-questionnaire-update',
  templateUrl: './questionnaire-update.component.html',
  styleUrls: ['./questionnaire-update.component.css']
})
export class QuestionnaireUpdateComponent implements OnInit {
  updateForm!: FormGroup;
  updateWhole!: Allwhole;
  wh!: Allwhole;
  user!: User;
  constructor(private location: Location,
              private questionnaireService: QuestionnaireService,
              private snackbar: MatSnackBar,
              private router: ActivatedRoute) {
  }

  ngOnInit(): void {
      this.questionnaireService.getQuestionnaireById(this.router.snapshot.params['id_updateQuestionnaire']).
        subscribe(data=> {
          this.updateWhole = data;
          console.log(data);
          this.updateForm = new FormGroup({
             questionnaireName: new FormControl('', Validators.compose([Validators.required])),
             questions: new FormArray([this.initQuestion()]),
          });
          for (let i = 0; i < data.questions.length; i++) {
             if(i != 0){
               this.addQuestion(i);
             }
            for (let j = 1; j < data.questions[i].answers.length; j++) {
              this.addAnswer(i)
            }
          }
      })
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
    const control = <FormArray>this.updateForm.get('questions');
    control.push(this.initQuestion());
  }

  addAnswer(j:number) {
    const control = <FormArray>this.updateForm.get(['questions',j,'answers']);
    control.push(this.initAnswer());
  }

  getQuestions(form: any) {
    return form.controls.questions.controls;
  }
  getAnswers(form: any) {
    return form.controls.answers.controls;
  }

  back():void{
    this.location.back();
  }

  update(): void{
    let qq: Question_to_server[] = new Array(0)
    let aa : AnswerDTO[] = new Array(0);
    for (let i = 0; i < this.updateForm.value.questions.length; i++) {
      for (let j = 0; j < this.updateForm.value.questions.at(i).answers.length; j++) {
        aa.push({
          id: this.updateWhole.questions[i].answers[j].id,
          answer:this.updateForm.value.questions.at(i).answers.at(j).answerTitle
        });
      }
      qq.push({
        id: this.updateWhole.questions[i].id,
        questionTitle: this.updateForm.value.questions.at(i).questionTitle,
        answer_options: this.updateForm.value.questions.at(i).answer_options,
        answers: aa
      })
      aa = [];
    }
    this.wh = {
      questionnaire: {
        id: this.updateWhole.questionnaire.id,
        name: this.updateForm.value.questionnaireName
      },
      questions: qq
    }
    this.questionnaireService.updateQuestionnaire(this.wh)
      .subscribe(data=>{
        this.wh = data
      })
    this.snackbar.open('The questionnaire was successfully updated!','Okay',{
      duration: 2000
    });
  }
}
