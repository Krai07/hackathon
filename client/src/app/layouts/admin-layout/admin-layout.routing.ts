import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/sys-admin-dashboard/sys-admin-dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import { TableComponent } from '../../pages/table/table.component';
import { TypographyComponent } from '../../pages/typography/typography.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { NotificationsComponent } from '../../pages/notifications/notifications.component';
import { UpgradeComponent } from '../../pages/upgrade/upgrade.component';
import { PurchaseAssetComponent } from 'app/pages/purchaseasset/purchaseasset.component';
import { AddAssetComponent } from 'app/pages/addasset/addasset.component';
import { AssetRequestComponent } from 'app/pages/assetrequest/assetrequest.component';
import { PurchaseOrderComponent } from 'app/pages/purchaseorder/purchaseorder.component';
import { UserDashboardComponent } from 'app/pages/user-dashboard/user-dashboard.component';
import { AddAssetRequestComponent } from 'app/pages/addassetrequest/addassetrequest.component';
import { AssetApprovalComponent } from 'app/pages/assetapproval/assetapproval.component';
import { AdminDashboardComponent } from 'app/pages/admin-dashboard/admin-dashboard.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'sys-admin-dashboard',      component: DashboardComponent               },
    { path: 'user',                     component: UserComponent                    },
    { path: 'user-dashboard',           component: UserDashboardComponent           },
    { path: 'table',                    component: TableComponent                   }, 
    { path: 'typography',               component: TypographyComponent              },
    { path: 'icons',                    component: IconsComponent                   },
    { path: 'maps',                     component: MapsComponent                    },
    { path: 'notifications',            component: NotificationsComponent           },
    { path: 'upgrade',                  component: UpgradeComponent                 },
    { path: 'addasset',                 component: AddAssetComponent                },
    { path: 'assetrequest',             component: AssetRequestComponent            },
    { path: 'purchaseasset',            component: PurchaseAssetComponent           },
    { path: 'purchaseorder',            component: PurchaseOrderComponent           },
    { path: 'addassetrequest',          component: AddAssetRequestComponent         },
    { path: 'assetapproval',            component: AssetApprovalComponent           },
    { path: 'admin-dashboard',          component: AdminDashboardComponent          }
    
];
