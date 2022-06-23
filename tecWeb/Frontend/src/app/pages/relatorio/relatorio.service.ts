import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export type TPig = {
  name: string;
  description: string;
  date: string;
}

@Injectable({
  providedIn: 'root'
})
export class RelatorioService {
  private arr: TPig[] = [
    { name: '1', date: '01-02', description: 'sdsd' },
    { name: '2', date: '01-06', description: 'sdsd' },
    { name: '3', date: '01-12', description: 'sdsd' },
  ];

  constructor() { }

  getRelatorio(): Observable<TPig[]> {
    return new Observable<TPig[]>(subscriber => {
      subscriber.next(this.arr);
      subscriber.complete();
    });
  }
}
