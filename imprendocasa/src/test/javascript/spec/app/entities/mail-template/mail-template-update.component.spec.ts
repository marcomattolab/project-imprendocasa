/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ImprendocasaTestModule } from '../../../test.module';
import { MailTemplateUpdateComponent } from 'app/entities/mail-template/mail-template-update.component';
import { MailTemplateService } from 'app/entities/mail-template/mail-template.service';
import { MailTemplate } from 'app/shared/model/mail-template.model';

describe('Component Tests', () => {
    describe('MailTemplate Management Update Component', () => {
        let comp: MailTemplateUpdateComponent;
        let fixture: ComponentFixture<MailTemplateUpdateComponent>;
        let service: MailTemplateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [MailTemplateUpdateComponent]
            })
                .overrideTemplate(MailTemplateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MailTemplateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MailTemplateService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MailTemplate(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mailTemplate = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MailTemplate();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mailTemplate = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
