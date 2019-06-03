/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ImprendocasaTestModule } from '../../../test.module';
import { AppSettingsComponent } from 'app/entities/app-settings/app-settings.component';
import { AppSettingsService } from 'app/entities/app-settings/app-settings.service';
import { AppSettings } from 'app/shared/model/app-settings.model';

describe('Component Tests', () => {
    describe('AppSettings Management Component', () => {
        let comp: AppSettingsComponent;
        let fixture: ComponentFixture<AppSettingsComponent>;
        let service: AppSettingsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [AppSettingsComponent],
                providers: []
            })
                .overrideTemplate(AppSettingsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AppSettingsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppSettingsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AppSettings(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.appSettings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
