import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

import { SlaughterService } from '&services/slaughter.service';

@Component({
  selector: 'app-slaughter-list',
  templateUrl: './slaughter-list.component.html',
  styleUrls: ['./slaughter-list.component.scss']
})
export class SlaughterListOneComponent implements OnInit {
  slaughterId: number | undefined = undefined;
  title = 'Abates';

  constructor(
    private readonly service: SlaughterService,
    private readonly serviceTitle: Title,
    private readonly activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.slaughterId = params['id'];
      this.title = `Abate - ${this.slaughterId}`;
      this.serviceTitle.setTitle(this.title);
    });
  //
  }
}
