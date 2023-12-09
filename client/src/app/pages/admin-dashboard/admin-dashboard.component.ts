import { Component, OnInit } from '@angular/core';
import { DeliveryService } from './admin-dashboard.service';
import { Router } from 'express';
import { ToastrService } from 'ngx-toastr';

@Component({
    selector: 'admin-dashboard-cmp',
    moduleId: module.id,
    templateUrl: 'admin-dashboard.component.html'
})

export class AdminDashboardComponent implements OnInit {
    comment: { [key: number]: string} = {};

    PendingDelivery = [];
    CompletedDelivery = [];

    constructor(private deliveryService: DeliveryService, private toastr: ToastrService) { }

    ngOnInit(): void {
        this.fetchPendingDelivery();
        this.fetchCompletedDelivery();
    }

    fetchPendingDelivery() {
        this.deliveryService.pendingDelivery().subscribe(
            (data) => {
                console.log('Fetched pending delivery: ',data);
                this.PendingDelivery = data;
            },
            (error) => {
                console.error('Error fetching pending delivery: ', error);
            }
        );
    }

    fetchCompletedDelivery() {
        this.deliveryService.completedDelivery().subscribe(
            (data) => {
                console.log('Fetched Completed Delivery: ', data);
                this.CompletedDelivery = data;
            },
            (error) => {
                console.error('Error fetching completed delivery: ', error);
            }
        );
    }

    deliveryComments(delivery: any) {
        const deliveryComment = {
            asset_request_id: delivery.asset_request_id,
            admin_comments: this.comment[delivery.asset_request_id] || ''
        }

        this.deliveryService.adminComment(delivery.asset_request_id, deliveryComment).subscribe(
            (response: any) => {
                console.log(response);
                if(response) {
                    console.log("Admin Comments Added!");
                    this.comment[delivery.asset_request_id] = '';
                    this.showNotification('top','center',2);
                    // window.alert("Delivery status updated");
                    
                } else {
                    console.log('Failed to add admin comments!');
                    // window.alert("Failed to add admin comments!");
                    this.showNotification('top','center',4);
                }
            },
            (error) => {
                console.error('HTTP error:', error);
                // Handle failed signup
                console.error('Failed to add comments!');
                this.showNotification('top','center',4);
            },
            ()=> {
                this.fetchPendingDelivery();
                this.fetchCompletedDelivery();
            }
        );
    }



    showNotification(from, align,color) {
        // const color = Math.floor(Math.random() * 5 + 1);
    
        switch (color) {
          case 1:
            this.toastr.info(
            '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">Delivery status updated</b></span>',
              "",
              {
                timeOut: 4000,
                closeButton: true,
                enableHtml: true,
                toastClass: "alert alert-info alert-with-icon",
                positionClass: "toast-" + from + "-" + align
              }
            );
            break;
          case 2:
            this.toastr.success(
              '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"> <b>Delivery status updated!</b></span>',
              "",
              {
                timeOut: 4000,
                closeButton: true,
                enableHtml: true,
                toastClass: "alert alert-success alert-with-icon",
                positionClass: "toast-" + from + "-" + align
              }
            );
            break;
          case 3:
            this.toastr.warning(
            '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">Welcome to <b>Paper Dashboard Angular</b> - a beautiful bootstrap dashboard for every web developer.</span>',
              "",
              {
                timeOut: 4000,
                closeButton: true,
                enableHtml: true,
                toastClass: "alert alert-warning alert-with-icon",
                positionClass: "toast-" + from + "-" + align
              }
            );
            break;
          case 4:
            this.toastr.error(
            '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"><b>Failed to update delivery status!</b></span>',
              "",
              {
                timeOut: 4000,
                enableHtml: true,
                closeButton: true,
                toastClass: "alert alert-danger alert-with-icon",
                positionClass: "toast-" + from + "-" + align
              }
            );
            break;
          case 5:
            this.toastr.show(
            '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">Welcome to <b>Paper Dashboard Angular</b> - a beautiful bootstrap dashboard for every web developer.</span>',
              "",
              {
                timeOut: 4000,
                closeButton: true,
                enableHtml: true,
                toastClass: "alert alert-primary alert-with-icon",
                positionClass: "toast-" + from + "-" + align
              }
            );
            break;
          default:
            break;
        }
      }

      getStatusColor(status: string): { color: string } {
        let color = ''; // Default color
    
        switch (status) {
          case 'DELIVERED':
            color = 'green';
            break;
          case 'DELIVERING':
            color = 'orange';
            break;
          default:
            break;
        }
        return { color };
      }
}
