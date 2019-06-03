/* tslint:disable max-line-length */
import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {map, take} from 'rxjs/operators';
import * as moment from 'moment';
import {DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {ListaContattiService} from 'app/entities/lista-contatti/lista-contatti.service';
import {EsitoChiamata, ListaContatti} from 'app/shared/model/lista-contatti.model';

describe('Service Tests', () => {
    describe('ListaContatti Service', () => {
        let injector: TestBed;
        let service: ListaContattiService;
        let httpMock: HttpTestingController;
        let elemDefault: ListaContatti;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ListaContattiService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new ListaContatti(0, currentDate, EsitoChiamata.ESEGUITA, 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateTime: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a ListaContatti', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new ListaContatti(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: expected}));
                const req = httpMock.expectOne({method: 'POST'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ListaContatti', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateTime: currentDate.format(DATE_TIME_FORMAT),
                        esito: 'BBBBBB',
                        motivazione: 'BBBBBB',
                        note: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateTime: currentDate
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

            it('should return a list of ListaContatti', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateTime: currentDate.format(DATE_TIME_FORMAT),
                        esito: 'BBBBBB',
                        motivazione: 'BBBBBB',
                        note: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateTime: currentDate
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

            it('should delete a ListaContatti', async () => {
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
