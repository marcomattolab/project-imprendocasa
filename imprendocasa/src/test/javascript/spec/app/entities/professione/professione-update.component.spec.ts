/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ImprendocasaTestModule } from '../../../test.module';
import { ProfessioneUpdateComponent } from 'app/entities/professione/professione-update.component';
import { ProfessioneService } from 'app/entities/professione/professione.service';
import { Professione } from 'app/shared/model/professione.model';

describe('Component Tests', () => {
    describe('Professione Management Update Component', () => {
        let comp: ProfessioneUpdateComponent;
        let fixture: ComponentFixture<ProfessioneUpdateComponent>;
        let service: ProfessioneService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [ProfessioneUpdateComponent]
            })
                .overrideTemplate(ProfessioneUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfessioneUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfessioneService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Professione(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.professione = entity;
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
                    const entity = new Professione();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.professione = entity;
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
