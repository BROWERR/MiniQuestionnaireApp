import {Questionnaire} from "./questionnaire";
import {Question_to_server} from "./question_to_server";

export interface Allwhole{
  id?:number;
  questionnaire: Questionnaire;
  questions: Question_to_server[];
}
