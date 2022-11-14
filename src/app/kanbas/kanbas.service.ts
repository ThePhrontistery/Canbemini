import { KANBAS_DATA_LIST } from './model/mock-kanbas-list';
import { Task, Lane } from './model/Kanbas';
import { Injectable, EventEmitter } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KanbasService {
  emitDeleteCard: EventEmitter<any> = new EventEmitter();
  emitAddCard: EventEmitter<Task> = new EventEmitter();
  emitAddKanba: EventEmitter<Lane> = new EventEmitter();
  
  constructor() { }

  getKanbas():Observable<Lane[]> {
      return  of(KANBAS_DATA_LIST);
  }

  saveItem(entitie:Task){
    this.emitAddCard.emit(entitie);
  }
  
  save(entitie:Lane){
    this.emitAddKanba.emit(entitie);
  }

}
