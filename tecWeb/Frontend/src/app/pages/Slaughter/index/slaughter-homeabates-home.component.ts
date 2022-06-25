import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { SlaughterService, TSlaughter } from '&services/slaughter.service';

@Component({
  selector: 'app-abates-home',
  templateUrl: './slaughter-home.component.html',
  styleUrls: ['./slaughter-home.component.scss']
})
export class SlaughterHomeComponent implements OnInit {
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
    private readonly service: SlaughterService,
    private readonly titleService: Title
  ) {
  }

  ngOnInit(): void {
    this.titleService.setTitle(this.title);
    this.service.getLastTenStalls().subscribe((stalls: TSlaughter[]) => {
      this.stalls = stalls;
    });
  }
}
