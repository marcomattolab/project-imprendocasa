import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ImprendocasaSharedModule } from '../../shared';
import { ChartModule } from 'primeng/primeng';

import {
    RadarchartComponent,
    radarchartRoute
} from './';

const DASHBOARD_STATES = [
    radarchartRoute
];

@NgModule({
    imports: [
        ImprendocasaSharedModule,
        ChartModule,
        RouterModule.forRoot(DASHBOARD_STATES, { useHash: true })
    ],
    declarations: [
        RadarchartComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaRadarchartModule {}
