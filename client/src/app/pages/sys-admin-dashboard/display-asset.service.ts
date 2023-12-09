import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DisplayAssetService {

  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getAsset(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getAssets`);
  }

  getTotalAssetCount(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/getAssetCount`);
  }

  getAssetLostCount(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/getLostAsset`);
  }

  getUnassignedAssets(): Observable<number>{
    return this.http.get<number>(`${this.apiUrl}/getUnassignedCount`);
  }

  getDamagedAssets(): Observable<number>{
    return this.http.get<number>(`${this.apiUrl}/getDamagedCount`);
  }

  displayUnassignedAssets(status: string): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/displayUnassignedAssets`);
  }

  displayAssignedAssets(status: string): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/displayAssignedAssets`);
  }

  getAssignedAssetsCount(): Observable<number>{
    return this.http.get<number>(`${this.apiUrl}/getAssignedCount`);
  }

  displayDamagedAssets(): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/displayDamagedAssets`);
  }
}
