import { Route } from '@angular/router';
import { UserRouteAccessService } from '../../core';
import { LinechartComponent } from './linechart.component';

export const linechartRoute: Route = {
    path: 'linechart',
    component: LinechartComponent,
    data: {
        authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
        pageTitle: 'dashboard.linechart.home.title'
    },
    canActivate: [UserRouteAccessService]
};
