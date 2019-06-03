import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {Subscription} from 'rxjs';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import {IGeolocalizzazione} from 'app/shared/model/geolocalizzazione.model';
import {Principal} from 'app/core';
import {GeolocalizzazioneService} from './geolocalizzazione.service';

@Component({
    selector: 'jhi-geolocalizzazione',
    templateUrl: './geolocalizzazione.component.html'
})
export class GeolocalizzazioneComponent implements OnInit, OnDestroy {
    geolocalizzaziones: IGeolocalizzazione[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private geolocalizzazioneService: GeolocalizzazioneService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.geolocalizzazioneService.query().subscribe(
            (res: IGeolocalizzazione[]) => {
                this.geolocalizzaziones = res;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGeolocalizzaziones();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGeolocalizzazione) {
        return item.id;
    }

    registerChangeInGeolocalizzaziones() {
        this.eventSubscriber = this.eventManager.subscribe('geolocalizzazioneListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
