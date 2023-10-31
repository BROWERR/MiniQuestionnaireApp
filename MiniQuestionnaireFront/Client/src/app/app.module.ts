import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";
import {MatSelectModule} from "@angular/material/select";
import {MatCardModule} from "@angular/material/card";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MaterialModule} from "./material-module";
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { QuestionnaireListComponent } from './components/questionnaire-list/questionnaire-list.component';
import {authInterceptorProviders} from "./helper/auth-interceptor.service";
import {authErrorInterceptorProviders} from "./helper/error-interceptor.service";
import { QuestionnaireAddComponent } from './components/questionnaire-add/questionnaire-add.component';
import {MatTooltipModule} from "@angular/material/tooltip";
import { QuestionnaireUpdateComponent } from './components/questionnaire-update/questionnaire-update.component';
import { QuestionnaireFillComponent } from './components/questionnaire-fill/questionnaire-fill.component';
import { UseranswerListComponent } from './components/useranswer-list/useranswer-list.component';
import { AnsweruserListComponent } from './components/answeruser-list/answeruser-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    QuestionnaireListComponent,
    QuestionnaireAddComponent,
    QuestionnaireUpdateComponent,
    QuestionnaireFillComponent,
    UseranswerListComponent,
    AnsweruserListComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MaterialModule,
        MatCardModule,
        ReactiveFormsModule,
        FormsModule,
        MatSelectModule,
        MatDatepickerModule,
        MatTooltipModule
    ],
  providers: [authInterceptorProviders,authErrorInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
