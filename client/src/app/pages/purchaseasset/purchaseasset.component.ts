import { Component, OnInit } from '@angular/core';
import { PurchaseAsset } from './purchaseasset.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
    selector: 'purchase-asset-cmp',
    moduleId: module.id,
    templateUrl: 'purchaseasset.component.html'
})

export class PurchaseAssetComponent implements OnInit {

    assetform: FormGroup;

    constructor(private fb: FormBuilder, private purchaseAssetService: PurchaseAsset,private toastr: ToastrService) {

    }

    ngOnInit() {
        this.initForm();
    }

    initForm() {
        this.assetform = this.fb.group({
            asset_category: ['', Validators.required],
            quantity: ['', Validators.required],
            selected_vendor: ['', Validators.required],
            payment_terms: ['', Validators.required],
            payment_terms_comments: ['', Validators.required],
            procurement_date: ['', Validators.required],
            procurement_desc: ['', Validators.required],
            total_value: ['', Validators.required],
            raised_by_id: ['', Validators.required],
            raised_date: ['', Validators.required],
            raised_comments: ['', Validators.required],
            approved_by_id: ['', Validators.required],
            approved_date: ['', Validators.required],
            approved_comments: ['', Validators.required],
        });


        const empIdFromSession = parseInt(localStorage.getItem('username'));
        console.log("ID fetched from session: ",empIdFromSession);
        this.assetform.get('raised_by_id').setValue(empIdFromSession);

    }


    onSubmit(): void {
        if (this.assetform.valid) {
            console.log(this.assetform.value);
            this.purchaseAssetService.addPurchaseAssetRecord(this.assetform.value).subscribe(
                (response: string) => {
                    console.log(response);

                    if (response) {
                        console.log("Purchase asset record added");
                        this.assetform.reset();
                        this.assetform.get('asset_category').setValue('');
                        // window.alert("Purchase asset record added")
                        this.showNotification('top','right',2);
                    } else {
                        // Handle failed signup
                        console.log('Failed to add record');
                        this.assetform.reset();
                        this.assetform.get('asset_category').setValue('');
                        // window.alert("Failed to add record")
                        this.showNotification('top','right',4);
                    }
                },
                (error) => {
                    console.log('Failed to add record');
                    this.assetform.reset();
                    this.assetform.get('asset_category').setValue('');
                    // window.alert("Failed to add record")
                    this.showNotification('top','right',4);
                }
            )
        } else {
            console.log("All Empty Feilds need to be filled!");
            // window.alert("All Empty Feilds need to be filled!");
            this.showNotification('top','right',3);
        }
    };



    
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
              '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"> <b>Purchase asset record added!</b></span>',
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
            '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">All Empty Feilds need to be filled!</span>',
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
            '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"><b>Failed to add record!</b></span>',
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