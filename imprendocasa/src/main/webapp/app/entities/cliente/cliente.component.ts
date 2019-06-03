import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import {Cliente} from 'app/shared/model/cliente.model';
import {Principal, User, UserService} from 'app/core';

import {
    checkAndCompileSearchBetween,
    checkAndCompileSearchFilterContains,
    checkAndCompileSearchFilterEquals,
    ITEMS_PER_PAGE
} from 'app/shared';
import {ClienteService} from './cliente.service';
import {FormControl, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MailEtichetteComponent} from 'app/entities/cliente/mail-etichette/mail-etichette.component';
import {Tag} from 'app/shared/model/tag.model';
import {TagService} from 'app/entities/tag';

@Component({
    selector: 'jhi-cliente',
    templateUrl: './cliente.component.html'
})
export class ClienteComponent implements OnInit, OnDestroy {
    currentAccount: any;
    clientes: Cliente[];

    tags: Tag[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeDataSubscription: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    completamento: number[];
    users: User[];

    formCliente: FormGroup;

    selezionaTuttiClienti = false;
    showIndeterminate = false;

    private selectedCustomersMap: Map<any, Cliente> = new Map<any, Cliente>();

    constructor(
        private clienteService: ClienteService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private tagService: TagService,
        private userService: UserService,
        private eventManager: JhiEventManager,
        private modalService: NgbModal
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeDataSubscription = this.activatedRoute.data.subscribe(data => {
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
        this.registerChangeInClientes();

        this.tagService.query().subscribe(
            (res: Tag[]) => {
                this.tags = res;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.initFormRicerca();
        this.retrieveUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    initFormRicerca() {
        this.formCliente = new FormGroup({
            codiceFiscale: new FormControl(''),
            nome: new FormControl(''),
            cap: new FormControl(''),
            cognome: new FormControl(''),
            telefonoCellulare: new FormControl(''),
            telefonoFisso: new FormControl(''),
            email: new FormControl(''),
            citta: new FormControl(''),
            indirizzo: new FormControl(''),
            tagId: new FormControl(''),
            punteggioD: new FormControl(''),
            punteggioA: new FormControl(''),
            dataD: new FormControl(''),
            dataA: new FormControl(''),
            importoProvvigioniD: new FormControl(''),
            importoProvvigioniA: new FormControl(''),
            numeroPraticheD: new FormControl(''),
            numeroPraticheA: new FormControl(''),
            numeroSegnalazioniD: new FormControl(''),
            numeroSegnalazioniA: new FormControl(''),
        });
        this.formCliente.updateValueAndValidity();

        this.tagService.query().subscribe(
            (res: Tag[]) => {
                this.tags = res;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    loadAll() {
        const queryParams = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };

        this.clienteService
            .query(queryParams)
            .subscribe(
                (res: HttpResponse<Cliente[]>) => this.paginateClientes(res.body, res.headers),
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
        this.router.navigate(['/cliente'], {
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
            '/cliente',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    trackId(index: number, item: Cliente) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInClientes() {
        this.eventSubscriber = this.eventManager.subscribe('clienteListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateClientes(data: Cliente[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.clientes = data;
        this.completamento = [];

        this.showIndeterminate = false;

        this.clientes.forEach(cliente => {
            this.retrievePercentualeCompletamento(cliente);

            if (this.selectedCustomersMap[cliente.id]) {
                this.selectClienteAndSetIndeterminate(cliente);
            }
        });
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    private retrievePercentualeCompletamento(current: Cliente) {
        const nCampi = 12;
        let i = 0;

        if (current.nome != null) {
            i++;
        }
        if (current.cognome != null) {
            i++;
        }
        if (current.dataNascita != null) {
            i++;
        }
        if (current.meseNascita != null) {
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
        const percentuale = (i / nCampi) * 100;
        this.completamento[current.id] = percentuale;
    }

    retrieveCompletamento(id: any) {
        return this.completamento[id].toPrecision(3);
    }

    private retrieveUsers() {
        this.userService.query().subscribe(
            (res: HttpResponse<User[]>) => this.users = res.body,
            (res: HttpResponse<any>) => this.onError(res.body)
        );
    }

    cerca() {
        const formClienteControls = this.formCliente.controls;

        let searchFilter = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };

        // filtro codiceFiscale
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'codiceFiscale');
        // filtro nome
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'nome');
        // filtro cap
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'cap');
        // filtro cognome
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'cognome');
        // filtro telefonoCellulare
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'telefonoCellulare');
        // filtro telefonoFisso
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'telefonoFisso');
        // filtro email
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'email');
        // filtro Indirizzo
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'indirizzo');
        // filtro Citta
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'citta');

        // filtro punteggio Da A
        // searchFilter =  checkAndCompileSearchFilterGt(formClienteControls, searchFilter, 'punteggioD');
        // searchFilter = checkAndCompileSearchFilterLt(formClienteControls, searchFilter, 'punteggioA');
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'punteggioD', 'punteggioA', 'punteggio');

        // filtro provvigione Da A
        // searchFilter = checkAndCompileSearchFilterGt(formClienteControls, searchFilter, 'importoProvvigioniD');
        // searchFilter = checkAndCompileSearchFilterLt(formClienteControls, searchFilter, 'importoProvvigioniA');
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'importoProvvigioniD', 'importoProvvigioniA', 'importoProvvigioni');

        // filtro praticheDa
        // searchFilter = checkAndCompileSearchFilterGt(formClienteControls, searchFilter, 'numeroPraticheD');
        // searchFilter = checkAndCompileSearchFilterLt(formClienteControls, searchFilter, 'numeroPraticheA');
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'numeroPraticheD', 'numeroPraticheA', 'numeroPratiche');

        // filtro Data Nascita Da/A
        // searchFilter = checkAndCompileSearchFilterGt(formClienteControls, searchFilter, 'dataD');
        // searchFilter = checkAndCompileSearchFilterLt(formClienteControls, searchFilter, 'dataA');
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'dataD', 'dataA', 'dataNascita');

        // filtro numero segnalazioni Da/A
        searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'numeroSegnalazioniD', 'numeroSegnalazioniA', 'numeroSegnalazioni');

        // filtro tag
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'tagId');
        this.clienteService
            .query(searchFilter)
            .subscribe(
                (res: HttpResponse<Cliente[]>) => this.paginateClientes(res.body, res.headers),
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

    resetFiltri() {
        this.initFormRicerca();
    }

    selectClienteAndSetIndeterminate(clienteToSelect: Cliente) {
        this.showIndeterminate = false;
        this.selezionaTuttiClienti = false;

        clienteToSelect.selected = Boolean(!clienteToSelect.selected);

        this.addOrRemoveSelectedCustomer(clienteToSelect);

        for (const cliente of this.clientes) {
            if (Boolean(cliente.selected) !== Boolean(clienteToSelect.selected)) {
                this.showIndeterminate = true;
                return;
            }
        }

        this.selezionaTuttiClienti = Boolean(clienteToSelect.selected);
    }

    private addOrRemoveSelectedCustomer(clienteToSelect: Cliente) {
        if (clienteToSelect.selected) {
            this.selectedCustomersMap[clienteToSelect.id] = clienteToSelect;
        } else {
            delete this.selectedCustomersMap[clienteToSelect.id];
        }
    }

    selectAll(previousChoice) {
        this.showIndeterminate = false;

        this.selezionaTuttiClienti = !previousChoice;
        this.clientes.forEach(cliente => {
            cliente.selected = Boolean(this.selezionaTuttiClienti);
            this.addOrRemoveSelectedCustomer(cliente);
        });
    }

    getSelectedCustomersAsArray(): Array<Cliente> {
        const result = [];
        Object.keys(this.selectedCustomersMap).forEach(key =>
            result.push(this.selectedCustomersMap[key])
        );

        return result;
    }

    noCustomerSelected(): boolean {
        return this.getSelectedCustomersAsArray().length === 0;
    }

    openInvioMailEtichette() {
        const modalRef = this.modalService.open(MailEtichetteComponent, {size: 'lg'});
        modalRef.componentInstance.selectedCustomers = this.getSelectedCustomersAsArray();
    }

    checkEditAvaiable(cliente: Cliente) {
       /* console.log('edit' + cliente.editAvaiable);
        if (cliente.editAvaiable) {
            console.log(cliente.editAvaiable);
            return false;
        } else { return true; }*/
       return false;
    }

    checkDeleteAvaiable(cliente: Cliente) {
      /*  if (cliente.deleteAvaiable) {
            return false;
        } else { return true; }*/
      return false;
    }

}
