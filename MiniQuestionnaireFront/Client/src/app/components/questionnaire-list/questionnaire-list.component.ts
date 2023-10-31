import { Component, OnInit } from '@angular/core';
import {QuestionnaireService} from "../../service/questionnaire.service";
import {Questionnaire} from "../../models/questionnaire";
import {UserService} from "../../service/user.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-questionnaire-list',
  templateUrl: './questionnaire-list.component.html',
  styleUrls: ['./questionnaire-list.component.css']
})
export class QuestionnaireListComponent implements OnInit {
  questionnaires!: Questionnaire[];
  user!: User;
  constructor(private questionnaireService: QuestionnaireService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.questionnaireService.getAllQuestionnaires().subscribe(data=>{
        this.questionnaires = data;
    });
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
      })
  }

  delete(questionnaire: Questionnaire): void{
    const message = confirm('Do you really want to delete this questionnaire?');
    if(message){
      this.questionnaireService.deleteQuestionnaire(questionnaire.id)
        .subscribe(() => {
          this.questionnaires.splice(<number>questionnaire.id, 1)
        });
      window.location.reload();
    }
  }
}
