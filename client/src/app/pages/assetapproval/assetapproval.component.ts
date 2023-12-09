import { Component, OnInit } from '@angular/core';
import { AssetapprovalmanagerService } from './assetapprovalmanager.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'asset-approval-cmp',
  moduleId: module.id,
  templateUrl: 'assetapproval.component.html'
})
export class AssetApprovalComponent implements OnInit {

  selectedAllocationType: any;
  comment: { [key: number]: string } = {};
  days: { [key: number]: string } = {};

  AssetApprovalManager: any[] = [];
  ApprovedAssetsManager: any[] = [];

  constructor(
    private assetApprovalManagerService: AssetapprovalmanagerService, 
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.fetchAssetApprovals();
    this.fetchApprovedAssets();
  }

  fetchAssetApprovals() {
    this.assetApprovalManagerService.getAssetApproval().subscribe(
      (data) => {
        console.log('Fetched asset approvals:', data);
        this.AssetApprovalManager = data;
      },
      (error) => {
        console.error('Error fetching approvals:', error);
      }
    );
  }

  fetchApprovedAssets() {
    this.assetApprovalManagerService.getApprovedAssets().subscribe(
      (data) => {
        console.log('Approved assets fetched: ', data);
        this.ApprovedAssetsManager = data;
      },
      (error) => {
        console.error('Error fetching approved assets:', error);
      }
    );
  }

  acceptComment(approval: any) {
    const checkComments = {
      asset_request_id: approval.asset_request_id,
      allocation_type: this.selectedAllocationType,
      approver_comments: this.comment[approval.asset_request_id] || '',
      approver_days: this.days[approval.asset_request_id] || null
    }

    const approvalId = parseInt(localStorage.getItem('username'));
    console.log("Approval id: ",approvalId);
    

    this.assetApprovalManagerService.acceptComment(approval.asset_request_id, checkComments,approvalId).subscribe(
      (response: any) => {
        console.log(response);
        if (response) {
          console.log("Comments Checked!");
          this.comment[approval.asset_request_id] = '';
          this.showNotification('top', 'right', 2);
        } else {
          console.log('failed to add comments!');
          this.showNotification('top', 'right', 4);
        }
      },
      (error) => {
        console.error('HTTP error:', error);
        console.error('Failed to add comments!');
        this.showNotification('top', 'right', 4);
      },
      () => {
        this.fetchAssetApprovals();
        this.fetchApprovedAssets();
      }
    );
  }

  rejectComment(approval: any) {
    const rejectComments = {
      asset_request_id: approval.asset_request_id,
      approver_comments: this.comment[approval.asset_request_id] || ''
    };

    this.assetApprovalManagerService.rejectComment(approval.asset_request_id, rejectComments).subscribe(
      (response: any) => {
        console.log(response);
        if (response) {
          console.log("Comments Rejected!");
          this.showNotification('top', 'right', 7);
        } else {
          console.log('failed to add comments!');
          this.showNotification('top', 'right', 4);
        }
      },
      (error) => {
        console.error('HTTP error:', error);
        console.error('Failed to add comments!');
        this.showNotification('top', 'right', 4);
      },
      () => {
        this.fetchAssetApprovals();
        this.fetchApprovedAssets();
      }
    );
  }

  showNotification(from, align, color) {
    // ... (your existing showNotification function)
  }

  getStatusColor(status: string): { color: string } {
    let color = ''; // Default color

    switch (status) {
      case 'ALLOCATED':
        color = 'green';
        break;
      case 'REJECTED':
        color = 'red';
        break;
      case 'APPROVED':
        color = 'blue';
        break;
      default:
        break;
    }
    return { color };
  }
}
