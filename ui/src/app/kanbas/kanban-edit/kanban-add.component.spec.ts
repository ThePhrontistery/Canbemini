import { of } from 'rxjs';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { KanbasService } from '../kanbas.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KanbanEditComponent } from './kanban-edit.component';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/compiler';
import { HttpClientTestingModule } from '@angular/common/http/testing';

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
    // let onSaveSpy = spyOn(component, 'onSave').and.callThrough();
    kanbasService.kanbas = [];
    expect(kanbasService.kanbas.length).toEqual(0);

    component.kanba.title = 'titulo';
    component.kanba.description = 'description';
    component.kanba.swimlanes = [];
    component.onSave();

    kanbasService.emitSaveKanba.subscribe((x) => {
      kanbasService.kanbas =[]
      kanbasService.kanbas.push(x);
      expect(kanbasService.kanbas.length).toEqual(1);
    });
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
