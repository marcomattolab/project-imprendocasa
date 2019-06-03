/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ImprendocasaTestModule } from '../../../test.module';
import { MailTemplateComponent } from 'app/entities/mail-template/mail-template.component';
import { MailTemplateService } from 'app/entities/mail-template/mail-template.service';
import { MailTemplate } from 'app/shared/model/mail-template.model';

describe('Component Tests', () => {
    describe('MailTemplate Management Component', () => {
        let comp: MailTemplateComponent;
        let fixture: ComponentFixture<MailTemplateComponent>;
        let service: MailTemplateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [MailTemplateComponent],
                providers: []
            })
                .overrideTemplate(MailTemplateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MailTemplateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MailTemplateService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MailTemplate(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.mailTemplates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
