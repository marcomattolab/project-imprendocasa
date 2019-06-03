import {Moment} from 'moment';
import {Cliente} from './cliente.model';

export const enum EsitoChiamata {
    ESEGUITA = 'ESEGUITA',
    NON_RAGGIUNGIBILE = 'NON_RAGGIUNGIBILE',
    OCCUPATO = 'OCCUPATO',
    NON_INTERESSATO = 'NON_INTERESSATO'
}

export class ListaContatti {
    constructor(
        public id?: number,
        public dateTime?: Moment,
        public esito?: EsitoChiamata,
        public motivazione?: string,
        public note?: any,
        public clienteCognome?: string,
        public clienteId?: number,
        public incaricoRiferimento?: string,
        public incaricoId?: number
    ) {
    }
}

export class ListaContattiExt extends ListaContatti {
    constructor(
        public id?: number,
        public dateTime?: Moment,
        public esito?: EsitoChiamata,
        public motivazione?: string,
        public note?: any,
        public cliente?: Cliente,
        public incaricoRiferimento?: string,
        public incaricoId?: number
    ) {
        super(
            id,
            dateTime,
            esito,
            motivazione,
            note,
            cliente.cognome,
            cliente.id,
            incaricoRiferimento,
            incaricoId
        );
    }
}
