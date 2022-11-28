import { TaskEditComponent } from '../task-edit/task-edit.component';
import { KanbasService } from '../kanbas.service';

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Lane } from '../model/Lane';
import { Task } from '../model/Task';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  @Input()item: Task; 
  @Input()index: number;
  @Input()indexY: number;
  @Input()taskLane: Lane;

 
  constructor(private kanbasService:KanbasService,   public dialog: MatDialog,  ) { }

  ngOnInit(): void {
  }

  remove(){
    this.kanbasService.emitDeleteCard.emit({KanbaIndex:this.indexY,Itemindex:this.index});

    return true;
  }

  edit(){
    const dialogRef = this.dialog.open(TaskEditComponent, {
      data: { task: this.item ,
              lane:this.taskLane
        }
    });

    dialogRef.afterClosed().subscribe(result => {
        this.ngOnInit();
    }); 
    
    return true;
  }

}
