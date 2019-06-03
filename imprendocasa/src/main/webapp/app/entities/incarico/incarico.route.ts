import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Incarico } from 'app/shared/model/incarico.model';
import { IncaricoService } from './incarico.service';
import { IncaricoComponent } from './incarico.component';
import { IncaricoDetailComponent } from './incarico-detail.component';
import { IncaricoUpdateComponent } from './incarico-update.component';
import { IncaricoDeletePopupComponent } from './incarico-delete-dialog.component';
import { IncaricoEditRouteAccessService, IncaricoDeleteRouteAccessService } from './incarico-route-access-service';

@Injectable({ providedIn: 'root' })
export class IncaricoResolve implements Resolve<Incarico> {
    constructor(private service: IncaricoService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Incarico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Incarico>) => response.ok),
                map((incarico: HttpResponse<Incarico>) => incarico.body)
            );
        }
        return of(new Incarico());
    }
}

export const incaricoRoute: Routes = [
    {
        path: 'incarico',
        component: IncaricoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            defaultSort: 'id,asc',
            pageTitle: 'imprendocasaApp.incarico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incarico/:id/view',
        component: IncaricoDetailComponent,
        resolve: {
            incarico: IncaricoResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.incarico.detail.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incarico/new',
        component: IncaricoUpdateComponent,
        resolve: {
            incarico: IncaricoResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.incarico.detail.title',
            isNew: true
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incarico/:id/edit',
        component: IncaricoUpdateComponent,
        resolve: {
            incarico: IncaricoResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.incarico.detail.title',
            isNew: false
        },
        canActivate: [UserRouteAccessService, IncaricoEditRouteAccessService]
    }
];

export const incaricoPopupRoute: Routes = [
    {
        path: 'incarico/:id/delete',
        component: IncaricoDeletePopupComponent,
        resolve: {
            incarico: IncaricoResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.incarico.detail.title'
        },
        canActivate: [UserRouteAccessService, IncaricoDeleteRouteAccessService],
        outlet: 'popup'
    }
];
