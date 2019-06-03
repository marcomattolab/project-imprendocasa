import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ImprendocasaBarchartModule } from './barchart/barchart.module';
import { ImprendocasaDoughnutchartModule } from './doughnutchart/doughnutchart.module';
import { ImprendocasaLinechartModule } from './linechart/linechart.module';
import { ImprendocasaPiechartModule } from './piechart/piechart.module';
import { ImprendocasaPolarareachartModule } from './polarareachart/polarareachart.module';
import { ImprendocasaRadarchartModule } from './radarchart/radarchart.module';

@NgModule({
    imports: [
        ImprendocasaBarchartModule,
        ImprendocasaDoughnutchartModule,
        ImprendocasaLinechartModule,
        ImprendocasaPiechartModule,
        ImprendocasaPolarareachartModule,
        ImprendocasaRadarchartModule,
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaDashboardModule {}
