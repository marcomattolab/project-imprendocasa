import { Route } from '@angular/router';
import { UserRouteAccessService } from '../../core';
import { PiechartComponent } from './piechart.component';

export const piechartRoute: Route = {
    path: 'piechart',
    component: PiechartComponent,
    data: {
        authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
        pageTitle: 'dashboard.piechart.home.title'
    },
    canActivate: [UserRouteAccessService]
};
