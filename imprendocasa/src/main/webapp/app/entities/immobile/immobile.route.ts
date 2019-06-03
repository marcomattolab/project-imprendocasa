import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {Immobile} from 'app/shared/model/immobile.model';
import {ImmobileService} from './immobile.service';
import {ImmobileComponent} from './immobile.component';
import {ImmobileDetailComponent} from './immobile-detail.component';
import {ImmobileUpdateComponent} from './immobile-update.component';
import {ImmobileDeletePopupComponent} from './immobile-delete-dialog.component';
import {ImmobileDeleteRouteAccessService, ImmobileEditRouteAccessService} from './immobile-route-access-service';

@Injectable({providedIn: 'root'})
export class ImmobileResolve implements Resolve<Immobile> {
    constructor(private service: ImmobileService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Immobile> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Immobile>) => response.ok),
                map((immobile: HttpResponse<Immobile>) => immobile.body)
            );
        }
        return of(new Immobile());
    }
}

export const immobileRoute: Routes = [
    {
        path: 'immobile',
        component: ImmobileComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            defaultSort: 'id,asc',
            pageTitle: 'imprendocasaApp.immobile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'immobile/:id/view',
        component: ImmobileDetailComponent,
        resolve: {
            immobile: ImmobileResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.immobile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'immobile/new',
        component: ImmobileUpdateComponent,
        resolve: {
            immobile: ImmobileResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.immobile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'immobile/:id/edit',
        component: ImmobileUpdateComponent,
        resolve: {
            immobile: ImmobileResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.immobile.home.title'
        },
        canActivate: [UserRouteAccessService, ImmobileEditRouteAccessService]
    }
];

export const immobilePopupRoute: Routes = [
    {
        path: 'immobile/:id/delete',
        component: ImmobileDeletePopupComponent,
        resolve: {
            immobile: ImmobileResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.immobile.home.title'
        },
        canActivate: [UserRouteAccessService, ImmobileDeleteRouteAccessService],
        outlet: 'popup'
    }
];
