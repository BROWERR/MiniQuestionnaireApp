import {Questionnaire} from "./questionnaire";

export interface Question{
  id?:number;
  question:string;
  questionnaire: Questionnaire;
}
