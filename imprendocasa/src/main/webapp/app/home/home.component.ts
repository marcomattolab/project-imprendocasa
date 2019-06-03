import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';
import {Account, LoginModalService, Principal} from 'app/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {GeolocalizzazioneService} from 'app/entities/geolocalizzazione';
import {IGeolocalizzazione} from 'app/shared/model/geolocalizzazione.model';
import {ImmobileService} from 'app/entities/immobile';
import {IncaricoService} from 'app/entities/incarico';
// import {IImmobile} from 'app/shared/model/immobile.model';
import {IDashboard} from 'app/shared/model/dashboard.model';
import {AgmMap} from '@agm/core';
import {IGeolocalizzazioneExtended} from 'app/shared/model/geolocalizzazioneExtended.model';
import {Subscription} from 'rxjs';
import {closeSubscription} from 'app/shared/util/handler-util';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, AfterViewInit, OnDestroy {
    data: any;
    fullresponse: any;

    account: Account;
    modalRef: NgbModalRef;
    lat1 = 38.151212;
    lng1 = 13.336005;
    locations: IGeolocalizzazioneExtended[] = [];
    // immobileInfo: IImmobile;
    readonly customicon = {
        url: 'content/images/gm-icon/bluemarker.png',
        scaledSize: {
            width: 40,
            height: 40
        }
    };

    @ViewChild('agmMap')
    agmMap: AgmMap;
    private geoLocalizazzioneSubscription: Subscription;
    private eventManagerSubscription: Subscription;
    private principalSubscription: Subscription;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private http: HttpClient,
        private jhiAlertService: JhiAlertService,
        private geolocalizzazioneService: GeolocalizzazioneService,
        private immobileService: ImmobileService,
        private incaricoService: IncaricoService
    ) {
        this.incaricoService.dashboardQuery().subscribe(
            (res: HttpResponse<IDashboard[]>) => this.setResponseDashboard(res.body),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.principalSubscription = this.principal.getAuthenticationState().subscribe(userIdentity => {
            this.account = userIdentity;
            this.updateMapLocations();
        });
    }

    ngAfterViewInit() {
        this.principal.identity().then(account => {
            this.account = account;
            this.updateMapLocations();
        }, error => this.resetLocations());

        this.registerAuthenticationSuccess();
    }

    ngOnDestroy() {
        this.resetLocations();
        closeSubscription(this.geoLocalizazzioneSubscription);
        closeSubscription(this.eventManagerSubscription);
        closeSubscription(this.principalSubscription);
    }

    private resetLocations() {
        this.locations = [];
    }

    private updateMapLocations() {
        if (this.account) {
            const requestParams = {filter: 'posizione(codice)-is-null'};

            closeSubscription(this.geoLocalizazzioneSubscription);
            this.geoLocalizazzioneSubscription =
                this.geolocalizzazioneService.findExtended(requestParams).subscribe(
                    (res: IGeolocalizzazioneExtended[]) => {
                        this.locations = res;
                        console.log(this.locations);
                        this.forceMapFitBounds();
                    },
                    error => this.onError(error));
        } else {
            this.resetLocations();
        }
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    private registerAuthenticationSuccess() {
        closeSubscription(this.eventManagerSubscription);
        this.eventManagerSubscription = this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
                this.updateMapLocations();
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    /* infoOnMouseOver(currentLocation: IGeolocalizzazione) {
        this.immobileService.find(+currentLocation.immobile).subscribe(res => {
            this.immobileInfo = res.body;
        });
    }*/

    forceMapFitBounds() {
        const google = window['google'];

        // zoom sulla mappa in base alle posizioni ottenute
        if (google && this.locations && this.locations.length > 0) {
            // se le coordinate sono presenti
            // e se la variabile 'google' è stata inizializzata dagli import di Angular
            const bounds = new google.maps.LatLngBounds();
            for (const mm of this.locations) {
                bounds.extend(new google.maps.LatLng(Number(mm.latitudine), Number(mm.longitudine)));
            }
            // @ts-ignore
            this.agmMap._mapsWrapper.fitBounds(bounds);
        }
    }

    private setResponseDashboard(body: any) {
        this.fullresponse = body;
        const currvalues: number[] = [];
        const currlabels: string[] = [];
        const colors: string[] = [];
        const allcolors: string[] = ['#FF6384', '#36A2EB', '#16A7AB', '#AA63EE'];

        for (const key in this.fullresponse) {
            if (this.fullresponse.hasOwnProperty(key)) {
                currlabels.push(this.fullresponse[key].label);
                currvalues.push(this.fullresponse[key].value);
                colors.push(allcolors[key]);
            }
        }

        this.data = {
            labels: currlabels,
            datasets: [
                {
                    data: currvalues,
                    backgroundColor: colors,
                    hoverBackgroundColor: colors
                }
            ]
        };
    }
}
