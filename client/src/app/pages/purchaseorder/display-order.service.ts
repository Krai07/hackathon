import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DisplayOrderService {
  private procurementId: number;
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getProcurement(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/getOrders`);
  }

  getAddedProcurement(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/getAddedProcurement`);
  }

  setProcurementId(id: number): void {
    this.procurementId = id;
  }

  getProcurementId(): number {
    return this.procurementId;
  }
}
