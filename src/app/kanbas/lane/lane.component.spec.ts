import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaneComponent } from './lane.component';

describe('KanbaComponent', () => {
  let component: LaneComponent;
  let fixture: ComponentFixture<LaneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LaneComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
