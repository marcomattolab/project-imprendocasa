import {Cliente} from 'app/shared/model/cliente.model';
import {Partner} from 'app/shared/model/partner.model';

export function getFormattedNomeCognomeAsString(cliente: Cliente | Partner): string {
    return `${cliente.nome} ${cliente.cognome}`;
}
