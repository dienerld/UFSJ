import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { RelatorioService, TPig } from './relatorio.service';

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.scss']
})
export class RelatorioComponent implements OnInit {
  title = 'Relatórios';
  pigs: TPig[];

  columns = [
    { field: 'name', header: 'Name' },
    { field: 'date', header: 'Date' },
    { field: 'description', header: 'Description' },
  ];

  constructor(
    private readonly service:RelatorioService,
    private readonly titleService: Title
  ) {
    titleService.setTitle(this.title);
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.service.getRelatorio().subscribe(pigs => { this.pigs = pigs; });
    }, 2000);
  }
}
