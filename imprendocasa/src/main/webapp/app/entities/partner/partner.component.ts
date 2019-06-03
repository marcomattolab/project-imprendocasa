import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import {Partner} from 'app/shared/model/partner.model';
import {Principal} from 'app/core';

import {checkAndCompileSearchFilterContains, checkAndCompileSearchFilterEquals, ITEMS_PER_PAGE} from 'app/shared';
import {PartnerService} from './partner.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Cliente} from 'app/shared/model/cliente.model';
import {IProfessione, Professione} from 'app/shared/model/professione.model';
import {ProfessioneService} from 'app/entities/professione';

@Component({
    selector: 'jhi-partner',
    templateUrl: './partner.component.html'
})
export class PartnerComponent implements OnInit, OnDestroy {
    currentAccount: any;
    partners: Partner[];
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
    professiones: Professione[];

    completamento: any;
    formPartners: FormGroup;

    constructor(
        private partnerService: PartnerService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private professioneService: ProfessioneService,
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

        this.formPartners = new FormGroup({
            nome: new FormControl(''),
            cognome: new FormControl(''),
            codiceFiscale: new FormControl(''),
            telefonoFisso: new FormControl(''),
            telefonoCellulare: new FormControl(''),
            email: new FormControl(''),
            tipoIndirizzo: new FormControl(''),
            indirizzo: new FormControl(''),
            cap: new FormControl(''),
            regione: new FormControl(''),
            provincia: new FormControl(''),
            citta: new FormControl(''),
            note: new FormControl(''),
            professioneId: new FormControl('')
        });
    }

    loadAll() {
        this.partnerService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<Partner[]>) => this.paginatePartners(res.body, res.headers),
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
        this.router.navigate(['/partner'], {
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
            '/partner',
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
        this.professioneService.query().subscribe(
            (res: HttpResponse<IProfessione[]>) => {
                this.professiones = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.registerChangeInPartners();
        this.initRicerca();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Partner) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInPartners() {
        this.eventSubscriber = this.eventManager.subscribe('partnerListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginatePartners(data: Partner[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.partners = data;
        this.completamento = [];
        this.partners.forEach(partner => {
            this.retrievePercentualeCompletamento(partner);
        });
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    retrievePercentualeCompletamento(current: Partner) {
        const nCampi = 12;
        let i = 0;

        if (current.nome != null) {
            i++;
        }
        if (current.cognome != null) {
            i++;
        }
        // if (current.professione != null) {
        //    i++;
        // }
        if (current.professioneId != null) {
            i++;
        }
        if (current.telefonoCellulare != null) {
            i++;
        }
        if (current.email != null) {
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
        this.completamento[current.id] = (i / nCampi) * 100;
    }

    retrieveCompletamento(id: any) {
        return this.completamento[id].toPrecision(3);
    }

    cerca() {
        const formClienteControls = this.formPartners.controls;
        let searchFilter = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        // filtro nome
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'nome');
        // filtro cognome
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'cognome');
        // filtro codiceFiscale
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'codiceFiscale');
        // filtro provincia
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'provincia');
        // filtro telefonoCellulare
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'telefonoCellulare');
        // filtro telefonoFisso
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'telefonoFisso');
        // filtro email
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'email');
        // filtro tipoIndirizzo
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'tipoIndirizzo');
        // filtro dati cap
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'cap');
        // filtro dati indirizzo
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'indirizzo');
        // filtro dati regione
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'regione');
        // filtro dati provincia
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'provincia');
        // filtro dati citta
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'citta');
        // filtro dati note
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'note');
        // filtro dati note
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'professioneId');

        this.partnerService
            .query(searchFilter)
            .subscribe(
                (res: HttpResponse<Cliente[]>) => this.paginatePartners(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    resetFiltri() {
        this.initRicerca();
    }
}
