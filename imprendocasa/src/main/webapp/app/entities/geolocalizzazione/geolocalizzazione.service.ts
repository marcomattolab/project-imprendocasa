import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IGeolocalizzazione} from 'app/shared/model/geolocalizzazione.model';
import {IGeolocalizzazioneExtended} from 'app/shared/model/geolocalizzazioneExtended.model';

type EntityResponseType = HttpResponse<IGeolocalizzazione>;
type EntityArrayResponseType = HttpResponse<IGeolocalizzazione[]>;

@Injectable({ providedIn: 'root' })
export class GeolocalizzazioneService {
    public resourceUrl = SERVER_API_URL + 'api/geolocalizzaziones';
    public resourceUrlExtended = SERVER_API_URL + 'api/geolocalizzazioneExtended';
    constructor(private http: HttpClient) {}

    create(geolocalizzazione: IGeolocalizzazione): Observable<EntityResponseType> {
        return this.http.post<IGeolocalizzazione>(this.resourceUrl, geolocalizzazione, { observe: 'response' });
    }

    update(geolocalizzazione: IGeolocalizzazione): Observable<EntityResponseType> {
        return this.http.put<IGeolocalizzazione>(this.resourceUrl, geolocalizzazione, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGeolocalizzazione>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<IGeolocalizzazione[]> {
        const options = createRequestOption(req);
        return this.http.get<IGeolocalizzazione[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map(res => res.body);
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    findExtended(req?: any): Observable<IGeolocalizzazioneExtended[]> {
        const options = createRequestOption(req);
        return this.http.get<IGeolocalizzazioneExtended[]>(this.resourceUrlExtended, { params: options, observe: 'response' })
            .map(res => res.body);
    }
}
