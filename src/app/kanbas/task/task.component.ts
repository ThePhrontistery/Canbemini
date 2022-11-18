import { TaskEditComponent } from '../task-edit/task-edit.component';
import { KanbasService } from '../kanbas.service';
import { Task, Lane } from '../model/Kanbas';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  @Input()task:Task; 
  @Input()index:number;
  @Input()indexY:number;
  @Input()lane:Lane;

 
  constructor(private kanbasService:KanbasService,   public dialog: MatDialog,  ) { }

  ngOnInit(): void {
  }

  remove(){
    this.kanbasService.emitDeleteCard.emit({KanbaIndex:this.indexY,Itemindex:this.index})
  }

  edit(){
    const dialogRef = this.dialog.open(TaskEditComponent, {
      data: { task: this.task ,
              lane: this.lane
        }
  });

  dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
  });   
  }

}
