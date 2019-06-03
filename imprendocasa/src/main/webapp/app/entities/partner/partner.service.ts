import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {Partner} from 'app/shared/model/partner.model';

type EntityResponseType = HttpResponse<Partner>;
type EntityArrayResponseType = HttpResponse<Partner[]>;

@Injectable({providedIn: 'root'})
export class PartnerService {
    public resourceUrl = SERVER_API_URL + 'api/partners';

    constructor(private http: HttpClient) {
    }

    create(partner: Partner): Observable<EntityResponseType> {
        return this.http.post<Partner>(this.resourceUrl, partner, {observe: 'response'});
    }

    update(partner: Partner): Observable<EntityResponseType> {
        return this.http.put<Partner>(this.resourceUrl, partner, {observe: 'response'});
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<Partner>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    /*checkIfExist(nome: string, cognome: string) {
        console.log('qui');
        const checkresult = this.http.get<boolean>(`${this.resourceUrl}/exist/`);
    }*/

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Partner[]>(this.resourceUrl, {params: options, observe: 'response'});
    }

    checkIfExist(nome: any, cognome: string) {
        return this.http.get<boolean>(`${this.resourceUrl}/exist/${nome}/${cognome}`);
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }
}
