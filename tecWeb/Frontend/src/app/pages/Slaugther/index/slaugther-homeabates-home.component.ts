import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { SlaugtherService, TSlaugther } from '&services/slaugther.service';

@Component({
  selector: 'app-abates-home',
  templateUrl: './slaugther-home.component.html',
  styleUrls: ['./slaugther-home.component.scss']
})
export class SlaugtherHomeComponent implements OnInit {
  title = 'Abates';
  description = 'Ultimo abate de cada baia';
  stalls: any[] = [];
  columns = [
    { field: 'stallId', header: 'Baia' },
    { field: 'stallName', header: 'Nome da Baia' },
    { field: 'weigth', header: 'Peso' },
    { field: 'date', header: 'Data' },

  ];

  constructor(
    private readonly service: SlaugtherService,
    private readonly titleService: Title
  ) {
  }

  ngOnInit(): void {
    this.titleService.setTitle(this.title);
    this.service.getLastTenStalls().subscribe((stalls: TSlaugther[]) => {
      this.stalls = stalls;
    });
  }
}
