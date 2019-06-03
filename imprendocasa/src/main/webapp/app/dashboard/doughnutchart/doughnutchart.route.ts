import { Route } from '@angular/router';
import { UserRouteAccessService } from '../../core';
import { DoughnutchartComponent } from './doughnutchart.component';

export const doughnutchartRoute: Route = {
    path: 'doughnutchart',
    component: DoughnutchartComponent,
    data: {
        authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
        pageTitle: 'dashboard.doughnutchart.home.title'
    },
    canActivate: [UserRouteAccessService]
};
