import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/compiler';
import { of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KanbasService } from '../kanbas.service';

import { LaneComponent } from './lane.component';
const MatDialogMock = {
  open() {
    return {
      afterClosed: () => of(true),
    };
  },
};

const event = {
  previousContainer: {
    data: [
      {
        id: 2,
        title: 'Client tasks usability',
        description:
          'Think and design how clients will interact with notes at the same time',
        laneId: 1,
      },
    ],
  },
  container: {
    data: [
      {
        id: 1,
        title: 'Cloud design',
        description: 'Design of our cloud-based backend',
        laneId: 2,
      },
      {
        id: 3,
        title: 'UI implementation',
        description: 'Implementation of our Angular 14 Kanva UI',
        laneId: 2,
      },
    ],
  },
  previousIndex: 0,
  currentIndex: 0,
};

fdescribe('LaneComponent', () => {
  let component: LaneComponent;
  let fixture: ComponentFixture<LaneComponent>;
  let kanbasService: KanbasService;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        // HttpClientTestingModule,
      ],
      declarations: [LaneComponent],
      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
      ],
     
    }).compileComponents();
  });

  beforeEach(async () => {
    fixture = TestBed.createComponent(LaneComponent);
    component = fixture.componentInstance;

    component.lane = {
      id: 1,
      title: 'To do',
      color: '',
      order: 0,
      items: [
        {
          id: 1,
          title: 'Cloud design',
          description: 'Design of our cloud-based backend',
          laneId: 1,
        },
        {
          id: 2,
          title: 'Client tasks usability',
          description:
            'Think and design how clients will interact with notes at the same time',
          laneId: 1,
        },
      ],
    };
    component.listId = ['list0', 'list1', 'list2'];
    component.index = 0;
    fixture.detectChanges();

    kanbasService = TestBed.inject(KanbasService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should add', () => {
    let resultAdd = component.add();
    expect(resultAdd).toBeTrue();
  });

  it('should delete', () => {
    let resultDelete = component.delete();
    expect(resultDelete).toBeTrue();
  });

  it('should edit', () => {
    let resultEdit = component.edit();
    expect(resultEdit).toBeTrue();
  });

  it('should edit', () => {
    let resultEdit = component.edit();
    expect(resultEdit).toBeTrue();
  });
 

  it('should drop', () => {
    expect(event.container.data.length).toEqual(2);
    let resultEvent = component.drop(event,0)
    expect(resultEvent.container.data.length).toEqual(3);
  });

  
});