import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Cliente } from 'app/shared/model/cliente.model';
import { ClienteService } from './cliente.service';
import { ClienteComponent } from './cliente.component';
import { ClienteDetailComponent } from './cliente-detail.component';
import { ClienteUpdateComponent } from './cliente-update.component';
import { ClienteDeletePopupComponent } from './cliente-delete-dialog.component';
import { ClienteEditRouteAccessService, ClienteDeleteRouteAccessService } from './cliente-route-access-service';
import { TagsResolve } from 'app/entities/tag/tags-resolve.service';

@Injectable({ providedIn: 'root' })
export class ClienteResolve implements Resolve<Cliente> {
    constructor(private service: ClienteService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Cliente> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Cliente>) => response.ok),
                map((cliente: HttpResponse<Cliente>) => cliente.body)
            );
        }
        return of(new Cliente());
    }
}

export const clienteRoute: Routes = [
    {
        path: 'cliente',
        component: ClienteComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            defaultSort: 'id,asc',
            pageTitle: 'imprendocasaApp.cliente.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cliente/:id/view',
        component: ClienteDetailComponent,
        resolve: {
            cliente: ClienteResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.cliente.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cliente/new',
        component: ClienteUpdateComponent,
        resolve: {
            cliente: ClienteResolve,
            tags: TagsResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.cliente.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cliente/:id/edit',
        component: ClienteUpdateComponent,
        resolve: {
            cliente: ClienteResolve,
            tags: TagsResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.cliente.home.title'
        },
        canActivate: [UserRouteAccessService, ClienteEditRouteAccessService]
    }
];

export const clientePopupRoute: Routes = [
    {
        path: 'cliente/:id/delete',
        component: ClienteDeletePopupComponent,
        resolve: {
            cliente: ClienteResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.cliente.home.title'
        },
        canActivate: [UserRouteAccessService, ClienteDeleteRouteAccessService],
        outlet: 'popup'
    }
];
