import { Route } from '@angular/router';
import { UserRouteAccessService } from '../../core';
import { PolarareachartComponent } from './polarareachart.component';

export const polarareachartRoute: Route = {
    path: 'polarareachart',
    component: PolarareachartComponent,
    data: {
        authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
        pageTitle: 'dashboard.polarareachart.home.title'
    },
    canActivate: [UserRouteAccessService]
};
