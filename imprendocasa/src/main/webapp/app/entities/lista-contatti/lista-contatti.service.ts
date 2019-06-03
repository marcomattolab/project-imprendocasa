import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {map} from 'rxjs/operators';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {ListaContatti} from 'app/shared/model/lista-contatti.model';

type EntityResponseType = HttpResponse<ListaContatti>;
type EntityArrayResponseType = HttpResponse<ListaContatti[]>;

@Injectable({providedIn: 'root'})
export class ListaContattiService {
    public resourceUrl = SERVER_API_URL + 'api/lista-contattis';

    constructor(private http: HttpClient) {
    }

    create(listaContatti: ListaContatti): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(listaContatti);
        return this.http
            .post<ListaContatti>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(listaContatti: ListaContatti): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(listaContatti);
        return this.http
            .put<ListaContatti>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ListaContatti>(`${this.resourceUrl}/${id}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ListaContatti[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    queryExt(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ListaContatti[]>(this.resourceUrl + '-ext', {params: options, observe: 'response'})
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    protected convertDateFromClient(listaContatti: ListaContatti): ListaContatti {
        const copy: ListaContatti = Object.assign({}, listaContatti, {
            dateTime: listaContatti.dateTime != null && listaContatti.dateTime.isValid() ? listaContatti.dateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateTime = res.body.dateTime != null ? moment(res.body.dateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((listaContatti: ListaContatti) => {
                listaContatti.dateTime = listaContatti.dateTime != null ? moment(listaContatti.dateTime) : null;
            });
        }
        return res;
    }
}
