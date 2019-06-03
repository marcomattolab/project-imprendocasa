import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Geolocalizzazione } from 'app/shared/model/geolocalizzazione.model';
import { GeolocalizzazioneService } from './geolocalizzazione.service';
import { GeolocalizzazioneComponent } from './geolocalizzazione.component';
import { GeolocalizzazioneDetailComponent } from './geolocalizzazione-detail.component';
import { GeolocalizzazioneUpdateComponent } from './geolocalizzazione-update.component';
import { GeolocalizzazioneDeletePopupComponent } from './geolocalizzazione-delete-dialog.component';
import { IGeolocalizzazione } from 'app/shared/model/geolocalizzazione.model';

@Injectable({ providedIn: 'root' })
export class GeolocalizzazioneResolve implements Resolve<IGeolocalizzazione> {
    constructor(private service: GeolocalizzazioneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Geolocalizzazione> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Geolocalizzazione>) => response.ok),
                map((geolocalizzazione: HttpResponse<Geolocalizzazione>) => geolocalizzazione.body)
            );
        }
        return of(new Geolocalizzazione());
    }
}

export const geolocalizzazioneRoute: Routes = [
    {
        path: 'geolocalizzazione',
        component: GeolocalizzazioneComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.geolocalizzazione.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'geolocalizzazione/:id/view',
        component: GeolocalizzazioneDetailComponent,
        resolve: {
            geolocalizzazione: GeolocalizzazioneResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.geolocalizzazione.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'geolocalizzazione/new',
        component: GeolocalizzazioneUpdateComponent,
        resolve: {
            geolocalizzazione: GeolocalizzazioneResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.geolocalizzazione.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'geolocalizzazione/:id/edit',
        component: GeolocalizzazioneUpdateComponent,
        resolve: {
            geolocalizzazione: GeolocalizzazioneResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.geolocalizzazione.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const geolocalizzazionePopupRoute: Routes = [
    {
        path: 'geolocalizzazione/:id/delete',
        component: GeolocalizzazioneDeletePopupComponent,
        resolve: {
            geolocalizzazione: GeolocalizzazioneResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.geolocalizzazione.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
