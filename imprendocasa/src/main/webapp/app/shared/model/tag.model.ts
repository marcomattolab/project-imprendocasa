export enum TagEnum {
    COMMITTENTE = 'COMMITTENTE',
    PROPONENTE = 'PROPONENTE',
    ACQUIRENTE_CONDUTTORE = 'ACQUIRENTE_CONDUTTORE',
    SEGNALATORE = 'SEGNALATORE'
}

export class Tag {
    constructor(
        public id?: number,
        // public codice?: string,
        public codice?: TagEnum,
        public denominazione?: string,
        public abilitato = false) {
    }
}
