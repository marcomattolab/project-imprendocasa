import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGeolocalizzazione } from 'app/shared/model/geolocalizzazione.model';
import { GeolocalizzazioneService } from './geolocalizzazione.service';
import { Immobile } from 'app/shared/model/immobile.model';
import { ImmobileService } from 'app/entities/immobile';

@Component({
    selector: 'jhi-geolocalizzazione-update',
    templateUrl: './geolocalizzazione-update.component.html'
})
export class GeolocalizzazioneUpdateComponent implements OnInit {
    geolocalizzazione: IGeolocalizzazione;
    isSaving: boolean;

    immobiles: Immobile[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private geolocalizzazioneService: GeolocalizzazioneService,
        private immobileService: ImmobileService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ geolocalizzazione }) => {
            this.geolocalizzazione = geolocalizzazione;
        });
        this.immobileService.query().subscribe(
            (res: HttpResponse<Immobile[]>) => {
                this.immobiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.geolocalizzazione.id !== undefined) {
            this.subscribeToSaveResponse(this.geolocalizzazioneService.update(this.geolocalizzazione));
        } else {
            this.subscribeToSaveResponse(this.geolocalizzazioneService.create(this.geolocalizzazione));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGeolocalizzazione>>) {
        result.subscribe((res: HttpResponse<IGeolocalizzazione>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackImmobileById(index: number, item: Immobile) {
        return item.id;
    }
}
