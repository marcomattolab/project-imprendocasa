/* tslint:disable max-line-length */
import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {map, take} from 'rxjs/operators';
import {ImmobileService} from 'app/entities/immobile/immobile.service';
import {Immobile} from 'app/shared/model/immobile.model';

describe('Service Tests', () => {
    describe('Immobile Service', () => {
        let injector: TestBed;
        let service: ImmobileService;
        let httpMock: HttpTestingController;
        let elemDefault: Immobile;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ImmobileService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Immobile(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find('123')
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: elemDefault}));

                const req = httpMock.expectOne({method: 'GET'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Immobile', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Immobile(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: expected}));
                const req = httpMock.expectOne({method: 'POST'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Immobile', async () => {
                const returnedFromService = Object.assign(
                    {
                        codice: 'BBBBBB',
                        descrizione: 'BBBBBB',
                        indirizzo: 'BBBBBB',
                        cap: 'BBBBBB',
                        regione: 'BBBBBB',
                        provincia: 'BBBBBB',
                        citta: 'BBBBBB',
                        note: 'BBBBBB',
                        pathFolder: 'BBBBBB',
                        datiCatastali: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: expected}));
                const req = httpMock.expectOne({method: 'PUT'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Immobile', async () => {
                const returnedFromService = Object.assign(
                    {
                        codice: 'BBBBBB',
                        descrizione: 'BBBBBB',
                        indirizzo: 'BBBBBB',
                        cap: 'BBBBBB',
                        regione: 'BBBBBB',
                        provincia: 'BBBBBB',
                        citta: 'BBBBBB',
                        note: 'BBBBBB',
                        pathFolder: 'BBBBBB',
                        datiCatastali: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
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

            it('should delete a Immobile', async () => {
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
