import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {JhiDataUtils} from 'ng-jhipster';
import {UploadFileService} from 'app/entities/immobile/upload-file.service';
import {Immobile} from 'app/shared/model/immobile.model';
import {IFiles} from 'app/shared/model/files.model';
import {Observable} from 'rxjs';

@Component({
    selector: 'jhi-immobile-detail',
    templateUrl: './immobile-detail.component.html'
})
export class ImmobileDetailComponent implements OnInit {
    immobile: Immobile;

    fileUploads: Observable<IFiles>;

    immobileId: any;
    uploadArray: Array<IFiles>;

    private incaricoRef: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private uploadService: UploadFileService) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({immobile}) => {
            this.immobile = immobile;
        });
        this.immobileId = '' + this.immobile.id;
        this.retrieveList();

        // parametri del routing alla creazione del componente
        this.incaricoRef = this.activatedRoute.snapshot.queryParamMap.get('incaricoRef') || null;
    }

    retrieveList() {
        this.fileUploads = this.uploadService.getFiles(this.immobileId);
        this.uploadArray = [];
        this.fileUploads.subscribe(data => {
            this.uploadArray.push(data);
        });
        console.log(this.uploadArray);
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    // previousState() {
    //     window.history.back();
    // }

    previousState() {
        if (this.incaricoRef) {
            const incaricoRefNumber = Number(this.incaricoRef);

            const isExistingRef = incaricoRefNumber > 0;
            const navigationUrl = isExistingRef ? [`incarico/${incaricoRefNumber}/edit`] : ['incarico/new'];

            this.router.navigate(navigationUrl);
        } else {
            window.history.back();
        }
    }
}
