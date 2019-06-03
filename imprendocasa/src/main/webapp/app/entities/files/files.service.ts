import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFiles } from 'app/shared/model/files.model';

type EntityResponseType = HttpResponse<IFiles>;
type EntityArrayResponseType = HttpResponse<IFiles[]>;

@Injectable({ providedIn: 'root' })
export class FilesService {
    public resourceUrl = SERVER_API_URL + 'api/files';

    constructor(private http: HttpClient) {}

    create(files: IFiles): Observable<EntityResponseType> {
        return this.http.post<IFiles>(this.resourceUrl, files, { observe: 'response' });
    }

    update(files: IFiles): Observable<EntityResponseType> {
        return this.http.put<IFiles>(this.resourceUrl, files, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFiles>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFiles[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
