import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Professione } from 'app/shared/model/professione.model';
import { ProfessioneService } from './professione.service';
import { ProfessioneComponent } from './professione.component';
import { ProfessioneDetailComponent } from './professione-detail.component';
import { ProfessioneUpdateComponent } from './professione-update.component';
import { ProfessioneDeletePopupComponent } from './professione-delete-dialog.component';
import { IProfessione } from 'app/shared/model/professione.model';

@Injectable({ providedIn: 'root' })
export class ProfessioneResolve implements Resolve<IProfessione> {
    constructor(private service: ProfessioneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Professione> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Professione>) => response.ok),
                map((professione: HttpResponse<Professione>) => professione.body)
            );
        }
        return of(new Professione());
    }
}

export const professioneRoute: Routes = [
    {
        path: 'professione',
        component: ProfessioneComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            defaultSort: 'id,asc',
            pageTitle: 'imprendocasaApp.professione.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'professione/:id/view',
        component: ProfessioneDetailComponent,
        resolve: {
            professione: ProfessioneResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.professione.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'professione/new',
        component: ProfessioneUpdateComponent,
        resolve: {
            professione: ProfessioneResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.professione.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'professione/:id/edit',
        component: ProfessioneUpdateComponent,
        resolve: {
            professione: ProfessioneResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.professione.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const professionePopupRoute: Routes = [
    {
        path: 'professione/:id/delete',
        component: ProfessioneDeletePopupComponent,
        resolve: {
            professione: ProfessioneResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.professione.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
