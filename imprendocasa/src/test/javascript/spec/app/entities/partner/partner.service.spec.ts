/* tslint:disable max-line-length */
import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {map, take} from 'rxjs/operators';
import {PartnerService} from 'app/entities/partner/partner.service';
import {Partner, TipoIndirizzo} from 'app/shared/model/partner.model';

describe('Service Tests', () => {
    describe('Partner Service', () => {
        let injector: TestBed;
        let service: PartnerService;
        let httpMock: HttpTestingController;
        let elemDefault: Partner;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PartnerService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Partner(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                TipoIndirizzo.CASA,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false
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

            it('should create a Partner', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Partner(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: expected}));
                const req = httpMock.expectOne({method: 'POST'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Partner', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        cognome: 'BBBBBB',
                        codiceFiscale: 'BBBBBB',
                        telefonoFisso: 'BBBBBB',
                        telefonoCellulare: 'BBBBBB',
                        email: 'BBBBBB',
                        tipoIndirizzo: 'BBBBBB',
                        indirizzo: 'BBBBBB',
                        cap: 'BBBBBB',
                        regione: 'BBBBBB',
                        provincia: 'BBBBBB',
                        citta: 'BBBBBB',
                        note: 'BBBBBB',
                        abilitato: true
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

            it('should return a list of Partner', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        cognome: 'BBBBBB',
                        codiceFiscale: 'BBBBBB',
                        telefonoFisso: 'BBBBBB',
                        telefonoCellulare: 'BBBBBB',
                        email: 'BBBBBB',
                        tipoIndirizzo: 'BBBBBB',
                        indirizzo: 'BBBBBB',
                        cap: 'BBBBBB',
                        regione: 'BBBBBB',
                        provincia: 'BBBBBB',
                        citta: 'BBBBBB',
                        note: 'BBBBBB',
                        abilitato: true
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

            it('should delete a Partner', async () => {
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
