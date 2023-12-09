import { Component, OnInit } from '@angular/core';
import { UserAssetService } from './user-asset.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  moduleId: module.id,
  selector: 'app-user-dashboard',
  templateUrl: 'user-dashboard.component.html',
})
export class UserDashboardComponent implements OnInit {
  panelOpenState = false;
  userAssets: any[] = [];
  deliveringAssets: any[] = [];
  assetRequestData: any[] = [];

  constructor(private userAssetService: UserAssetService, private toastr: ToastrService) {}

  ngOnInit(): void {
    this.fetchEmployeeAssets();
    this.fetchRequests();
    this.fetchDeliveringAssets();
  }

  fetchEmployeeAssets() {
    this.userAssetService.getEmployeeAssets().subscribe(
      (data: any[]) => {
        this.userAssets = data;
        console.log(this.userAssets);
      },
      (error) => {
        console.error('Error fetching user assets:', error);
      }
    );
  }

  fetchDeliveringAssets() {
    this.userAssetService.getDeliveringAssets().subscribe(
      (data: any[]) => {
        this.deliveringAssets = data;
        console.log(this.deliveringAssets);
      },
      (error) => {
        console.error('Error fetching delivering assets:', error);
      }
    );
  }

  fetchRequests() {
    this.userAssetService.getAssetRequest().subscribe(
      (data: any[]) => {
        this.assetRequestData = data;
        console.log(this.assetRequestData);
      },
      (error) => {
        console.error('Error fetching asset requests:', error);
      }
    );
  }

  confirmAllocation(deliveringAssets: any, i: number) {
    console.log(deliveringAssets[i].asset_request_id);
    this.userAssetService.updateAssetAllocation(deliveringAssets[i].asset_request_id).subscribe(
      (data: any[]) => {
        console.log(data);
        this.showNotification('top', 'center', 1);
      },
      (error) => {
        console.log(error);
        this.showNotification('top', 'center', 4);
      },
      () => {
        this.fetchEmployeeAssets();
        this.fetchDeliveringAssets();
        this.fetchRequests();
      }
    );
  }

  getStatusColor(status: string): { color: string } {
    let color = ''; // Default color

    switch (status) {
      case 'PENDING':
        color = 'orange';
        break;
      case 'DELIVERING':
        color = '#f2ce0b';
        break;
      case 'REJECTED':
        color = 'red';
        break;
      case 'APPROVED':
        color = 'blue';
        break;
      case 'ALLOCATED':
        color = 'green';
        break;
      case 'ASSIGNING':
        color = 'purple';
        break;  

    }

    return { color };
  }

  showNotification(from, align, color) {
    switch (color) {
      case 1:
        this.toastr.info(
          '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">Asset allocated!</span>',
          '',
          {
            timeOut: 4000,
            closeButton: true,
            enableHtml: true,
            toastClass: 'alert alert-info alert-with-icon',
            positionClass: 'toast-' + from + '-' + align,
          }
        );
        break;
      case 4:
        this.toastr.error(
          '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"><b>Failed to allocate asset</b></span>',
          '',
          {
            timeOut: 4000,
            enableHtml: true,
            closeButton: true,
            toastClass: 'alert alert-danger alert-with-icon',
            positionClass: 'toast-' + from + '-' + align,
          }
        );
        break;
      default:
        break;
    }
  }
}
