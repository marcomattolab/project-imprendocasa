import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {map} from 'rxjs/operators';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {Cliente} from 'app/shared/model/cliente.model';

type EntityResponseType = HttpResponse<Cliente>;
type EntityArrayResponseType = HttpResponse<Cliente[]>;

@Injectable({providedIn: 'root'})
export class ClienteService {
    public resourceUrl = SERVER_API_URL + 'api/clientes';

    constructor(private http: HttpClient) {
    }

    create(cliente: Cliente): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cliente);
        return this.http
            .post<Cliente>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(cliente: Cliente): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cliente);
        return this.http
            .put<Cliente>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<Cliente>(`${this.resourceUrl}/${id}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Cliente[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    protected convertDateFromClient(cliente: Cliente): Cliente {
        const copy: Cliente = Object.assign({}, cliente, {
            dataNascita: cliente.dataNascita != null && cliente.dataNascita.isValid() ? cliente.dataNascita.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dataNascita = res.body.dataNascita != null ? moment(res.body.dataNascita) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((cliente: Cliente) => {
                cliente.dataNascita = cliente.dataNascita != null ? moment(cliente.dataNascita) : null;
            });
        }
        return res;
    }

    findByUser(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        const url = this.resourceUrl + '/currentUser';
        return this.http.get<Cliente[]>(url, {params: options, observe: 'response'});
    }

    /* findByNome(nome: string): Observable<EntityArrayResponseType> {
         const options = createRequestOption(nome);
         return this.http.get<Cliente[]>(`${this.resourceUrl}/nome/${nome}`, { params: options, observe: 'response' });
     }

     findByCognome(cognome: string): Observable<EntityArrayResponseType> {
         const options = createRequestOption(cognome);
         return this.http.get<Cliente[]>(`${this.resourceUrl}/cognome/${cognome}`, { params: options, observe: 'response' });
     }

     findByDate(data: moment.Moment): Observable<EntityArrayResponseType> {
         const options = createRequestOption(data);
         return this.http.get<Cliente[]>(`${this.resourceUrl}/data/${data}`, { params: options, observe: 'response' });
     }

      findByNumero(numero: string): Observable<EntityArrayResponseType> {
         const options = createRequestOption(numero);
         return this.http.get<Cliente[]>(`${this.resourceUrl}/numero/${numero}`, { params: options, observe: 'response' });
     }

      findByEmail(email: string): Observable<EntityArrayResponseType> {
         const options = createRequestOption(email);
         return this.http.get<Cliente[]>(`${this.resourceUrl}/email/${email}`, { params: options, observe: 'response' });
     }

      findByIndirizzo(indirizzo: string): Observable<EntityArrayResponseType> {
         const options = createRequestOption(indirizzo);
         return this.http.get<Cliente[]>(`${this.resourceUrl}/indirizzo/${indirizzo}`, { params: options, observe: 'response' });
      }*/

    findByFreeSearch(stringa: string): Observable<EntityArrayResponseType> {
        const options = createRequestOption(stringa);
        return this.http.get<Cliente[]>(`${this.resourceUrl}/freeSearch/${stringa}`, {
            params: options,
            observe: 'response'
        });
    }

}
