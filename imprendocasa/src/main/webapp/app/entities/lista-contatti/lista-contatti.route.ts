import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {ListaContatti} from 'app/shared/model/lista-contatti.model';
import {ListaContattiService} from './lista-contatti.service';
import {ListaContattiComponent} from './lista-contatti.component';
import {ListaContattiDetailComponent} from './lista-contatti-detail.component';
import {ListaContattiUpdateComponent} from './lista-contatti-update.component';
import {ListaContattiDeletePopupComponent} from './lista-contatti-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class ListaContattiResolve implements Resolve<ListaContatti> {
    constructor(private service: ListaContattiService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ListaContatti> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ListaContatti>) => response.ok),
                map((listaContatti: HttpResponse<ListaContatti>) => listaContatti.body)
            );
        }
        return of(new ListaContatti());
    }
}

export const listaContattiRoute: Routes = [
    {
        path: 'lista-contatti',
        component: ListaContattiComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            defaultSort: 'id,asc',
            pageTitle: 'imprendocasaApp.listaContatti.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lista-contatti/:id/view',
        component: ListaContattiDetailComponent,
        resolve: {
            listaContatti: ListaContattiResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.listaContatti.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lista-contatti/new',
        component: ListaContattiUpdateComponent,
        resolve: {
            listaContatti: ListaContattiResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.listaContatti.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lista-contatti/:id/edit',
        component: ListaContattiUpdateComponent,
        resolve: {
            listaContatti: ListaContattiResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.listaContatti.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const listaContattiPopupRoute: Routes = [
    {
        path: 'lista-contatti/:id/delete',
        component: ListaContattiDeletePopupComponent,
        resolve: {
            listaContatti: ListaContattiResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.listaContatti.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
