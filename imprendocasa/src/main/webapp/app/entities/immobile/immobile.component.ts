import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import {Immobile} from 'app/shared/model/immobile.model';
import {Principal} from 'app/core';

import {checkAndCompileSearchFilterContains, ITEMS_PER_PAGE} from 'app/shared';
import {ImmobileService} from './immobile.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Cliente} from 'app/shared/model/cliente.model';

@Component({
    selector: 'jhi-immobile',
    templateUrl: './immobile.component.html'
})
export class ImmobileComponent implements OnInit, OnDestroy {
    currentAccount: any;
    immobiles: Immobile[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    completamento: number[];
    formImmobile: FormGroup;

    constructor(
        private immobileService: ImmobileService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });

    }

    initRicerca() {

        this.formImmobile = new FormGroup({
            codice: new FormControl(''),
            descrizione: new FormControl(''),
            indirizzo: new FormControl(''),
            regione: new FormControl(''),
            provincia: new FormControl(''),
            citta: new FormControl(''),
            cap: new FormControl(''),
            note: new FormControl(''),
            datiCatastali: new FormControl(''),
            foglio: new FormControl(''),
            sub: new FormControl(''),
            particella: new FormControl('')
        });

    }

    loadAll() {
        const queryParams = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };

        this.immobileService
            .query(queryParams)
            .subscribe(
                (res: HttpResponse<Immobile[]>) => this.paginateImmobiles(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/immobile'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/immobile',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImmobiles();
        this.initRicerca();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Immobile) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInImmobiles() {
        this.eventSubscriber = this.eventManager.subscribe('immobileListModification', response => this.loadAll());
    }

    retrievePercentualeCompletamento(current: Immobile) {
        const nCampi = 8;
        let i = 0;

        if (current.codice != null) {
            i++;
        }
        if (current.descrizione != null) {
            i++;
        }
        if (current.indirizzo != null) {
            i++;
        }
        if (current.cap != null) {
            i++;
        }
        if (current.regione != null) {
            i++;
        }
        if (current.provincia != null) {
            i++;
        }
        if (current.citta != null) {
            i++;
        }
        if (current.note != null) {
            i++;
        }
        console.log('per immobile:' + current.id + 'i:' + i);
        const percentuale = (i / nCampi) * 100;
        this.completamento[current.id] = percentuale;
        console.log(this.completamento);
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateImmobiles(data: Immobile[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.immobiles = data;
        this.completamento = [];
        this.immobiles.forEach(immobile => {
            this.retrievePercentualeCompletamento(immobile);
        });
    }

    retrieveCompletamento(id: any) {
        return this.completamento[id].toPrecision(3);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    cerca() {
        const formClienteControls = this.formImmobile.controls;
        let searchFilter = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };

        // filtro codice
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'codice');
        // filtro descrizione
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'descrizione');
        // filtro regione
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'regione');
        // filtro provincia
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'provincia');
        // filtro indirizzo
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'indirizzo');
        // filtro citta
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'citta');
        // filtro cap
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'cap');
        // filtro note
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'note');
        // filtro dati catastali
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'datiCatastali');
        // filtro particella
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'particella');
        // filtro sub
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'sub');
        // filtro foglio
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'foglio');

        this.immobileService
            .query(searchFilter)
            .subscribe(
                (res: HttpResponse<Cliente[]>) => this.paginateImmobiles(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    resetFiltri() {
        this.initRicerca();
    }
}
