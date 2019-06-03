import { Route } from '@angular/router';
import { UserRouteAccessService } from '../../core';
import { RadarchartComponent } from './radarchart.component';

export const radarchartRoute: Route = {
    path: 'radarchart',
    component: RadarchartComponent,
    data: {
        authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
        pageTitle: 'dashboard.radarchart.home.title'
    },
    canActivate: [UserRouteAccessService]
};
