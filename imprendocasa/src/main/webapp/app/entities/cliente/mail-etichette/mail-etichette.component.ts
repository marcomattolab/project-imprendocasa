import {Component, Input, OnInit} from '@angular/core';
import {MailTemplate} from 'app/shared/model/mail-template.model';
import {HttpErrorResponse} from '@angular/common/http';
import {MailTemplateService} from 'app/entities/mail-template';
import {JhiAlertService} from 'ng-jhipster';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Cliente} from 'app/shared/model/cliente.model';

@Component({
    selector: 'jhi-mail-etichette',
    templateUrl: './mail-etichette.component.html',
    styles: []
})
export class MailEtichetteComponent implements OnInit {
    mailTemplates: MailTemplate[];

    @Input() selectedCustomers: Array<Cliente>;

    constructor(
        public activeModal: NgbActiveModal,
        private mailTemplateService: MailTemplateService,
        private jhiAlertService: JhiAlertService
    ) {
    }

    ngOnInit() {
        this.mailTemplateService.query().subscribe(
            (res: MailTemplate[]) => {
                this.mailTemplates = res;
            },
            (error: HttpErrorResponse) => this.onError(error.message)
        );
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
