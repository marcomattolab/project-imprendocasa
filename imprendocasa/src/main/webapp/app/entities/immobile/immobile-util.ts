import {Immobile} from 'app/shared/model/immobile.model';

export function getFormattedCodiceIndirizzoAsString(immobile: Immobile): string {
    const foglio = immobile.foglio || '';
    const particella = immobile.particella ? ` / ${immobile.particella}` : '';
    const sub = immobile.sub ? ` / ${immobile.sub}` : '';

    const foglioNumeroParticellaPart = `(${foglio} ${particella} ${sub})`;

    return `${immobile.indirizzo}, ${immobile.citta} ` + foglioNumeroParticellaPart;
}
