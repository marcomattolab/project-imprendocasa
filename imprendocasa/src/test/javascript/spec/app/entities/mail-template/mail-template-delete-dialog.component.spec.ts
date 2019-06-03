/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ImprendocasaTestModule } from '../../../test.module';
import { MailTemplateDeleteDialogComponent } from 'app/entities/mail-template/mail-template-delete-dialog.component';
import { MailTemplateService } from 'app/entities/mail-template/mail-template.service';

describe('Component Tests', () => {
    describe('MailTemplate Management Delete Component', () => {
        let comp: MailTemplateDeleteDialogComponent;
        let fixture: ComponentFixture<MailTemplateDeleteDialogComponent>;
        let service: MailTemplateService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [MailTemplateDeleteDialogComponent]
            })
                .overrideTemplate(MailTemplateDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MailTemplateDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MailTemplateService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
