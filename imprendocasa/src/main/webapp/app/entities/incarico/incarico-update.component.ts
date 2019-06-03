import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {merge, Observable, Subject, Subscription} from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {
    BooleanStatus,
    CategoriaIncarico,
    Incarico,
    IncaricoStatutes,
    StatoIncarico,
    TipoNegoziazione
} from 'app/shared/model/incarico.model';
import {IncaricoService} from './incarico.service';
import {Partner} from 'app/shared/model/partner.model';
import {PartnerService} from 'app/entities/partner';
import {Cliente} from 'app/shared/model/cliente.model';
import {ClienteService} from 'app/entities/cliente';
import {Immobile} from 'app/shared/model/immobile.model';
import {ImmobileService} from 'app/entities/immobile';
import {NgbModal, NgbModalRef, NgbTypeahead, NgbTypeaheadSelectItemEvent} from '@ng-bootstrap/ng-bootstrap';
import {SelectItem} from 'primeng/components/common/api';
import {User, UserService} from 'app/core';
import {TagEnum} from 'app/shared/model/tag.model';
import {closeSubscription} from 'app/shared/util/handler-util';
import {debounceTime, distinctUntilChanged, filter, map} from 'rxjs/operators';
import {getFormattedNomeCognomeAsString} from 'app/entities/cliente/cliente-util';
import {getFormattedCodiceIndirizzoAsString} from 'app/entities/immobile/immobile-util';
import {isNullOrUndefined} from 'util';
import {IMPORT_VALIDATOR} from 'app/shared';
import {FormGroup} from '@angular/forms';
import {ListaContatti} from 'app/shared/model/lista-contatti.model';
import {ListaContattiService} from 'app/entities/lista-contatti';
import {CambioStatoModalComponent} from 'app/entities/incarico/cambio-stato-modal/cambio-stato-modal.component';

@Component({
    selector: 'jhi-incarico-update',
    templateUrl: './incarico-update.component.html',
    styleUrls: ['./incarico-update.component.scss']
})
export class IncaricoUpdateComponent implements OnInit, OnDestroy {
    incarico: Incarico;
    isSaving: boolean;
    partners: Partner[];

    @ViewChild('formIncarico')
    formIncarico: FormGroup;

    clientes: Cliente[] = [];
    committenti: Cliente[] = [];
    proponenti: Cliente[] = [];
    acquirenti: Cliente[] = [];
    segnalatori: Cliente[] = [];
    listaContatti: ListaContatti[];

    validator = IMPORT_VALIDATOR;
    cliente: Cliente;
    immobili: Immobile[];
    readOnly: boolean;
    partnerItems: SelectItem[];

    modalRef: NgbModalRef;
    dataContatto: string;
    isNew: boolean;
    statiIncarico: Array<StatoIncarico>;

    tipiNegoziazione: TipoNegoziazione[];
    categorie: CategoriaIncarico[];
    users: User[];

    tagEnum = TagEnum;
    @ViewChild('partnerTypeahead')
    partnerTypeahead: NgbTypeahead;
    partnerFocus$ = new Subject<string>();

    partnerClick$ = new Subject<string>();
    @ViewChild('immobileTypeahead')
    immobileTypeahead: NgbTypeahead;
    immobileFocus$ = new Subject<string>();

    immobileClick$ = new Subject<string>();

    selectedImmobile: Immobile;

    private incaricoSubscription: Subscription;
    private clientiSubscription: Subscription;
    private immobileSubscription: Subscription;
    private permissionSubscription: Subscription;
    private clienteSubscription: Subscription;
    private partnerSubscription: Subscription;
    private immobiliSubscription: Subscription;
    private partnersSubscription: Subscription;

    private usersSubscription: Subscription;
    private getStatusSubscription: Subscription;
    incaricoStatutes: IncaricoStatutes = {changeStatusEnabled: false};

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private incaricoService: IncaricoService,
        private listaContattiService: ListaContattiService,
        private partnerService: PartnerService,
        private clienteService: ClienteService,
        private immobileService: ImmobileService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private modalService: NgbModal
    ) {
    }

    ngOnInit() {
        this.isSaving = false;

        this.init();

        this.retrieveStatuses();
        this.retrieveClienti();
        this.retrievePartner();
        this.retrieveImmobili();
        this.retrieveUsersAgents();
    }

    private retrieveRelalationListsComingFromOtherComponents() {

        // Se si arriva a questa pagina venendo da /cliente/:id/edit
        const routeSnapshotQueryParamMap = this.activatedRoute.snapshot.queryParamMap;
        const clienteRef = routeSnapshotQueryParamMap.get('clienteRef') || null;
        if (clienteRef) {
            closeSubscription(this.clienteSubscription);
            this.clienteSubscription = this.clienteService.find(clienteRef).subscribe(
                (res: HttpResponse<Cliente>) => {
                    this.incarico.committentes.push(res.body);
                    this.checkClientiAndUpdateListaContattiAndSelect();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }

        // Se si arriva a questa pagina venendo da /immobile/:id/edit
        const immobileRef = routeSnapshotQueryParamMap.get('immobileRef') || null;
        if (immobileRef) {
            closeSubscription(this.immobileSubscription);
            this.immobileSubscription = this.immobileService.find(immobileRef).subscribe(
                (res: HttpResponse<Immobile>) => {
                    const incarico = res.body;

                    this.selectImmobile(incarico);
                    this.retrieveImmobili();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }

        // Se si arriva a questa pagina venendo da /partner/:id/edit
        const partnerRef = routeSnapshotQueryParamMap.get('partnerRef') || null;
        if (partnerRef) {
            closeSubscription(this.partnerSubscription);
            this.partnerSubscription = this.partnerService.find(partnerRef).subscribe(
                (res: HttpResponse<Partner>) => {
                    this.incarico.partners.push(res.body);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    private selectImmobile(incarico) {
        this.incarico.immobileId = incarico.id;
        this.incarico.immobileCodice = incarico.codice;
        this.selectedImmobile = incarico;
    }

    ngOnDestroy(): void {
        closeSubscription(this.clientiSubscription);
        closeSubscription(this.incaricoSubscription);
        closeSubscription(this.immobileSubscription);
        closeSubscription(this.permissionSubscription);
        closeSubscription(this.clienteSubscription);
        closeSubscription(this.partnerSubscription);
        closeSubscription(this.immobiliSubscription);
        closeSubscription(this.partnersSubscription);
        closeSubscription(this.usersSubscription);
    }

    private init() {
        this.activatedRoute.data.subscribe(data => {
            this.isNew = Boolean(data['isNew']);

            this.initStatiIncarico();
            this.initTipiNegoziazione();

            this.incarico = data['incarico'];

            this.incarico.committentes = this.incarico.committentes || [];
            this.incarico.proponentes = this.incarico.proponentes || [];
            this.incarico.acquirenteLocatarios = this.incarico.acquirenteLocatarios || [];
            this.incarico.segnalatores = this.incarico.segnalatores || [];

            if (this.isNew) {
                this.incarico.stato = StatoIncarico.BOZZA;
                this.incarico.alertFiscali = BooleanStatus.SI;
                this.incarico.alertCortesia = BooleanStatus.SI;
            } else {
                if (this.incarico.id) {
                    this.permissionSubscription = this.incaricoService.retrievePermission(this.incarico.id)
                        .subscribe(permission => {
                            this.readOnly = permission;
                        });
                }

                if (this.incarico.immobileId != null) {
                    const immobileIdStr = String(this.incarico.immobileId);
                    this.immobileSubscription = this.immobileService.find(immobileIdStr).subscribe(
                        (res: HttpResponse<Immobile>) => {
                            if (res.body != null) {
                                this.selectImmobile(res.body);
                            }
                        });
                }

                this.checkClientiAndUpdateListaContattiAndSelect();

                this.retrieveRelalationListsComingFromOtherComponents();
            }
        });
    }

    private checkClientiAndUpdateListaContattiAndSelect() {
        if (this.incarico.committentes && this.incarico.committentes.length > 0) {
            const listaCommittenteId: Array<number> =
                this.incarico.committentes.map(incarico => incarico.id);
            const listaContattiQueryParams = {
                'clienteId.in': listaCommittenteId.join()
            };
            this.listaContattiService.query(listaContattiQueryParams).subscribe(
                (res: HttpResponse<ListaContatti[]>) => this.listaContatti = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        } else {
            this.listaContatti = [];
        }
    }

    private initStatiIncarico() {
        this.statiIncarico = [StatoIncarico.BOZZA];
        if (!this.isNew) {
            this.statiIncarico.push(
                ...[
                    StatoIncarico.ATTIVO,
                    StatoIncarico.CONCLUSO,
                    StatoIncarico.INTERROTTO,
                    StatoIncarico.SCADUTO
                ]
            );
        }
    }

    private retrieveClienti() {
        this.clientiSubscription = this.clienteService.query().subscribe(
            (res: HttpResponse<Cliente[]>) => {
                if (res.body != null) {
                    this.clientes = res.body;
                    this.filterClientiByTag();
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private filterClientiByTag() {
        this.clientes.forEach(cliente => {
            for (const tag of cliente.tags) {
                if (tag.codice === TagEnum.COMMITTENTE) {
                    this.committenti.push(cliente);
                }

                if (tag.codice === TagEnum.PROPONENTE) {
                    this.proponenti.push(cliente);
                }

                if (tag.codice === TagEnum.ACQUIRENTE_CONDUTTORE) {
                    this.acquirenti.push(cliente);
                }

                if (tag.codice === TagEnum.SEGNALATORE) {
                    this.segnalatori.push(cliente);
                }
            }
        });
    }

    statoIncaricoIsDisabled(): boolean {
        return this.readAuth() || this.isNew;
    }

    readAuth() {
        return this.readOnly;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    previousState() {
        // window.history.back();
        this.router.navigate(['incarico'], {});
    }

    save() {
        this.isSaving = true;

        this.setIncaricoPropertiesOnTipoLocazione();

        closeSubscription(this.incaricoSubscription);

        if (this.incarico.id !== undefined) {
            this.incaricoSubscription = this.incaricoService.update(this.incarico).subscribe(
                () => this.onSaveSuccess(),
                () => this.onSaveError()
            );
        } else {
            this.incaricoSubscription = this.incaricoService.create(this.incarico).subscribe(
                () => this.onSaveSuccess(),
                () => this.onSaveError()
            );
        }
    }

    setIncaricoPropertiesOnTipoLocazione() {
        if (this.tipoIncaricoIsLocazione()) {
            this.incarico.flagLocato = null;
            this.incarico.nomeConduttore = null;
            this.incarico.nomeConduttore = null;
            this.incarico.dataInizioLocazione = null;
            this.incarico.dataFineLocazione = null;
            this.incarico.noteLocazione = null;
        }
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

    retrievePartner() {
        closeSubscription(this.partnersSubscription);
        this.partnersSubscription = this.partnerService.query().subscribe(
            (res: HttpResponse<Partner[]>) => {
                if (res.body != null) {
                    this.partners = res.body;
                    if (this.incarico.partners != null) {
                        this.incarico.partners.forEach(partner => {
                            this.partners = this.partners.filter(order => order.id !== partner.id);
                        });
                    }
                    this.retrievePartnerItems();
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

    }

    retrieveImmobili() {
        closeSubscription(this.immobiliSubscription);
        this.immobiliSubscription = this.immobileService.query().subscribe(
            (res: HttpResponse<Immobile[]>) => {
                if (res.body != null) {
                    this.immobili = res.body;
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    retrievePartnerItems() {
        let id = 0;
        this.partnerItems = [];
        this.partners.forEach(el => {

            const partnerItem: SelectItem = {
                label: el.cognome + ' ' + el.nome,
                value: {id, name: el.cognome, code: el.id}
            };
            this.partnerItems.push(partnerItem);
            id += 1;
        });
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

    private retrieveUsersAgents() {
        closeSubscription(this.usersSubscription);
        this.usersSubscription = this.userService.queryAgents().subscribe(
            (res: HttpResponse<User[]>) => {
                this.users = res.body;
                if (this.users && this.users.length === 1) {
                    this.incarico.agente = this.users[0].login;
                }
            },
            (res: HttpResponse<any>) => this.onError(res.body)
        );
    }

    tipoIncaricoIsLocazione(): boolean {
        return this.incarico.tipo === TipoNegoziazione.LOCAZIONE;
    }

    getIncaricoId() {
        return this.isNew ? '0' : this.incarico.id;
    }

    onSelectPartner(ngbTypeaheadSelectItem: NgbTypeaheadSelectItemEvent) {
        ngbTypeaheadSelectItem.preventDefault();

        const partnerFound = this.partners.find(partner =>
            getFormattedNomeCognomeAsString(partner) === ngbTypeaheadSelectItem.item);

        if (partnerFound) {
            this.incarico.partners.push(partnerFound);
        }

        this.partnerTypeahead.writeValue('');
    }

    private getAvailablePartners() {
        const result = [];

        this.partners.forEach(cliente => {
            let toAdd = true;
            for (const incaricoCliente of this.incarico.partners) {
                if (incaricoCliente.id === cliente.id) {
                    toAdd = false;
                    break;
                }
            }

            if (toAdd) {
                result.push(getFormattedNomeCognomeAsString(cliente));
            }
        });

        return result;
    }

    searchPartner = (text$: Observable<string>) => {
        const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());

        const clicksWithClosedPopup$ = this.partnerClick$.pipe(
            filter(() => !this.partnerTypeahead.isPopupOpen()));

        const inputFocus$ = this.partnerFocus$;

        return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
            map(term => (term === '' ?
                this.getAvailablePartners() : this.getAvailablePartners()
                    .filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    };

    rimuoviPartner(partner: Partner) {
        const indexOf = this.incarico.partners.indexOf(partner);
        this.incarico.partners.splice(indexOf, 1);
    }

    nuovoPartner() {
        const navigationExtras = {
            queryParams: {
                incaricoRef: this.incarico.id
            }
        };
        this.router.navigate(['/partner/new'], navigationExtras);
    }

    isPartnerSelezionati(): boolean {
        return this.incarico && this.incarico.partners && this.incarico.partners.length > 0;
    }

    private getAvailableImmobili(): Array<string> {
        const result = [];

        this.immobili.forEach((immobile: Immobile) => {
            if (isNullOrUndefined(this.selectedImmobile) || immobile.id !== this.selectedImmobile.id) {
                result.push(getFormattedCodiceIndirizzoAsString(immobile));
            }
        });

        return result;
    }

    searchImmobile = (text$: Observable<string>) => {
        const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());

        const clicksWithClosedPopup$ = this.immobileClick$.pipe(
            filter(() => !this.immobileTypeahead.isPopupOpen()));

        return merge(debouncedText$, this.immobileFocus$, clicksWithClosedPopup$).pipe(
            map(term => (term === '' ?
                this.getAvailableImmobili() : this.getAvailableImmobili()
                    .filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    };

    nuovoImmobile() {
        const navigationExtras = {
            queryParams: {
                incaricoRef: this.incarico.id
            }
        };
        this.router.navigate(['/immobile/new'], navigationExtras);
    }

    onSelectImmobile(ngbTypeaheadSelectItem: NgbTypeaheadSelectItemEvent) {
        ngbTypeaheadSelectItem.preventDefault();

        const selectedItem = ngbTypeaheadSelectItem.item;
        const immobileFound = this.immobili.find(immobile =>
            getFormattedCodiceIndirizzoAsString(immobile) === selectedItem);

        if (immobileFound) {
            this.selectImmobile(immobileFound);
        }

        this.immobileTypeahead.writeValue('');
    }

    isImmobileSelected(): boolean {
        return !isNullOrUndefined(this.selectedImmobile) &&
            !isNullOrUndefined(this.selectedImmobile.id);
    }

    rimuoviImmobile() {
        this.incarico.immobileCodice = null;
        this.incarico.immobileId = null;
        this.selectedImmobile = null;
    }

    visualizzaDettaglioImmobile() {
        const navigationExtras = {
            queryParams: {
                incaricoRef: this.incarico.id
            }
        };
        this.router.navigate(['/immobile', this.selectedImmobile.id, 'view'], navigationExtras);
    }

    newContatto() {
        const navigationExtras = {
            queryParams: {
                incaricoRef: this.incarico.id
            }
        };
        this.router.navigate(['/lista-contatti/new'], navigationExtras);
    }

    onCommittentiChanges(cliente: Cliente) {
        this.checkClientiAndUpdateListaContattiAndSelect();
    }

    isImmobileRequired(): boolean {
        return this.incarico.tipo === TipoNegoziazione.LOCAZIONE ||
            this.incarico.tipo === TipoNegoziazione.COMPRAVENDITA;
    }

    hideImmobileRequired(): boolean {
        return !this.isImmobileRequired() ||
            this.isImmobileRequired() && this.isImmobileSelected();
    }

    isSaveButtonDisabled(): boolean {
        return this.isSaving ||
            !this.hideImmobileRequired() ||
            (this.formIncarico &&
                this.formIncarico['form'] &&
                this.formIncarico['form'].invalid);
    }

    private retrieveStatuses() {
        closeSubscription(this.getStatusSubscription);
        this.getStatusSubscription = this.incaricoService.getStatus(this.incarico.id).subscribe(
            incaricoStatutes => {
                this.incaricoStatutes = incaricoStatutes;
            },
            error => this.onError(error.message));
    }

    openChangeStatusModal() {
        const modalInstance = this.modalService.open(CambioStatoModalComponent);

        modalInstance.componentInstance.statiIncarico = this.incaricoStatutes.statuses;
        modalInstance.componentInstance.statoIniziale = this.incaricoStatutes.status;
        modalInstance.componentInstance.incarico = this.incarico;

        modalInstance.result.then((incarico: Incarico) => {
            if (incarico) {
                this.incarico = incarico;
            }
        }, () => {
        });
    }
}
