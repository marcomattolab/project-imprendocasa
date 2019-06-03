import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable, Subscription} from 'rxjs';
import {JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks} from 'ng-jhipster';
import {FormControl, FormGroup} from '@angular/forms';
import {EsitoChiamata, ListaContatti} from 'app/shared/model/lista-contatti.model';
import {Cliente} from 'app/shared/model/cliente.model';
import {Principal} from 'app/core';
import {ITEMS_PER_PAGE} from 'app/shared';
import {ClienteService} from '../cliente/cliente.service';
import {ListaContattiService} from './lista-contatti.service';
import * as moment from 'moment';
import {toTimestampFine, toTimestampInizio} from 'app/shared/util/date-util';
import {debounceTime, map} from 'rxjs/operators';
import {IncaricoService} from '../incarico/incarico.service';
import {Incarico} from 'app/shared/model/incarico.model';
import {closeSubscription} from 'app/shared/util/handler-util';

@Component({
    selector: 'jhi-lista-contatti',
    templateUrl: './lista-contatti.component.html'
})
export class ListaContattiComponent implements OnInit, OnDestroy {
    currentAccount: any;
    listaContattis: ListaContatti[];
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
    listaContattiForm: FormGroup;
    clientes: Cliente[];
    incaricos: Incarico[];
    private clientiSubscription: Subscription;

    statiChiamata = [
        EsitoChiamata.ESEGUITA.valueOf(),
        EsitoChiamata.NON_RAGGIUNGIBILE.valueOf(),
        EsitoChiamata.OCCUPATO.valueOf(),
        EsitoChiamata.NON_INTERESSATO.valueOf()
    ];

    constructor(
        private clienteService: ClienteService,
        private listaContattiService: ListaContattiService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private incaricoService: IncaricoService,
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

    initFormRicerca() {
        this.listaContattiForm = new FormGroup({
            dataDa: new FormControl(''),
            dataA: new FormControl(''),
            esito: new FormControl(''),
            cliente: new FormControl(''),
            motivazione: new FormControl(''),
            incarico: new FormControl('')
        });
        this.listaContattiForm.updateValueAndValidity();
    }

    loadAll() {
        this.listaContattiService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ListaContatti[]>) => this.paginateListaContattis(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    retrieveIncarichi() {
        this.incaricoService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<Incarico[]>) => this.incaricos = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    searchClientes = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            map(term => term === '' ? []
                : this.clientes.filter(v => v.cognome.toLowerCase().indexOf(term.toLowerCase()) > -1
                    || v.nome.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
        );

    searchIncarico = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            map(term => term === '' ? []
                : this.incaricos.filter(v => v.riferimento.toLowerCase().indexOf(term.toLowerCase()) > -1
                    || v.tipo.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.id > -1).slice(0, 10))
        );

    // clienteFormatter = (x: Cliente) => x ? (x.cognome + ' ' + x.nome + ' (' + x.codiceFiscale + ')') : '';
    clienteFormatter = (x: Cliente) => x ? (x.cognome + ' ' + x.nome) : '';
    incaricoFormatter = (x: Incarico) => x ? (x.riferimento + ' ' + x.tipo + ' (' + x.id + ') ') : '';

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/lista-contatti'], {
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
            '/lista-contatti',
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
        this.registerChangeInListaContattis();

        closeSubscription(this.clientiSubscription);
        this.clientiSubscription = this.clienteService.query().subscribe(
            (res: HttpResponse<Cliente[]>) => this.clientes = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.initFormRicerca();
        this.retrieveIncarichi();

    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ListaContatti) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInListaContattis() {
        this.eventSubscriber = this.eventManager.subscribe('listaContattiListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateListaContattis(data: ListaContatti[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.listaContattis = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    resetFiltri() {
        this.initFormRicerca();
    }

    cerca() {
        const listaContattiFormControls = this.listaContattiForm.controls;
        // filtro data inizio
        let dataInizio: moment.Moment;
        const dataDa = listaContattiFormControls['dataDa'].value;
        if (dataDa) {
            dataInizio = toTimestampInizio(moment(dataDa));
        }

        // filtro data fine
        let dataFine: moment.Moment;
        const dataA = listaContattiFormControls['dataA'].value;
        if (dataA) {
            dataFine = toTimestampFine(moment(dataA));
        }

        // filtro cliente
        let idCliente = '';
        const cliente = listaContattiFormControls['cliente'].value;
        if (cliente) {
            idCliente = cliente.id.toString();
        }

        // filtro cliente
        let idIncarico = '';
        const incarico = listaContattiFormControls['incarico'].value;
        if (incarico) {
            idIncarico = incarico.id.toString();
        }

        // filtro esito
        const esito: String = listaContattiFormControls['esito'].value;

        // filtro motivazione
        const motivazione: String = listaContattiFormControls['motivazione'].value;

        const listaContattiQueryParams = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            'dateTime.greaterOrEqualThan': dataInizio && dataInizio.isValid() ? dataInizio.toJSON() : '',
            'dateTime.lessOrEqualThan': dataFine && dataFine.isValid() ? dataFine.toJSON() : '',
            'esito.equals': esito,
            'clienteId.equals': idCliente,
            'motivazione.contains': motivazione,
            'incaricoId.equals': idIncarico
        };

        this.listaContattiService.query(listaContattiQueryParams).subscribe(
            (res: HttpResponse<ListaContatti[]>) => this.paginateListaContattis(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    getOrderByIcon(param: string) {
        if (param === this.predicate) {
            return this.reverse ? 'fa-sort-up' : 'fa-sort-down';
        } else {
            return 'fa-sort';
        }
    }
}
