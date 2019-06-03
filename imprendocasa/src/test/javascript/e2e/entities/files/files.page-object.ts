import { element, by, ElementFinder } from 'protractor';

export class FilesComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-files div table .btn-danger'));
    title = element.all(by.css('jhi-files div h2#page-heading span')).first();

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

export class FilesUpdatePage {
    pageTitle = element(by.id('jhi-files-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nomeInput = element(by.id('field_nome'));
    dimensioneInput = element(by.id('field_dimensione'));
    estensioneInput = element(by.id('field_estensione'));
    immobileSelect = element(by.id('field_immobile'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNomeInput(nome) {
        await this.nomeInput.sendKeys(nome);
    }

    async getNomeInput() {
        return this.nomeInput.getAttribute('value');
    }

    async setDimensioneInput(dimensione) {
        await this.dimensioneInput.sendKeys(dimensione);
    }

    async getDimensioneInput() {
        return this.dimensioneInput.getAttribute('value');
    }

    async setEstensioneInput(estensione) {
        await this.estensioneInput.sendKeys(estensione);
    }

    async getEstensioneInput() {
        return this.estensioneInput.getAttribute('value');
    }

    async immobileSelectLastOption() {
        await this.immobileSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async immobileSelectOption(option) {
        await this.immobileSelect.sendKeys(option);
    }

    getImmobileSelect(): ElementFinder {
        return this.immobileSelect;
    }

    async getImmobileSelectedOption() {
        return this.immobileSelect.element(by.css('option:checked')).getText();
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

export class FilesDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-files-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-files'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
