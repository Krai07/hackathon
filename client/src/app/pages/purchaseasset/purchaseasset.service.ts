import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class PurchaseAsset{

    private addPurchaseAssetData = 'http://localhost:8080/addPurchaseAsset'

    constructor(private http:HttpClient) { }

    addPurchaseAssetRecord(purchase_asset_data: any):Observable<String>{
        const httpOptions = {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
          }),
        };
    
        return this.http.post<string>(this.addPurchaseAssetData,purchase_asset_data,httpOptions);
      }

}