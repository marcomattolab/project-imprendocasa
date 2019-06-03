import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ImprendocasaClienteModule } from './cliente/cliente.module';
import { ImprendocasaImmobileModule } from './immobile/immobile.module';
import { ImprendocasaFilesModule } from './files/files.module';
import { ImprendocasaPartnerModule } from './partner/partner.module';
import { ImprendocasaIncaricoModule } from './incarico/incarico.module';
import { ImprendocasaNotificheModule } from './notifiche/notifiche.module';
import { ImprendocasaListaContattiModule } from './lista-contatti/lista-contatti.module';
import { ImprendocasaGeolocalizzazioneModule } from './geolocalizzazione/geolocalizzazione.module';
import { ImprendocasaMailTemplateModule } from './mail-template/mail-template.module';
import { ImprendocasaAppSettingsModule } from './app-settings/app-settings.module';
import { ImprendocasaTagModule } from './tag/tag.module';
import { ImprendocasaProfessioneModule } from './professione/professione.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ImprendocasaClienteModule,
        ImprendocasaImmobileModule,
        ImprendocasaFilesModule,
        ImprendocasaPartnerModule,
        ImprendocasaIncaricoModule,
        ImprendocasaNotificheModule,
        ImprendocasaListaContattiModule,
        ImprendocasaGeolocalizzazioneModule,
        ImprendocasaMailTemplateModule,
        ImprendocasaAppSettingsModule,
        ImprendocasaTagModule,
        ImprendocasaProfessioneModule
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ImprendocasaEntityModule {}
