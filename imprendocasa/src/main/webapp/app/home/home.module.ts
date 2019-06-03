import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ImprendocasaSharedModule} from 'app/shared';
import {HOME_ROUTE, HomeComponent} from './';
import {AgmCoreModule} from '@agm/core';
import {ChartModule} from 'primeng/chart';

@NgModule({
    imports: [
        ImprendocasaSharedModule,
        ChartModule,
        RouterModule.forChild([HOME_ROUTE]),
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyD_kgbp3VVJES9KTb7bOLgMDs1GfZ4RRIo'
        })
    ],
    declarations: [HomeComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaHomeModule {
}
