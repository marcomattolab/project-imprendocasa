import {Routes} from '@angular/router';
import {UserRouteAccessService} from 'app/core';
import {TagComponent} from './tag.component';
import {TagDetailComponent} from './tag-detail.component';
import {TagUpdateComponent} from './tag-update.component';
import {TagDeletePopupComponent} from './tag-delete-dialog.component';
import {TagResolve} from 'app/entities/tag/tag-resolve.service';

// @Injectable({providedIn: 'root'})
// export class TagResolve implements Resolve<Tag> {
//     constructor(private service: TagService) {
//     }
//
//     resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Tag> {
//         const id = route.params['id'] ? route.params['id'] : null;
//         if (id) {
//             return this.service.find(id).pipe(
//                 filter((response: HttpResponse<Tag>) => response.ok),
//                 map((tag: HttpResponse<Tag>) => tag.body)
//             );
//         }
//         return of(new Tag());
//     }
// }

export const tagRoute: Routes = [
    {
        path: 'tag',
        component: TagComponent,
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.tag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tag/:id/view',
        component: TagDetailComponent,
        resolve: {
            tag: TagResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.tag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tag/new',
        component: TagUpdateComponent,
        resolve: {
            tag: TagResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.tag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tag/:id/edit',
        component: TagUpdateComponent,
        resolve: {
            tag: TagResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.tag.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tagPopupRoute: Routes = [
    {
        path: 'tag/:id/delete',
        component: TagDeletePopupComponent,
        resolve: {
            tag: TagResolve
        },
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_OPERATOR', 'ROLE_AGENT', 'ROLE_AGENT_PLUS'],
            pageTitle: 'imprendocasaApp.tag.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
