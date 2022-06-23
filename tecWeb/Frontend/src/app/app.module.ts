import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MenubarModule } from 'primeng/menubar';
import { TableModule } from 'primeng/table';
import { MultiSelectModule } from 'primeng/multiselect';
import { DropdownModule } from 'primeng/dropdown';
import { SliderModule } from 'primeng/slider';
import { ProgressBarModule } from 'primeng/progressbar';
import { CardModule } from 'primeng/card';

import { ButtonModule } from 'primeng/button';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { RelatorioComponent } from './pages/relatorio/relatorio.component';
import { TableComponent } from './components/table/table.component';
import { SlaugtherHomeComponent } from './pages/Slaugther/index/slaugther-homeabates-home.component';
import { SlaugtherListAllComponent } from './pages/Slaugther/listAll/slaugther-list.component';
import { SlaugtherListOneComponent } from './pages/Slaugther/listOne/slaugther-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RelatorioComponent,
    TableComponent,
    SlaugtherHomeComponent,
    SlaugtherListAllComponent,
    SlaugtherListOneComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MenubarModule,
    TableModule,
    ButtonModule,
    MultiSelectModule,
    DropdownModule,
    ProgressBarModule,
    SliderModule,
    CardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
