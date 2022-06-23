import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { SlaugtherService } from '&services/slaugther.service';

@Component({
  selector: 'app-stalls-list',
  templateUrl: './slaugther-list.component.html',
  styleUrls: ['./slaugther-list.component.scss']
})
export class SlaugtherListAllComponent implements OnInit {
  title = 'Abates';
  constructor(
    private readonly service: SlaugtherService,
    private readonly serviceTitle: Title
  ) { }

  stalls: any[] = [
    {
      id: 1,
      name: 'Baia 1',
      weightAverage: 10,
      date: '2020-01-01',
      description: 'Descrição da baia 1'
    }, {
      id: 2,
      name: 'Baia 2',
      weightAverage: 20,
      date: '2020-01-02',
      description: 'Descrição da baia 2'
    }, {
      id: 3,
      name: 'Baia 3',
      weightAverage: 30,
      date: '2020-01-03',
      description: 'Descrição da baia 3'
    },
  ];

  ngOnInit(): void {
    this.serviceTitle.setTitle(this.title);

    this.service.getLastTenStalls().subscribe((stalls: any[]) => {
      this.stalls = stalls;
    });
  }
}
