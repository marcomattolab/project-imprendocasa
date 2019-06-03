import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {ListaContatti} from 'app/shared/model/lista-contatti.model';
import {ListaContattiService} from './lista-contatti.service';
import {Cliente} from 'app/shared/model/cliente.model';
import {ClienteService} from 'app/entities/cliente';
import {Incarico} from 'app/shared/model/incarico.model';
import {IncaricoService} from 'app/entities/incarico';

@Component({
    selector: 'jhi-lista-contatti-update',
    templateUrl: './lista-contatti-update.component.html'
})
export class ListaContattiUpdateComponent implements OnInit {
    listaContatti: ListaContatti;
    isSaving: boolean;

    clientes: Cliente[];

    incaricos: Incarico[];
    dateTime: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private listaContattiService: ListaContattiService,
        private clienteService: ClienteService,
        private incaricoService: IncaricoService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({listaContatti}) => {
            this.listaContatti = listaContatti;
            this.dateTime = this.listaContatti.dateTime != null ? this.listaContatti.dateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.clienteService.query().subscribe(
            (res: HttpResponse<Cliente[]>) => {
                this.clientes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.incaricoService.query().subscribe(
            (res: HttpResponse<Incarico[]>) => {
                this.incaricos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.listaContatti.dateTime = this.dateTime != null ? moment(this.dateTime, DATE_TIME_FORMAT) : null;
        if (this.listaContatti.id !== undefined) {
            this.subscribeToSaveResponse(this.listaContattiService.update(this.listaContatti));
        } else {
            this.subscribeToSaveResponse(this.listaContattiService.create(this.listaContatti));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ListaContatti>>) {
        result.subscribe((res: HttpResponse<ListaContatti>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackClienteById(index: number, item: Cliente) {
        return item.id;
    }

    trackIncaricoById(index: number, item: Incarico) {
        return item.id;
    }
}
