import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddAsset } from './addasset.service';
import { DisplayOrderService } from '../purchaseorder/display-order.service';
import { ProcurementService } from '../purchaseorder/procurement.service';
import { Subject, takeUntil } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'add-asset-cmp',
  moduleId: module.id,
  templateUrl: 'addasset.component.html'
})

export class AddAssetComponent implements OnInit, OnDestroy {
  assetForm: FormGroup;
  procurementId: number;
  vendorName: string; // To store the fetched vendor name
  procurementDate: Date; // To store the fetched procurement date
  assetCategory: string;
  private destroy$: Subject<void> = new Subject<void>();


  constructor(
    private fb: FormBuilder,
    private addAssetService: AddAsset,
    private cdr: ChangeDetectorRef,
    private procurementService: ProcurementService,
    private toastr: ToastrService
  ) {
    this.assetForm = this.fb.group({
      asset_category: ['', Validators.required],
      manufacturer: ['', Validators.required],
      product: ['', Validators.required],
      model: ['', Validators.required],
      serial_number: ['', Validators.required],
      watts: ['', Validators.required],
      end_of_support: ['', Validators.required],
      end_of_extended_support_year: ['', Validators.required],
      end_of_extended_support: [null],
      processor: ['', Validators.required],
      ram: ['', Validators.required],
      hdd_ssd: ['', Validators.required],
      licence_type: ['', Validators.required],
      os: ['', Validators.required],
      with_os: [null], // Initialize with null
      procurement_id: ['', Validators.required],
      procurementDate: [{ value: '', disabled: true }],
      vendorName: [{ value: '', disabled: true }],
      state: ['', Validators.required],
      sub_state: ['', Validators.required],
      sub_state1: ['', Validators.required],
      with_antivirus_yn: [false, Validators.required],
      with_msoffice_yn: [false, Validators.required],
      with_charger_yn: [false, Validators.required],
      with_laptop_bag_yn: [false, Validators.required],
    });
  }

  ngOnInit() {
    
    this.assetForm.get('state').valueChanges.pipe(
      takeUntil(this.destroy$)
    ).subscribe((state) => {
      if (state === 'instock') {
        this.assetForm.get('sub_state').setValue('available', { emitEvent: false });
        this.assetForm.get('sub_state1').setValue('working', { emitEvent: false });

        this.assetForm.get('sub_state').valueChanges.pipe(
          takeUntil(this.destroy$)
        ).subscribe((sub_state) => {
          if (sub_state === 'damaged') {
            this.assetForm.get('sub_state1').setValue('needrepair', { emitEvent: false });
          } else {
            this.assetForm.get('sub_state1').setValue('', { emitEvent: false });
          }

          this.cdr.detectChanges();
        });
      } else if (state === 'inuse') {
        console.log(state);
        this.assetForm.get('sub_state').setValue('assigned', { emitEvent: false });
        this.assetForm.get('sub_state1').setValue('working', { emitEvent: false });

        this.cdr.detectChanges();
      } else {
        this.assetForm.get('sub_state').setValue('', { emitEvent: false });
        this.assetForm.get('sub_state1').setValue('', { emitEvent: false });

        this.cdr.detectChanges();
      }
    });

    this.procurementService.currentProcurementId.subscribe((procurementId) => {
      if (procurementId) {
        this.fetchProcurementDetails(procurementId);
      } else {
        this.resetProcurementDetails();
      }
    });


    this.onProcurementIdChange();


    // Os autoconfiguration :
    this.assetForm.get('os').valueChanges.subscribe((selectedOs) => {
      const withOsValue = selectedOs === 'windows' ? 'Y' : 'N';
      this.assetForm.get('with_os').setValue(withOsValue);
    })


    // console.log("Final values: "+this.assetForm.get('procurement_id'));

    // this.assetForm.get('procurement_id').setValue(this.procurementId);



    this.assetForm.get('end_of_support').valueChanges.subscribe(() => this.calculateExtendedSupportDate());
    this.assetForm.get('end_of_extended_support_year').valueChanges.subscribe(() => this.calculateExtendedSupportDate());


  }





  // Method to fetch procurement details based on ID
  fetchProcurementDetails(procurementId: number) {
    // Call your service to fetch details from the backend
    this.addAssetService.getProcurementDetails(procurementId).subscribe(
      (response: any) => {
        console.log(response)
        // Update the form controls with the fetched values
        this.assetCategory = response.assetCategory;
        this.vendorName = response.vendorName;
        this.procurementDate = response.procurementDate;
        this.assetForm.get('asset_category').setValue(this.assetCategory);
        this.assetForm.get('procurement_id').setValue(procurementId);
        this.assetForm.get('vendorName').setValue(this.vendorName);
        this.assetForm.get('procurementDate').setValue(this.procurementDate);

      },
      (error) => {
        // Handle errors
        console.error('Failed to fetch procurement details:', error);
        // window.alert("Error!, failed to fetch procurement details")
        this.showNotification('top','center',3);
        // You may want to reset the values or show an error message here
      }
    );
  }




  // Method to reset procurement details
  resetProcurementDetails() {
    this.vendorName = '';
    this.procurementDate = null;

    this.assetForm.get('vendorName').setValue('');
    this.assetForm.get('procurementDate').setValue('');
  }


  onProcurementIdChange() {
    console.log('Procurement ID changed:', this.assetForm.get('procurement_id').value);
    const procurementId = this.assetForm.get('procurement_id').value;
    if (procurementId) {
      // calling a method to fetch procurement details based on ID
      this.fetchProcurementDetails(procurementId);
    } else {
      this.resetProcurementDetails();
    }
  }


  // Method to calculate extended support date :
  calculateExtendedSupportDate() {
    const supportDate = this.assetForm.get('end_of_support').value;
    const extendedYears = this.assetForm.get('end_of_extended_support_year').value;

    if (supportDate && extendedYears) {
      const supportDateObj = new Date(supportDate);
      supportDateObj.setFullYear(supportDateObj.getFullYear() + extendedYears);
      console.log("After adding : ", supportDateObj);


      const formatedExtendedSupportDate = this.formatDate(supportDateObj);

      this.assetForm.get('end_of_extended_support').setValue(formatedExtendedSupportDate);
    }
  }

  formatDate(date: Date): string {
    // Example: format as 'MM/DD/YYYY'
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const year = date.getFullYear().toString();
    return `${year}-${month}-${day}`;
  }



  onSubmit() {
    // console.log(this.assetForm.value);

    // Set values based on checkbox conditions
    const formValue = this.assetForm.value;
    formValue.with_antivirus_yn = formValue.with_antivirus_yn ? 'Y' : 'N';
    formValue.with_msoffice_yn = formValue.with_msoffice_yn ? 'Y' : 'N';
    formValue.with_charger_yn = formValue.with_charger_yn ? 'Y' : 'N';
    formValue.with_laptop_bag_yn = formValue.with_laptop_bag_yn ? 'Y' : 'N';

    // Now you can use the formValue object for further processing or submission
    console.log("Form values: ", formValue);
    console.log("Form values: ", formValue.extended_support_date);


    this.addAssetService.addAssetRecord(formValue).subscribe(
      (response: string) => {
        console.log(response);

        if (response) {
          console.log("Asset added in inventory");
          this.assetForm.reset();
          this.assetForm.get('asset_category').setValue('');
          this.assetForm.get('manufacturer').setValue('');
          this.assetForm.get('os').setValue('');
          this.assetForm.get('state').setValue('');
          this.assetForm.get('sub_state').setValue('');
          this.assetForm.get('sub_state1').setValue('');


          // window.alert("Asset added in inventory")
          this.showNotification('top','center',2);
        } else {
          console.log('failed to add asset');
          this.assetForm.reset();
          this.assetForm.get('asset_category').setValue('');
          this.assetForm.get('state').setValue('');
          this.assetForm.get('sub_state').setValue('');
          this.assetForm.get('sub_state1').setValue('');
          // window.alert("failed to add asset")
          this.showNotification('top','center',4);
        }
      },
      (error) => {
        // Handle HTTP error
        console.error('HTTP error:', error);
        // Handle failed signup
        console.error('Failed to add asset');
        this.assetForm.reset();
        this.assetForm.get('asset_category').setValue('');
        this.assetForm.get('state').setValue('');
        this.assetForm.get('sub_state').setValue('');
        this.assetForm.get('sub_state1').setValue('');
        // window.alert("failed to add asset")
        this.showNotification('top','center',4);
      }
    )

  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
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
          '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"> <b>Asset added in inventory!</b></span>',
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
        '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message">Failed to fetch procurement details!</span>',
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
        '<span data-notify="icon" class="nc-icon nc-bell-55"></span><span data-notify="message"><b>Failed to add asset!</b></span>',
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
