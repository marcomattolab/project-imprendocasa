import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Subscription} from 'rxjs';
import {JhiAlertService, JhiDataUtils, JhiEventManager} from 'ng-jhipster';

import {MailTemplate} from 'app/shared/model/mail-template.model';
import {Principal} from 'app/core';
import {MailTemplateService} from './mail-template.service';
import {FormControl, FormGroup} from '@angular/forms';
import {
    checkAndCompileSearchBetween,
    checkAndCompileSearchFilterContains,
    checkAndCompileSearchFilterEquals
} from 'app/shared';

@Component({
    selector: 'jhi-mail-template',
    templateUrl: './mail-template.component.html'
})
export class MailTemplateComponent implements OnInit, OnDestroy {
    mailTemplates: MailTemplate[];
    currentAccount: any;
    eventSubscriber: Subscription;
    formMail: FormGroup;

    constructor(
        private mailTemplateService: MailTemplateService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.mailTemplateService.query().subscribe(
            (res: MailTemplate[]) => {
                this.mailTemplates = res;
            },
            (error: HttpErrorResponse) => this.onError(error.message)
        );
    }

    initRicerca() {

        this.formMail = new FormGroup({
            codice: new FormControl('')
        });
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMailTemplates();
        this.initRicerca();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: MailTemplate) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInMailTemplates() {
        this.eventSubscriber = this.eventManager.subscribe('mailTemplateListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    getModelloText(mailTemplate: MailTemplate) {
        const htmlContent = mailTemplate.modello;

        const span = document.createElement('span');
        span.innerHTML = htmlContent;
        const result = span.textContent || span.innerText;

        if (span.parentNode) {
            span.parentNode.removeChild(span);
        } else {
            span.remove();
        }

        return result;
    }

    cerca() {
        const formClienteControls = this.formMail.controls;

        let searchFilter = {
        };

        // filtro riferimento
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'codice');

        this.mailTemplateService.query(searchFilter).subscribe(
            (res: MailTemplate[]) => {
                this.mailTemplates = res;
            },
            (error: HttpErrorResponse) => this.onError(error.message)
        );
    }

    resetFiltri() {
        this.initRicerca();
    }
}
