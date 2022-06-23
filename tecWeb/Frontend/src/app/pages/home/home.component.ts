import { Component, NgIterable, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { SlaugtherService, TSlaugther } from '&services/slaugther.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  title = 'Home';
  lastSlaugther: NgIterable<TSlaugther>;

  constructor(
    private readonly slaugtherService: SlaugtherService,
    private readonly titleService: Title
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle(this.title);

    this.slaugtherService.getLastSlaugther().subscribe((slaugther: TSlaugther) => {
      this.lastSlaugther = slaugther;
    });
  }
}
