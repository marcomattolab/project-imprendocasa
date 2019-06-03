/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ImprendocasaTestModule } from '../../../test.module';
import { ProfessioneDeleteDialogComponent } from 'app/entities/professione/professione-delete-dialog.component';
import { ProfessioneService } from 'app/entities/professione/professione.service';

describe('Component Tests', () => {
    describe('Professione Management Delete Component', () => {
        let comp: ProfessioneDeleteDialogComponent;
        let fixture: ComponentFixture<ProfessioneDeleteDialogComponent>;
        let service: ProfessioneService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [ProfessioneDeleteDialogComponent]
            })
                .overrideTemplate(ProfessioneDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfessioneDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfessioneService);
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
