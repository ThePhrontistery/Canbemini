import { Kanban } from './../model/Kanban';
import { Lane } from './../model/Lane';

import { KanbasService } from '../kanbas.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-lane-edit',
  templateUrl: './lane-edit.component.html',
  styleUrls: ['./lane-edit.component.css'],
})
export class LaneEditComponent implements OnInit {
  entitie: Lane;
  kanbanId: number;

  constructor(
    public dialogRef: MatDialogRef<LaneEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private KanbasService: KanbasService
  ) {}

  ngOnInit(): void {
   
    if (this.data.entitie != null) {
      this.entitie = Object.assign({}, this.data.entitie);
    } else {
      this.entitie = new Lane();
    }

    this.entitie.kanban = new Kanban()
    this.kanbanId = this.data.kanbanId;
  }

  onSave() {
    
    this.KanbasService.saveSwimlane(this.kanbanId,this.entitie).subscribe(result=>{      
      this.dialogRef.close(this.entitie);
    });
    
    return true;
  }

  onClose() {
    this.dialogRef.close();
    return true;
  }
}
