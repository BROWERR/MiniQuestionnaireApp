import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./auth/register/register.component";
import {LoginComponent} from "./auth/login/login.component";
import {QuestionnaireListComponent} from "./components/questionnaire-list/questionnaire-list.component";
import {AuthGuardService} from "./helper/auth-guard.service";
import {QuestionnaireAddComponent} from "./components/questionnaire-add/questionnaire-add.component";
import {AuthAdminService} from "./helper/auth-admin.service";
import {QuestionnaireUpdateComponent} from "./components/questionnaire-update/questionnaire-update.component";
import {QuestionnaireFillComponent} from "./components/questionnaire-fill/questionnaire-fill.component";
import {UseranswerListComponent} from "./components/useranswer-list/useranswer-list.component";
import {AnsweruserListComponent} from "./components/answeruser-list/answeruser-list.component";

const routes: Routes = [
  {path:'login',component: LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'questionnaire', canActivate:[AuthGuardService], children:[
      {path:'update/:id_updateQuestionnaire',component: QuestionnaireUpdateComponent},
      {path:'fill/:id_Questionnaire',component: QuestionnaireFillComponent},
      {path:'useranswer/:id_Questionnaire',component: UseranswerListComponent},
      {path: 'useranswer/:id_Questionnaire/:idUser', component: AnsweruserListComponent},
      {path:'add',canActivate:[AuthAdminService] ,component: QuestionnaireAddComponent},
      {path:'', component:QuestionnaireListComponent},
    ]},
  {path:'',redirectTo:'login',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
