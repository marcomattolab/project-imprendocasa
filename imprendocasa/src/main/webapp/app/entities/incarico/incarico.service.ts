import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {map} from 'rxjs/operators';
import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {Incarico, IncaricoStatutes} from 'app/shared/model/incarico.model';
import {IDashboard} from 'app/shared/model/dashboard.model';
import {Moment} from 'moment';

type EntityResponseType = HttpResponse<Incarico>;
type EntityArrayResponseType = HttpResponse<Incarico[]>;

type EntityDashResponseType = HttpResponse<IDashboard>;
type EntityDashArrayResponseType = HttpResponse<IDashboard[]>;

@Injectable({providedIn: 'root'})
export class IncaricoService {
    public resourceUrl = SERVER_API_URL + 'api/incaricos';
    public dashboardUrl = SERVER_API_URL + 'api/dashboard/incarico';

    constructor(private http: HttpClient) {
    }

    create(incarico: Incarico): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(incarico);
        return this.http.post<Incarico>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.mapIncaricoDates(res)));
    }

    update(incarico: Incarico): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(incarico);
        return this.http.put<Incarico>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.mapIncaricoDates(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Incarico>(`${this.resourceUrl}/${id}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.mapIncaricoDates(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<Incarico[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: EntityArrayResponseType) => this.mapDateArray(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    protected convertDateFromClient(incarico: Incarico): Incarico {
        function convertMomentToJson(date: Moment) {
            return date != null && date.isValid() ? date.toJSON() : null;
        }

        return Object.assign({}, incarico, {
            dataContatto: convertMomentToJson(incarico.dataContatto),
            dataScadenza: convertMomentToJson(incarico.dataScadenza),
            dataAlertFiscali: convertMomentToJson(incarico.dataAlertFiscali),
            dataAlertCortesia: convertMomentToJson(incarico.dataAlertCortesia),
            dataAlertRicorrenza: convertMomentToJson(incarico.dataAlertRicorrenza),
            dataInizioLocazione: convertMomentToJson(incarico.dataInizioLocazione),
            dataFineLocazione: convertMomentToJson(incarico.dataFineLocazione),
        });
    }

    private convertIncaricoDates(incarico: Incarico) {
        if (incarico) {
            incarico.dataScadenza = incarico.dataScadenza != null ? moment(incarico.dataScadenza) : null;
            incarico.dataContatto = incarico.dataContatto != null ? moment(incarico.dataContatto) : null;
            incarico.dataAlertFiscali = incarico.dataAlertFiscali != null ? moment(incarico.dataAlertFiscali) : null;
            incarico.dataAlertCortesia = incarico.dataAlertCortesia != null ? moment(incarico.dataAlertCortesia) : null;
            incarico.dataAlertRicorrenza = incarico.dataAlertRicorrenza != null ? moment(incarico.dataAlertRicorrenza) : null;
            incarico.dataInizioLocazione = incarico.dataInizioLocazione != null ? moment(incarico.dataInizioLocazione) : null;
            incarico.dataFineLocazione = incarico.dataFineLocazione != null ? moment(incarico.dataFineLocazione) : null;
        }
    }

    protected mapIncaricoDates(res: EntityResponseType): EntityResponseType {
        this.convertIncaricoDates(res.body);
        return res;
    }

    protected mapDateArray(res: EntityArrayResponseType): EntityArrayResponseType {
        const incarichi: Array<Incarico> = res.body;
        if (incarichi) {
            incarichi.forEach((incarico: Incarico) => {
                this.convertIncaricoDates(incarico);
            });
        }
        return res;
    }

    findByUser(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        const url = this.resourceUrl + '/currentUser';
        return this.http.get<Incarico[]>(url, {params: options, observe: 'response'});
    }

    retrievePermission(parametro: number): Observable<any> {
        console.log('Sto avviando il servizio con: ' + parametro);
        const params = new HttpParams().set('idIncarico', ' ' + parametro);
        return this.http.get(`${this.resourceUrl}/getPermission`, {params});
    }

    dashboardQuery(req?: any): Observable<EntityDashArrayResponseType> {
        return this.http.get<IDashboard[]>(this.dashboardUrl, {observe: 'response'});
    }

    getStatus(idIncarico: string | number): Observable<IncaricoStatutes> {
        return this.http
            .get<IncaricoStatutes>(this.resourceUrl + '/getStatus/' + idIncarico)
            ;
    }

    changeStatus(incarico: Incarico): Observable<HttpResponse<Incarico>> {
        const copy = this.convertDateFromClient(incarico);
        return this.http.post<HttpResponse<Incarico>>(
            this.resourceUrl + '/changestatus', copy, {observe: 'response'})
            .map(res => res.body);
    }
}
