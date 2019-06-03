import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { INotifiche } from 'app/shared/model/notifiche.model';

@Component({
    selector: 'jhi-notifiche-detail',
    templateUrl: './notifiche-detail.component.html'
})
export class NotificheDetailComponent implements OnInit {
    notifiche: INotifiche;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
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
    previousState() {
        window.history.back();
    }
}
