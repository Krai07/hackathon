import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class DeliveryService {
    private url = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    pendingDelivery(): Observable<any[]> {
        return this.http.get<any[]>(`${this.url}/getpendingDelivery`);
    }

    completedDelivery(): Observable<any[]> {
        return this.http.get<any[]>(`${this.url}/getcompletedDelivery`);
    }

    adminComment(assetrequestId: number, deliveryComments: any): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type': 'application/json',
            }),
          };
      
          const url = `${this.url}/adminComment/${assetrequestId}`;
      
          return this.http.put(url, deliveryComments, httpOptions);   
    }
}