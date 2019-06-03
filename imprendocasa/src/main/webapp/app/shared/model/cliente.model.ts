import {Moment} from 'moment';
import {ListaContatti} from 'app/shared/model/lista-contatti.model';
import {Incarico} from 'app/shared/model/incarico.model';
import {Tag} from 'app/shared/model/tag.model';

export const enum BooleanStatus {
    SI = 'SI',
    NO = 'NO'
}

export const enum TipoMese {
    GENNAIO = 'GENNAIO',
    FEBBRAIO = 'FEBBRAIO',
    MARZO = 'MARZO',
    APRILE = 'APRILE',
    MAGGIO = 'MAGGIO',
    GIUGNO = 'GIUGNO',
    LUGLIO = 'LUGLIO',
    AGOSTO = 'AGOSTO',
    SETTEMBRE = 'SETTEMBRE',
    OTTOBRE = 'OTTOBRE',
    NOVEMBRE = 'NOVEMBRE',
    DICEMBRE = 'DICEMBRE'
}

export class Cliente {
    // campi esistenti solo a FE
    selected = false;

    constructor(
        public id?: number,
        public nome?: string,
        public cognome?: string,
        public codiceFiscale?: string,
        public titolo?: string,
        public ragioneSociale?: string,
        public agente?: string,
        public alertCompleanno?: BooleanStatus,
        public dataNascita?: Moment,
        public meseNascita?: TipoMese,
        public telefonoFisso?: string,
        public telefonoCellulare?: string,
        public email?: string,
        public indirizzo?: string,
        public cap?: string,
        public regione?: string,
        public provincia?: string,
        public citta?: string,
        public note?: any,
        public codiceAntiriciclaggio?: string,
        public importoProvvigioni?: number,
        public importoProvvigioniDerivate?: number,
        public numeroPratiche?: number,
        public numeroSegnalazioni?: number,
        public punteggio?: number,
        public abilitato = true,
        public listaContattis?: ListaContatti[],
        public tags?: Tag[],
//        public incaricos?: Incarico[], // FIXME
        public incaricoCommittentes?: Incarico[],
        public incaricoProponentes?: Incarico[],
        public incaricoAcquirenteLocatarios?: Incarico[],
        public incaricoSegnalatores?: Incarico[],
        public editAvaiable = true,
        public deleteAvaiable = true
    ) {
    }
}
