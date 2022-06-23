import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export type TSlaugther = {
  id: number,
  weigth: number,
  date: string,
  stallId: number,
  stallName: string,
}

@Injectable({
  providedIn: 'root'
})
export class SlaugtherService {
  constructor() { }

  private arr: TSlaugther[] = [
    { stallId: 1, stallName: 'Stall 1', id: 1, weigth: 1, date: '2020-01-01', },
    { stallId: 2, stallName: 'Stall 2', id: 2, weigth: 2, date: '2020-01-02', },
    { stallId: 3, stallName: 'Stall 3', id: 3, weigth: 3, date: '2020-01-03', },
    { stallId: 4, stallName: 'Stall 4', id: 4, weigth: 4, date: '2020-01-04', },
  ];

  // TODO: implementar o serviço de busca de ultimo abate de cada baia
  getLastTenStalls():Observable<TSlaugther[]> {
    return new Observable<TSlaugther[]>(subscriber => {
      subscriber.next(this.arr);
      subscriber.complete();
    });
  }

  // TODO: implementar o serviço de busca de ultimo abate geral
  getLastSlaugther():Observable<TSlaugther> {
    return new Observable<TSlaugther>(subscriber => {
      subscriber.next(this.arr[0]);
      subscriber.complete();
    });
  }
}
