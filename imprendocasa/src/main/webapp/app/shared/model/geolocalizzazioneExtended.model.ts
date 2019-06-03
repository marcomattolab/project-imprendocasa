export const enum StatoIncarico {
    BOZZA = 'BOZZA',
    ATTIVO = 'ATTIVO',
    CONCLUSO = 'CONCLUSO',
    INTERROTTO = 'INTERROTTO',
    SCADUTO = 'SCADUTO'
}

export interface IGeolocalizzazioneExtended {
    idLocazione?: number;
    immobile?: string;
    latitudine?: string;
    longitudine?: string;
    posizioneId?: number;
    statoIncarico?: StatoIncarico;
    riferimentoIncarico?: string,
    idIncarico?: number;
    idImmobile?: number;
    codiceImmobile?: string;
    descrizioneImmobilr?: any;

}

export class GeolocalizzazioneExtended implements IGeolocalizzazioneExtended {
    constructor(
        public idLocazione?: number,
        public immobile?: string,
        public latitudine?: string,
        public longitudine?: string,
        public posizioneId?: number,
        public statoIncarico?: StatoIncarico,
        public riferimentoIncarico?: string,
        public idIncarico?: number,
        public idImmobile?: number,
        public codiceImmobile?: string,
        public descrizioneImmobile?: any
    ) {}
}
