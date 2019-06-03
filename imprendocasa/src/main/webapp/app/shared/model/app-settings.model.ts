export interface IAppSettings {
    id?: number;
    categoria?: string;
    chiave?: string;
    valore?: string;
    abilitato?: boolean;
}

export class AppSettings implements IAppSettings {
    constructor(public id?: number, public categoria?: string, public chiave?: string, public valore?: string, public abilitato?: boolean) {
        this.abilitato = this.abilitato || false;
    }
}
