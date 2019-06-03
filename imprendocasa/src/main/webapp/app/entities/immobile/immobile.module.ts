import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ButtonModule, GrowlModule} from 'primeng/primeng';
import {FileUploadModule} from 'primeng/components/fileupload/fileupload';
import {CheckboxModule} from 'primeng/components/checkbox/checkbox';
import {WizardModule} from 'primeng-extensions/components/wizard/wizard.js';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DataTableModule} from 'primeng/components/datatable/datatable';
import {DialogModule} from 'primeng/components/dialog/dialog';
import {ContextMenuModule} from 'primeng/components/contextmenu/contextmenu';
import {AgmCoreModule} from '@agm/core';

import {ImprendocasaSharedModule} from 'app/shared';
import {
    ImmobileComponent,
    ImmobileDeleteDialogComponent,
    ImmobileDeletePopupComponent,
    ImmobileDetailComponent,
    immobilePopupRoute,
    immobileRoute,
    ImmobileUpdateComponent
} from './';

const ENTITY_STATES = [...immobileRoute, ...immobilePopupRoute];

@NgModule({
    imports: [
        ImprendocasaSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        CheckboxModule,
        GrowlModule,
        BrowserAnimationsModule,
        FileUploadModule,
        ButtonModule,
        DataTableModule,
        DialogModule,
        ContextMenuModule,
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyD_kgbp3VVJES9KTb7bOLgMDs1GfZ4RRIo',
            clientId: '<mandatory>',
            language: 'it',
            libraries: ['geometry', 'places']
        }),
        WizardModule],
    declarations: [
        ImmobileComponent,
        ImmobileDetailComponent,
        ImmobileUpdateComponent,
        ImmobileDeleteDialogComponent,
        ImmobileDeletePopupComponent
    ],
    entryComponents: [ImmobileComponent, ImmobileUpdateComponent, ImmobileDeleteDialogComponent, ImmobileDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaImmobileModule {
}
