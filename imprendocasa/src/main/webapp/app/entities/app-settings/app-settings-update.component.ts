import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAppSettings } from 'app/shared/model/app-settings.model';
import { AppSettingsService } from './app-settings.service';

@Component({
    selector: 'jhi-app-settings-update',
    templateUrl: './app-settings-update.component.html'
})
export class AppSettingsUpdateComponent implements OnInit {
    appSettings: IAppSettings;
    isSaving: boolean;

    constructor(private appSettingsService: AppSettingsService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ appSettings }) => {
            this.appSettings = appSettings;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.appSettings.id !== undefined) {
            this.subscribeToSaveResponse(this.appSettingsService.update(this.appSettings));
        } else {
            this.subscribeToSaveResponse(this.appSettingsService.create(this.appSettings));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAppSettings>>) {
        result.subscribe((res: HttpResponse<IAppSettings>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
