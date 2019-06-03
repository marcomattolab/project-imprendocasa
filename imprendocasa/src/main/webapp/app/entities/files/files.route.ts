import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Files } from 'app/shared/model/files.model';
import { FilesService } from './files.service';
import { FilesComponent } from './files.component';
import { FilesDetailComponent } from './files-detail.component';
import { FilesUpdateComponent } from './files-update.component';
import { FilesDeletePopupComponent } from './files-delete-dialog.component';
import { IFiles } from 'app/shared/model/files.model';

@Injectable({ providedIn: 'root' })
export class FilesResolve implements Resolve<IFiles> {
    constructor(private service: FilesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Files> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Files>) => response.ok),
                map((files: HttpResponse<Files>) => files.body)
            );
        }
        return of(new Files());
    }
}

export const filesRoute: Routes = [
    {
        path: 'files',
        component: FilesComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.files.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'files/:id/view',
        component: FilesDetailComponent,
        resolve: {
            files: FilesResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.files.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'files/new',
        component: FilesUpdateComponent,
        resolve: {
            files: FilesResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.files.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'files/:id/edit',
        component: FilesUpdateComponent,
        resolve: {
            files: FilesResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.files.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const filesPopupRoute: Routes = [
    {
        path: 'files/:id/delete',
        component: FilesDeletePopupComponent,
        resolve: {
            files: FilesResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.files.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
