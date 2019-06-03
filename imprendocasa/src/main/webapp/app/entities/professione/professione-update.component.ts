import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IProfessione } from 'app/shared/model/professione.model';
import { ProfessioneService } from './professione.service';

@Component({
    selector: 'jhi-professione-update',
    templateUrl: './professione-update.component.html'
})
export class ProfessioneUpdateComponent implements OnInit {
    professione: IProfessione;
    isSaving: boolean;

    constructor(private professioneService: ProfessioneService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ professione }) => {
            this.professione = professione;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.professione.id !== undefined) {
            this.subscribeToSaveResponse(this.professioneService.update(this.professione));
        } else {
            this.subscribeToSaveResponse(this.professioneService.create(this.professione));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfessione>>) {
        result.subscribe((res: HttpResponse<IProfessione>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
