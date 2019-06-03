/* tslint:disable max-line-length */
import {getTestBed, TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {take} from 'rxjs/operators';
import {TagService} from 'app/entities/tag/tag.service';
import {Tag, TagEnum} from 'app/shared/model/tag.model';

describe('Service Tests', () => {
    describe('Tag Service', () => {
        let injector: TestBed;
        let service: TagService;
        let httpMock: HttpTestingController;
        let elemDefault: Tag;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(TagService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Tag(0, TagEnum.COMMITTENTE, 'AAAAAAA', false);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: elemDefault}));

                const req = httpMock.expectOne({method: 'GET'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Tag', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Tag(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({body: expected}));
                const req = httpMock.expectOne({method: 'POST'});
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Tag', async () => {
                const returnedFromService = Object.assign(
                    {
                        codice: 'BBBBBB',
                        denominazione: 'BBBBBB',
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

            it('should return a list of Tag', async () => {
                const returnedFromService = Object.assign(
                    {
                        codice: 'BBBBBB',
                        denominazione: 'BBBBBB',
                        abilitato: true
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({method: 'GET'});
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Tag', async () => {
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
