import { Injectable } from '@angular/core';
import { DisplayRequestService } from './display-request.service';

@Injectable({
    providedIn: 'root',
})

export class AutocompleteService {
    assetCodes: any;

    constructor(private displayrequestService: DisplayRequestService) {
        this.displayrequestService.getAssetCode().subscribe(
            (data) => {
                console.log(data);
                this.assetCodes = data;
            },
            (error) => {
                console.error('Error Fetching Asset Codes', error);
            }
        );
    }

    filterAssetData(value: string): any[] {
        // const filterValue = value.toLowerCase();
        // return this.assetCodes.filter(asset => {
        //   // Filter based on asset_code, processor, ram, hdd_ssd, and os
        //   return (
        //     asset.asset_code.toLowerCase().includes(filterValue) ||
        //     asset.processor.toLowerCase().includes(filterValue) ||
        //     asset.ram.toLowerCase().includes(filterValue) ||
        //     asset.hdd_ssd.toLowerCase().includes(filterValue) ||
        //     asset.os.toLowerCase().includes(filterValue)
        //   );
        // });

        const filterValue = value.toLowerCase();
        return this.assetCodes.filter(asset => asset.asset_code.toLowerCase().includes(filterValue));
    }
}
