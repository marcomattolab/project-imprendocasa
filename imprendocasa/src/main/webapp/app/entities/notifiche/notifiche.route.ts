import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Notifiche } from 'app/shared/model/notifiche.model';
import { NotificheService } from './notifiche.service';
import { NotificheComponent } from './notifiche.component';
import { NotificheDetailComponent } from './notifiche-detail.component';
import { NotificheUpdateComponent } from './notifiche-update.component';
import { NotificheDeletePopupComponent } from './notifiche-delete-dialog.component';
import { INotifiche } from 'app/shared/model/notifiche.model';

@Injectable({ providedIn: 'root' })
export class NotificheResolve implements Resolve<INotifiche> {
    constructor(private service: NotificheService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Notifiche> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Notifiche>) => response.ok),
                map((notifiche: HttpResponse<Notifiche>) => notifiche.body)
            );
        }
        return of(new Notifiche());
    }
}

export const notificheRoute: Routes = [
    {
        path: 'notifiche',
        component: NotificheComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            defaultSort: 'id,asc',
            pageTitle: 'imprendocasaApp.notifiche.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notifiche/:id/view',
        component: NotificheDetailComponent,
        resolve: {
            notifiche: NotificheResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.notifiche.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notifiche/new',
        component: NotificheUpdateComponent,
        resolve: {
            notifiche: NotificheResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.notifiche.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notifiche/:id/edit',
        component: NotificheUpdateComponent,
        resolve: {
            notifiche: NotificheResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.notifiche.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const notifichePopupRoute: Routes = [
    {
        path: 'notifiche/:id/delete',
        component: NotificheDeletePopupComponent,
        resolve: {
            notifiche: NotificheResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.notifiche.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
