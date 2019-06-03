import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ImprendocasaSharedModule } from '../../shared';
import { ChartModule } from 'primeng/primeng';

import {
    BarchartComponent,
    barchartRoute
} from './';

const DASHBOARD_STATES = [
    barchartRoute
];

@NgModule({
    imports: [
        ImprendocasaSharedModule,
        ChartModule,
        RouterModule.forRoot(DASHBOARD_STATES, { useHash: true })
    ],
    declarations: [
        BarchartComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaBarchartModule {}
