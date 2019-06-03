import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ImprendocasaSharedModule } from 'app/shared';
import {
    GeolocalizzazioneComponent,
    GeolocalizzazioneDetailComponent,
    GeolocalizzazioneUpdateComponent,
    GeolocalizzazioneDeletePopupComponent,
    GeolocalizzazioneDeleteDialogComponent,
    geolocalizzazioneRoute,
    geolocalizzazionePopupRoute
} from './';

const ENTITY_STATES = [...geolocalizzazioneRoute, ...geolocalizzazionePopupRoute];

@NgModule({
    imports: [ImprendocasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GeolocalizzazioneComponent,
        GeolocalizzazioneDetailComponent,
        GeolocalizzazioneUpdateComponent,
        GeolocalizzazioneDeleteDialogComponent,
        GeolocalizzazioneDeletePopupComponent
    ],
    entryComponents: [
        GeolocalizzazioneComponent,
        GeolocalizzazioneUpdateComponent,
        GeolocalizzazioneDeleteDialogComponent,
        GeolocalizzazioneDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaGeolocalizzazioneModule {}
