// procurement.service.ts

import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcurementService {
  private procurementIdSource = new BehaviorSubject<number>(null);
  // currentProcurementId: number;
  currentProcurementId = this.procurementIdSource.asObservable();

  setProcurementId(procurementId: number) {
    console.log("fetched values : "+procurementId)
    this.procurementIdSource.next(procurementId);
  }
}
