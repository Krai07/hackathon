import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAssetService {

  private url = 'http://localhost:8080/getUserAssets';
  private apiurl = 'http://localhost:8080/getAssetStatus';


  constructor(private http: HttpClient) { }

  getEmployeeAssets(): Observable<any> {
    return this.http.get<any>(this.url);
  }

  getAssetRequest(): Observable<any[]> {
    return this.http.get<any[]>(this.apiurl);
  }

  getDeliveringAssets(): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/getDeliveringAssets`);
  }

  updateAssetAllocation(asset_request_id: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/updateAssetAllocation/${asset_request_id}`);
  }


}
