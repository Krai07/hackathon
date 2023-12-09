import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AdminLayoutRoutes } from './admin-layout.routing';

import { DashboardComponent } from '../../pages/sys-admin-dashboard/sys-admin-dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import { TableComponent } from '../../pages/table/table.component';
import { TypographyComponent } from '../../pages/typography/typography.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { NotificationsComponent } from '../../pages/notifications/notifications.component';
import { UpgradeComponent } from '../../pages/upgrade/upgrade.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddAssetComponent } from 'app/pages/addasset/addasset.component';
import { LoginComponent } from 'app/pages/login/login.component';
import { UserService } from 'app/pages/login/login.service';
import { AddAssetRequestComponent } from 'app/pages/addassetrequest/addassetrequest.component';
import { PurchaseOrderComponent } from 'app/pages/purchaseorder/purchaseorder.component';
import { AssetRequestComponent } from 'app/pages/assetrequest/assetrequest.component';
import { AdminDashboardComponent } from 'app/pages/admin-dashboard/admin-dashboard.component';
import { AssetApprovalComponent } from 'app/pages/assetapproval/assetapproval.component';
import { UserDashboardComponent } from 'app/pages/user-dashboard/user-dashboard.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { AutocompleteDirective } from 'app/pages/assetrequest/autocomplete.directive';
import { PaginationComponent } from 'app/pages/pagination/pagination.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    MatExpansionModule
  ],
  declarations: [
    DashboardComponent,
    PurchaseOrderComponent,
    UserDashboardComponent,
    AssetRequestComponent,
    UserComponent,
    TableComponent,
    UpgradeComponent,
    TypographyComponent,
    IconsComponent,
    MapsComponent,
    NotificationsComponent,
    AddAssetComponent,
    LoginComponent,
    AddAssetRequestComponent,
    AssetApprovalComponent,
    AdminDashboardComponent,
    AutocompleteDirective,
    PaginationComponent
  ]
})

export class AdminLayoutModule { }
