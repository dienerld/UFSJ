import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { SlaughterService, TSlaughter } from '&services/slaughter.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  title = 'Home';
  lastSlaughter?: TSlaughter;

  constructor(
    private readonly slaughterService: SlaughterService,
    private readonly titleService: Title
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle(this.title);

    this.slaughterService.getLastSlaughter().subscribe((slaughter: TSlaughter) => {
      this.lastSlaughter = slaughter;
    });
  }
}
