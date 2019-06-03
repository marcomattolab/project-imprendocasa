/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AppSettingsComponentsPage, AppSettingsDeleteDialog, AppSettingsUpdatePage } from './app-settings.page-object';

const expect = chai.expect;

describe('AppSettings e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let appSettingsUpdatePage: AppSettingsUpdatePage;
    let appSettingsComponentsPage: AppSettingsComponentsPage;
    let appSettingsDeleteDialog: AppSettingsDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load AppSettings', async () => {
        await navBarPage.goToEntity('app-settings');
        appSettingsComponentsPage = new AppSettingsComponentsPage();
        expect(await appSettingsComponentsPage.getTitle()).to.eq('imprendocasaApp.appSettings.home.title');
    });

    it('should load create AppSettings page', async () => {
        await appSettingsComponentsPage.clickOnCreateButton();
        appSettingsUpdatePage = new AppSettingsUpdatePage();
        expect(await appSettingsUpdatePage.getPageTitle()).to.eq('imprendocasaApp.appSettings.home.createOrEditLabel');
        await appSettingsUpdatePage.cancel();
    });

    it('should create and save AppSettings', async () => {
        const nbButtonsBeforeCreate = await appSettingsComponentsPage.countDeleteButtons();

        await appSettingsComponentsPage.clickOnCreateButton();
        await promise.all([
            appSettingsUpdatePage.setCategoriaInput('categoria'),
            appSettingsUpdatePage.setChiaveInput('chiave'),
            appSettingsUpdatePage.setValoreInput('valore')
        ]);
        expect(await appSettingsUpdatePage.getCategoriaInput()).to.eq('categoria');
        expect(await appSettingsUpdatePage.getChiaveInput()).to.eq('chiave');
        expect(await appSettingsUpdatePage.getValoreInput()).to.eq('valore');
        const selectedAbilitato = appSettingsUpdatePage.getAbilitatoInput();
        if (await selectedAbilitato.isSelected()) {
            await appSettingsUpdatePage.getAbilitatoInput().click();
            expect(await appSettingsUpdatePage.getAbilitatoInput().isSelected()).to.be.false;
        } else {
            await appSettingsUpdatePage.getAbilitatoInput().click();
            expect(await appSettingsUpdatePage.getAbilitatoInput().isSelected()).to.be.true;
        }
        await appSettingsUpdatePage.save();
        expect(await appSettingsUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await appSettingsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last AppSettings', async () => {
        const nbButtonsBeforeDelete = await appSettingsComponentsPage.countDeleteButtons();
        await appSettingsComponentsPage.clickOnLastDeleteButton();

        appSettingsDeleteDialog = new AppSettingsDeleteDialog();
        expect(await appSettingsDeleteDialog.getDialogTitle()).to.eq('imprendocasaApp.appSettings.delete.question');
        await appSettingsDeleteDialog.clickOnConfirmButton();

        expect(await appSettingsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
