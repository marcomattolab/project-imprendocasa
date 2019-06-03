import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {Tag} from 'app/shared/model/tag.model';

type EntityResponseType = HttpResponse<Tag>;
type EntityArrayResponseType = HttpResponse<Tag[]>;

@Injectable({providedIn: 'root'})
export class TagService {
    public resourceUrl = SERVER_API_URL + 'api/tags';

    constructor(private http: HttpClient) {
    }

    create(tag: Tag): Observable<EntityResponseType> {
        return this.http.post<Tag>(this.resourceUrl, tag, {observe: 'response'});
    }

    update(tag: Tag): Observable<EntityResponseType> {
        return this.http.put<Tag>(this.resourceUrl, tag, {observe: 'response'});
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Tag>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    query(req?: any): Observable<Tag[]> {
        const options = createRequestOption(req);
        // return this.http.get<Tag[]>(this.resourceUrl, {params: options, observe: 'response'})
        return this.http.get<Tag[]>(this.resourceUrl, {params: options, observe: 'response'})
            .map(res => res.body);
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }
}
