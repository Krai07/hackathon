import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js';
import { DisplayAssetService } from './display-asset.service';

@Component({
    selector: 'sys-admin-dashboard-cmp',
    moduleId: module.id,
    templateUrl: 'sys-admin-dashboard.component.html',
    styles: [`
    .active-border {
      border: 2px solid lightblue;
      border-radius: 0px;
      padding: 0px;
      border-top-left-radius: 10px;
      border-bottom-left-radius: 10px;
      border-top-right-radius: 10px;
      border-bottom-right-radius: 10px;
    }
    .inactive-border {
      border: 2px solid transparent; /* Transparent border to occupy space */
      border-radius: 0px;
      padding: 0px; /* Adjust padding based on content */
      border-top-left-radius: 10px;
      border-bottom-left-radius: 10px;
      border-top-right-radius: 10px;
      border-bottom-right-radius: 10px;
    }
  `]
})

export class DashboardComponent implements OnInit {

  displayData = [];
  assetStatusData = [];
  AssignedAssetData = [];
  UnassignedAssetData = [];
  DamagedAssetData = [];
  
  totalAssetsCount: number = 0;
  totalLostAssets: number = 0;
  totalUnassignedAssets: number = 0;
  totalDamagedAssets: number = 0;
  totalAssignedAssets: number = 0;
  isBorderActive: boolean[] = [false, false, false, false];
  tableContent = [];

  allAssets: boolean = true;
  assignedAssets: boolean = false;
  unassignedAssets: boolean = false;
  damagedAssets: boolean = false;

  toggleBorder(index: number) {
    this.isBorderActive = this.isBorderActive.map((value, i) => i === index ? !value : false)

    if (this.isBorderActive[index]) {
      // Execute data fetching functions only if the border is active
      if (index === 1) {
        this.allAssets = false;
        this.assignedAssets = true;
        this.unassignedAssets = false;
        this.damagedAssets = false;
        this.fetchAssignedAssets();
        console.log('Assigned Assets',this.assignedAssets);
        this.displayData = this.AssignedAssetData;

      } else if (index === 2){
        this.allAssets = false;
        this.assignedAssets = false;
        this.unassignedAssets = true;
        this.damagedAssets = false;
        this.fetchUnassignedAssets();
        console.log('Un - Assigned Assets',this.unassignedAssets);
        this.displayData = this.UnassignedAssetData;

      } else if (index === 3){
        this.allAssets = false;
        this.assignedAssets = false;
        this.unassignedAssets = false;
        this.damagedAssets = true;
        this.fetchDamagedAssets();
        console.log('Damaged Assets',this.damagedAssets);
        this.displayData = this.DamagedAssetData;
      
      }    
    } else{
      const allCardsUnclicked = this.isBorderActive.every((value) => !value);
      if (allCardsUnclicked) {
        this.fetchAssetStatus();
        this.allAssets = true;
        this.assignedAssets = false;
        this.unassignedAssets = false;
        this.damagedAssets = false;
        this.displayData = this.assetStatusData;

      }
    }
  }

  handleCardClick(cardName: string) {
    // Implement the logic you want when a card is clicked
    console.log(`Clicked on ${cardName}`);

  }

  handleassigned() {
    this.assignedAssets = true;
    this.fetchAssignedAssets();
    console.log(this.assignedAssets);

  }

  handleunassigned(cardName: string) {
    
    
    console.log(`Clicked on ${cardName}`);
    this.fetchUnassignedAssets();


  }
  
  handledamaged(cardName: string) {

    
    console.log(`Clicked on ${cardName}`);
    this.fetchDamagedAssets();

  }


  constructor(private displayassetService: DisplayAssetService) {}

  ngOnInit(): void {

    this.fetchAssetStatus(); // Fetch total assets data
    this.fetchTotalAssetsCount();
    this.fetchLostAssetsCount();
    this.fetchUnassignedCount();
    this.fetchDamagedAssetsCount();
    this.fetchUnassignedAssets();
    this.fetchAssignedAssets();
    this.fetchAssignedAssetsCount();
    this.fetchDamagedAssets();
  }

  fetchAssetStatus() {
    this.displayassetService.getAsset().subscribe(
      (data1) => {
        console.log('Fetched data:', data1);
        this.assetStatusData = data1;
        this.displayData = data1;
      },
      (error) => {
        console.error('Error fetching assets: ', error);
      }
    );
  }

  fetchUnassignedAssets() {
    this.displayassetService.displayUnassignedAssets('instock').subscribe(
      (data) => {
        console.log('Fetched unassigned assets:', data);
        this.UnassignedAssetData = data;
      },
      (error) => {
        console.error('Error fetching unassigned assets: ',error);
      }
    )
  }

  fetchAssignedAssets() {
    this.displayassetService.displayAssignedAssets('inuse').subscribe(
      (data) => {
        console.log('Assigned assets fetched: ', data);
        this.AssignedAssetData = data;
      },
      (error) => {
        console.error('Error fetching assigned assets: ',error);
      }
    )
  }

  fetchTotalAssetsCount() {
    this.displayassetService.getTotalAssetCount().subscribe(
      (count: number) => {
        this.totalAssetsCount = count;
      },
      (error) => {
        console.error('Error fetching total assets count: ', error);
      }
    );
  }

  fetchLostAssetsCount() {
    this.displayassetService.getAssetLostCount().subscribe(
      (count: number) => {
        this.totalLostAssets = count;
      },
      (error) => {
        console.error('Error fetching lost assets: ', error);
      }
    );
  }

  fetchUnassignedCount() {
    this.displayassetService.getUnassignedAssets().subscribe(
      (count: number) => {
        this.totalUnassignedAssets = count;
      },
      (error) => {
        console.error('Error fetching unassigned assets: ', error);
      }
    );
  }

  fetchDamagedAssetsCount() {
    this.displayassetService.getDamagedAssets().subscribe(
      (count: number) => {
        this.totalDamagedAssets = count;
      },
      (error) => {
        console.error('Error fetching damaged assets: ', error);
      }
    );
  }

  fetchAssignedAssetsCount() {
    this.displayassetService.getAssignedAssetsCount().subscribe(
      (count: number) => {
        this.totalAssignedAssets = count;
      },
      (error) => {
        console.error('Error fetching assigned assets: ',error);
      }
    );
  }

  fetchDamagedAssets(){
    this.displayassetService.displayDamagedAssets().subscribe(
      (data) => {
        console.log('Damaged assets fetched: ', data);
        this.DamagedAssetData = data;
      },
      (error) => {
        console.error('Error fetching damaged assets: ',error);
      }
    )
  }

  // Method to determine the color based on the status
  getStatusColor(state: string): string {
    switch (state) {
      case 'INSTOCK':
        return 'blue'; // or any other color
      case 'INUSE':
        return 'orange';
      case 'DAMAGED':
        return 'red';
      // Add more cases as needed
      default:
        return 'black'; // Default color
    }
  }
}
