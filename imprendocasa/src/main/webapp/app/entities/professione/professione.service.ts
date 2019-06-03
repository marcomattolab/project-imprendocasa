import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProfessione } from 'app/shared/model/professione.model';

type EntityResponseType = HttpResponse<IProfessione>;
type EntityArrayResponseType = HttpResponse<IProfessione[]>;

@Injectable({ providedIn: 'root' })
export class ProfessioneService {
    public resourceUrl = SERVER_API_URL + 'api/professiones';

    constructor(private http: HttpClient) {}

    create(professione: IProfessione): Observable<EntityResponseType> {
        return this.http.post<IProfessione>(this.resourceUrl, professione, { observe: 'response' });
    }

    update(professione: IProfessione): Observable<EntityResponseType> {
        return this.http.put<IProfessione>(this.resourceUrl, professione, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProfessione>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProfessione[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
