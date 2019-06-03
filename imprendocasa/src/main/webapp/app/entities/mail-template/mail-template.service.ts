import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {MailTemplate} from 'app/shared/model/mail-template.model';

type EntityResponseType = HttpResponse<MailTemplate>;
type EntityArrayResponseType = HttpResponse<MailTemplate[]>;

@Injectable({providedIn: 'root'})
export class MailTemplateService {
    public resourceUrl = SERVER_API_URL + 'api/mail-templates';

    constructor(private http: HttpClient) {
    }

    create(mailTemplate: MailTemplate): Observable<EntityResponseType> {
        return this.http.post<MailTemplate>(this.resourceUrl, mailTemplate, {observe: 'response'});
    }

    update(mailTemplate: MailTemplate): Observable<EntityResponseType> {
        return this.http.put<MailTemplate>(this.resourceUrl, mailTemplate, {observe: 'response'});
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MailTemplate>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    query(req?: any): Observable<MailTemplate[]> {
        const options = createRequestOption(req);
        return this.http.get<MailTemplate[]>(this.resourceUrl, {params: options, observe: 'response'})
            .map(res => res.body);
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }
}
