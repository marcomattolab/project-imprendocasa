import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {JhiDataUtils} from 'ng-jhipster';

import {MailTemplate} from 'app/shared/model/mail-template.model';

@Component({
    selector: 'jhi-mail-template-detail',
    templateUrl: './mail-template-detail.component.html'
})
export class MailTemplateDetailComponent implements OnInit {
    mailTemplate: MailTemplate;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mailTemplate }) => {
            this.mailTemplate = mailTemplate;
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
