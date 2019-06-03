/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NotificheComponentsPage, NotificheDeleteDialog, NotificheUpdatePage } from './notifiche.page-object';

const expect = chai.expect;

describe('Notifiche e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let notificheUpdatePage: NotificheUpdatePage;
    let notificheComponentsPage: NotificheComponentsPage;
    let notificheDeleteDialog: NotificheDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Notifiches', async () => {
        await navBarPage.goToEntity('notifiche');
        notificheComponentsPage = new NotificheComponentsPage();
        expect(await notificheComponentsPage.getTitle()).to.eq('imprendocasaApp.notifiche.home.title');
    });

    it('should load create Notifiche page', async () => {
        await notificheComponentsPage.clickOnCreateButton();
        notificheUpdatePage = new NotificheUpdatePage();
        expect(await notificheUpdatePage.getPageTitle()).to.eq('imprendocasaApp.notifiche.home.createOrEditLabel');
        await notificheUpdatePage.cancel();
    });

    it('should create and save Notifiches', async () => {
        const nbButtonsBeforeCreate = await notificheComponentsPage.countDeleteButtons();

        await notificheComponentsPage.clickOnCreateButton();
        await promise.all([
            notificheUpdatePage.canaleNotificaSelectLastOption(),
            notificheUpdatePage.tipoNotificaSelectLastOption(),
            notificheUpdatePage.categoriaNotificaSelectLastOption(),
            notificheUpdatePage.setOggettoNotificaInput('oggettoNotifica'),
            notificheUpdatePage.setTestoNotificaInput('testoNotifica'),
            notificheUpdatePage.setDestinatariNorificaInput('destinatariNorifica'),
            notificheUpdatePage.esitoNotificaSelectLastOption()
        ]);
        expect(await notificheUpdatePage.getOggettoNotificaInput()).to.eq('oggettoNotifica');
        expect(await notificheUpdatePage.getTestoNotificaInput()).to.eq('testoNotifica');
        expect(await notificheUpdatePage.getDestinatariNorificaInput()).to.eq('destinatariNorifica');
        await notificheUpdatePage.save();
        expect(await notificheUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await notificheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Notifiche', async () => {
        const nbButtonsBeforeDelete = await notificheComponentsPage.countDeleteButtons();
        await notificheComponentsPage.clickOnLastDeleteButton();

        notificheDeleteDialog = new NotificheDeleteDialog();
        expect(await notificheDeleteDialog.getDialogTitle()).to.eq('imprendocasaApp.notifiche.delete.question');
        await notificheDeleteDialog.clickOnConfirmButton();

        expect(await notificheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
