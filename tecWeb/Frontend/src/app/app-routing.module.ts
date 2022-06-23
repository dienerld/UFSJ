import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RelatorioComponent } from './pages/relatorio/relatorio.component';
import { SlaugtherHomeComponent } from './pages/Slaugther/index/slaugther-homeabates-home.component';
import { SlaugtherListAllComponent } from './pages/Slaugther/listAll/slaugther-list.component';
import { SlaugtherListOneComponent } from './pages/Slaugther/listOne/slaugther-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'relatorios', component: RelatorioComponent },
  { path: 'stalls', component: SlaugtherHomeComponent },
  { path: 'stalls/all', component: SlaugtherListAllComponent },
  { path: 'stalls/:id', component: SlaugtherListOneComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
