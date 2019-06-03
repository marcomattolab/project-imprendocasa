import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {JhiDataUtils} from 'ng-jhipster';

import {ListaContatti} from 'app/shared/model/lista-contatti.model';

@Component({
    selector: 'jhi-lista-contatti-detail',
    templateUrl: './lista-contatti-detail.component.html'
})
export class ListaContattiDetailComponent implements OnInit {
    listaContatti: ListaContatti;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({listaContatti}) => {
            this.listaContatti = listaContatti;
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
