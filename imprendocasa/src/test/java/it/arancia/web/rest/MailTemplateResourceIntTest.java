package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.MailTemplate;
import it.arancia.repository.MailTemplateRepository;
import it.arancia.service.MailTemplateService;
import it.arancia.service.dto.MailTemplateDTO;
import it.arancia.service.mapper.MailTemplateMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.MailTemplateCriteria;
import it.arancia.service.MailTemplateQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static it.arancia.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.arancia.domain.enumeration.CategoriaNotifica;
/**
 * Test class for the MailTemplateResource REST controller.
 *
 * @see MailTemplateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class MailTemplateResourceIntTest {

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final CategoriaNotifica DEFAULT_CATEGORIA = CategoriaNotifica.COMPLEANNO;
    private static final CategoriaNotifica UPDATED_CATEGORIA = CategoriaNotifica.FATTURA;

    private static final String DEFAULT_OGGETTO = "AAAAAAAAAA";
    private static final String UPDATED_OGGETTO = "BBBBBBBBBB";

    private static final String DEFAULT_MODELLO = "AAAAAAAAAA";
    private static final String UPDATED_MODELLO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ABILITATO = false;
    private static final Boolean UPDATED_ABILITATO = true;

    @Autowired
    private MailTemplateRepository mailTemplateRepository;

    @Autowired
    private MailTemplateMapper mailTemplateMapper;

    @Autowired
    private MailTemplateService mailTemplateService;

    @Autowired
    private MailTemplateQueryService mailTemplateQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMailTemplateMockMvc;

    private MailTemplate mailTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MailTemplateResource mailTemplateResource = new MailTemplateResource(mailTemplateService, mailTemplateQueryService);
        this.restMailTemplateMockMvc = MockMvcBuilders.standaloneSetup(mailTemplateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MailTemplate createEntity(EntityManager em) {
        MailTemplate mailTemplate = new MailTemplate()
            .codice(DEFAULT_CODICE)
            .categoria(DEFAULT_CATEGORIA)
            .oggetto(DEFAULT_OGGETTO)
            .modello(DEFAULT_MODELLO)
            .abilitato(DEFAULT_ABILITATO);
        return mailTemplate;
    }

    @Before
    public void initTest() {
        mailTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createMailTemplate() throws Exception {
        int databaseSizeBeforeCreate = mailTemplateRepository.findAll().size();

        // Create the MailTemplate
        MailTemplateDTO mailTemplateDTO = mailTemplateMapper.toDto(mailTemplate);
        restMailTemplateMockMvc.perform(post("/api/mail-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the MailTemplate in the database
        List<MailTemplate> mailTemplateList = mailTemplateRepository.findAll();
        assertThat(mailTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        MailTemplate testMailTemplate = mailTemplateList.get(mailTemplateList.size() - 1);
        assertThat(testMailTemplate.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testMailTemplate.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testMailTemplate.getOggetto()).isEqualTo(DEFAULT_OGGETTO);
        assertThat(testMailTemplate.getModello()).isEqualTo(DEFAULT_MODELLO);
        assertThat(testMailTemplate.isAbilitato()).isEqualTo(DEFAULT_ABILITATO);
    }

    @Test
    @Transactional
    public void createMailTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mailTemplateRepository.findAll().size();

        // Create the MailTemplate with an existing ID
        mailTemplate.setId(1L);
        MailTemplateDTO mailTemplateDTO = mailTemplateMapper.toDto(mailTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMailTemplateMockMvc.perform(post("/api/mail-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MailTemplate in the database
        List<MailTemplate> mailTemplateList = mailTemplateRepository.findAll();
        assertThat(mailTemplateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodiceIsRequired() throws Exception {
        int databaseSizeBeforeTest = mailTemplateRepository.findAll().size();
        // set the field null
        mailTemplate.setCodice(null);

        // Create the MailTemplate, which fails.
        MailTemplateDTO mailTemplateDTO = mailTemplateMapper.toDto(mailTemplate);

        restMailTemplateMockMvc.perform(post("/api/mail-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<MailTemplate> mailTemplateList = mailTemplateRepository.findAll();
        assertThat(mailTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoriaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mailTemplateRepository.findAll().size();
        // set the field null
        mailTemplate.setCategoria(null);

        // Create the MailTemplate, which fails.
        MailTemplateDTO mailTemplateDTO = mailTemplateMapper.toDto(mailTemplate);

        restMailTemplateMockMvc.perform(post("/api/mail-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<MailTemplate> mailTemplateList = mailTemplateRepository.findAll();
        assertThat(mailTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMailTemplates() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList
        restMailTemplateMockMvc.perform(get("/api/mail-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mailTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].oggetto").value(hasItem(DEFAULT_OGGETTO.toString())))
            .andExpect(jsonPath("$.[*].modello").value(hasItem(DEFAULT_MODELLO.toString())))
            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getMailTemplate() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get the mailTemplate
        restMailTemplateMockMvc.perform(get("/api/mail-templates/{id}", mailTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mailTemplate.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.oggetto").value(DEFAULT_OGGETTO.toString()))
            .andExpect(jsonPath("$.modello").value(DEFAULT_MODELLO.toString()))
            .andExpect(jsonPath("$.abilitato").value(DEFAULT_ABILITATO.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByCodiceIsEqualToSomething() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where codice equals to DEFAULT_CODICE
        defaultMailTemplateShouldBeFound("codice.equals=" + DEFAULT_CODICE);

        // Get all the mailTemplateList where codice equals to UPDATED_CODICE
        defaultMailTemplateShouldNotBeFound("codice.equals=" + UPDATED_CODICE);
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByCodiceIsInShouldWork() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where codice in DEFAULT_CODICE or UPDATED_CODICE
        defaultMailTemplateShouldBeFound("codice.in=" + DEFAULT_CODICE + "," + UPDATED_CODICE);

        // Get all the mailTemplateList where codice equals to UPDATED_CODICE
        defaultMailTemplateShouldNotBeFound("codice.in=" + UPDATED_CODICE);
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByCodiceIsNullOrNotNull() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where codice is not null
        defaultMailTemplateShouldBeFound("codice.specified=true");

        // Get all the mailTemplateList where codice is null
        defaultMailTemplateShouldNotBeFound("codice.specified=false");
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByCategoriaIsEqualToSomething() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where categoria equals to DEFAULT_CATEGORIA
        defaultMailTemplateShouldBeFound("categoria.equals=" + DEFAULT_CATEGORIA);

        // Get all the mailTemplateList where categoria equals to UPDATED_CATEGORIA
        defaultMailTemplateShouldNotBeFound("categoria.equals=" + UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByCategoriaIsInShouldWork() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where categoria in DEFAULT_CATEGORIA or UPDATED_CATEGORIA
        defaultMailTemplateShouldBeFound("categoria.in=" + DEFAULT_CATEGORIA + "," + UPDATED_CATEGORIA);

        // Get all the mailTemplateList where categoria equals to UPDATED_CATEGORIA
        defaultMailTemplateShouldNotBeFound("categoria.in=" + UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByCategoriaIsNullOrNotNull() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where categoria is not null
        defaultMailTemplateShouldBeFound("categoria.specified=true");

        // Get all the mailTemplateList where categoria is null
        defaultMailTemplateShouldNotBeFound("categoria.specified=false");
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByOggettoIsEqualToSomething() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where oggetto equals to DEFAULT_OGGETTO
        defaultMailTemplateShouldBeFound("oggetto.equals=" + DEFAULT_OGGETTO);

        // Get all the mailTemplateList where oggetto equals to UPDATED_OGGETTO
        defaultMailTemplateShouldNotBeFound("oggetto.equals=" + UPDATED_OGGETTO);
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByOggettoIsInShouldWork() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where oggetto in DEFAULT_OGGETTO or UPDATED_OGGETTO
        defaultMailTemplateShouldBeFound("oggetto.in=" + DEFAULT_OGGETTO + "," + UPDATED_OGGETTO);

        // Get all the mailTemplateList where oggetto equals to UPDATED_OGGETTO
        defaultMailTemplateShouldNotBeFound("oggetto.in=" + UPDATED_OGGETTO);
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByOggettoIsNullOrNotNull() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where oggetto is not null
        defaultMailTemplateShouldBeFound("oggetto.specified=true");

        // Get all the mailTemplateList where oggetto is null
        defaultMailTemplateShouldNotBeFound("oggetto.specified=false");
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByAbilitatoIsEqualToSomething() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where abilitato equals to DEFAULT_ABILITATO
        defaultMailTemplateShouldBeFound("abilitato.equals=" + DEFAULT_ABILITATO);

        // Get all the mailTemplateList where abilitato equals to UPDATED_ABILITATO
        defaultMailTemplateShouldNotBeFound("abilitato.equals=" + UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByAbilitatoIsInShouldWork() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where abilitato in DEFAULT_ABILITATO or UPDATED_ABILITATO
        defaultMailTemplateShouldBeFound("abilitato.in=" + DEFAULT_ABILITATO + "," + UPDATED_ABILITATO);

        // Get all the mailTemplateList where abilitato equals to UPDATED_ABILITATO
        defaultMailTemplateShouldNotBeFound("abilitato.in=" + UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void getAllMailTemplatesByAbilitatoIsNullOrNotNull() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        // Get all the mailTemplateList where abilitato is not null
        defaultMailTemplateShouldBeFound("abilitato.specified=true");

        // Get all the mailTemplateList where abilitato is null
        defaultMailTemplateShouldNotBeFound("abilitato.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMailTemplateShouldBeFound(String filter) throws Exception {
        restMailTemplateMockMvc.perform(get("/api/mail-templates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mailTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].oggetto").value(hasItem(DEFAULT_OGGETTO.toString())))
            .andExpect(jsonPath("$.[*].modello").value(hasItem(DEFAULT_MODELLO.toString())))
            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));

        // Check, that the count call also returns 1
        restMailTemplateMockMvc.perform(get("/api/mail-templates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMailTemplateShouldNotBeFound(String filter) throws Exception {
        restMailTemplateMockMvc.perform(get("/api/mail-templates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMailTemplateMockMvc.perform(get("/api/mail-templates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMailTemplate() throws Exception {
        // Get the mailTemplate
        restMailTemplateMockMvc.perform(get("/api/mail-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMailTemplate() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        int databaseSizeBeforeUpdate = mailTemplateRepository.findAll().size();

        // Update the mailTemplate
        MailTemplate updatedMailTemplate = mailTemplateRepository.findById(mailTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedMailTemplate are not directly saved in db
        em.detach(updatedMailTemplate);
        updatedMailTemplate
            .codice(UPDATED_CODICE)
            .categoria(UPDATED_CATEGORIA)
            .oggetto(UPDATED_OGGETTO)
            .modello(UPDATED_MODELLO)
            .abilitato(UPDATED_ABILITATO);
        MailTemplateDTO mailTemplateDTO = mailTemplateMapper.toDto(updatedMailTemplate);

        restMailTemplateMockMvc.perform(put("/api/mail-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the MailTemplate in the database
        List<MailTemplate> mailTemplateList = mailTemplateRepository.findAll();
        assertThat(mailTemplateList).hasSize(databaseSizeBeforeUpdate);
        MailTemplate testMailTemplate = mailTemplateList.get(mailTemplateList.size() - 1);
        assertThat(testMailTemplate.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testMailTemplate.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testMailTemplate.getOggetto()).isEqualTo(UPDATED_OGGETTO);
        assertThat(testMailTemplate.getModello()).isEqualTo(UPDATED_MODELLO);
        assertThat(testMailTemplate.isAbilitato()).isEqualTo(UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void updateNonExistingMailTemplate() throws Exception {
        int databaseSizeBeforeUpdate = mailTemplateRepository.findAll().size();

        // Create the MailTemplate
        MailTemplateDTO mailTemplateDTO = mailTemplateMapper.toDto(mailTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMailTemplateMockMvc.perform(put("/api/mail-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MailTemplate in the database
        List<MailTemplate> mailTemplateList = mailTemplateRepository.findAll();
        assertThat(mailTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMailTemplate() throws Exception {
        // Initialize the database
        mailTemplateRepository.saveAndFlush(mailTemplate);

        int databaseSizeBeforeDelete = mailTemplateRepository.findAll().size();

        // Get the mailTemplate
        restMailTemplateMockMvc.perform(delete("/api/mail-templates/{id}", mailTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MailTemplate> mailTemplateList = mailTemplateRepository.findAll();
        assertThat(mailTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MailTemplate.class);
        MailTemplate mailTemplate1 = new MailTemplate();
        mailTemplate1.setId(1L);
        MailTemplate mailTemplate2 = new MailTemplate();
        mailTemplate2.setId(mailTemplate1.getId());
        assertThat(mailTemplate1).isEqualTo(mailTemplate2);
        mailTemplate2.setId(2L);
        assertThat(mailTemplate1).isNotEqualTo(mailTemplate2);
        mailTemplate1.setId(null);
        assertThat(mailTemplate1).isNotEqualTo(mailTemplate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MailTemplateDTO.class);
        MailTemplateDTO mailTemplateDTO1 = new MailTemplateDTO();
        mailTemplateDTO1.setId(1L);
        MailTemplateDTO mailTemplateDTO2 = new MailTemplateDTO();
        assertThat(mailTemplateDTO1).isNotEqualTo(mailTemplateDTO2);
        mailTemplateDTO2.setId(mailTemplateDTO1.getId());
        assertThat(mailTemplateDTO1).isEqualTo(mailTemplateDTO2);
        mailTemplateDTO2.setId(2L);
        assertThat(mailTemplateDTO1).isNotEqualTo(mailTemplateDTO2);
        mailTemplateDTO1.setId(null);
        assertThat(mailTemplateDTO1).isNotEqualTo(mailTemplateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mailTemplateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mailTemplateMapper.fromId(null)).isNull();
    }
}
