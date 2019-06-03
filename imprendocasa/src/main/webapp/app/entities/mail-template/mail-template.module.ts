import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {ImprendocasaSharedModule} from 'app/shared';
import {
    MailTemplateComponent,
    MailTemplateDetailComponent,
    MailTemplateUpdateComponent,
    MailTemplateDeletePopupComponent,
    MailTemplateDeleteDialogComponent,
    mailTemplateRoute,
    mailTemplatePopupRoute
} from './';

import { CKEditorModule } from 'ngx-ckeditor';

const ENTITY_STATES = [...mailTemplateRoute, ...mailTemplatePopupRoute];

@NgModule({
    imports: [
        ImprendocasaSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        CKEditorModule
    ],
    declarations: [
        MailTemplateComponent,
        MailTemplateDetailComponent,
        MailTemplateUpdateComponent,
        MailTemplateDeleteDialogComponent,
        MailTemplateDeletePopupComponent
    ],
    entryComponents: [
        MailTemplateComponent,
        MailTemplateUpdateComponent,
        MailTemplateDeleteDialogComponent,
        MailTemplateDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaMailTemplateModule {
}
