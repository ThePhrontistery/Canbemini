export class Lane {
    id:number;
    title: string;
    order:number;
    items:Task[];
    color:string;
    
}

export class Task {
    title:string;
    description:string;
    LaneId:number; 
    id:number;       
}
