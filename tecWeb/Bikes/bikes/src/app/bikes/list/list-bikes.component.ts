import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-bikes',
  templateUrl: './list-bikes.component.html',
  styleUrls: ['./list-bikes.component.sass']
})
export class ListBikesComponent implements OnInit {
  columns = ['dsd', 'sdsd'];
  value = [32, 434];

  ngOnInit(): void {
    //
  }
}
