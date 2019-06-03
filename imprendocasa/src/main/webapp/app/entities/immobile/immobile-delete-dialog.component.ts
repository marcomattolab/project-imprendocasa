import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {Immobile} from 'app/shared/model/immobile.model';
import {ImmobileService} from './immobile.service';
import {GeolocalizzazioneService} from 'app/entities/geolocalizzazione';

@Component({
    selector: 'jhi-immobile-delete-dialog',
    templateUrl: './immobile-delete-dialog.component.html'
})
export class ImmobileDeleteDialogComponent {
    immobile: Immobile;

    constructor(private immobileService: ImmobileService,
                public activeModal: NgbActiveModal,
                private eventManager: JhiEventManager,
                private geolocalizzazioneService: GeolocalizzazioneService
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.immobileService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'immobileListModification',
                content: 'Deleted an immobile'
            });
            this.activeModal.dismiss(true);
        });

        // if (this.immobile.geolocalizzazioneId) {
        //     this.geolocalizzazioneService.delete(this.immobile.geolocalizzazioneId).subscribe(response => {
        //         this.eventManager.broadcast({
        //             name: 'geolocalizzazioneListModification',
        //             content: 'Deleted an geolocalizzazione'
        //         });
        //         this.activeModal.dismiss(true);
        //     });
        // }
    }
}

@Component({
    selector: 'jhi-immobile-delete-popup',
    template: ''
})
export class ImmobileDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({immobile}) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImmobileDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.immobile = immobile;
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
