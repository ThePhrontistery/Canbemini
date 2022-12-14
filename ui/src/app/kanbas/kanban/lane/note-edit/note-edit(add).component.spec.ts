import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { of } from 'rxjs';
import { KanbasService } from '../../../kanbas.service';
import { Lane } from '../../../model/Lane';
import { Notes } from '../../../model/Notes';
import { NoteEditComponent } from './note-edit.component';

const mockTask: Notes = {
  id: 1,
  content: 'Cloud design',
  order: 3,
};
const mockLane: Lane = {
  id: 1,
  title: 'To do',
  order: 1,
  notes: [
    {
      id: 1,
      content: 'Cloud design',
      order: 1,
    },
    {
      id: 2,        
      content:
        'Think and design how clients will interact with notes at the same time',
        order: 2,
    },
  ],
}
const data = {
  lane: mockLane,
  task: mockTask
}

const MatDialogMock = {
  open() {
      return {
          afterClosed: () => of(true)
      };
  }
};

describe('TaskEditComponent', () => {
  let component: NoteEditComponent;
  let fixture: ComponentFixture<NoteEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      declarations: [ 
        NoteEditComponent,
        
      ],
      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
        { provide: MatDialogRef, useValue: {} }, { provide: MAT_DIALOG_DATA, useValue: data }
      ]
      
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteEditComponent);
    component = fixture.componentInstance;
    

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
