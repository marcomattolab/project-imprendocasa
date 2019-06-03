import { element, by, ElementFinder } from 'protractor';

export class MailTemplateComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-mail-template div table .btn-danger'));
    title = element.all(by.css('jhi-mail-template div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class MailTemplateUpdatePage {
    pageTitle = element(by.id('jhi-mail-template-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    categoriaInput = element(by.id('field_categoria'));
    modelloInput = element(by.id('field_modello'));
    abilitatoInput = element(by.id('field_abilitato'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCategoriaInput(categoria) {
        await this.categoriaInput.sendKeys(categoria);
    }

    async getCategoriaInput() {
        return this.categoriaInput.getAttribute('value');
    }

    async setModelloInput(modello) {
        await this.modelloInput.sendKeys(modello);
    }

    async getModelloInput() {
        return this.modelloInput.getAttribute('value');
    }

    getAbilitatoInput() {
        return this.abilitatoInput;
    }
    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class MailTemplateDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-mailTemplate-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-mailTemplate'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
