import { User } from 'src/app/kanbas/model/User';
import { Attachment } from "./attachment";
import { Lane } from "./Lane";

export class Notes {
    id:number;
    content:string;  
    swimlane ?:Lane;
    order: number;
    attachment?:Attachment[];
    usersBlock?:User
    
}
