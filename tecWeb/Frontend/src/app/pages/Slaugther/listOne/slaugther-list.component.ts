import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { SlaugtherService } from '&services/slaugther.service';

@Component({
  selector: 'app-slaugther-list',
  templateUrl: './slaugther-list.component.html',
  styleUrls: ['./slaugther-list.component.scss']
})
export class SlaugtherListOneComponent implements OnInit {
  slaugtherId: number | undefined = undefined;
  title = 'Abates';

  constructor(
    private readonly service: SlaugtherService,
    private readonly serviceTitle: Title,
    private readonly activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.slaugtherId = params['id'];
      this.title = `Abate - ${this.slaugtherId}`;
      this.serviceTitle.setTitle(this.title);
    });
  //
  }
}
