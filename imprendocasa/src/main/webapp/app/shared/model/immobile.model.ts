import { IFiles } from 'app/shared/model/files.model';
import { Incarico } from 'app/shared/model/incarico.model';

export class Immobile {
    constructor(
        public id?: number,
        public codice?: string,
        public agente?: string,
        public descrizione?: any,
        public indirizzo?: string,
        public cap?: string,
        public regione?: string,
        public provincia?: string,
        public citta?: string,
        public note?: any,
        public pathFolder?: string,
        public datiCatastali?: string,
        public foglio?: string,
        public particella?: string,
        public sub?: string,
        public geolocalizzazioneImmobile?: string,
        public geolocalizzazioneId?: number,
        public files?: IFiles[],
        // public incaricoId?: number
        public incaricos?: Incarico[],
        public editAvaiable = true,
        public deleteAvaiable = true
    ) {
    }
}
