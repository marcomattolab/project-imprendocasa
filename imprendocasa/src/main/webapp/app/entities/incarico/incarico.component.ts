import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {Incarico, StatoIncarico, TipoNegoziazione} from 'app/shared/model/incarico.model';
import {Principal, User, UserService} from 'app/core';
import {ClienteService} from 'app/entities/cliente';
import {
    checkAndCompileSearchBetween,
    checkAndCompileSearchFilterContains, checkAndCompileSearchFilterEquals,
    checkAndCompileSearchFilterGt,
    checkAndCompileSearchFilterLt,
    ITEMS_PER_PAGE
} from 'app/shared';
import {IncaricoService} from './incarico.service';
import {FormGroup, FormControl} from '@angular/forms';
import {Cliente} from 'app/shared/model/cliente.model';
import {Tag} from 'app/shared/model/tag.model';
import {TagService} from 'app/entities/tag';
import {Partner} from 'app/shared/model/partner.model';
import {PartnerService} from 'app/entities/partner';
import {closeSubscription} from 'app/shared/util/handler-util';

@Component({
    selector: 'jhi-incarico',
    templateUrl: './incarico.component.html'
})
export class IncaricoComponent implements OnInit, OnDestroy {
    currentAccount: any;
    incaricos: Incarico[];
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
    formIncarico: FormGroup;
    tags: Tag[];
    statiIncarico: StatoIncarico[];
    users: User[];
    tempUsers: User[];
    committenti: Cliente[];
    proponenti: Cliente[];
    acquirenti_locatari: Cliente[];
    clienti: Cliente[];
    COMMITTENTE = 1;
    PROPONENTE = 2;
    ACQLOC = 3;
    EMPTY = '';

    completamento: number[];
    partners: Partner[];
    tipiNegoziazione: TipoNegoziazione[];
    private incaricoServiceSubscription: Subscription;
    private usersSubscription: Subscription;

    constructor(
        private incaricoService: IncaricoService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private tagService: TagService,
        private userService: UserService,
        private clienteService: ClienteService,
        private eventManager: JhiEventManager,
        private partnerService: PartnerService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });

        this.retrievePartners();
        this.registerChangeInIncaricos();
        this.initRicerca();
        this.initStatiIncarico();
        this.initTipiNegoziazione();
        this.retrieveUsersAgents();
        this.retrieveClienti();
    }

    private retrieveClienti() {
        this.clienteService.query().subscribe(res => {
            this.clienti = res.body;
            this.retrieveCommittenti();
            this.retrieveProponenti();
            this.retrieveAcquirenteLocatario();
        });

    }

    private retrieveCommittenti() {
        this.committenti = new Array;
        this.clienti.forEach(current => {
            current.tags.forEach(res => {
                if (res.id === this.COMMITTENTE) {
                    this.committenti.push(current);
                }
            });
        });
    }

    private retrieveProponenti() {
        this.proponenti = new Array;
        this.clienti.forEach(current => {
            current.tags.forEach(res => {
                if (res.id === this.PROPONENTE) {
                    this.proponenti.push(current);
                }
            });
        });
    }

    private retrieveAcquirenteLocatario() {
        this.acquirenti_locatari = new Array;
        this.clienti.forEach(current => {
            current.tags.forEach(res => {
                if (res.id === this.ACQLOC) {
                    this.acquirenti_locatari.push(current);
                }
            });
        });
    }

    private retrievePartners() {
        this.partnerService.query().subscribe(
            (res: HttpResponse<Partner[]>) => {
                this.partners = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private retrieveUsersAgents() {
        closeSubscription(this.usersSubscription);
        this.usersSubscription = this.userService.queryAgents().subscribe(
            (res: HttpResponse<User[]>) => {
                this.tempUsers = res.body;
                if (this.users && this.users.length === 1) {
                    this.users = new Array();
                    this.users.push(this.tempUsers[0]);
                } else {
                    this.users = this.tempUsers;
                }
            },
            (res: HttpResponse<any>) => this.onError(res.body)
        );
    }

    private initStatiIncarico() {

        this.statiIncarico = [
            StatoIncarico.BOZZA,
            StatoIncarico.ATTIVO,
            StatoIncarico.CONCLUSO,
            StatoIncarico.INTERROTTO,
            StatoIncarico.SCADUTO
        ];
    }

    private initTipiNegoziazione() {
        this.tipiNegoziazione = [
            TipoNegoziazione.COMPRAVENDITA,
            TipoNegoziazione.LOCAZIONE,
            TipoNegoziazione.PRATICA_TECNICA,
            TipoNegoziazione.PRATICA_LEGALE,
            TipoNegoziazione.MUTUO,
            TipoNegoziazione.SERVIZI_ACCESSORI,
            TipoNegoziazione.ALTRO
        ];
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
        closeSubscription(this.incaricoServiceSubscription);
    }

    initRicerca() {

        this.formIncarico = new FormGroup({
            riferimento: new FormControl(''),
            agente: new FormControl(''),
            partnerId: new FormControl(''),
            tipo: new FormControl(''),
            stato: new FormControl(''),
            dataScadenzaD: new FormControl(''),
            dataScadenzaA: new FormControl(''),
            dataInizioD: new FormControl(''),
            dataInizioA: new FormControl(''),
            prezzoRichiestaD: new FormControl(''),
            prezzoRichiestaA: new FormControl(''),
            prezzoAcquistoD: new FormControl(''),
            prezzoAcquistoA: new FormControl(''),
            importoProvvigioneD: new FormControl(''),
            importoProvvigioneA: new FormControl(''),
            committenteId: new FormControl(''),
            proponenteId: new FormControl(''),
            acquirenteLocatarioId: new FormControl(''),
            categoriaIncarico: new FormControl('')
        });
    }

    loadAll() {
        this.incaricoService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<Incarico[]>) => this.paginateIncaricos(res.body, res.headers),
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
        this.router.navigate(['/incarico'], {
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
            '/incarico',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    trackId(index: number, item: Incarico) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInIncaricos() {
        this.eventSubscriber = this.eventManager.subscribe('incaricoListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateIncaricos(data: Incarico[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.incaricos = data;
        this.completamento = [];
        this.incaricos.forEach(incarico => {
            this.retrievePercentualeCompletamento(incarico);
        });
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    retrievePercentualeCompletamento(current: Incarico) {
        const nCampi = 9;
        let i = 0;

        if (current.riferimento != null) {
            i++;
        }
        if (current.tipo != null) {
            i++;
        }
        if (current.stato != null) {
            i++;
        }
        if (current.agente != null) {
            i++;
        }
        if (current.agenteDiContatto != null) {
            i++;
        }
        if (current.dataContatto != null) {
            i++;
        }
        if (current.noteTrattativa != null) {
            i++;
        }
        if (current.datiFiscali != null) {
            i++;
        }
        /*        if (current.ruolo != null) {
                    i++;
                }*/
        const percentuale = (i / nCampi) * 100;
        this.completamento[current.id] = percentuale;
    }

    retrieveCompletamento(id: any) {
        return this.completamento[id].toPrecision(3);
    }

    cerca() {
        const formClienteControls = this.formIncarico.controls;

        let searchFilter = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };

        // filtro riferimento
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'riferimento');
        // filtro agente
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'agente');
        // filtro partner
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'partnerId');
        // filtro tipo
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'tipo');
        // filtro stato
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'stato');

        // searchFilter = checkAndCompileSearchFilterGt(formClienteControls, searchFilter, 'dataScadenzaD');
        // searchFilter = checkAndCompileSearchFilterLt(formClienteControls, searchFilter, 'dataScadenzaA');
        // filtro dataScadA
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'dataScadenzaD', 'dataScadenzaA', 'dataScadenza');

        // searchFilter =  checkAndCompileSearchFilterGt(formClienteControls, searchFilter, 'prezzoRichiestaD');
        // searchFilter = checkAndCompileSearchFilterLt(formClienteControls, searchFilter, 'prezzoRichiestaA');
        // filtro prezzo richiesta
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'prezzoRichiestaD', 'prezzoRichiestaA', 'prezzoRichiesta');

        // searchFilter = checkAndCompileSearchFilterGt(formClienteControls, searchFilter, 'importoProvvigioneD');
        // searchFilter = checkAndCompileSearchFilterLt(formClienteControls, searchFilter, 'importoProvvigioneA');
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'importoProvvigioneD', 'importoProvvigioneA', 'importoProvvigioni');

        // searchFilter = checkAndCompileSearchFilterGt(formClienteControls, searchFilter, 'prezzoAcquistoD');
        // searchFilter = checkAndCompileSearchFilterLt(formClienteControls, searchFilter, 'prezzoAcquistoA');
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'prezzoAcquistoD', 'prezzoAcquistoA', 'prezzoAcquisto');

        // filtro committenteId
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'committenteId');
        // filtr proponenteId
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'proponenteId');
        // filtro acquirenteLocatoreId
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'acquirenteLocatarioId');

        // filtro data inizio
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'dataInizioD', 'dataInizioA', 'dataInizioLocazione');

        // filtro categoriaIncarico
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'categoriaIncarico');

        closeSubscription(this.incaricoServiceSubscription);
        this.incaricoServiceSubscription = this.incaricoService.query(searchFilter).subscribe(
            (res: HttpResponse<Incarico[]>) => this.paginateIncaricos(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    resetFiltri() {
        this.initRicerca();
    }

    getOrderByIcon(param: string) {
        if (param === this.predicate) {
            return this.reverse ? 'fa-sort-up' : 'fa-sort-down';
        } else {
            return 'fa-sort';
        }
    }

    private onSuccessRetrieveUsers(users: User[]) {
        this.users = users;
    }
}
