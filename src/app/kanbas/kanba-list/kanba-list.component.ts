import { Title } from '@angular/platform-browser';
import { MatDialog } from '@angular/material/dialog';
import { Lane, Task } from './../model/Kanbas';
import { Component, OnInit } from '@angular/core';
import { KanbasService } from '../kanbas.service';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { LaneEditComponent } from '../lane-edit/lane-edit.component';
@Component({
  selector: 'app-kanba-list',
  templateUrl: './kanba-list.component.html',
  styleUrls: ['./kanba-list.component.css'],
})
export class KanbaListComponent implements OnInit {
  kanbas: Lane[] = [];
  kanbasListId: string[] = [];

  constructor(
    private KanbasService: KanbasService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.KanbasService.getKanbas().subscribe((kanbasList) => {
      this.kanbas.push(...kanbasList);

      this.kanbas.forEach((e, i) => {
        this.kanbasListId.push('list' + i);
      });
    });

    this.KanbasService.emitDeleteCard.subscribe((y) => {
      console.log('aqui elminar', y);
      this.kanbas[y.KanbaIndex].items.splice(y.Itemindex, 1);
    });

    this.KanbasService.emitAddCard.subscribe((entitie: Task) => {
      let kanba = this.kanbas.find((x) => x.id == entitie.LaneId);

      if (entitie.id == null) {
        entitie.id = this.getId();
        kanba.items.push(entitie);
      } else {
        let index = kanba.items.findIndex((item) => item.id == entitie.id);
        kanba.items[index] = entitie;
      }
    });
    
    this.KanbasService.emitAddKanba.subscribe((entitie: Lane) => {
      

      if (entitie.id == null) {
        entitie.id = this.kanbas.length + 1;
        this.kanbas.push(entitie);
      } else {
        let index = this.kanbas.findIndex((item) => item.id == entitie.id);
        this.kanbas[index].title = entitie.title;
      }
      this.kanbas.forEach((e, i) => {
        this.kanbasListId.push('list' + i);
      });
    });
  }

  drop(event: any) {
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
    console.log('event.previousContainer.data', event.previousContainer.data);
    console.log('event.container.data', event.container.data);
    console.log('event.previousIndex', event.previousIndex);
    console.log('event.currentIndex', event.currentIndex);

    console.log('lista', this.kanbas);
  }

  getId() {
    let c = 1;
    this.kanbas.forEach((r) => {
      r.items.forEach((l) => {
        c++;
      });
    });
    return c++;
  }
  
  
  add() {
    const dialogRef = this.dialog.open(LaneEditComponent, {
      data: {},
    });
  }



}
