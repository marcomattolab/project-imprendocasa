import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map, catchError } from 'rxjs/operators';

import { SERVER_API_URL, MAPS_KEY } from 'app/app.constants';

@Injectable({ providedIn: 'root' })
export class MapsService {
    public resourceUrl = SERVER_API_URL + 'api/clientes';
    private extractData(res: Response) {
        const body = res;
        return body || {};
    }

    constructor(private http: HttpClient) { }

    retrieveLatLong(via: string, citta: string) {
        const address = 'https://maps.googleapis.com/maps/api/geocode/json?address=' + via + ','  + citta + ',' + 'CA&key=' + MAPS_KEY;
        console.log(address);
       return this.http.get(address).pipe(
            map(this.extractData)
        );
    }
  /*  retrieveLocation() {
        const address = 'https://maps.googleapis.com/maps/api/geocode/json?address=palermo,italy,+CA&key=AIzaSyD_kgbp3VVJES9KTb7bOLgMDs1GfZ4RRIo';
        // return this.http.get(address).map((response: Response) => response.json());

        return this.http.get(address).pipe(
            map(this.extractData)
        );
    } */
}
