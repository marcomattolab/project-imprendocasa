import { Route } from '@angular/router';
import { UserRouteAccessService } from '../../core';
import { BarchartComponent } from './barchart.component';

export const barchartRoute: Route = {
    path: 'barchart',
    component: BarchartComponent,
    data: {
        authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
        pageTitle: 'dashboard.barchart.home.title'
    },
    canActivate: [UserRouteAccessService]
};
