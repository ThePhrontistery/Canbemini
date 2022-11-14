import { LaneEditComponent } from '../lane-edit/lane-edit.component';
import { TaskEditComponent } from '../task-edit/task-edit.component';
import { MatDialog } from '@angular/material/dialog';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { Lane } from '../model/Kanbas';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-lane',
  templateUrl: './lane.component.html',
  styleUrls: ['./lane.component.css'],
})
export class LaneComponent implements OnInit {
  @Input() kanba: Lane;
  @Input() index: number;
  @Input() listId: string[];
  constructor(public matDialog: MatDialog) {}

  ngOnInit(): void {}

  drop(event: any, LaneId: number) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
    event.container.data.forEach((element) => {
      element.LaneId = LaneId;
    });
    console.log('event.previousContainer.data', event.previousContainer.data);
    console.log('event.container.data', event.container.data);
    console.log('event.previousIndex', event.previousIndex);
    console.log('event.currentIndex', event.currentIndex);
    console.log('event.currentIndex', console.log(LaneId));
  }

  add() {
    const dialogRef = this.matDialog.open(TaskEditComponent, {
      data: { kanba: this.kanba },
    });
  }
 
  edit() {
    const dialogRef = this.matDialog.open(LaneEditComponent, {
      data: { entitie: this.kanba },
    });
  }
}
