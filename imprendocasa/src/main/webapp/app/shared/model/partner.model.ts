import {Incarico} from 'app/shared/model/incarico.model';

export const enum TipoIndirizzo {
    CASA = 'CASA',
    LAVORO = 'LAVORO'
}

/* export const enum Professioni {
    NOTAIO = 'NOTAIO',
    AVVOCATO = 'AVVOCATO',
    ARCHITETTO = 'ARCHITETTO',
    INTERMEDIARIO = 'INTERMEDIARIO',
    AMMINISTRATORE = 'AMMINISTRATORE',
    BANCARIO = 'BANCARIO',
    PORTIERE = 'PORTIERE',
    ESPERTO = 'ESPERTO',
    INGEGNERE = 'INGEGNERE',
    FREELANCE = 'FREELANCE'
} */

export class Partner {
    constructor(
        public id?: number,
        public nome?: string,
        public cognome?: string,
        public codiceFiscale?: string,
        public telefonoFisso?: string,
        public telefonoCellulare?: string,
        public email?: string,
        public tipoIndirizzo?: TipoIndirizzo,
        public indirizzo?: string,
        public cap?: string,
        public regione?: string,
        public provincia?: string,
        public citta?: string,
        public note?: any,
        public abilitato = false,
        public professioneDenominazione?: string,
        public professioneId?: number,
        public incaricos?: Incarico[]
    ) {
    }
}
