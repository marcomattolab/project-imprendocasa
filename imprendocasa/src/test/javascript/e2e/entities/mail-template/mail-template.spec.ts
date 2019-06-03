/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MailTemplateComponentsPage, MailTemplateDeleteDialog, MailTemplateUpdatePage } from './mail-template.page-object';

const expect = chai.expect;

describe('MailTemplate e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let mailTemplateUpdatePage: MailTemplateUpdatePage;
    let mailTemplateComponentsPage: MailTemplateComponentsPage;
    let mailTemplateDeleteDialog: MailTemplateDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load MailTemplates', async () => {
        await navBarPage.goToEntity('mail-template');
        mailTemplateComponentsPage = new MailTemplateComponentsPage();
        expect(await mailTemplateComponentsPage.getTitle()).to.eq('imprendocasaApp.mailTemplate.home.title');
    });

    it('should load create MailTemplate page', async () => {
        await mailTemplateComponentsPage.clickOnCreateButton();
        mailTemplateUpdatePage = new MailTemplateUpdatePage();
        expect(await mailTemplateUpdatePage.getPageTitle()).to.eq('imprendocasaApp.mailTemplate.home.createOrEditLabel');
        await mailTemplateUpdatePage.cancel();
    });

    it('should create and save MailTemplates', async () => {
        const nbButtonsBeforeCreate = await mailTemplateComponentsPage.countDeleteButtons();

        await mailTemplateComponentsPage.clickOnCreateButton();
        await promise.all([mailTemplateUpdatePage.setCategoriaInput('categoria'), mailTemplateUpdatePage.setModelloInput('modello')]);
        expect(await mailTemplateUpdatePage.getCategoriaInput()).to.eq('categoria');
        expect(await mailTemplateUpdatePage.getModelloInput()).to.eq('modello');
        const selectedAbilitato = mailTemplateUpdatePage.getAbilitatoInput();
        if (await selectedAbilitato.isSelected()) {
            await mailTemplateUpdatePage.getAbilitatoInput().click();
            expect(await mailTemplateUpdatePage.getAbilitatoInput().isSelected()).to.be.false;
        } else {
            await mailTemplateUpdatePage.getAbilitatoInput().click();
            expect(await mailTemplateUpdatePage.getAbilitatoInput().isSelected()).to.be.true;
        }
        await mailTemplateUpdatePage.save();
        expect(await mailTemplateUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await mailTemplateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last MailTemplate', async () => {
        const nbButtonsBeforeDelete = await mailTemplateComponentsPage.countDeleteButtons();
        await mailTemplateComponentsPage.clickOnLastDeleteButton();

        mailTemplateDeleteDialog = new MailTemplateDeleteDialog();
        expect(await mailTemplateDeleteDialog.getDialogTitle()).to.eq('imprendocasaApp.mailTemplate.delete.question');
        await mailTemplateDeleteDialog.clickOnConfirmButton();

        expect(await mailTemplateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
