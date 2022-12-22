import { NoteBlock } from './../../../model/note-block';
import { StompService } from './../../../websockect/stomp.service';
import { DialogConfirmationComponent } from './../../../../core/dialog-confirmation/dialog-confirmation.component';
import { Attachment } from './../../../model/attachment';
import { NoteEditComponent } from '../note-edit/note-edit.component';
import { KanbasService } from '../../../kanbas.service';

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Lane } from '../../../model/Lane';
import { Notes } from '../../../model/Notes';
import { FileType } from 'src/app/kanbas/model/file-type';

import {DomSanitizer} from '@angular/platform-browser';
import { finalize, Subscription } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { AttachmentViewerComponent } from './attachment-viewer/attachment-viewer.component';
@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css'],
})
export class NoteComponent implements OnInit {
  @Input() item: Notes;
  @Input() index: number;
  @Input() indexY: number;
  @Input() taskLane: Lane;
  @Input() isEditorOwner: boolean;
  @Output() editNote: EventEmitter<Notes> = new EventEmitter();
  @Output() removeNote: EventEmitter<any> = new EventEmitter();
  @Output() block: EventEmitter<NoteBlock> = new EventEmitter();

  spiner:boolean = false;

  uploadProgress:number;
  uploadSub: Subscription;
  styleBlock = ''
  blockBy:boolean = false;
  constructor(
    private kanbasService: KanbasService,
    public dialog: MatDialog,
    private stompService:StompService

     ) {}

  ngOnInit(): void {
    if(this.item.usersBlock!=null){
      this.styleBlock = "border: 1rem solid red; display: flex; flex-direction: column;"
    }
  }

  remove() {
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: {
        title: 'Eliminar nota',
        description:
          'Atención si borra la nota:' +
          this.item.content +
          ' se perderán sus datos.<br> ¿Desea eliminar la nota?',
      },
    });
     
    dialogRef.afterClosed().subscribe((result) => {
       if (result) {
        this.kanbasService.removeNote(this.item).subscribe((result) => {
          this.removeNote.emit({
            note: this.item,
            indexLanba: this.indexY,
            indexNote: this.index,
          });
        });
       }
     });
    
    return true;
  }

  edit() {
    const dialogRef = this.dialog.open(NoteEditComponent, {
      data: { task: this.item, lane: this.taskLane },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != null) this.editNote.emit(result);
    });

    return true;
  }

   onFileSelected(event) {

    const file : File = event.target.files[0];

    if(file){
      
      let attachment= new Attachment();
      attachment.name = file.name;

      const upload =  this.kanbasService.uploadAttachment(this.item.id, file).pipe(
        finalize(() =>{
          this.reset()
        })
      );

      this.uploadSub = upload.subscribe(event => {
        if(event instanceof HttpResponse){  
          if(this.item.attachment==null){
            this.item.attachment = [];
          }        
           this.item.attachment.push(event.body);
        }
         
        if (event.type == HttpEventType.UploadProgress) {
          this.uploadProgress = Math.round(100 * (event.loaded / event.total));
        }
      });
    }

  }

  cancelUpload() {
    this.uploadSub.unsubscribe();
    this.reset();
  }

  reset() {
    this.uploadProgress = null;
    this.uploadSub = null;
  }

  downloadFile(att:Attachment) {
       window.open(att.document_path);
  }

  viewFile(att:Attachment){
    const dialogRef = this.dialog.open(AttachmentViewerComponent, {
      data: { attachment: att },
    });

    
  }
  
  attachmentDelete(att:Attachment){
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: {
        title: 'Eliminar fichero',
        description:
          'Atención si borra el fichero:' +
          att.name +
          ' se perderán sus datos.<br> ¿Desea eliminar el fichero?',
      },
    });
     
    dialogRef.afterClosed().subscribe((result) => {
       if (result) {
        this.kanbasService.removeAttachment(att.id).subscribe(r=>{
           let index=this.item.attachment.findIndex(tatt=> tatt.id==att.id);
           this.item.attachment.splice(index,1);
        })
       }
     });
  }

  emit(block:boolean){
    
     let noteBlock:NoteBlock = {noteId:this.item.id,swimlaneId:null,userId:null, block:block};
     this.block.emit(noteBlock);
     this.blockBy = block;
  }

  blocker(){
    this.blockBy = !this.blockBy;
    this.emit(this.blockBy);
  }
  
}
