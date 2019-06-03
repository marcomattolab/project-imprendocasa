import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {JhiDataUtils} from 'ng-jhipster';

import {Partner} from 'app/shared/model/partner.model';

@Component({
    selector: 'jhi-partner-detail',
    templateUrl: './partner-detail.component.html'
})
export class PartnerDetailComponent implements OnInit {
    partner: Partner;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({partner}) => {
            this.partner = partner;
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
