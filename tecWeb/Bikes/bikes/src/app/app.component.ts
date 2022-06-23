import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig, MenuItem } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {
  constructor(private primengConfig: PrimeNGConfig) {}

  items: MenuItem[];

  ngOnInit() {
    this.primengConfig.ripple = true;

    this.items = [{ label: 'File' },
      {
        label: 'Edit',
        icon: 'pi pi-fw pi-pencil',
      }
    ];
  }

  title = 'bikes';
}
