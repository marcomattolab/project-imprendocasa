import {Moment} from 'moment';
import {ListaContatti} from 'app/shared/model//lista-contatti.model';
import {Partner} from 'app/shared/model//partner.model';
import {Cliente} from 'app/shared/model//cliente.model';

export const enum TipoNegoziazione {
    COMPRAVENDITA = 'COMPRAVENDITA',
    LOCAZIONE = 'LOCAZIONE',
    PRATICA_TECNICA = 'PRATICA_TECNICA',
    PRATICA_LEGALE = 'PRATICA_LEGALE',
    MUTUO = 'MUTUO',
    SERVIZI_ACCESSORI = 'SERVIZI_ACCESSORI',
    ALTRO = 'ALTRO'
}

export const enum CategoriaIncarico {
    RESIDENZIALE = 'RESIDENZIALE',
    COMMERCIALE = 'COMMERCIALE'
}

export class IncaricoStatutes {
    constructor(
        public changeStatusEnabled: boolean,
        public id?: number,
        public status?: StatoIncarico,
        public statuses?: Array<StatoIncarico>,
        public errorCode?: string
    ) {
    }
}

export const enum StatoIncarico {
    BOZZA = 'BOZZA',
    ATTIVO = 'ATTIVO',
    CONCLUSO = 'CONCLUSO',
    INTERROTTO = 'INTERROTTO',
    SCADUTO = 'SCADUTO'
}

export const enum BooleanStatus {
    SI = 'SI',
    NO = 'NO'
}

export class Incarico {
    constructor(
        public id?: number,
        public riferimento?: string,
        public tipo?: TipoNegoziazione,
        public categoriaIncarico?: CategoriaIncarico,
        public stato?: StatoIncarico,
        public dataScadenza?: Moment,
        public agente?: string,
        public agenteDiContatto?: string,
        public dataContatto?: Moment,
        public noteTrattativa?: any,
        public datiFiscali?: any,
        public dataAlertFiscali?: Moment,
        public ricorrenzaAlertFiscali?: string,
        public dataAlertCortesia?: Moment,
        public ricorrenzaAlertCortesia?: string,
        public alertRicorrenza?: BooleanStatus,
        public dataAlertRicorrenza?: Moment,
        public ricorrenzaAlertRicorrenza?: string,
        public alertFiscali?: BooleanStatus,
        public alertCortesia?: BooleanStatus,
        public privacy = false,
        public antiriciclaggio = false,
        public privacyAcquirenti?: boolean,
        public antiriciclaggioAcquirenti?: boolean,
        public prezzoRichiesta?: number,
        public prezzoMinimo?: number,
        public prezzoAcquisto?: number,
        public importoProvvigione?: number,
        public importoProvvigioneAcquirenti?: number,
        public oscuraIncarico = false,
        public flagLocato = false,
        public nomeConduttore?: string,
        public cognomeConduttore?: string,
        public canoneCorrisposto?: number,
        public telefonoConduttore?: string,
        public dataInizioLocazione?: Moment,
        public dataFineLocazione?: Moment,
        public noteLocazione?: any,
        public immobileCodice?: string,
        public immobileId?: number,
        public partners?: Partner[],
        public committentes?: Cliente[],
        public proponentes?: Cliente[],
        public acquirenteLocatarios?: Cliente[],
        public segnalatores?: Cliente[],
        public editAvaiable = true,
        public deleteAvaiable = true
    ) {
    }
}
