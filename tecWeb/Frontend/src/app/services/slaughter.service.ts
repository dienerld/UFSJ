import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export type TSlaughter = {
  id: number,
  weight: number,
  date: string,
  stallId: number,
  stallName: string,
}

@Injectable({
  providedIn: 'root'
})
export class SlaughterService {
  constructor() { }

  private arr: TSlaughter[] = [
    { stallId: 1, stallName: 'Stall 1', id: 1, weight: 1, date: '2020-01-01', },
    { stallId: 2, stallName: 'Stall 2', id: 2, weight: 2, date: '2020-01-02', },
    { stallId: 3, stallName: 'Stall 3', id: 3, weight: 3, date: '2020-01-03', },
    { stallId: 4, stallName: 'Stall 4', id: 4, weight: 4, date: '2020-01-04', },
  ];

  // TODO: implementar o serviço de busca de ultimo abate de cada baia"eslint": "^7.32.0 || ^8.2.0",
  getLastTenStalls():Observable<TSlaughter[]> {
    return new Observable<TSlaughter[]>(subscriber => {
      subscriber.next(this.arr);
      subscriber.complete();
    });
  }

  // TODO: implementar o serviço de busca de ultimo abate geral
  getLastSlaughter():Observable<TSlaughter> {
    return new Observable<TSlaughter>(subscriber => {
      subscriber.next(this.arr[0]);
      subscriber.complete();
    });
  }
}
