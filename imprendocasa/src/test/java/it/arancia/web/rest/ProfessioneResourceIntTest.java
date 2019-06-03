package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Professione;
import it.arancia.repository.ProfessioneRepository;
import it.arancia.service.ProfessioneService;
import it.arancia.service.dto.ProfessioneDTO;
import it.arancia.service.mapper.ProfessioneMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.ProfessioneCriteria;
import it.arancia.service.ProfessioneQueryService;

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

import javax.persistence.EntityManager;
import java.util.List;


import static it.arancia.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProfessioneResource REST controller.
 *
 * @see ProfessioneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class ProfessioneResourceIntTest {

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMINAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINAZIONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ABILITATO = false;
    private static final Boolean UPDATED_ABILITATO = true;

    @Autowired
    private ProfessioneRepository professioneRepository;

    @Autowired
    private ProfessioneMapper professioneMapper;

    @Autowired
    private ProfessioneService professioneService;

    @Autowired
    private ProfessioneQueryService professioneQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfessioneMockMvc;

    private Professione professione;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfessioneResource professioneResource = new ProfessioneResource(professioneService, professioneQueryService);
        this.restProfessioneMockMvc = MockMvcBuilders.standaloneSetup(professioneResource)
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
    public static Professione createEntity(EntityManager em) {
        Professione professione = new Professione()
            .codice(DEFAULT_CODICE)
            .denominazione(DEFAULT_DENOMINAZIONE)
            .abilitato(DEFAULT_ABILITATO);
        return professione;
    }

    @Before
    public void initTest() {
        professione = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfessione() throws Exception {
        int databaseSizeBeforeCreate = professioneRepository.findAll().size();

        // Create the Professione
        ProfessioneDTO professioneDTO = professioneMapper.toDto(professione);
        restProfessioneMockMvc.perform(post("/api/professiones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professioneDTO)))
            .andExpect(status().isCreated());

        // Validate the Professione in the database
        List<Professione> professioneList = professioneRepository.findAll();
        assertThat(professioneList).hasSize(databaseSizeBeforeCreate + 1);
        Professione testProfessione = professioneList.get(professioneList.size() - 1);
        assertThat(testProfessione.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testProfessione.getDenominazione()).isEqualTo(DEFAULT_DENOMINAZIONE);
        assertThat(testProfessione.isAbilitato()).isEqualTo(DEFAULT_ABILITATO);
    }

    @Test
    @Transactional
    public void createProfessioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = professioneRepository.findAll().size();

        // Create the Professione with an existing ID
        professione.setId(1L);
        ProfessioneDTO professioneDTO = professioneMapper.toDto(professione);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfessioneMockMvc.perform(post("/api/professiones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professioneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Professione in the database
        List<Professione> professioneList = professioneRepository.findAll();
        assertThat(professioneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodiceIsRequired() throws Exception {
        int databaseSizeBeforeTest = professioneRepository.findAll().size();
        // set the field null
        professione.setCodice(null);

        // Create the Professione, which fails.
        ProfessioneDTO professioneDTO = professioneMapper.toDto(professione);

        restProfessioneMockMvc.perform(post("/api/professiones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professioneDTO)))
            .andExpect(status().isBadRequest());

        List<Professione> professioneList = professioneRepository.findAll();
        assertThat(professioneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDenominazioneIsRequired() throws Exception {
        int databaseSizeBeforeTest = professioneRepository.findAll().size();
        // set the field null
        professione.setDenominazione(null);

        // Create the Professione, which fails.
        ProfessioneDTO professioneDTO = professioneMapper.toDto(professione);

        restProfessioneMockMvc.perform(post("/api/professiones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professioneDTO)))
            .andExpect(status().isBadRequest());

        List<Professione> professioneList = professioneRepository.findAll();
        assertThat(professioneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProfessiones() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList
        restProfessioneMockMvc.perform(get("/api/professiones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(professione.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].denominazione").value(hasItem(DEFAULT_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProfessione() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get the professione
        restProfessioneMockMvc.perform(get("/api/professiones/{id}", professione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(professione.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.denominazione").value(DEFAULT_DENOMINAZIONE.toString()))
            .andExpect(jsonPath("$.abilitato").value(DEFAULT_ABILITATO.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllProfessionesByCodiceIsEqualToSomething() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where codice equals to DEFAULT_CODICE
        defaultProfessioneShouldBeFound("codice.equals=" + DEFAULT_CODICE);

        // Get all the professioneList where codice equals to UPDATED_CODICE
        defaultProfessioneShouldNotBeFound("codice.equals=" + UPDATED_CODICE);
    }

    @Test
    @Transactional
    public void getAllProfessionesByCodiceIsInShouldWork() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where codice in DEFAULT_CODICE or UPDATED_CODICE
        defaultProfessioneShouldBeFound("codice.in=" + DEFAULT_CODICE + "," + UPDATED_CODICE);

        // Get all the professioneList where codice equals to UPDATED_CODICE
        defaultProfessioneShouldNotBeFound("codice.in=" + UPDATED_CODICE);
    }

    @Test
    @Transactional
    public void getAllProfessionesByCodiceIsNullOrNotNull() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where codice is not null
        defaultProfessioneShouldBeFound("codice.specified=true");

        // Get all the professioneList where codice is null
        defaultProfessioneShouldNotBeFound("codice.specified=false");
    }

    @Test
    @Transactional
    public void getAllProfessionesByDenominazioneIsEqualToSomething() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where denominazione equals to DEFAULT_DENOMINAZIONE
        defaultProfessioneShouldBeFound("denominazione.equals=" + DEFAULT_DENOMINAZIONE);

        // Get all the professioneList where denominazione equals to UPDATED_DENOMINAZIONE
        defaultProfessioneShouldNotBeFound("denominazione.equals=" + UPDATED_DENOMINAZIONE);
    }

    @Test
    @Transactional
    public void getAllProfessionesByDenominazioneIsInShouldWork() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where denominazione in DEFAULT_DENOMINAZIONE or UPDATED_DENOMINAZIONE
        defaultProfessioneShouldBeFound("denominazione.in=" + DEFAULT_DENOMINAZIONE + "," + UPDATED_DENOMINAZIONE);

        // Get all the professioneList where denominazione equals to UPDATED_DENOMINAZIONE
        defaultProfessioneShouldNotBeFound("denominazione.in=" + UPDATED_DENOMINAZIONE);
    }

    @Test
    @Transactional
    public void getAllProfessionesByDenominazioneIsNullOrNotNull() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where denominazione is not null
        defaultProfessioneShouldBeFound("denominazione.specified=true");

        // Get all the professioneList where denominazione is null
        defaultProfessioneShouldNotBeFound("denominazione.specified=false");
    }

    @Test
    @Transactional
    public void getAllProfessionesByAbilitatoIsEqualToSomething() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where abilitato equals to DEFAULT_ABILITATO
        defaultProfessioneShouldBeFound("abilitato.equals=" + DEFAULT_ABILITATO);

        // Get all the professioneList where abilitato equals to UPDATED_ABILITATO
        defaultProfessioneShouldNotBeFound("abilitato.equals=" + UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void getAllProfessionesByAbilitatoIsInShouldWork() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where abilitato in DEFAULT_ABILITATO or UPDATED_ABILITATO
        defaultProfessioneShouldBeFound("abilitato.in=" + DEFAULT_ABILITATO + "," + UPDATED_ABILITATO);

        // Get all the professioneList where abilitato equals to UPDATED_ABILITATO
        defaultProfessioneShouldNotBeFound("abilitato.in=" + UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void getAllProfessionesByAbilitatoIsNullOrNotNull() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        // Get all the professioneList where abilitato is not null
        defaultProfessioneShouldBeFound("abilitato.specified=true");

        // Get all the professioneList where abilitato is null
        defaultProfessioneShouldNotBeFound("abilitato.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultProfessioneShouldBeFound(String filter) throws Exception {
        restProfessioneMockMvc.perform(get("/api/professiones?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(professione.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].denominazione").value(hasItem(DEFAULT_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));

        // Check, that the count call also returns 1
        restProfessioneMockMvc.perform(get("/api/professiones/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultProfessioneShouldNotBeFound(String filter) throws Exception {
        restProfessioneMockMvc.perform(get("/api/professiones?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProfessioneMockMvc.perform(get("/api/professiones/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingProfessione() throws Exception {
        // Get the professione
        restProfessioneMockMvc.perform(get("/api/professiones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfessione() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        int databaseSizeBeforeUpdate = professioneRepository.findAll().size();

        // Update the professione
        Professione updatedProfessione = professioneRepository.findById(professione.getId()).get();
        // Disconnect from session so that the updates on updatedProfessione are not directly saved in db
        em.detach(updatedProfessione);
        updatedProfessione
            .codice(UPDATED_CODICE)
            .denominazione(UPDATED_DENOMINAZIONE)
            .abilitato(UPDATED_ABILITATO);
        ProfessioneDTO professioneDTO = professioneMapper.toDto(updatedProfessione);

        restProfessioneMockMvc.perform(put("/api/professiones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professioneDTO)))
            .andExpect(status().isOk());

        // Validate the Professione in the database
        List<Professione> professioneList = professioneRepository.findAll();
        assertThat(professioneList).hasSize(databaseSizeBeforeUpdate);
        Professione testProfessione = professioneList.get(professioneList.size() - 1);
        assertThat(testProfessione.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testProfessione.getDenominazione()).isEqualTo(UPDATED_DENOMINAZIONE);
        assertThat(testProfessione.isAbilitato()).isEqualTo(UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void updateNonExistingProfessione() throws Exception {
        int databaseSizeBeforeUpdate = professioneRepository.findAll().size();

        // Create the Professione
        ProfessioneDTO professioneDTO = professioneMapper.toDto(professione);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfessioneMockMvc.perform(put("/api/professiones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professioneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Professione in the database
        List<Professione> professioneList = professioneRepository.findAll();
        assertThat(professioneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfessione() throws Exception {
        // Initialize the database
        professioneRepository.saveAndFlush(professione);

        int databaseSizeBeforeDelete = professioneRepository.findAll().size();

        // Get the professione
        restProfessioneMockMvc.perform(delete("/api/professiones/{id}", professione.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Professione> professioneList = professioneRepository.findAll();
        assertThat(professioneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Professione.class);
        Professione professione1 = new Professione();
        professione1.setId(1L);
        Professione professione2 = new Professione();
        professione2.setId(professione1.getId());
        assertThat(professione1).isEqualTo(professione2);
        professione2.setId(2L);
        assertThat(professione1).isNotEqualTo(professione2);
        professione1.setId(null);
        assertThat(professione1).isNotEqualTo(professione2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfessioneDTO.class);
        ProfessioneDTO professioneDTO1 = new ProfessioneDTO();
        professioneDTO1.setId(1L);
        ProfessioneDTO professioneDTO2 = new ProfessioneDTO();
        assertThat(professioneDTO1).isNotEqualTo(professioneDTO2);
        professioneDTO2.setId(professioneDTO1.getId());
        assertThat(professioneDTO1).isEqualTo(professioneDTO2);
        professioneDTO2.setId(2L);
        assertThat(professioneDTO1).isNotEqualTo(professioneDTO2);
        professioneDTO1.setId(null);
        assertThat(professioneDTO1).isNotEqualTo(professioneDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(professioneMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(professioneMapper.fromId(null)).isNull();
    }
}
