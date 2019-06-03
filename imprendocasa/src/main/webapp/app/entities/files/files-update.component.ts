import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFiles } from 'app/shared/model/files.model';
import { FilesService } from './files.service';
import { Immobile } from 'app/shared/model/immobile.model';
import { ImmobileService } from 'app/entities/immobile';

@Component({
    selector: 'jhi-files-update',
    templateUrl: './files-update.component.html'
})
export class FilesUpdateComponent implements OnInit {
    files: IFiles;
    isSaving: boolean;

    immobiles: Immobile[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private filesService: FilesService,
        private immobileService: ImmobileService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ files }) => {
            this.files = files;
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
        if (this.files.id !== undefined) {
            this.subscribeToSaveResponse(this.filesService.update(this.files));
        } else {
            this.subscribeToSaveResponse(this.filesService.create(this.files));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFiles>>) {
        result.subscribe((res: HttpResponse<IFiles>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
