import './vendor.ts';

import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {NgbDatepickerConfig} from '@ng-bootstrap/ng-bootstrap';
import {Ng2Webstorage} from 'ngx-webstorage';

import {AuthExpiredInterceptor} from './blocks/interceptor/auth-expired.interceptor';
import {ErrorHandlerInterceptor} from './blocks/interceptor/errorhandler.interceptor';
import {NotificationInterceptor} from './blocks/interceptor/notification.interceptor';
import {ImprendocasaSharedModule} from 'app/shared';
import {ImprendocasaCoreModule} from 'app/core';
import {ImprendocasaAppRoutingModule} from './app-routing.module';
import {ImprendocasaHomeModule} from 'app/home';
import {ImprendocasaAccountModule} from './account/account.module';
import {ImprendocasaEntityModule} from './entities/entity.module';
import {MDBBootstrapModule} from 'angular-bootstrap-md';
import * as moment from 'moment';
import {ImprendocasaDashboardModule} from './dashboard/dashboard.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import {
    ActiveMenuDirective,
    ErrorComponent,
    FooterComponent,
    JhiMainComponent,
    NavbarComponent,
    PageRibbonComponent
} from './layouts';

@NgModule({
    imports: [
        ImprendocasaAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-' }),
        ImprendocasaSharedModule,
        ImprendocasaCoreModule,
        ImprendocasaHomeModule,
        ImprendocasaAccountModule,
        MDBBootstrapModule.forRoot(),
        ImprendocasaDashboardModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
        ImprendocasaEntityModule
    ],
    declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true
        },
    ],
    bootstrap: [JhiMainComponent]
})
export class ImprendocasaAppModule {
    constructor(private dpConfig: NgbDatepickerConfig) {
        this.dpConfig.minDate = { year: moment().year() - 100, month: 1, day: 1 };
    }
}
