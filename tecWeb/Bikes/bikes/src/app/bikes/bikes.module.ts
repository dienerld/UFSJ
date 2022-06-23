import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { BikesComponent } from './bikes.component';
import { CarouselBikesModule } from './carousel/carousel-bikes.module';
import { ListBikesComponent } from './list/list-bikes.component';

@NgModule({
  declarations: [
    BikesComponent,
    ListBikesComponent,
  ],
  imports: [
    CommonModule,
    TableModule,
    ButtonModule,
    CarouselBikesModule
  ],
  exports: [BikesComponent]
})
export class BikesModule { }
