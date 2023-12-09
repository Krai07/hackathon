import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DisplayRequestService {

  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getAssetRequest(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/getRequest`);
  }

  getAssetCode(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/getAssetCode`);
  }

  checkAsset(assetCode: string): Observable<any> {
      return this.http.get<any[]>(`${this.apiUrl}/checkAsset/${assetCode}`);
  }

  assignAsset(assetrequestId: number, assignAsset: any): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };

    const url = `${this.apiUrl}/assignAsset/${assetrequestId}`;

    return this.http.put(url, assignAsset, httpOptions);
  }
}
