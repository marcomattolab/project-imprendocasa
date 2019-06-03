import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeolocalizzazione } from 'app/shared/model/geolocalizzazione.model';

@Component({
    selector: 'jhi-geolocalizzazione-detail',
    templateUrl: './geolocalizzazione-detail.component.html'
})
export class GeolocalizzazioneDetailComponent implements OnInit {
    geolocalizzazione: IGeolocalizzazione;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ geolocalizzazione }) => {
            this.geolocalizzazione = geolocalizzazione;
        });
    }

    previousState() {
        window.history.back();
    }
}
