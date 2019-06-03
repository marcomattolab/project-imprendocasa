import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {Partner} from 'app/shared/model/partner.model';
import {PartnerService} from './partner.service';
import {PartnerComponent} from './partner.component';
import {PartnerDetailComponent} from './partner-detail.component';
import {PartnerUpdateComponent} from './partner-update.component';
import {PartnerDeletePopupComponent} from './partner-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class PartnerResolve implements Resolve<Partner> {
    constructor(private service: PartnerService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Partner> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Partner>) => response.ok),
                map((partner: HttpResponse<Partner>) => partner.body)
            );
        }
        return of(new Partner());
    }
}

export const partnerRoute: Routes = [
    {
        path: 'partner',
        component: PartnerComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            defaultSort: 'id,asc',
            pageTitle: 'imprendocasaApp.partner.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'partner/:id/view',
        component: PartnerDetailComponent,
        resolve: {
            partner: PartnerResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.partner.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'partner/new',
        component: PartnerUpdateComponent,
        resolve: {
            partner: PartnerResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.partner.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'partner/:id/edit',
        component: PartnerUpdateComponent,
        resolve: {
            partner: PartnerResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.partner.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const partnerPopupRoute: Routes = [
    {
        path: 'partner/:id/delete',
        component: PartnerDeletePopupComponent,
        resolve: {
            partner: PartnerResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.partner.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
