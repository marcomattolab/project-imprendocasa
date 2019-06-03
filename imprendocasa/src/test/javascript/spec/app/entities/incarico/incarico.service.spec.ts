/* tslint:disable max-line-length */
/*import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {map, take} from 'rxjs/operators';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {IncaricoService} from 'app/entities/incarico/incarico.service';
import {
    BooleanStatus,
    CategoriaIncarico,
    Incarico,
    StatoIncarico,
    TipoNegoziazione
} from 'app/shared/model/incarico.model';

describe('Service Tests', () => {
    describe('Incarico Service', () => {
        let injector: TestBed;
        let service: IncaricoService;
        let httpMock: HttpTestingController;
        let elemDefault: Incarico;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(IncaricoService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Incarico(
                0,
                'AAAAAAA',
                TipoNegoziazione.COMPRAVENDITA,
                CategoriaIncarico.COMMERCIALE,
                StatoIncarico.SCADUTO,
                moment(),
                'AAAAAAA',
                "12/05/2018",
                moment(),
                'AAAAAAA',
                BooleanStatus.SI,
                moment(),
                "false",
                moment(),
                0,
                0,
                0,
                0,
                false,
                false,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                currentDate,
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataScadenza: currentDate.format(DATE_FORMAT),
                        dataContatto: currentDate.format(DATE_FORMAT),
                        dataInizioLocazione: currentDate.format(DATE_FORMAT),
                        dataFineLocazione: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: elemDefault}));

                const req = httpMock.expectOne({method: 'GET'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Incarico', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dataScadenza: currentDate.format(DATE_FORMAT),
                        dataContatto: currentDate.format(DATE_FORMAT),
                        dataInizioLocazione: currentDate.format(DATE_FORMAT),
                        dataFineLocazione: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataScadenza: currentDate,
                        dataContatto: currentDate,
                        dataInizioLocazione: currentDate,
                        dataFineLocazione: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Incarico(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: expected}));
                const req = httpMock.expectOne({method: 'POST'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Incarico', async () => {
                const returnedFromService = Object.assign(
                    {
                        riferimento: 'BBBBBB',
                        tipo: 'BBBBBB',
                        stato: 'BBBBBB',
                        dataScadenza: currentDate.format(DATE_FORMAT),
                        agente: 'BBBBBB',
                        agenteDiContatto: 'BBBBBB',
                        dataContatto: currentDate.format(DATE_FORMAT),
                        noteTrattativa: 'BBBBBB',
                        datiFiscali: 'BBBBBB',
                        alertFiscali: 'BBBBBB',
                        alertCortesia: 'BBBBBB',
                        privacy: true,
                        antiriciclaggio: true,
                        prezzoRichiesta: 1,
                        prezzoMinimo: 1,
                        prezzoAcquisto: 1,
                        importoProvvigione: 1,
                        oscuraIncarico: true,
                        flagLocato: true,
                        nomeConduttore: 'BBBBBB',
                        cognomeConduttore: 'BBBBBB',
                        dataInizioLocazione: currentDate.format(DATE_FORMAT),
                        dataFineLocazione: currentDate.format(DATE_FORMAT),
                        noteLocazione: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dataScadenza: currentDate,
                        dataContatto: currentDate,
                        dataInizioLocazione: currentDate,
                        dataFineLocazione: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: expected}));
                const req = httpMock.expectOne({method: 'PUT'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Incarico', async () => {
                const returnedFromService = Object.assign(
                    {
                        riferimento: 'BBBBBB',
                        tipo: 'BBBBBB',
                        stato: 'BBBBBB',
                        dataScadenza: currentDate.format(DATE_FORMAT),
                        agente: 'BBBBBB',
                        agenteDiContatto: 'BBBBBB',
                        dataContatto: currentDate.format(DATE_FORMAT),
                        noteTrattativa: 'BBBBBB',
                        datiFiscali: 'BBBBBB',
                        alertFiscali: 'BBBBBB',
                        alertCortesia: 'BBBBBB',
                        privacy: true,
                        antiriciclaggio: true,
                        prezzoRichiesta: 1,
                        prezzoMinimo: 1,
                        prezzoAcquisto: 1,
                        importoProvvigione: 1,
                        oscuraIncarico: true,
                        flagLocato: true,
                        nomeConduttore: 'BBBBBB',
                        cognomeConduttore: 'BBBBBB',
                        dataInizioLocazione: currentDate.format(DATE_FORMAT),
                        dataFineLocazione: currentDate.format(DATE_FORMAT),
                        noteLocazione: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataScadenza: currentDate,
                        dataContatto: currentDate,
                        dataInizioLocazione: currentDate,
                        dataFineLocazione: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({method: 'GET'});
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Incarico', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({method: 'DELETE'});
                req.flush({status: 200});
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
*/
//to-do