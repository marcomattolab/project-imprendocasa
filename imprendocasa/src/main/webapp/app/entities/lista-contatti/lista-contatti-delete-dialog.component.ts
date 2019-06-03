import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {ListaContatti} from 'app/shared/model/lista-contatti.model';
import {ListaContattiService} from './lista-contatti.service';

@Component({
    selector: 'jhi-lista-contatti-delete-dialog',
    templateUrl: './lista-contatti-delete-dialog.component.html'
})
export class ListaContattiDeleteDialogComponent {
    listaContatti: ListaContatti;

    constructor(
        private listaContattiService: ListaContattiService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.listaContattiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'listaContattiListModification',
                content: 'Deleted an listaContatti'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-lista-contatti-delete-popup',
    template: ''
})
export class ListaContattiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({listaContatti}) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ListaContattiDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.listaContatti = listaContatti;
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
