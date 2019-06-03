import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {ImprendocasaSharedModule} from 'app/shared';
import {
    FilesComponent,
    FilesDeleteDialogComponent,
    FilesDeletePopupComponent,
    FilesDetailComponent,
    filesPopupRoute,
    filesRoute,
    FilesUpdateComponent
} from './';

const ENTITY_STATES = [...filesRoute, ...filesPopupRoute];

@NgModule({
    imports: [ImprendocasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [FilesComponent, FilesDetailComponent, FilesUpdateComponent, FilesDeleteDialogComponent, FilesDeletePopupComponent],
    entryComponents: [FilesComponent, FilesUpdateComponent, FilesDeleteDialogComponent, FilesDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaFilesModule {
}
