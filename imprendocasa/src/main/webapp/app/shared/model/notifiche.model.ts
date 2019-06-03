export const enum CanaleNotifica {
    MAIL = 'MAIL',
    SMS = 'SMS',
    FACEBOOK = 'FACEBOOK',
    TWITTER = 'TWITTER',
    INSTAGRAM = 'INSTAGRAM',
    RACCOMANDATA = 'RACCOMANDATA'
}

export const enum TipoNotifica {
    MANUALE = 'MANUALE',
    AUTOMATICO = 'AUTOMATICO'
}

export const enum CategoriaNotifica {
    COMPLEANNO = 'COMPLEANNO',
    FATTURA = 'FATTURA',
    PROMOZIONE = 'PROMOZIONE',
    ALTRO = 'ALTRO'
}

export const enum TipoEsito {
    DA_INVIARE = 'DA_INVIARE',
    INVIATA = 'INVIATA',
    NON_INVIATA = 'NON_INVIATA'
}

export interface INotifiche {
    id?: number;
    canaleNotifica?: CanaleNotifica;
    tipoNotifica?: TipoNotifica;
    categoriaNotifica?: CategoriaNotifica;
    oggettoNotifica?: string;
    testoNotifica?: any;
    numeroDestinatari?: number;
    destinatariNotifica?: any;
    esitoNotifica?: TipoEsito;
    dettagliEsito?: any;
}

export class Notifiche implements INotifiche {
    constructor(
        public id?: number,
        public canaleNotifica?: CanaleNotifica,
        public tipoNotifica?: TipoNotifica,
        public categoriaNotifica?: CategoriaNotifica,
        public oggettoNotifica?: string,
        public testoNotifica?: any,
        public numeroDestinatari?: number,
        public destinatariNotifica?: any,
        public esitoNotifica?: TipoEsito,
        public dettagliEsito?: any
    ) {}
}
