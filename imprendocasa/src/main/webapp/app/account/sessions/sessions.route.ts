import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { SessionsComponent } from './sessions.component';

export const sessionsRoute: Route = {
    path: 'sessions',
    component: SessionsComponent,
    data: {
        authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
        pageTitle: 'global.menu.account.sessions'
    },
    canActivate: [UserRouteAccessService]
};
