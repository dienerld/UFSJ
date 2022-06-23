/* eslint-disable prefer-destructuring */
import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig, MenuItem } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title: 'Home';
  items: MenuItem[] = [];
  activeItem: Object = this.items[0];
  constructor(private primengConfig: PrimeNGConfig) {}

  ngOnInit() {
    this.primengConfig.ripple = true;

    this.items = [
      { label: 'Home', icon: 'pi pi-fw pi-home', routerLink: 'home' },
      { label: 'Relatorios', icon: 'pi pi-fw pi-calendar', routerLink: 'relatorios' },
      { label: 'Abates', icon: 'pi pi-fw pi-calendar', routerLink: 'stalls' },
    ];
    this.activeItem = this.items[0];
  }
}
