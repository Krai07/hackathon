import { Component, OnInit } from '@angular/core';
import { DisplayOrderService } from './display-order.service';
import { Router } from '@angular/router';
import { ProcurementService } from './procurement.service';
import { AddAssetComponent } from '../addasset/addasset.component';

@Component({
    selector: 'purchase-order-cmp',
    moduleId: module.id,
    templateUrl: 'purchaseorder.component.html'
})

export class PurchaseOrderComponent implements OnInit{

    ProcurementData: any[] = [];
    ProcurementDataAdded: any[] = [];

    constructor(private displayorderService: DisplayOrderService, private router: Router, private procurementService: ProcurementService) {}

    ngOnInit(): void{

        this.fetchOrders();
        this.fetchAddedOrders();

    }

    fetchOrders(){
        this.displayorderService.getProcurement().subscribe(
            (data) => {
                console.log('Fetched orders data:', data);
                this.ProcurementData = data;
            },
            (error) => {
                console.error('Error fetching orders: ',error);
            }
        )
    }
    
    fetchAddedOrders(){
        this.displayorderService.getAddedProcurement().subscribe(
            (data) => {
                console.log('Fetched orders data:', data);
                this.ProcurementDataAdded = data;
            },
            (error) => {
                console.error('Error fetching orders: ',error);
            }
        )
    }

    sendtoAsset(order: any) {
        console.log("Hii!!!");
        console.log(order.procurement_id);
        this.procurementService.setProcurementId(order.procurement_id)
        this.router.navigate(['/addasset'])
    }

}
