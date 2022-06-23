import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarouselBikesComponent } from './carousel-bikes.component';

@NgModule({
  declarations: [
    CarouselBikesComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [CarouselBikesComponent]
})
export class CarouselBikesModule { }
