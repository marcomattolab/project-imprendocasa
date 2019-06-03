import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {MailTemplate} from 'app/shared/model/mail-template.model';
import {MailTemplateService} from './mail-template.service';

@Component({
    selector: 'jhi-mail-template-delete-dialog',
    templateUrl: './mail-template-delete-dialog.component.html'
})
export class MailTemplateDeleteDialogComponent {
    mailTemplate: MailTemplate;

    constructor(
        private mailTemplateService: MailTemplateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mailTemplateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mailTemplateListModification',
                content: 'Deleted an mailTemplate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-mail-template-delete-popup',
    template: ''
})
export class MailTemplateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({mailTemplate}) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MailTemplateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mailTemplate = mailTemplate;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{outlets: {popup: null}}], {
                            replaceUrl: true,
                            queryParamsHandling: 'merge'
                        });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{outlets: {popup: null}}], {
                            replaceUrl: true,
                            queryParamsHandling: 'merge'
                        });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
