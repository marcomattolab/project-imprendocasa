import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ImprendocasaSharedModule } from 'app/shared';
import {
    ListaContattiComponent,
    ListaContattiDetailComponent,
    ListaContattiUpdateComponent,
    ListaContattiDeletePopupComponent,
    ListaContattiDeleteDialogComponent,
    listaContattiRoute,
    listaContattiPopupRoute
} from './';

const ENTITY_STATES = [...listaContattiRoute, ...listaContattiPopupRoute];

@NgModule({
    imports: [ImprendocasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ListaContattiComponent,
        ListaContattiDetailComponent,
        ListaContattiUpdateComponent,
        ListaContattiDeleteDialogComponent,
        ListaContattiDeletePopupComponent
    ],
    entryComponents: [
        ListaContattiComponent,
        ListaContattiUpdateComponent,
        ListaContattiDeleteDialogComponent,
        ListaContattiDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaListaContattiModule {}
