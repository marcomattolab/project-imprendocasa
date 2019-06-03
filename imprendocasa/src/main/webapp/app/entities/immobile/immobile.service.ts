import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {Immobile} from 'app/shared/model/immobile.model';

type EntityResponseType = HttpResponse<Immobile>;
type EntityArrayResponseType = HttpResponse<Immobile[]>;

@Injectable({providedIn: 'root'})
export class ImmobileService {
    public resourceUrl = SERVER_API_URL + 'api/immobiles';

    constructor(private http: HttpClient) {
    }

    create(immobile: Immobile): Observable<EntityResponseType> {
        return this.http.post<Immobile>(this.resourceUrl, immobile, {observe: 'response'});
    }

    update(immobile: Immobile): Observable<EntityResponseType> {
        return this.http.put<Immobile>(this.resourceUrl, immobile, {observe: 'response'});
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<Immobile>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Immobile[]>(this.resourceUrl, {params: options, observe: 'response'});
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    findByUser(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        const url = this.resourceUrl + '/currentUser';
        return this.http.get<Immobile[]>(url, {params: options, observe: 'response'});
    }
}
