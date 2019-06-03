export interface IProfessione {
    id?: number;
    codice?: string;
    denominazione?: string;
    abilitato?: boolean;
}

export class Professione implements IProfessione {
    constructor(public id?: number, public codice?: string, public denominazione?: string, public abilitato?: boolean) {
        this.abilitato = this.abilitato || false;
    }
}
