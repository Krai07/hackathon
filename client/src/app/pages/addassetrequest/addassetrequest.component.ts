import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddassetrequestService } from './addassetrequest.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'add-asset-request-cmp',
  moduleId: module.id,
  templateUrl: 'addassetrequest.component.html'
})

export class AddAssetRequestComponent implements OnInit {
  assetRequestForm: FormGroup

  constructor(private fb: FormBuilder, private AddassetrequestService: AddassetrequestService, private toastr: ToastrService) {
    this.assetRequestForm = this.fb.group({
      category: ['', [Validators.required]],
      request: ['', [Validators.required]],
      delivery: ['', [Validators.required]]
    });
  }

  ngOnInit() {

    this.initForm();
    this.getRole();
  }


  initForm() {
    this.assetRequestForm = this.fb.group({
      asset_category: ['', Validators.required],
      emp_id: ['', Validators.required],
      project_code: ['', Validators.required],
      request_type: ['', Validators.required],
      request_comments: ['', Validators.required],
      delivery_location: ['', Validators.required],
      delivery_comments: ['', Validators.required],
    });


    const empIdFromSession = parseInt(localStorage.getItem('username'));
    console.log("ID fetched from session: ", empIdFromSession);
    this.assetRequestForm.get('emp_id').setValue(empIdFromSession);
  }

  getRole() {
    const roleFromSession = localStorage.getItem('role');
    console.log(roleFromSession)

    if(roleFromSession == 'ROLE_DADMIN') {
      return true
    }
  }

  onSubmit(): void {
    // if (this.assetRequestForm.valid) {
    console.log("Form values", this.assetRequestForm.value);

    this.AddassetrequestService.addRequestAssetRecord(this.assetRequestForm.value).subscribe(
      (response: string) => {
        console.log(response);

                    if (response) {
                        console.log("Asset request send!");
                        this.assetRequestForm.reset();
                        this.assetRequestForm.get('asset_category').setValue('');
                        this.assetRequestForm.get('request_type').setValue('');
                        this.showNotification('top','right',2);
                    } else {
                        console.log('Error, Request cannot be send!');
                        this.assetRequestForm.reset();
                        this.assetRequestForm.get('asset_category').setValue('');
                        this.assetRequestForm.get('request_type').setValue('');
                        this.showNotification('top','right',4);
                    }
                },
                (error) => {
                    this.assetRequestForm.reset();
                    console.error('Error, Request cannot be send!', error);
                    this.assetRequestForm.reset();
                    this.assetRequestForm.get('asset_category').setValue('');
                    this.assetRequestForm.get('request_type').setValue('');
                    this.showNotification('top','right',4);
                }
            )
        // } else {
            // console.log("All Empty Feilds need to be filled!");
            // window.alert("All Empty Feilds need to be filled!");
        // }

  };



  showNotification(from, align, color) {
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
          '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"> <b>Assets request send!</b></span>',
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
          '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"><b>Failed to send asset request</b></span>',
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

}