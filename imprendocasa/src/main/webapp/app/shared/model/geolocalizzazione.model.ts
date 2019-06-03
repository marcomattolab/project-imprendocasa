export interface IGeolocalizzazione {
    id?: number;
    immobile?: string;
    latitudine?: string;
    longitudine?: string;
    posizioneId?: number;
}

export class Geolocalizzazione implements IGeolocalizzazione {
    constructor(
        public id?: number,
        public immobile?: string,
        public latitudine?: string,
        public longitudine?: string,
        public posizioneId?: number
    ) {}
}
