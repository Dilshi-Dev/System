import { Component, OnInit } from '@angular/core';
import PerfectScrollbar from 'perfect-scrollbar';

declare const $: any;

// Metadata
export interface RouteInfo {
  path: string;
  title: string;
  type: string;
  icontype: string;
  collapse?: string;
  children?: ChildrenItems[];
}

export interface ChildrenItems {
  path: string;
  title: string;
  ab: string;
  type?: string;
  active: boolean;
}

// Menu Items
export const ROUTES: RouteInfo[] = [
  {
    path: '/dashboard/',
    title: 'Dashboard',
    type: 'link',
    icontype: 'dashboard',
    collapse: 'dashboard',
  },

  {
    path: '/user',
    title: 'Users',
    type: 'sub',
    icontype: 'person',
    collapse: 'user',
    children: [
      { path: 'view', title: 'View Users', ab: 'VU', active: true },
      { path: 'create', title: 'Create Users', ab: 'CU', active: true },
      { path: 'edit', title: 'Edit Users', ab: 'EU', active: false },
    ],
  },

  {
    path: '/booking',
    title: 'Booking',
    type: 'sub',
    icontype: 'supervised_user_circle',
    collapse: 'Booking',
    children: [
      { path: 'view', title: 'View Bookings', ab: 'VB', active: true },
      { path: 'create', title: 'Create Booking', ab: 'CB', active: true },
      // { path: 'edit', title: 'Edit Employees', ab: 'EU', active: false },
    ],
  },

  {
    path: '/employee',
    title: 'Employee',
    type: 'sub',
    icontype: 'supervised_user_circle',
    collapse: 'Employee',
    children: [
      { path: 'create', title: 'Create Employee', ab: 'CE', active: true },
      { path: 'view', title: 'View Employees', ab: 'VE', active: true },
    ],
  },
  {
    path: '/delivery',
    title: 'Delivery',
    type: 'sub',
    icontype: 'supervised_user_circle',
    collapse: 'deliveryelivery',
    children: [
      { path: 'create', title: 'Create Delivery', ab: 'CD', active: true },
      { path: 'view', title: 'View Delivery', ab: 'VD', active: true },
    ],
  },
  {
    path: '/vehicle',
    title: 'Vehicle',
    type: 'sub',
    icontype: 'supervised_user_circle',
    collapse: 'Vehicle',
    children: [
      { path: 'create', title: 'Create Vehicle', ab: 'CV', active: true },
      { path: 'view', title: 'View Vehicle', ab: 'VV', active: true },
    ],
  },
  {
    path: '/addingfood',
    title: 'Addingfood',
    type: 'sub',
    icontype: 'supervised_user_circle',
    collapse: 'Addingfood',
    children: [
      { path: 'create', title: 'Create Addingfood', ab: 'CA', active: true },
      { path: 'view', title: 'View Addingfood', ab: 'VA', active: true },
    ],
  },

  {
    path: '/stock',
    title: 'Stock',
    type: 'sub',
    icontype: 'supervised_user_circle',
    collapse: 'Stock',
    children: [
      { path: 'create', title: 'Create Stock', ab: 'CS', active: true },
      { path: 'view', title: 'View Stock', ab: 'VS', active: true },
    ],
  },
];

@Component({
  selector: 'app-sidebar-cmp',
  templateUrl: 'sidebar.component.html',
})
export class SidebarComponent implements OnInit {
  public menuItems: any[];
  ps: any;
  userName: any;

  constructor() {}

  isMobileMenu() {
    if ($(window).width() > 991) {
      return false;
    }
    return true;
  }

  ngOnInit() {
    if (sessionStorage.getItem('role') === 'CUSTOMER') {
      this.menuItems = [
        {
          path: '/booking',
          title: 'Booking',
          type: 'sub',
          icontype: 'devices_other',
          collapse: 'booking',
          children: [
            {
              path: 'create',
              title: 'Create booking',
              ab: 'CB',
              active: false,
            },
            { path: 'edit', title: 'Edit booking', ab: 'EB', active: true },
          ],
        },
      ];
    } else {
      this.menuItems = ROUTES.filter((menuItem) => menuItem);
    }
    if (window.matchMedia(`(min-width: 960px)`).matches && !this.isMac()) {
      const elemSidebar = <HTMLElement>(
        document.querySelector('.sidebar .sidebar-wrapper')
      );
      this.ps = new PerfectScrollbar(elemSidebar);
    }
  }

  updatePS(): void {
    if (window.matchMedia(`(min-width: 960px)`).matches && !this.isMac()) {
      this.ps.update();
    }
  }

  isMac(): boolean {
    let bool = false;
    if (
      navigator.platform.toUpperCase().indexOf('MAC') >= 0 ||
      navigator.platform.toUpperCase().indexOf('IPAD') >= 0
    ) {
      bool = true;
    }
    return bool;
  }
}
