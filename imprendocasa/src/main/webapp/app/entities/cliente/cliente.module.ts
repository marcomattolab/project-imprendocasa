import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {ImprendocasaSharedModule} from 'app/shared';
import {
    ClienteComponent,
    ClienteDeleteDialogComponent,
    ClienteDeletePopupComponent,
    ClienteDetailComponent,
    clientePopupRoute,
    clienteRoute,
    ClienteUpdateComponent
} from './';
import {MailEtichetteComponent} from './mail-etichette/mail-etichette.component';
import {HttpClientModule} from '@angular/common/http';

const ENTITY_STATES = [...clienteRoute, ...clientePopupRoute];

@NgModule({
    imports: [
        ImprendocasaSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],

    declarations: [
        ClienteComponent,
        ClienteDetailComponent,
        ClienteUpdateComponent,
        ClienteDeleteDialogComponent,
        ClienteDeletePopupComponent,
        MailEtichetteComponent
    ],
    entryComponents: [
        ClienteComponent,
        ClienteUpdateComponent,
        ClienteDeleteDialogComponent,
        ClienteDeletePopupComponent,
        MailEtichetteComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaClienteModule {
}
