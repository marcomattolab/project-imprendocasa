import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAppSettings } from 'app/shared/model/app-settings.model';
import { AppSettingsService } from './app-settings.service';

@Component({
    selector: 'jhi-app-settings-delete-dialog',
    templateUrl: './app-settings-delete-dialog.component.html'
})
export class AppSettingsDeleteDialogComponent {
    appSettings: IAppSettings;

    constructor(
        private appSettingsService: AppSettingsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.appSettingsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'appSettingsListModification',
                content: 'Deleted an appSettings'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-app-settings-delete-popup',
    template: ''
})
export class AppSettingsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ appSettings }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AppSettingsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.appSettings = appSettings;
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
