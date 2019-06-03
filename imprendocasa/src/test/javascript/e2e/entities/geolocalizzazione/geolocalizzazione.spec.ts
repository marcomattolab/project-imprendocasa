/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    GeolocalizzazioneComponentsPage,
    GeolocalizzazioneDeleteDialog,
    GeolocalizzazioneUpdatePage
} from './geolocalizzazione.page-object';

const expect = chai.expect;

describe('Geolocalizzazione e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let geolocalizzazioneUpdatePage: GeolocalizzazioneUpdatePage;
    let geolocalizzazioneComponentsPage: GeolocalizzazioneComponentsPage;
    let geolocalizzazioneDeleteDialog: GeolocalizzazioneDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Geolocalizzaziones', async () => {
        await navBarPage.goToEntity('geolocalizzazione');
        geolocalizzazioneComponentsPage = new GeolocalizzazioneComponentsPage();
        expect(await geolocalizzazioneComponentsPage.getTitle()).to.eq('imprendocasaApp.geolocalizzazione.home.title');
    });

    it('should load create Geolocalizzazione page', async () => {
        await geolocalizzazioneComponentsPage.clickOnCreateButton();
        geolocalizzazioneUpdatePage = new GeolocalizzazioneUpdatePage();
        expect(await geolocalizzazioneUpdatePage.getPageTitle()).to.eq('imprendocasaApp.geolocalizzazione.home.createOrEditLabel');
        await geolocalizzazioneUpdatePage.cancel();
    });

    it('should create and save Geolocalizzaziones', async () => {
        const nbButtonsBeforeCreate = await geolocalizzazioneComponentsPage.countDeleteButtons();

        await geolocalizzazioneComponentsPage.clickOnCreateButton();
        await promise.all([
            geolocalizzazioneUpdatePage.setImmobileInput('immobile'),
            geolocalizzazioneUpdatePage.setLatitudineInput('latitudine'),
            geolocalizzazioneUpdatePage.setLongitudineInput('longitudine')
        ]);
        expect(await geolocalizzazioneUpdatePage.getImmobileInput()).to.eq('immobile');
        expect(await geolocalizzazioneUpdatePage.getLatitudineInput()).to.eq('latitudine');
        expect(await geolocalizzazioneUpdatePage.getLongitudineInput()).to.eq('longitudine');
        await geolocalizzazioneUpdatePage.save();
        expect(await geolocalizzazioneUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await geolocalizzazioneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Geolocalizzazione', async () => {
        const nbButtonsBeforeDelete = await geolocalizzazioneComponentsPage.countDeleteButtons();
        await geolocalizzazioneComponentsPage.clickOnLastDeleteButton();

        geolocalizzazioneDeleteDialog = new GeolocalizzazioneDeleteDialog();
        expect(await geolocalizzazioneDeleteDialog.getDialogTitle()).to.eq('imprendocasaApp.geolocalizzazione.delete.question');
        await geolocalizzazioneDeleteDialog.clickOnConfirmButton();

        expect(await geolocalizzazioneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
