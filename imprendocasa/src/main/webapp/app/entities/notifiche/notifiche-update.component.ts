import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { INotifiche } from 'app/shared/model/notifiche.model';
import { NotificheService } from './notifiche.service';

@Component({
    selector: 'jhi-notifiche-update',
    templateUrl: './notifiche-update.component.html'
})
export class NotificheUpdateComponent implements OnInit {
    notifiche: INotifiche;
    isSaving: boolean;

    constructor(private dataUtils: JhiDataUtils, private notificheService: NotificheService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ notifiche }) => {
            this.notifiche = notifiche;
        });
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.notifiche.id !== undefined) {
            this.subscribeToSaveResponse(this.notificheService.update(this.notifiche));
        } else {
            this.subscribeToSaveResponse(this.notificheService.create(this.notifiche));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INotifiche>>) {
        result.subscribe((res: HttpResponse<INotifiche>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
