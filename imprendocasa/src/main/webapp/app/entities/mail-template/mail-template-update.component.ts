import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiDataUtils} from 'ng-jhipster';

import {MailTemplate} from 'app/shared/model/mail-template.model';
import {MailTemplateService} from './mail-template.service';
import {FormGroup} from '@angular/forms';

@Component({
    selector: 'jhi-mail-template-update',
    templateUrl: './mail-template-update.component.html',
    styleUrls: ['mail-template-update.component.scss']
})
export class MailTemplateUpdateComponent implements OnInit {
    mailTemplate: MailTemplate;
    isSaving: boolean;

    @ViewChild('ckeditor')
    ckeditor: any;

    @ViewChild('editForm')
    editForm: FormGroup;

    ckEditorConfig = {
        allowedContent: false,
        extraPlugins: 'placeholder',
        removeButtons:
        // elenco bottoni https://ckeditor.com/old/forums/CKEditor/Complete-list-of-toolbar-items#comment-123266
            [
                'Source',
                'Save',
                'NewPage',
                'DocProps',
                'Preview',
                'Print',
                'Templates',

                'Find',
                'Replace',
                'SelectAll',
                'Scayt',

                'Form',
                'Checkbox',
                'Radio',
                'TextField',
                'Textarea',
                'Select',
                'Button',
                'ImageButton',
                'HiddenField',

                'Blockquote',
                'CreateDiv',
                'BidiLtr',
                'BidiRtl',

                'Link',
                'Unlink',
                'Anchor',

                'Image',
                'Flash',
                'Table',
                'HorizontalRule',
                'Smiley',
                'SpecialChar',
                'PageBreak',
                'Iframe',
                'InsertPre',

                'UIColor',
                'Maximize',
                'ShowBlocks'
            ].join(','),
        forcePasteAsPlainText: true
    };

    constructor(
        private dataUtils: JhiDataUtils,
        private mailTemplateService: MailTemplateService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({mailTemplate}) => {
            this.mailTemplate = mailTemplate;
        });
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mailTemplate.id !== undefined) {
            this.subscribeToSaveResponse(this.mailTemplateService.update(this.mailTemplate));
        } else {
            this.subscribeToSaveResponse(this.mailTemplateService.create(this.mailTemplate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MailTemplate>>) {
        result.subscribe((res: HttpResponse<MailTemplate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private templateIsValid() {
        return '' !== this.mailTemplate.modello.trim();
    }

    getModelloValidity(): string {
        return this.templateIsValid() ? 'ckeditor-valid' : 'ckeditor-invalid';
    }

    saveButtonIsDisabled() {
        const isDisabled = this.editForm.invalid || this.isSaving || !this.templateIsValid();
        return isDisabled;
    }
}
