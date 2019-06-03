/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ImprendocasaTestModule } from '../../../test.module';
import { ProfessioneDetailComponent } from 'app/entities/professione/professione-detail.component';
import { Professione } from 'app/shared/model/professione.model';

describe('Component Tests', () => {
    describe('Professione Management Detail Component', () => {
        let comp: ProfessioneDetailComponent;
        let fixture: ComponentFixture<ProfessioneDetailComponent>;
        const route = ({ data: of({ professione: new Professione(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [ProfessioneDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProfessioneDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfessioneDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.professione).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
