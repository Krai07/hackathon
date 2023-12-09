import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, retry, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssetapprovalmanagerService {

  private url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getAssetApproval(): Observable<any[]>{
    return this.http.get<any[]>(`${this.url}/getAssetsApproval`);
  }

  
  acceptComment(assetrequestId: number, checkComments: any,approvalId: number): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };

    const url = `${this.url}/checkComments/${assetrequestId}/${approvalId}`;
    console.log("Approval id at service: ",approvalId);

    const requestPayload = {
      ...checkComments,
      approvalId: approvalId,
    };
    

    return this.http.put(url,checkComments, httpOptions);
  }

  rejectComment(assetrequestId: number, rejectComments: any): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };

    const url = `${this.url}/rejectComments/${assetrequestId}`;

    return this.http.put(url, rejectComments, httpOptions);
  }

  getApprovedAssets(): Observable<any[]>{
    return this.http.get<any[]>(`${this.url}/getApprovedAssets`);
  }
}
