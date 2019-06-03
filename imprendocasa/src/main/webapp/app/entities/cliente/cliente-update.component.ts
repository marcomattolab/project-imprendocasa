import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {Cliente} from 'app/shared/model/cliente.model';
import {ClienteService} from './cliente.service';
import {Tag, TagEnum} from 'app/shared/model/tag.model';
import {TagService} from 'app/entities/tag';
import {Incarico} from 'app/shared/model/incarico.model';
import {IncaricoService} from 'app/entities/incarico';
import {autoComplete, MapsData} from 'app/shared/util/google-utils';
import {MapsAPILoader} from '@agm/core';
import {FormControl, FormGroup} from '@angular/forms';
import {User, UserService} from 'app/core';
import {IMPORT_VALIDATOR} from 'app/shared';

@Component({
    selector: 'jhi-cliente-update',
    templateUrl: './cliente-update.component.html'
})
export class ClienteUpdateComponent implements OnInit {
    cliente: Cliente;
    isSaving: boolean;

    private incaricoRef: string;

    tags: Tag[];
//    incaricos: Incarico[]; // FIXME
    incaricoCommittentes: Incarico[]; // FIXME
    incaricoProponentes: Incarico[]; // FIXME
    incaricoAcquirenteLocatarios: Incarico[]; // FIXME
    incaricoSegnalatores: Incarico[]; // FIXME

    dataNascitaDp: any;
    clienteTag: TagEnum;

    @ViewChild('formCliente')
    formCliente: FormGroup;
    users: User[] = [];
    @ViewChild('search')
    public searchElementRef: ElementRef;
    public searchControl: FormControl;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private clienteService: ClienteService,
        private incaricoService: IncaricoService,
        private tagService: TagService,
        private mapsAPILoader: MapsAPILoader,
        private userService: UserService,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private ngZone: NgZone,
    ) {
    }

    ngOnInit() {
        this.isSaving = false;

        // this.incaricoService.query().subscribe(
        //     (res: HttpResponse<Incarico[]>) => {
        //         this.incaricoCommittentes = res.body;
        //     },
        //     (res: HttpErrorResponse) => this.onError(res.message)
        // );

        // parametri del routing alla creazione del componente
        this.incaricoRef = this.activatedRoute.snapshot.queryParamMap.get('incaricoRef') || null;

        const clienteTagStr = this.activatedRoute.snapshot.queryParamMap.get('clienteTag') || null;
        if (clienteTagStr) {
            this.clienteTag = TagEnum[clienteTagStr];
        }
        // parametri del routing asincroni
        this.activatedRoute.data.subscribe(data => {
            this.cliente = data['cliente'];
            this.tags = data['tags'];

            if (this.clienteTag) {
                // TODO:
                const foundTag = this.tags.find(tag => tag.codice === this.clienteTag);
                if (foundTag) {
                    setTimeout(() => {
                        this.formCliente.controls['tag'].setValue([foundTag]);
                        this.formCliente.controls['tag'].disable();
                    }, 0);
                }
            } else {
                setTimeout(() => {
                    this.formCliente.controls['tag'].enable();
                }, 0);
            }

            this.retrieveCordinate();
            this.retrieveUsersAgents();
        });

        this.searchControl = new FormControl();
    }

    private retrieveUsersAgents() {
        this.userService.queryAgents().subscribe(
            (res: HttpResponse<User[]>) =>
                this.onQueryAgentsSuccess(res.body),
            (res: HttpResponse<any>) => this.onError(res.body)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    private onQueryAgentsSuccess(users) {
        this.users = users;

        if (this.isAgenteDisabled()) {
            this.cliente.agente = this.users[0].login;
        }
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    retrieveCordinate() {
        this.mapsAPILoader.load().then(() => {
            autoComplete(this.cliente, this.searchElementRef, this.ngZone, null, new MapsData());
        });
    }

    isAgenteDisabled(): boolean {
        return this.users && this.users.length === 1;
    }

    save() {
        this.isSaving = true;
        if (this.cliente.id !== undefined) {
            this.subscribeToSaveResponse(this.clienteService.update(this.cliente));
        } else {
            this.subscribeToSaveResponse(this.clienteService.create(this.cliente));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Cliente>>) {
        result.subscribe(
            (res: HttpResponse<Cliente>) => this.onSaveSuccess(res.body),
            (res: HttpErrorResponse) => this.onSaveError(res));
    }

    private onSaveSuccess(newCliente: Cliente) {
        this.isSaving = false;
        this.previousState(newCliente);
    }

    private onSaveError(res: HttpErrorResponse) {
        this.isSaving = false;
        this.onError(res.message);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    previousState(newCliente?: Cliente) {
        if (newCliente && newCliente.id) {
            if (this.incaricoRef) {
                const incaricoRefNumber = Number(this.incaricoRef);

                const extras = {queryParams: {clienteRef: newCliente.id}};
                const isExistingRef = incaricoRefNumber > 0;
                const navigationUrl = isExistingRef ? [`incarico/${incaricoRefNumber}/edit`] : ['incarico/new'];

                this.router.navigate(navigationUrl, extras);
            } else {
                window.history.back();
            }
        } else {
            window.history.back();
        }
    }

    trackIncaricoById(index: number, item: Incarico) {
        return item.id;
    }

    trackItemById(index: number, item: any) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
