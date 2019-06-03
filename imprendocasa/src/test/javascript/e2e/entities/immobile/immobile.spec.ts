/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ImmobileComponentsPage, ImmobileDeleteDialog, ImmobileUpdatePage } from './immobile.page-object';

const expect = chai.expect;

describe('Immobile e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let immobileUpdatePage: ImmobileUpdatePage;
    let immobileComponentsPage: ImmobileComponentsPage;
    let immobileDeleteDialog: ImmobileDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Immobiles', async () => {
        await navBarPage.goToEntity('immobile');
        immobileComponentsPage = new ImmobileComponentsPage();
        expect(await immobileComponentsPage.getTitle()).to.eq('imprendocasaApp.immobile.home.title');
    });

    it('should load create Immobile page', async () => {
        await immobileComponentsPage.clickOnCreateButton();
        immobileUpdatePage = new ImmobileUpdatePage();
        expect(await immobileUpdatePage.getPageTitle()).to.eq('imprendocasaApp.immobile.home.createOrEditLabel');
        await immobileUpdatePage.cancel();
    });

    it('should create and save Immobiles', async () => {
        const nbButtonsBeforeCreate = await immobileComponentsPage.countDeleteButtons();

        await immobileComponentsPage.clickOnCreateButton();
        await promise.all([
            immobileUpdatePage.setCodiceInput('codice'),
            immobileUpdatePage.setDescrizioneInput('descrizione'),
            immobileUpdatePage.setIndirizzoInput('indirizzo'),
            immobileUpdatePage.setCapInput('cap'),
            immobileUpdatePage.setRegioneInput('regione'),
            immobileUpdatePage.setProvinciaInput('provincia'),
            immobileUpdatePage.setCittaInput('citta'),
            immobileUpdatePage.setNoteInput('note'),
            immobileUpdatePage.setPathFolderInput('pathFolder'),
            immobileUpdatePage.locazioneSelectLastOption()
        ]);
        expect(await immobileUpdatePage.getCodiceInput()).to.eq('codice');
        expect(await immobileUpdatePage.getDescrizioneInput()).to.eq('descrizione');
        expect(await immobileUpdatePage.getIndirizzoInput()).to.eq('indirizzo');
        expect(await immobileUpdatePage.getCapInput()).to.eq('cap');
        expect(await immobileUpdatePage.getRegioneInput()).to.eq('regione');
        expect(await immobileUpdatePage.getProvinciaInput()).to.eq('provincia');
        expect(await immobileUpdatePage.getCittaInput()).to.eq('citta');
        expect(await immobileUpdatePage.getNoteInput()).to.eq('note');
        expect(await immobileUpdatePage.getPathFolderInput()).to.eq('pathFolder');
        await immobileUpdatePage.save();
        expect(await immobileUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await immobileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Immobile', async () => {
        const nbButtonsBeforeDelete = await immobileComponentsPage.countDeleteButtons();
        await immobileComponentsPage.clickOnLastDeleteButton();

        immobileDeleteDialog = new ImmobileDeleteDialog();
        expect(await immobileDeleteDialog.getDialogTitle()).to.eq('imprendocasaApp.immobile.delete.question');
        await immobileDeleteDialog.clickOnConfirmButton();

        expect(await immobileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
