import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse, HttpEventType, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';
import {Immobile} from 'app/shared/model/immobile.model';
import {ImmobileService} from './immobile.service';
import {IGeolocalizzazione} from 'app/shared/model/geolocalizzazione.model';
import {GeolocalizzazioneService} from 'app/entities/geolocalizzazione';
import {Incarico} from 'app/shared/model/incarico.model';
import {IncaricoService} from 'app/entities/incarico';
import {UploadFileService} from 'app/entities/immobile/upload-file.service';
import {FormControl} from '@angular/forms';
import {IFiles} from 'app/shared/model/files.model';
import {MapsAPILoader} from '@agm/core';
import {autoComplete} from 'app/shared/util/google-utils';
import {MapsData} from 'app/shared/util/google-utils';
import {User, UserService} from 'app/core';

const DIM_1 = 1;

@Component({
    selector: 'jhi-immobile-update',
    templateUrl: './immobile-update.component.html'
})
export class ImmobileUpdateComponent implements OnInit {
    immobile: Immobile;
    isSaving: boolean;
    locaziones: IGeolocalizzazione[];
    incaricos: Incarico[];
    showUpload = false;
    fileUploads: Observable<IFiles>;
    aux: any;
    uploadArray: Array<IFiles>;
    fileList: Array<String>;
    filesToUpload: Array<File> = [];
    currentFileUpload: File;
    immobileId: string;
    progress: { percentage: number } = {percentage: 0};
    reload: string;
    incaricoRef: number;
    idLocazione: any;
    mapsData: MapsData;
    users: User[];

    public searchControl: FormControl;

    public latitude: number;
    public longitude: number;

    public zoom: number;

    @ViewChild('search')
    public searchElementRef: ElementRef;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private immobileService: ImmobileService,
        private geolocalizzazioneService: GeolocalizzazioneService,
        private incaricoService: IncaricoService,
        private activatedRoute: ActivatedRoute,
        private uploadService: UploadFileService,
        private userService: UserService,
        private router: Router,
        // private mapsService: MapsService,

        private mapsAPILoader: MapsAPILoader,
        private ngZone: NgZone
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({immobile}) => {
            this.immobile = immobile;
        });
        this.mapsData = new MapsData();
        /* this.geolocalizzazioneService.query({ filter: 'posizione(codice)-is-null' }).subscribe(
             (res: HttpResponse<IGeolocalizzazione[]>) => {
                 if (!this.immobile.geolocalizzazioneId) {
                     this.locaziones = res.body;
                 } else {
                     this.geolocalizzazioneService.find(this.immobile.geolocalizzazioneId).subscribe(
                         (subRes: HttpResponse<IGeolocalizzazione>) => {
                             this.locaziones = [subRes.body].concat(res.body);
                         },
                         (subRes: HttpErrorResponse) => this.onError(subRes.message)
                     );
                 }
             },
             (res: HttpErrorResponse) => this.onError(res.message)
         ); */
        console.log(this.immobile.geolocalizzazioneId);
        this.incaricoService.query().subscribe(
            (res: HttpResponse<Incarico[]>) => {
                this.incaricos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.incaricoRef = this.activatedRoute.snapshot.queryParamMap.get('incaricoRef') ? +this.activatedRoute.snapshot.queryParamMap.get('incaricoRef') : null;

        this.showUpload = true;
        if (this.immobile.id != null) {
            this.immobileId = '' + this.immobile.id;
            this.retrieveList();
        }

        this.searchControl = new FormControl();
        this.retrieveCordinate();
        this.retrieveUsersAgents();

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

    previousState(newImmobile?: Immobile) {
        if (newImmobile && newImmobile.id) {
            console.log('newImmobile.id:' + newImmobile.id);
            if (this.incaricoRef) {
                if (this.incaricoRef > 0) {
                    this.router.navigate(['incarico/' + this.incaricoRef + '/edit'], {queryParams: {immobileRef: newImmobile.id}});
                } else {
                    this.router.navigate(['incarico/new'], {queryParams: {immobileRef: newImmobile.id}});
                }
            } else {
                window.history.back();
            }
        } else {
            window.history.back();
        }
    }

    /* save() {
        this.isSaving = true;
        if (this.immobile.id !== undefined) {
            if (this.immobile.indirizzo ) {
                this.impostaLocazione();
            } else {
                this.subscribeToSaveResponse(this.immobileService.update(this.immobile));
            }
        } else {
            this.subscribeToSaveResponseAndStay(this.immobileService.create(this.immobile));
        }
    } */

    retrieveCordinate() {

        this.mapsAPILoader.load().then(() => {
            autoComplete(this.immobile, this.searchElementRef, this.ngZone, this.zoom, this.mapsData);
            this.latitude = this.mapsData.latitude;
            this.longitude = this.mapsData.longitude;
            console.log(this.mapsData);
        });

    }

    private retrieveUsersAgents() {
        this.userService.queryAgents().subscribe(
            (res: HttpResponse<User[]>) =>
                this.checkAgente(res.body),
            (res: HttpResponse<any>) => this.onError(res.body)
        );
    }

    checkAgente(users) {
        this.users = users;
        if (this.checkDim()) {
            this.immobile.agente = this.users[0].login;
        }
    }

    checkDim() {
        if (this.users.length === DIM_1) {
            return true;
        }
    }

    save() {
        this.isSaving = true;
        if (this.immobile.id !== undefined) {
            if (this.immobile.indirizzo) {
                this.impostaLocazione();
            } else {
                this.subscribeToSaveResponse(this.immobileService.update(this.immobile));
            }
        } else {
            if (this.immobile.indirizzo) {
                this.immobileService.create(this.immobile).subscribe(res => {
                    this.immobile.id = res.body.id;
                    this.impostaLocazione();
                });
            } else {
                this.subscribeToSaveResponse(this.immobileService.create(this.immobile));
            }
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Immobile>>) {
        result.subscribe((res: HttpResponse<Immobile>) => this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(newImmobile: Immobile) {
        this.isSaving = false;
        this.previousState(newImmobile);

    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackGeolocalizzazioneById(index: number, item: IGeolocalizzazione) {
        return item.id;
    }

    trackIncaricoById(index: number, item: Incarico) {
        return item.id;
    }

    trackItemById(index: number, item: any) {
        return item.id;
    }

    selectFile(event) {
        this.filesToUpload = <Array<File>>event.target.files;
    }

    retrieveList() {
        this.fileUploads = this.uploadService.getFiles(this.immobileId);
        this.uploadArray = [];
        this.fileUploads.subscribe(data => {
            this.uploadArray.push(data);
        });
    }

    myUploader(event) {
        for (const file of event.files) {
            this.filesToUpload.push(file);
        }
        console.log(this.filesToUpload);
    }

    /* retrieveCordinate() {
         this.mapsService.retrieveLatLong(this.immobile.indirizzo.trim(), this.immobile.citta).subscribe(
             data => {
                 this.lat = data['results'][0]['geometry']['location']['lat'];
                 this.long = data['results'][0]['geometry']['location']['lng'];
                 this.impostaLocazione();
             },
             err => {
                 console.log(err);
             }
         );
     }*/

    impostaLocazione() {

        let locazione: IGeolocalizzazione = null;
        if (this.immobile.geolocalizzazioneId) {
            locazione = {
                id: this.immobile.geolocalizzazioneId,
                immobile: '' + this.immobile.id,
                latitudine: '' + this.mapsData.latitude,
                longitudine: '' + this.mapsData.longitude
            };
            this.geolocalizzazioneService.update(locazione).subscribe(res => {
            });
            this.subscribeToSaveResponse(this.immobileService.update(this.immobile));
            console.log('posizione immobile aggiornata');
        } else {
            locazione = {
                immobile: '' + this.immobile.id,
                latitudine: '' + this.mapsData.latitude,
                longitudine: '' + this.mapsData.longitude
            };
            this.geolocalizzazioneService.create(locazione).subscribe(res => {
                this.immobile.geolocalizzazioneId = res.body.id;
                this.subscribeToSaveResponse(this.immobileService.update(this.immobile));
            });
            console.log('posizione immobile creata');
        }
    }

    myUploaderEnd(event) {
        if (this.immobile.id === undefined) {
            this.immobileService.create(this.immobile).subscribe(res => {
                this.immobile.id = res.body.id;
                this.immobileId = '' + res.body.id;
                this.eseguiUpload(event);
            });

        } else {
            this.eseguiUpload(event);
        }
        // this.filesToUpload = undefined;
    }

    eseguiUpload(event) {
        this.progress.percentage = 0;
        const files: Array<File> = this.filesToUpload;
        for (let i = 0; i < files.length; i++) {
            this.currentFileUpload = files[i];
            this.uploadService.pushFileToStorage(this.currentFileUpload, this.immobileId).subscribe(evento => {
                if (evento.type === HttpEventType.UploadProgress) {
                    this.progress.percentage = Math.round(100 * event.loaded / event.total);
                } else if (evento instanceof HttpResponse) {
                    console.log('File is completely uploaded!');
                    this.fileUploads = this.uploadService.getFiles(this.immobileId);
                }
            });
        }
        this.retrieveList();
    }

    delete(id) {
        this.uploadService.delete(id).subscribe(event => {
            if (event instanceof HttpResponse) {
                console.log('File is completely removed!');
                this.retrieveList();
            }
        });
    }
}
