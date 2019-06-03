import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ImprendocasaSharedModule} from 'app/shared';
import {
    PartnerComponent,
    PartnerDeleteDialogComponent,
    PartnerDeletePopupComponent,
    PartnerDetailComponent,
    partnerPopupRoute,
    partnerRoute,
    PartnerUpdateComponent
} from './';

const ENTITY_STATES = [...partnerRoute, ...partnerPopupRoute];

@NgModule({
    imports: [ImprendocasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PartnerComponent,
        PartnerDetailComponent,
        PartnerUpdateComponent,
        PartnerDeleteDialogComponent,
        PartnerDeletePopupComponent
    ],
    entryComponents: [PartnerComponent, PartnerUpdateComponent, PartnerDeleteDialogComponent, PartnerDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaPartnerModule {
}
