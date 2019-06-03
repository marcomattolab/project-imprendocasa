import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {JhiDataUtils} from 'ng-jhipster';

import {Incarico} from 'app/shared/model/incarico.model';

@Component({
    selector: 'jhi-incarico-detail',
    templateUrl: './incarico-detail.component.html'
})
export class IncaricoDetailComponent implements OnInit {
    incarico: Incarico;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({incarico}) => {
            this.incarico = incarico;
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
