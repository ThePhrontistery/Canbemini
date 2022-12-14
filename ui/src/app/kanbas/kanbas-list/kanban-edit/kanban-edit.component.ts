import { LoginService } from './../../../login/login.service';
import { Component, OnInit, Inject} from '@angular/core';
import { KanbasService } from '../../kanbas.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Kanban } from '../../model/Kanban';

@Component({
  selector: 'app-kanbas-edit',
  templateUrl: './kanban-edit.component.html',
  styleUrls: ['./kanban-edit.component.css']
})
export class KanbanEditComponent implements OnInit {

  kanban:Kanban;
  busy:boolean = false;  
  userId:number = 1;
  constructor(private kanbasService:KanbasService,
              public dialogRef:MatDialogRef<KanbanEditComponent>,
              public loginService:LoginService,
              @Inject(MAT_DIALOG_DATA) public data: any) { }
              

  ngOnInit(): void {

    this.loginService.user.subscribe(user=>{
      this.userId = user.id;
    });

    if(this.data == null){
      this.kanban = new Kanban();

      this.kanban.swimlanes=[];
    }      
    else{
      this.kanban = Object.assign({}, this.data);        
    }
  }

  onCancel(){
    this.dialogRef.close();
    return true;
  }

  onSave(){

    //Control de doble click
    if(this.busy === true) return false ;
    
    this.busy = true;
    
    
    this.kanbasService.saveKanban(this.kanban,this.userId).subscribe(result =>  {
      this.dialogRef.close();
    }); 
    
    return true;
  }
}
