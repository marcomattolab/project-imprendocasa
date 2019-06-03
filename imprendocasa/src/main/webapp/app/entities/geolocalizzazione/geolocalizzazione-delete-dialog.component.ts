import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeolocalizzazione } from 'app/shared/model/geolocalizzazione.model';
import { GeolocalizzazioneService } from './geolocalizzazione.service';

@Component({
    selector: 'jhi-geolocalizzazione-delete-dialog',
    templateUrl: './geolocalizzazione-delete-dialog.component.html'
})
export class GeolocalizzazioneDeleteDialogComponent {
    geolocalizzazione: IGeolocalizzazione;

    constructor(
        private geolocalizzazioneService: GeolocalizzazioneService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.geolocalizzazioneService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'geolocalizzazioneListModification',
                content: 'Deleted an geolocalizzazione'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-geolocalizzazione-delete-popup',
    template: ''
})
export class GeolocalizzazioneDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ geolocalizzazione }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GeolocalizzazioneDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.geolocalizzazione = geolocalizzazione;
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
