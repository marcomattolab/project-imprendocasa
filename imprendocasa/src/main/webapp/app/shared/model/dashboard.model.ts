export interface IDashboard {
    label?: string;
    value?: number;
}

export class Dashboard implements IDashboard {
    constructor(
        public label?: string,
        public value?: number
    ) {}
}
