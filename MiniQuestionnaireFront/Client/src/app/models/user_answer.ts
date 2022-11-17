import {User} from "./user";
import { AnswerDTO} from "./answerDTO";

export interface User_answer{
  id?:number;
  user: User;
  answer: AnswerDTO;
}
