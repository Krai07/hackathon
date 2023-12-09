import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { DisplayRequestService } from './display-request.service';
import { EMPTY, Subject, mergeMap, takeUntil } from 'rxjs';
import { AutocompleteDirective } from './autocomplete.directive';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'asset-request-cmp',
  moduleId: module.id,
  templateUrl: 'assetrequest.component.html'
})

export class AssetRequestComponent implements OnInit, OnDestroy {

  assetRequestData: any[] = [];
  assetCode: { [key: number]: string } = {};
  private ngUnsubscribe = new Subject();
  @ViewChild(AutocompleteDirective) autocompleteDirective: AutocompleteDirective;

  constructor(
    private displayrequestService: DisplayRequestService,
    private toastr: ToastrService
  ) {
  }

  ngOnInit(): void {
    this.fetchRequests();
  }

  fetchRequests() {
    this.displayrequestService.getAssetRequest().subscribe(
      (data) => {
        console.log('Fetched requests data at system admin:', data);
        this.assetRequestData = data;
      },
      (error) => {
        console.error('Error fetching requests at system admin: ', error);
      }
    )
  }

  assignAsset(request: any) {
    const assignAsset = {
      asset_request_id: request.asset_request_id,
      asset_code: this.assetCode[request.asset_request_id] || ''
    };
    console.log(this.assetCode)
    this.displayrequestService.checkAsset(assignAsset.asset_code).pipe(
      mergeMap((checkResponse: any) => {
        console.log('Check Asset Response:', checkResponse);

        if (checkResponse && checkResponse.status === 'success') {
          console.log("Asset is valid. Proceeding with assignment...");
          // window.alert("Asset Code Found!")
          this.showNotification('top', 'right', 2);
          return this.displayrequestService.assignAsset(request.asset_request_id, assignAsset);
        } else {
          console.log('Asset check failed. Not proceeding with assignment.');
          // window.alert("Asset Code Not Found!")
          this.showNotification('top', 'right', 4);
          return EMPTY;
        }
      })
    ).subscribe(
      (assignResponse: any) => {
        console.log('Assign Asset Response:', assignResponse);
        if (assignResponse) {
          console.log("Asset Code Assigned!");
          this.assetCode[request.asset_request_id] = '';
          // window.alert("Asset Code Assigned!");
          this.showNotification('top', 'right', 1);
        } else {
          console.log('Failed to assign asset code!');
          // window.alert("Failed to assign asset code!");
          this.showNotification('top', 'right', 3);
        }
      },
      (error) => {
        // Handle HTTP error
        console.error('HTTP error:', error);
        console.error('Failed to assign asset code!');
        // window.alert("Failed to assign asset code!");
        this.showNotification('top', 'right', 3);
      },
      () => {
        this.fetchRequests();
      }
    );
  }

  ngOnDestroy() {
    if (this.autocompleteDirective) {
      this.autocompleteDirective.destroyPopup();
    }
  }

  showNotification(from, align, color) {
    // Define a delay for the second notification (in milliseconds)
    const secondNotificationDelay = 3000;

    switch (color) {
      case 1:
  this.toastr.info(
    '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">Asset code found! </b></span>',
    "",
    {
      timeOut: 4000,
      closeButton: true,
      enableHtml: true,
      toastClass: "alert alert-success alert-with-icon", // Change the class to success for light green color
      positionClass: "toast-" + from + "-" + align
    }
  );
  break;

      case 2:
        // The first notification will appear after the specified delay
        setTimeout(() => {
          this.showFirstNotification(from, align);
        }, secondNotificationDelay);
        break;
      case 3:
        this.toastr.warning(
          '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">Failed to assigned asset code</span>',
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
        // The second notification will appear after the specified delay
        setTimeout(() => {
          this.showSecondNotification(from, align);
        }, secondNotificationDelay);
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

  showFirstNotification(from, align) {
    this.toastr.info(
      '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">Asset code assigned</b></span>',
      "",
      {
        timeOut: 4000,
        closeButton: true,
        enableHtml: true,
        toastClass: "alert alert-info alert-with-icon",
        positionClass: "toast-" + from + "-" + align
      }
    );
  }

  showSecondNotification(from, align) {
    this.toastr.success(
      '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"> <b>Asset code found!</b></span>',
      "",
      {
        timeOut: 4000,
        closeButton: true,
        enableHtml: true,
        toastClass: "alert alert-success alert-with-icon",
        positionClass: "toast-" + from + "-" + align
      }
    );
  }
}
