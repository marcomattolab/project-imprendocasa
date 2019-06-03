/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { NotificheService } from 'app/entities/notifiche/notifiche.service';
import { INotifiche, Notifiche, CanaleNotifica, TipoNotifica, CategoriaNotifica, TipoEsito } from 'app/shared/model/notifiche.model';

describe('Service Tests', () => {
    describe('Notifiche Service', () => {
        let injector: TestBed;
        let service: NotificheService;
        let httpMock: HttpTestingController;
        let elemDefault: INotifiche;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(NotificheService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Notifiche(
                0,
                CanaleNotifica.MAIL,
                TipoNotifica.MANUALE,
                CategoriaNotifica.COMPLEANNO,
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                TipoEsito.DA_INVIARE,
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Notifiche', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Notifiche(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Notifiche', async () => {
                const returnedFromService = Object.assign(
                    {
                        canaleNotifica: 'BBBBBB',
                        tipoNotifica: 'BBBBBB',
                        categoriaNotifica: 'BBBBBB',
                        oggettoNotifica: 'BBBBBB',
                        testoNotifica: 'BBBBBB',
                        numeroDestinatari: 1,
                        destinatariNotifica: 'BBBBBB',
                        esitoNotifica: 'BBBBBB',
                        dettagliEsito: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Notifiche', async () => {
                const returnedFromService = Object.assign(
                    {
                        canaleNotifica: 'BBBBBB',
                        tipoNotifica: 'BBBBBB',
                        categoriaNotifica: 'BBBBBB',
                        oggettoNotifica: 'BBBBBB',
                        testoNotifica: 'BBBBBB',
                        numeroDestinatari: 1,
                        destinatariNotifica: 'BBBBBB',
                        esitoNotifica: 'BBBBBB',
                        dettagliEsito: 'BBBBBB'
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
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Notifiche', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
