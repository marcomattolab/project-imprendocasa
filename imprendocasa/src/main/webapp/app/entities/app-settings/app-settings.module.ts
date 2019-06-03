import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ImprendocasaSharedModule } from 'app/shared';
import {
    AppSettingsComponent,
    AppSettingsDetailComponent,
    AppSettingsUpdateComponent,
    AppSettingsDeletePopupComponent,
    AppSettingsDeleteDialogComponent,
    appSettingsRoute,
    appSettingsPopupRoute
} from './';

const ENTITY_STATES = [...appSettingsRoute, ...appSettingsPopupRoute];

@NgModule({
    imports: [ImprendocasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AppSettingsComponent,
        AppSettingsDetailComponent,
        AppSettingsUpdateComponent,
        AppSettingsDeleteDialogComponent,
        AppSettingsDeletePopupComponent
    ],
    entryComponents: [AppSettingsComponent, AppSettingsUpdateComponent, AppSettingsDeleteDialogComponent, AppSettingsDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaAppSettingsModule {}
