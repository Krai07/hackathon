import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddassetrequestService {

  private addAssetRequestData = "http://localhost:8080/insertAssetReq"

  constructor(private http: HttpClient) { }

  addRequestAssetRecord(request_asset_data: any): Observable<String>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };

    return this.http.post<string>(this.addAssetRequestData,request_asset_data,httpOptions);
  }
}
