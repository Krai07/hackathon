import { Component, OnInit } from '@angular/core';


export interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
  roles: any;
  children?: RouteInfo[];
}

export const ROUTES: RouteInfo[] = [
  // { path: '/dashboard', title: 'Dashboard', icon: 'nc-bank', class: '', roles: ['ROLE_SYS_ADMIN'] },
  { 
    path: '/user-dashboard', 
    title: 'Asset ', 
    icon: 'nc-bank', 
    class: '', 
    roles: ['ROLE_USER', 'ROLE_MANAGER','ROLE_DADMIN'],
    children: [
      {
        path: '/addassetrequest',
        title: 'Add Asset Request',
        icon: 'nc-diamond',
        class: '',
        roles: ['ROLE_USER', 'ROLE_MANAGER','ROLE_DADMIN']
      },
      {
        path: '/assetapproval',
        title: 'Asset Approval',
        icon: 'nc-diamond',
        class: '',
        roles: ['ROLE_MANAGER','ROLE_DADMIN']
      }
    ] 
  },
  {
    path: '/sys-admin-dashboard',
    title: 'Assets',
    icon: 'nc-diamond',
    class: '',
    roles: ['ROLE_SYS_ADMIN'],
    children: [
      {
        path: '/addasset',
        title: 'Add Asset',
        icon: 'nc-diamond',
        class: '',
        roles: ['ROLE_SYS_ADMIN']
      },
      {
        path: '/assetrequest',
        title: 'Asset Requests',
        icon: 'nc-box',
        class: '',
        roles: ['ROLE_SYS_ADMIN']
      }
    ],
  },
  {
    path: '/purchaseorder',
    title: 'Procurement',
    icon: 'nc-diamond',
    class: '',
    roles: ['ROLE_SYS_ADMIN'],
    children: [
      {
        path: '/purchaseasset',
        title: 'Purchase Asset',
        icon: 'nc-diamond',
        class: '',
        roles: ['ROLE_SYS_ADMIN']
      }
    ],
  },
  // { path: '/icons', title: 'Icons', icon: 'nc-diamond', class: '', roles: ['ROLE_USER'] },
  { path: '/admin-dashboard', title: 'Delivery', icon: 'nc-diamond', class: '', roles: ['ROLE_ADMIN'] },
  // { path: '/maps', title: 'Maps', icon: 'nc-pin-3', class: '' },
  // { path: '/notifications', title: 'Notifications', icon: 'nc-bell-55', class: '' },
  // { path: '/user', title: 'User Profile', icon: 'nc-single-02', class: '' },
  // { path: '/table', title: 'Table List', icon: 'nc-tile-56', class: '', roles: ['ROLE_USER'] },
  // { path: '/typography',    title: 'Typography',        icon:'nc-caps-small', class: '' },
  // { path: '/upgrade',       title: 'Upgrade to PRO',    icon:'nc-spaceship',  class: 'active-pro' },
];

@Component({
  moduleId: module.id,
  selector: 'sidebar-cmp',
  templateUrl: 'sidebar.component.html',
})

export class SidebarComponent implements OnInit {
  public menuItems: any[];
  ngOnInit() {
    const userRole = localStorage.getItem("role");
    this.menuItems = ROUTES.filter(menuItem => this.filterRoutesByRole(menuItem, userRole));
  }

  private filterRoutesByRole(route: RouteInfo, userRole: string): boolean {
    // If the route has children, filter the children based on the user's role
    if (route.children) {
      route.children = route.children.filter(child => this.filterRoutesByRole(child, userRole));
    }

    // Return true if the route is allowed for the user's role, or if it has children after filtering
    return !route.roles || route.roles.includes(userRole) || (route.children && route.children.length > 0);
  }

  toggleDropdown(menuItem) {
    if (menuItem.children) {
      menuItem.active = !menuItem.active;
      // Reset the active state for other items
      this.menuItems.forEach(item => {
        if (item !== menuItem && item.children) {
          item.active = false;
        }
      });
    } else {
      // When a leaf menu item is clicked, reset the active state for all items
      this.menuItems.forEach(item => {
        item.active = false;
        if (item.children) {
          item.children.forEach(childItem => {
            childItem.active = false;
          });
        }
      });
      menuItem.active = true;
    }
  }

}
