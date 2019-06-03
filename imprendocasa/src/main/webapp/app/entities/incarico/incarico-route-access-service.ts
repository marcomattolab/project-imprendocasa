import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { JhiAlertService } from 'ng-jhipster';

@Injectable({ providedIn: 'root' })
export class IncaricoDeleteRouteAccessService implements CanActivate {

    public resourceUrl = SERVER_API_URL + 'api/incaricos';

    constructor(
        private http: HttpClient,
        private jhiAlertService: JhiAlertService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Promise<boolean> {
        const idIncarico = route.params['id'] ? route.params['id'] : null;
        return new Promise((resolve, reject) => {
            this.checkDeleteAvaiable(idIncarico).subscribe(
                (res: HttpResponse<any>) => {
                    resolve(true);
                },
                (res: HttpErrorResponse) => {
                    if (res.error.errorKey) {
                        this.jhiAlertService.error(res.error.errorKey, null, null);
                    } else {
                        this.jhiAlertService.error(res.message, null, null);
                    }
                    resolve(false);
                }
            );
        });
    }

    checkDeleteAvaiable(id: number): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.resourceUrl}/deleteAvaiable/${id}`, { observe: 'response' });
    }

}

@Injectable({ providedIn: 'root' })
export class IncaricoEditRouteAccessService implements CanActivate {

    public resourceUrl = SERVER_API_URL + 'api/immobiles';

    constructor(
        private http: HttpClient,
        private jhiAlertService: JhiAlertService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Promise<boolean> {
        const idIncarico = route.params['id'] ? route.params['id'] : null;
        return new Promise((resolve, reject) => {
            this.checkEditAvaiable(idIncarico).subscribe(
                (res: HttpResponse<any>) => {
                    resolve(true);
                },
                (res: HttpErrorResponse) => {
                    if (res.error.errorKey) {
                        this.jhiAlertService.error(res.error.errorKey, null, null);
                    } else {
                        this.jhiAlertService.error(res.message, null, null);
                    }
                    resolve(false);
                }
            );
        });
    }

    checkEditAvaiable(id: number): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.resourceUrl}/editAvaiable/${id}`, { observe: 'response' });
    }

}
