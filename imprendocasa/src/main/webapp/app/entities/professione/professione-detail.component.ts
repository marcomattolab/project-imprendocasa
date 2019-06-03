import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfessione } from 'app/shared/model/professione.model';

@Component({
    selector: 'jhi-professione-detail',
    templateUrl: './professione-detail.component.html'
})
export class ProfessioneDetailComponent implements OnInit {
    professione: IProfessione;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ professione }) => {
            this.professione = professione;
        });
    }

    previousState() {
        window.history.back();
    }
}
