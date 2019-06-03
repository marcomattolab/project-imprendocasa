/* tslint:disable max-line-length */
import {ComponentFixture, TestBed} from '@angular/core/testing';
import {of} from 'rxjs';
import {HttpHeaders, HttpResponse} from '@angular/common/http';

import {ImprendocasaTestModule} from '../../../test.module';
import {TagComponent} from 'app/entities/tag/tag.component';
import {TagService} from 'app/entities/tag/tag.service';
import {Tag} from 'app/shared/model/tag.model';

describe('Component Tests', () => {
    describe('Tag Management Component', () => {
        let comp: TagComponent;
        let fixture: ComponentFixture<TagComponent>;
        let service: TagService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ImprendocasaTestModule],
                declarations: [TagComponent],
                providers: []
            })
                .overrideTemplate(TagComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TagComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TagService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            spyOn(service, 'query').and.returnValue(
                of(
                    [new Tag(123)]
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.tags[0]).toEqual(jasmine.objectContaining({id: 123}));
        });
    });
});
