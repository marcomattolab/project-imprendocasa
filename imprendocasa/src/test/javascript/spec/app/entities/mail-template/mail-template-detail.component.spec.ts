/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ImprendocasaTestModule } from '../../../test.module';
import { MailTemplateDetailComponent } from 'app/entities/mail-template/mail-template-detail.component';
import { MailTemplate } from 'app/shared/model/mail-template.model';

describe('Component Tests', () => {
    describe('MailTemplate Management Detail Component', () => {
        let comp: MailTemplateDetailComponent;
        let fixture: ComponentFixture<MailTemplateDetailComponent>;
        const route = ({ data: of({ mailTemplate: new MailTemplate(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [MailTemplateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MailTemplateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MailTemplateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mailTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
