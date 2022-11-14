import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaneEditComponent } from './lane-edit.component';

describe('KanbaEditComponent', () => {
  let component: LaneEditComponent;
  let fixture: ComponentFixture<LaneEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LaneEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaneEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
