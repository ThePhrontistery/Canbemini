import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KanbaListComponent } from './kanba-list.component';

describe('KanbaListComponent', () => {
  let component: KanbaListComponent;
  let fixture: ComponentFixture<KanbaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KanbaListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KanbaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
