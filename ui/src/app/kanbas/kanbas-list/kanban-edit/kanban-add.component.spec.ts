import { of } from 'rxjs';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { KanbasService } from '../../kanbas.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KanbanEditComponent } from './kanban-edit.component';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/compiler';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LANE_DATA_LIST } from '../../model/mock-kanbas-list';

const MatDialogRefMock = {
  close() {
    return {
      close: () => {},
    };
  },
};

// value from new
const data = null;

const MatDialogMock = {
  open() {
    return {
      afterClosed: () => of(true),
    };
  },
};

const ActivatedRouteMock = {
  snapshot: {
    params: {
      code: 'mural-0002',
    },
  },
};

describe('KanbasAddComponent', () => {
  let component: KanbanEditComponent;
  let fixture: ComponentFixture<KanbanEditComponent>;
  let kanbasService: KanbasService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      declarations: [KanbanEditComponent],
      providers: [
        KanbasService,
        { provide: MatDialogRef, useValue: MatDialogRefMock },
        { provide: MAT_DIALOG_DATA, useValue: data },
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KanbanEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    kanbasService = TestBed.inject(KanbasService);
    // spyOn(kanbasService, 'getBooksFromCart').and.callFake(() => listBook);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should save', () => {
    let onSaveSpy = spyOn(component, 'onSave').and.callFake(() => { return true });
    component.kanban = {
      id: 1,
      title: 'titulo',
      description: 'desc',
      code: 'sabdhuavd',
      select: true,
        userKanbanPermission:[
          {id:1, users:{id:1,email:'mercedes@escape.com', online:false}, permission:{id:1,rol:"Owner"}},
          {id:2, users:{id:2,email:'raul@escape.com', online:false}, permission:{id:2,rol:"Editor"}}
        ],
        swimlanes:LANE_DATA_LIST
    };
    expect(component.kanban).toBeTruthy;
    let ok = component.onSave();

    expect(ok).toBeTrue();
  });

  it('should onCancel', () => {
    let onCancelResult = component.onCancel();
    expect(onCancelResult).toBeTrue();
  })
  
  it('should busy', () => {
    component.busy =true;
    let onCancelResult = component.onSave();
    expect(onCancelResult).toBeFalse()
  }) 
});
