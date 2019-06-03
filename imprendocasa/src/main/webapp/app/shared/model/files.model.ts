export interface IFiles {
    id?: number;
    nome?: string;
    dimensione?: string;
    estensione?: string;
    immobilePathFolder?: string;
    immobileId?: number;
}

export class Files implements IFiles {
    constructor(
        public id?: number,
        public nome?: string,
        public dimensione?: string,
        public estensione?: string,
        public immobilePathFolder?: string,
        public immobileId?: number
    ) {}
}
