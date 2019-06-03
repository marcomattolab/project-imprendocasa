/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { IncaricoComponentsPage, IncaricoDeleteDialog, IncaricoUpdatePage } from './incarico.page-object';

const expect = chai.expect;

describe('Incarico e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let incaricoUpdatePage: IncaricoUpdatePage;
    let incaricoComponentsPage: IncaricoComponentsPage;
    let incaricoDeleteDialog: IncaricoDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Incaricos', async () => {
        await navBarPage.goToEntity('incarico');
        incaricoComponentsPage = new IncaricoComponentsPage();
        expect(await incaricoComponentsPage.getTitle()).to.eq('imprendocasaApp.incarico.home.title');
    });

    it('should load create Incarico page', async () => {
        await incaricoComponentsPage.clickOnCreateButton();
        incaricoUpdatePage = new IncaricoUpdatePage();
        expect(await incaricoUpdatePage.getPageTitle()).to.eq('imprendocasaApp.incarico.home.createOrEditLabel');
        await incaricoUpdatePage.cancel();
    });

    it('should create and save Incaricos', async () => {
        const nbButtonsBeforeCreate = await incaricoComponentsPage.countDeleteButtons();

        await incaricoComponentsPage.clickOnCreateButton();
        await promise.all([
            incaricoUpdatePage.setRiferimentoInput('riferimento'),
            incaricoUpdatePage.tipoSelectLastOption(),
            incaricoUpdatePage.statoSelectLastOption(),
            incaricoUpdatePage.setAgenteInput('agente'),
            incaricoUpdatePage.setAgenteDiContattoInput('agenteDiContatto'),
            incaricoUpdatePage.setDataContattoInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            incaricoUpdatePage.setDataScadenzaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            incaricoUpdatePage.setPrezzoRichiestaInput('5'),
            incaricoUpdatePage.setPrezzoMinimoInput('5'),
            incaricoUpdatePage.setPrezzoAcquistoInput('5'),
            incaricoUpdatePage.setProvvigioneInput('5'),
            incaricoUpdatePage.setNoteTrattativaInput('noteTrattativa'),
            incaricoUpdatePage.setDatiFiscaliInput('datiFiscali'),
            incaricoUpdatePage.ruoloSelectLastOption(),
            incaricoUpdatePage.alertFiscaliSelectLastOption(),
            incaricoUpdatePage.alertCortesiaSelectLastOption(),
            // incaricoUpdatePage.partnerSelectLastOption(),
            // incaricoUpdatePage.clienteSelectLastOption(),
            // incaricoUpdatePage.acquirenteSelectLastOption(),
            incaricoUpdatePage.immobileSelectLastOption()
        ]);
        expect(await incaricoUpdatePage.getRiferimentoInput()).to.eq('riferimento');
        expect(await incaricoUpdatePage.getAgenteInput()).to.eq('agente');
        expect(await incaricoUpdatePage.getAgenteDiContattoInput()).to.eq('agenteDiContatto');
        expect(await incaricoUpdatePage.getDataContattoInput()).to.contain('2001-01-01T02:30');
        expect(await incaricoUpdatePage.getDataScadenzaInput()).to.contain('2001-01-01T02:30');
        const selectedPrivacy = incaricoUpdatePage.getPrivacyInput();
        if (await selectedPrivacy.isSelected()) {
            await incaricoUpdatePage.getPrivacyInput().click();
            expect(await incaricoUpdatePage.getPrivacyInput().isSelected()).to.be.false;
        } else {
            await incaricoUpdatePage.getPrivacyInput().click();
            expect(await incaricoUpdatePage.getPrivacyInput().isSelected()).to.be.true;
        }
        const selectedAntiriciclaggio = incaricoUpdatePage.getAntiriciclaggioInput();
        if (await selectedAntiriciclaggio.isSelected()) {
            await incaricoUpdatePage.getAntiriciclaggioInput().click();
            expect(await incaricoUpdatePage.getAntiriciclaggioInput().isSelected()).to.be.false;
        } else {
            await incaricoUpdatePage.getAntiriciclaggioInput().click();
            expect(await incaricoUpdatePage.getAntiriciclaggioInput().isSelected()).to.be.true;
        }
        expect(await incaricoUpdatePage.getPrezzoRichiestaInput()).to.eq('5');
        expect(await incaricoUpdatePage.getPrezzoMinimoInput()).to.eq('5');
        expect(await incaricoUpdatePage.getPrezzoAcquistoInput()).to.eq('5');
        expect(await incaricoUpdatePage.getProvvigioneInput()).to.eq('5');
        expect(await incaricoUpdatePage.getNoteTrattativaInput()).to.eq('noteTrattativa');
        expect(await incaricoUpdatePage.getDatiFiscaliInput()).to.eq('datiFiscali');
        const selectedOscuraIncarico = incaricoUpdatePage.getOscuraIncaricoInput();
        if (await selectedOscuraIncarico.isSelected()) {
            await incaricoUpdatePage.getOscuraIncaricoInput().click();
            expect(await incaricoUpdatePage.getOscuraIncaricoInput().isSelected()).to.be.false;
        } else {
            await incaricoUpdatePage.getOscuraIncaricoInput().click();
            expect(await incaricoUpdatePage.getOscuraIncaricoInput().isSelected()).to.be.true;
        }
        await incaricoUpdatePage.save();
        expect(await incaricoUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await incaricoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Incarico', async () => {
        const nbButtonsBeforeDelete = await incaricoComponentsPage.countDeleteButtons();
        await incaricoComponentsPage.clickOnLastDeleteButton();

        incaricoDeleteDialog = new IncaricoDeleteDialog();
        expect(await incaricoDeleteDialog.getDialogTitle()).to.eq('imprendocasaApp.incarico.delete.question');
        await incaricoDeleteDialog.clickOnConfirmButton();

        expect(await incaricoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
