import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarouselBikesComponent } from './carousel-bikes.component';

describe('CarouselBikesComponent', () => {
  let component: CarouselBikesComponent;
  let fixture: ComponentFixture<CarouselBikesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarouselBikesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarouselBikesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
