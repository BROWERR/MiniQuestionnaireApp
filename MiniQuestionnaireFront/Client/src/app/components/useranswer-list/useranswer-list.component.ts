import { Component, OnInit } from '@angular/core';
import {QuestionnaireService} from "../../service/questionnaire.service";
import {UserService} from "../../service/user.service";
import {Allwhole} from "../../models/allwhole";
import {User} from "../../models/user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-useranswer-list',
  templateUrl: './useranswer-list.component.html',
  styleUrls: ['./useranswer-list.component.css']
})
export class UseranswerListComponent implements OnInit {
  whole!: Allwhole;
  user!: User[];

  constructor(private questionnaireService: QuestionnaireService,
              private userService: UserService,
              private location: Location,
              private snackbar: MatSnackBar,
              private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.userService.getListUser(this.router.snapshot.params['id_Questionnaire']).
    subscribe(data=> {
      this.user = data;
      console.log(data);
     })
  }

  back():void{
    this.location.back();
  }
}
