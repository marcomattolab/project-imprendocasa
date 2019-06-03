import { Injectable } from '@angular/core';

import { HttpClient, HttpEvent, HttpRequest, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

    public resourceUrl = SERVER_API_URL + 'api';

  constructor(private http: HttpClient) { }

  pushFileToStorage(file: File, idImmobile: string): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
    const formdata2: FormData = new FormData();

    formdata.append('file', file);
    formdata.append('idImmobile', idImmobile);

    const url = this.resourceUrl + '/post';
    const req = new HttpRequest('POST', url, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  getFiles(idImmobile: string): Observable<any> {
    const formdata: FormData = new FormData();
    const params = new HttpParams().set('idImmobile', idImmobile);
    const url = this.resourceUrl + '/getallfiles';
    return this.http.get(url, { params });
  }

  delete(idFile: string): Observable<any> {
    console.log(idFile);

    const formdata: FormData = new FormData();
    formdata.append('id', idFile);

    const url = this.resourceUrl + '/deleteFile';
    const req = new HttpRequest('POST', url, formdata, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }
}
