import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {MailTemplate} from 'app/shared/model/mail-template.model';
import {MailTemplateService} from './mail-template.service';
import {MailTemplateComponent} from './mail-template.component';
import {MailTemplateDetailComponent} from './mail-template-detail.component';
import {MailTemplateUpdateComponent} from './mail-template-update.component';
import {MailTemplateDeletePopupComponent} from './mail-template-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class MailTemplateResolve implements Resolve<MailTemplate> {
    constructor(private service: MailTemplateService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MailTemplate> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MailTemplate>) => response.ok),
                map((mailTemplate: HttpResponse<MailTemplate>) => mailTemplate.body)
            );
        }
        return of(new MailTemplate());
    }
}

export const mailTemplateRoute: Routes = [
    {
        path: 'mail-template',
        component: MailTemplateComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.mailTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mail-template/:id/view',
        component: MailTemplateDetailComponent,
        resolve: {
            mailTemplate: MailTemplateResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.mailTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mail-template/new',
        component: MailTemplateUpdateComponent,
        resolve: {
            mailTemplate: MailTemplateResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.mailTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mail-template/:id/edit',
        component: MailTemplateUpdateComponent,
        resolve: {
            mailTemplate: MailTemplateResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.mailTemplate.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mailTemplatePopupRoute: Routes = [
    {
        path: 'mail-template/:id/delete',
        component: MailTemplateDeletePopupComponent,
        resolve: {
            mailTemplate: MailTemplateResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.mailTemplate.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
