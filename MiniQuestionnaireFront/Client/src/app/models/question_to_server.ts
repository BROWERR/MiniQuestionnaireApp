import {AnswerDTO} from "./answerDTO";

export interface Question_to_server{
  id?:number;
  questionTitle:string;
  answer_options: number;
  answers: AnswerDTO[];
}
