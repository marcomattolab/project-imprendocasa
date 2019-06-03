/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ImprendocasaTestModule } from '../../../test.module';
import { AppSettingsDeleteDialogComponent } from 'app/entities/app-settings/app-settings-delete-dialog.component';
import { AppSettingsService } from 'app/entities/app-settings/app-settings.service';

describe('Component Tests', () => {
    describe('AppSettings Management Delete Component', () => {
        let comp: AppSettingsDeleteDialogComponent;
        let fixture: ComponentFixture<AppSettingsDeleteDialogComponent>;
        let service: AppSettingsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [AppSettingsDeleteDialogComponent]
            })
                .overrideTemplate(AppSettingsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AppSettingsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppSettingsService);
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
