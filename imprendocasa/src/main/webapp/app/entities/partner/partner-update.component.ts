import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {Partner} from 'app/shared/model/partner.model';
import {PartnerService} from './partner.service';
import {IProfessione} from 'app/shared/model/professione.model';
import {ProfessioneService} from 'app/entities/professione';
import {Incarico} from 'app/shared/model/incarico.model';
import {IncaricoService} from 'app/entities/incarico';
import {autoComplete, MapsData} from 'app/shared/util/google-utils';
import {MapsAPILoader} from '@agm/core';
import {FormControl} from '@angular/forms';

@Component({
    selector: 'jhi-partner-update',
    templateUrl: './partner-update.component.html'
})
export class PartnerUpdateComponent implements OnInit {
    partner: Partner;
    isSaving: boolean;

    professiones: IProfessione[];

    incaricos: Incarico[];
    reload: string;
    incaricoRef: number;
    checked: boolean;
    exist: boolean;

    @ViewChild('search')
    public searchElementRef: ElementRef;
    public searchControl: FormControl;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private partnerService: PartnerService,
        private incaricoService: IncaricoService,
        private professioneService: ProfessioneService,
        private activatedRoute: ActivatedRoute,
        private mapsAPILoader: MapsAPILoader,
        private router: Router,
        private ngZone: NgZone
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({partner}) => {
            this.partner = partner;
        });
        this.professioneService.query().subscribe(
            (res: HttpResponse<IProfessione[]>) => {
                this.professiones = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.incaricoService.query().subscribe(
            (res: HttpResponse<Incarico[]>) => {
                this.incaricos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.incaricoRef = this.activatedRoute.snapshot.queryParamMap.get('incaricoRef') ? +this.activatedRoute.snapshot.queryParamMap.get('incaricoRef') : null;

        this.reload = localStorage.getItem('reload');
        if (this.reload === '1') {
            this.reload = '0';
            localStorage.setItem('reload', this.reload);
            location.reload();
        }

        this.checked = false;
        this.exist = false;
        this.searchControl = new FormControl();
        this.retrieveCordinate();
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState(newPartner?: Partner) {
        if (newPartner && newPartner.id) {
            console.log('newPartner.id:' + newPartner.id);
            if (this.incaricoRef) {
                const extrasParams = {queryParams: {partnerRef: newPartner.id}};
                const incaricoUrl = this.incaricoRef > 0 ? ['incarico/' + this.incaricoRef + '/edit'] : ['incarico/new'];
                this.router.navigate(incaricoUrl, extrasParams);
            } else {
                window.history.back();
            }
        } else {
            window.history.back();
        }
    }

    retrieveCordinate() {

        this.mapsAPILoader.load().then(() => {
            autoComplete(this.partner, this.searchElementRef, this.ngZone, null, new MapsData());
        });

    }

    save() {

        if (!this.checked) {
            this.partnerService.checkIfExist(this.partner.nome, this.partner.cognome).subscribe(res => {
                this.exist = res.valueOf();
                if (this.exist) {
                    this.checked = true;
                } else {
                    this.executeSave();
                }
            });
        } else {
            this.executeSave();
        }
    }

    executeSave() {

        this.isSaving = true;
        if (this.partner.id !== undefined) {
            this.subscribeToSaveResponse(this.partnerService.update(this.partner));
        } else {
            this.subscribeToSaveResponse(this.partnerService.create(this.partner));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Partner>>) {
        result.subscribe((res: HttpResponse<Partner>) => this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(newPartner: Partner) {
        this.isSaving = false;
        this.previousState(newPartner);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackIncaricoById(index: number, item: Incarico) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
