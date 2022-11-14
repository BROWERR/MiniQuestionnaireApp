import {User} from "./user";
import {Answer} from "./answerDTO";

export interface User_answer{
  id?:number;
  user: User;
  answer: Answer;
}
