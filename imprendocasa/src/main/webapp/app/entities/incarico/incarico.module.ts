import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DropdownModule} from 'primeng/components/dropdown/dropdown';
import {MultiSelectModule} from 'primeng/components/multiselect/multiselect';
import {GrowlModule} from 'primeng/components/growl/growl';
import {SelectButtonModule} from 'primeng/components/selectbutton/selectbutton';
import {CambioStatoModalComponent} from './cambio-stato-modal/cambio-stato-modal.component';
import {ImprendocasaSharedModule} from 'app/shared';
import {
    IncaricoComponent,
    IncaricoDeleteDialogComponent,
    IncaricoDeletePopupComponent,
    IncaricoDetailComponent,
    incaricoPopupRoute,
    incaricoRoute,
    IncaricoUpdateComponent
} from './';
import { SelezioneClienteComponent } from './selezione-cliente/selezione-cliente.component';

const ENTITY_STATES = [...incaricoRoute, ...incaricoPopupRoute];

@NgModule({
    // tslint:disable-next-line:max-line-length
    imports: [ImprendocasaSharedModule, RouterModule.forChild(ENTITY_STATES),
        GrowlModule, DropdownModule, SelectButtonModule, MultiSelectModule],
    declarations: [
        IncaricoComponent,
        IncaricoDetailComponent,
        IncaricoUpdateComponent,
        IncaricoDeleteDialogComponent,
        CambioStatoModalComponent,
        IncaricoDeletePopupComponent,
        SelezioneClienteComponent
    ],
    entryComponents: [IncaricoComponent, IncaricoUpdateComponent, IncaricoDeleteDialogComponent, IncaricoDeletePopupComponent, CambioStatoModalComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaIncaricoModule {
}
