export class MailTemplate {
    constructor(
        public id?: number,
        public categoria?: string,
        public codice?: string,
        public oggetto?: string,
        public modello = '',
        public abilitato = false) {
    }
}
