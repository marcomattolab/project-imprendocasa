import { element, by, ElementFinder } from 'protractor';

export class AppSettingsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-app-settings div table .btn-danger'));
    title = element.all(by.css('jhi-app-settings div h2#page-heading span')).first();

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

export class AppSettingsUpdatePage {
    pageTitle = element(by.id('jhi-app-settings-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    categoriaInput = element(by.id('field_categoria'));
    chiaveInput = element(by.id('field_chiave'));
    valoreInput = element(by.id('field_valore'));
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

    async setChiaveInput(chiave) {
        await this.chiaveInput.sendKeys(chiave);
    }

    async getChiaveInput() {
        return this.chiaveInput.getAttribute('value');
    }

    async setValoreInput(valore) {
        await this.valoreInput.sendKeys(valore);
    }

    async getValoreInput() {
        return this.valoreInput.getAttribute('value');
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

export class AppSettingsDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-appSettings-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-appSettings'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
