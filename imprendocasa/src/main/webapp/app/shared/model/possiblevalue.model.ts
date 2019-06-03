export interface IPossibleValues {
    key: string;
    value?: string;
}

export class PossibleValues implements IPossibleValues {
    constructor(
        public key: string,
        public value?: string
    ) {
        
      }
}
