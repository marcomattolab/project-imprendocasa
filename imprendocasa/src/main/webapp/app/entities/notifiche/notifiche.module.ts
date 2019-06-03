import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ImprendocasaSharedModule } from 'app/shared';
import {
    NotificheComponent,
    NotificheDetailComponent,
    NotificheUpdateComponent,
    NotificheDeletePopupComponent,
    NotificheDeleteDialogComponent,
    notificheRoute,
    notifichePopupRoute
} from './';

const ENTITY_STATES = [...notificheRoute, ...notifichePopupRoute];

@NgModule({
    imports: [ImprendocasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NotificheComponent,
        NotificheDetailComponent,
        NotificheUpdateComponent,
        NotificheDeleteDialogComponent,
        NotificheDeletePopupComponent
    ],
    entryComponents: [NotificheComponent, NotificheUpdateComponent, NotificheDeleteDialogComponent, NotificheDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaNotificheModule {}
