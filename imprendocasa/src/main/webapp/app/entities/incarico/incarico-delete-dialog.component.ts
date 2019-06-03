import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {Incarico} from 'app/shared/model/incarico.model';
import {IncaricoService} from './incarico.service';

@Component({
    selector: 'jhi-incarico-delete-dialog',
    templateUrl: './incarico-delete-dialog.component.html'
})
export class IncaricoDeleteDialogComponent {
    incarico: Incarico;

    constructor(private incaricoService: IncaricoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.incaricoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'incaricoListModification',
                content: 'Deleted an incarico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-incarico-delete-popup',
    template: ''
})
export class IncaricoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({incarico}) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(IncaricoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.incarico = incarico;
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
