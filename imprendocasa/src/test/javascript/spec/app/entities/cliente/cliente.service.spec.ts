/* tslint:disable max-line-length */
import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {map, take} from 'rxjs/operators';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {ClienteService} from 'app/entities/cliente/cliente.service';
import {BooleanStatus, Cliente, TipoMese} from 'app/shared/model/cliente.model';

describe('Service Tests', () => {
    describe('Cliente Service', () => {
        let injector: TestBed;
        let service: ClienteService;
        let httpMock: HttpTestingController;
        let elemDefault: Cliente;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ClienteService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Cliente(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                BooleanStatus.SI,
                currentDate.toString(),
                TipoMese.GENNAIO,
                BooleanStatus.NO,
                moment(),
                TipoMese.AGOSTO,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'miami',
                0,
                '2',
                0,
                2
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataNascita: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find('123')
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: elemDefault}));

                const req = httpMock.expectOne({method: 'GET'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Cliente', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dataNascita: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataNascita: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Cliente(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: expected}));
                const req = httpMock.expectOne({method: 'POST'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Cliente', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        cognome: 'BBBBBB',
                        codiceFiscale: 'BBBBBB',
                        alertCompleanno: 'BBBBBB',
                        dataNascita: currentDate.format(DATE_FORMAT),
                        meseNascita: 'BBBBBB',
                        telefonoFisso: 'BBBBBB',
                        telefonoCellulare: 'BBBBBB',
                        email: 'BBBBBB',
                        indirizzo: 'BBBBBB',
                        cap: 'BBBBBB',
                        regione: 'BBBBBB',
                        provincia: 'BBBBBB',
                        citta: 'BBBBBB',
                        note: 'BBBBBB',
                        codiceAntiriciclaggio: 'BBBBBB',
                        importoProvvigioni: 1,
                        numeroPratiche: 1,
                        numeroSegnalazioni: 1,
                        punteggio: 1,
                        abilitato: true
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dataNascita: currentDate
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

            it('should return a list of Cliente', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        cognome: 'BBBBBB',
                        codiceFiscale: 'BBBBBB',
                        alertCompleanno: 'BBBBBB',
                        dataNascita: currentDate.format(DATE_FORMAT),
                        meseNascita: 'BBBBBB',
                        telefonoFisso: 'BBBBBB',
                        telefonoCellulare: 'BBBBBB',
                        email: 'BBBBBB',
                        indirizzo: 'BBBBBB',
                        cap: 'BBBBBB',
                        regione: 'BBBBBB',
                        provincia: 'BBBBBB',
                        citta: 'BBBBBB',
                        note: 'BBBBBB',
                        codiceAntiriciclaggio: 'BBBBBB',
                        importoProvvigioni: 1,
                        numeroPratiche: 1,
                        numeroSegnalazioni: 1,
                        punteggio: 1,
                        abilitato: true
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataNascita: currentDate
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

            it('should delete a Cliente', async () => {
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
