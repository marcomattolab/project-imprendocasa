import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AppSettings } from 'app/shared/model/app-settings.model';
import { AppSettingsService } from './app-settings.service';
import { AppSettingsComponent } from './app-settings.component';
import { AppSettingsDetailComponent } from './app-settings-detail.component';
import { AppSettingsUpdateComponent } from './app-settings-update.component';
import { AppSettingsDeletePopupComponent } from './app-settings-delete-dialog.component';
import { IAppSettings } from 'app/shared/model/app-settings.model';

@Injectable({ providedIn: 'root' })
export class AppSettingsResolve implements Resolve<IAppSettings> {
    constructor(private service: AppSettingsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AppSettings> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AppSettings>) => response.ok),
                map((appSettings: HttpResponse<AppSettings>) => appSettings.body)
            );
        }
        return of(new AppSettings());
    }
}

export const appSettingsRoute: Routes = [
    {
        path: 'app-settings',
        component: AppSettingsComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.appSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'app-settings/:id/view',
        component: AppSettingsDetailComponent,
        resolve: {
            appSettings: AppSettingsResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.appSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'app-settings/new',
        component: AppSettingsUpdateComponent,
        resolve: {
            appSettings: AppSettingsResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.appSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'app-settings/:id/edit',
        component: AppSettingsUpdateComponent,
        resolve: {
            appSettings: AppSettingsResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.appSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const appSettingsPopupRoute: Routes = [
    {
        path: 'app-settings/:id/delete',
        component: AppSettingsDeletePopupComponent,
        resolve: {
            appSettings: AppSettingsResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.appSettings.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
