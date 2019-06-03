import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ImprendocasaSharedModule } from 'app/shared';
import {
    ProfessioneComponent,
    ProfessioneDetailComponent,
    ProfessioneUpdateComponent,
    ProfessioneDeletePopupComponent,
    ProfessioneDeleteDialogComponent,
    professioneRoute,
    professionePopupRoute
} from './';

const ENTITY_STATES = [...professioneRoute, ...professionePopupRoute];

@NgModule({
    imports: [ImprendocasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProfessioneComponent,
        ProfessioneDetailComponent,
        ProfessioneUpdateComponent,
        ProfessioneDeleteDialogComponent,
        ProfessioneDeletePopupComponent
    ],
    entryComponents: [ProfessioneComponent, ProfessioneUpdateComponent, ProfessioneDeleteDialogComponent, ProfessioneDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaProfessioneModule {}
