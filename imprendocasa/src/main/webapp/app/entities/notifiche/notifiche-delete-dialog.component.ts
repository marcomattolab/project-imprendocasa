import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotifiche } from 'app/shared/model/notifiche.model';
import { NotificheService } from './notifiche.service';

@Component({
    selector: 'jhi-notifiche-delete-dialog',
    templateUrl: './notifiche-delete-dialog.component.html'
})
export class NotificheDeleteDialogComponent {
    notifiche: INotifiche;

    constructor(private notificheService: NotificheService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.notificheService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'notificheListModification',
                content: 'Deleted an notifiche'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-notifiche-delete-popup',
    template: ''
})
export class NotificheDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ notifiche }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NotificheDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.notifiche = notifiche;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
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
