package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Immobile;
import it.arancia.domain.Geolocalizzazione;
import it.arancia.domain.Files;
import it.arancia.domain.Incarico;
import it.arancia.repository.ImmobileRepository;
import it.arancia.service.ImmobileService;
import it.arancia.service.dto.ImmobileDTO;
import it.arancia.service.mapper.ImmobileMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.ImmobileCriteria;
import it.arancia.service.ImmobileQueryService;

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


//import static it.arancia.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ImmobileResource REST controller.
 *
 * @see ImmobileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class ImmobileResourceIntTest {

//    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
//    private static final String UPDATED_CODICE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
//    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_INDIRIZZO = "AAAAAAAAAA";
//    private static final String UPDATED_INDIRIZZO = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CAP = "AAAAAAAAAA";
//    private static final String UPDATED_CAP = "BBBBBBBBBB";
//
//    private static final String DEFAULT_REGIONE = "AAAAAAAAAA";
//    private static final String UPDATED_REGIONE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PROVINCIA = "AAAAAAAAAA";
//    private static final String UPDATED_PROVINCIA = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CITTA = "AAAAAAAAAA";
//    private static final String UPDATED_CITTA = "BBBBBBBBBB";
//
//    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
//    private static final String UPDATED_NOTE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PATH_FOLDER = "AAAAAAAAAA";
//    private static final String UPDATED_PATH_FOLDER = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DATI_CATASTALI = "AAAAAAAAAA";
//    private static final String UPDATED_DATI_CATASTALI = "BBBBBBBBBB";
//
//    @Autowired
//    private ImmobileRepository immobileRepository;
//
//    @Autowired
//    private ImmobileMapper immobileMapper;
//
//    @Autowired
//    private ImmobileService immobileService;
//
//    @Autowired
//    private ImmobileQueryService immobileQueryService;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    private MockMvc restImmobileMockMvc;
//
//    private Immobile immobile;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final ImmobileResource immobileResource = new ImmobileResource(immobileService, immobileQueryService);
//        this.restImmobileMockMvc = MockMvcBuilders.standaloneSetup(immobileResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
////            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Immobile createEntity(EntityManager em) {
//        Immobile immobile = new Immobile()
//            .codice(DEFAULT_CODICE)
//            .descrizione(DEFAULT_DESCRIZIONE)
//            .indirizzo(DEFAULT_INDIRIZZO)
//            .cap(DEFAULT_CAP)
//            .regione(DEFAULT_REGIONE)
//            .provincia(DEFAULT_PROVINCIA)
//            .citta(DEFAULT_CITTA)
//            .note(DEFAULT_NOTE)
//            .pathFolder(DEFAULT_PATH_FOLDER)
//            .datiCatastali(DEFAULT_DATI_CATASTALI);
//        return immobile;
//    }
//
//    @Before
//    public void initTest() {
//        immobile = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createImmobile() throws Exception {
//        int databaseSizeBeforeCreate = immobileRepository.findAll().size();
//
//        // Create the Immobile
//        ImmobileDTO immobileDTO = immobileMapper.toDto(immobile);
//        restImmobileMockMvc.perform(post("/api/immobiles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(immobileDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Immobile in the database
//        List<Immobile> immobileList = immobileRepository.findAll();
//        assertThat(immobileList).hasSize(databaseSizeBeforeCreate + 1);
//        Immobile testImmobile = immobileList.get(immobileList.size() - 1);
//        assertThat(testImmobile.getCodice()).isEqualTo(DEFAULT_CODICE);
//        assertThat(testImmobile.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
//        assertThat(testImmobile.getIndirizzo()).isEqualTo(DEFAULT_INDIRIZZO);
//        assertThat(testImmobile.getCap()).isEqualTo(DEFAULT_CAP);
//        assertThat(testImmobile.getRegione()).isEqualTo(DEFAULT_REGIONE);
//        assertThat(testImmobile.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
//        assertThat(testImmobile.getCitta()).isEqualTo(DEFAULT_CITTA);
//        assertThat(testImmobile.getNote()).isEqualTo(DEFAULT_NOTE);
//        assertThat(testImmobile.getPathFolder()).isEqualTo(DEFAULT_PATH_FOLDER);
//        assertThat(testImmobile.getDatiCatastali()).isEqualTo(DEFAULT_DATI_CATASTALI);
//    }
//
//    @Test
//    @Transactional
//    public void createImmobileWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = immobileRepository.findAll().size();
//
//        // Create the Immobile with an existing ID
//        immobile.setId(1L);
//        ImmobileDTO immobileDTO = immobileMapper.toDto(immobile);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restImmobileMockMvc.perform(post("/api/immobiles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(immobileDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Immobile in the database
//        List<Immobile> immobileList = immobileRepository.findAll();
//        assertThat(immobileList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobiles() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList
//        restImmobileMockMvc.perform(get("/api/immobiles?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(immobile.getId().intValue())))
//            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
//            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
//            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO.toString())))
//            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP.toString())))
//            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE.toString())))
//            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA.toString())))
//            .andExpect(jsonPath("$.[*].citta").value(hasItem(DEFAULT_CITTA.toString())))
//            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
//            .andExpect(jsonPath("$.[*].pathFolder").value(hasItem(DEFAULT_PATH_FOLDER.toString())))
//            .andExpect(jsonPath("$.[*].datiCatastali").value(hasItem(DEFAULT_DATI_CATASTALI.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getImmobile() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get the immobile
//        restImmobileMockMvc.perform(get("/api/immobiles/{id}", immobile.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(immobile.getId().intValue()))
//            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
//            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()))
//            .andExpect(jsonPath("$.indirizzo").value(DEFAULT_INDIRIZZO.toString()))
//            .andExpect(jsonPath("$.cap").value(DEFAULT_CAP.toString()))
//            .andExpect(jsonPath("$.regione").value(DEFAULT_REGIONE.toString()))
//            .andExpect(jsonPath("$.provincia").value(DEFAULT_PROVINCIA.toString()))
//            .andExpect(jsonPath("$.citta").value(DEFAULT_CITTA.toString()))
//            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
//            .andExpect(jsonPath("$.pathFolder").value(DEFAULT_PATH_FOLDER.toString()))
//            .andExpect(jsonPath("$.datiCatastali").value(DEFAULT_DATI_CATASTALI.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCodiceIsEqualToSomething() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where codice equals to DEFAULT_CODICE
//        defaultImmobileShouldBeFound("codice.equals=" + DEFAULT_CODICE);
//
//        // Get all the immobileList where codice equals to UPDATED_CODICE
//        defaultImmobileShouldNotBeFound("codice.equals=" + UPDATED_CODICE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCodiceIsInShouldWork() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where codice in DEFAULT_CODICE or UPDATED_CODICE
//        defaultImmobileShouldBeFound("codice.in=" + DEFAULT_CODICE + "," + UPDATED_CODICE);
//
//        // Get all the immobileList where codice equals to UPDATED_CODICE
//        defaultImmobileShouldNotBeFound("codice.in=" + UPDATED_CODICE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCodiceIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where codice is not null
//        defaultImmobileShouldBeFound("codice.specified=true");
//
//        // Get all the immobileList where codice is null
//        defaultImmobileShouldNotBeFound("codice.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByIndirizzoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where indirizzo equals to DEFAULT_INDIRIZZO
//        defaultImmobileShouldBeFound("indirizzo.equals=" + DEFAULT_INDIRIZZO);
//
//        // Get all the immobileList where indirizzo equals to UPDATED_INDIRIZZO
//        defaultImmobileShouldNotBeFound("indirizzo.equals=" + UPDATED_INDIRIZZO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByIndirizzoIsInShouldWork() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where indirizzo in DEFAULT_INDIRIZZO or UPDATED_INDIRIZZO
//        defaultImmobileShouldBeFound("indirizzo.in=" + DEFAULT_INDIRIZZO + "," + UPDATED_INDIRIZZO);
//
//        // Get all the immobileList where indirizzo equals to UPDATED_INDIRIZZO
//        defaultImmobileShouldNotBeFound("indirizzo.in=" + UPDATED_INDIRIZZO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByIndirizzoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where indirizzo is not null
//        defaultImmobileShouldBeFound("indirizzo.specified=true");
//
//        // Get all the immobileList where indirizzo is null
//        defaultImmobileShouldNotBeFound("indirizzo.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCapIsEqualToSomething() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where cap equals to DEFAULT_CAP
//        defaultImmobileShouldBeFound("cap.equals=" + DEFAULT_CAP);
//
//        // Get all the immobileList where cap equals to UPDATED_CAP
//        defaultImmobileShouldNotBeFound("cap.equals=" + UPDATED_CAP);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCapIsInShouldWork() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where cap in DEFAULT_CAP or UPDATED_CAP
//        defaultImmobileShouldBeFound("cap.in=" + DEFAULT_CAP + "," + UPDATED_CAP);
//
//        // Get all the immobileList where cap equals to UPDATED_CAP
//        defaultImmobileShouldNotBeFound("cap.in=" + UPDATED_CAP);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCapIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where cap is not null
//        defaultImmobileShouldBeFound("cap.specified=true");
//
//        // Get all the immobileList where cap is null
//        defaultImmobileShouldNotBeFound("cap.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByRegioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where regione equals to DEFAULT_REGIONE
//        defaultImmobileShouldBeFound("regione.equals=" + DEFAULT_REGIONE);
//
//        // Get all the immobileList where regione equals to UPDATED_REGIONE
//        defaultImmobileShouldNotBeFound("regione.equals=" + UPDATED_REGIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByRegioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where regione in DEFAULT_REGIONE or UPDATED_REGIONE
//        defaultImmobileShouldBeFound("regione.in=" + DEFAULT_REGIONE + "," + UPDATED_REGIONE);
//
//        // Get all the immobileList where regione equals to UPDATED_REGIONE
//        defaultImmobileShouldNotBeFound("regione.in=" + UPDATED_REGIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByRegioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where regione is not null
//        defaultImmobileShouldBeFound("regione.specified=true");
//
//        // Get all the immobileList where regione is null
//        defaultImmobileShouldNotBeFound("regione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByProvinciaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where provincia equals to DEFAULT_PROVINCIA
//        defaultImmobileShouldBeFound("provincia.equals=" + DEFAULT_PROVINCIA);
//
//        // Get all the immobileList where provincia equals to UPDATED_PROVINCIA
//        defaultImmobileShouldNotBeFound("provincia.equals=" + UPDATED_PROVINCIA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByProvinciaIsInShouldWork() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where provincia in DEFAULT_PROVINCIA or UPDATED_PROVINCIA
//        defaultImmobileShouldBeFound("provincia.in=" + DEFAULT_PROVINCIA + "," + UPDATED_PROVINCIA);
//
//        // Get all the immobileList where provincia equals to UPDATED_PROVINCIA
//        defaultImmobileShouldNotBeFound("provincia.in=" + UPDATED_PROVINCIA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByProvinciaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where provincia is not null
//        defaultImmobileShouldBeFound("provincia.specified=true");
//
//        // Get all the immobileList where provincia is null
//        defaultImmobileShouldNotBeFound("provincia.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCittaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where citta equals to DEFAULT_CITTA
//        defaultImmobileShouldBeFound("citta.equals=" + DEFAULT_CITTA);
//
//        // Get all the immobileList where citta equals to UPDATED_CITTA
//        defaultImmobileShouldNotBeFound("citta.equals=" + UPDATED_CITTA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCittaIsInShouldWork() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where citta in DEFAULT_CITTA or UPDATED_CITTA
//        defaultImmobileShouldBeFound("citta.in=" + DEFAULT_CITTA + "," + UPDATED_CITTA);
//
//        // Get all the immobileList where citta equals to UPDATED_CITTA
//        defaultImmobileShouldNotBeFound("citta.in=" + UPDATED_CITTA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByCittaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where citta is not null
//        defaultImmobileShouldBeFound("citta.specified=true");
//
//        // Get all the immobileList where citta is null
//        defaultImmobileShouldNotBeFound("citta.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByPathFolderIsEqualToSomething() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where pathFolder equals to DEFAULT_PATH_FOLDER
//        defaultImmobileShouldBeFound("pathFolder.equals=" + DEFAULT_PATH_FOLDER);
//
//        // Get all the immobileList where pathFolder equals to UPDATED_PATH_FOLDER
//        defaultImmobileShouldNotBeFound("pathFolder.equals=" + UPDATED_PATH_FOLDER);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByPathFolderIsInShouldWork() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where pathFolder in DEFAULT_PATH_FOLDER or UPDATED_PATH_FOLDER
//        defaultImmobileShouldBeFound("pathFolder.in=" + DEFAULT_PATH_FOLDER + "," + UPDATED_PATH_FOLDER);
//
//        // Get all the immobileList where pathFolder equals to UPDATED_PATH_FOLDER
//        defaultImmobileShouldNotBeFound("pathFolder.in=" + UPDATED_PATH_FOLDER);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByPathFolderIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where pathFolder is not null
//        defaultImmobileShouldBeFound("pathFolder.specified=true");
//
//        // Get all the immobileList where pathFolder is null
//        defaultImmobileShouldNotBeFound("pathFolder.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByDatiCatastaliIsEqualToSomething() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where datiCatastali equals to DEFAULT_DATI_CATASTALI
//        defaultImmobileShouldBeFound("datiCatastali.equals=" + DEFAULT_DATI_CATASTALI);
//
//        // Get all the immobileList where datiCatastali equals to UPDATED_DATI_CATASTALI
//        defaultImmobileShouldNotBeFound("datiCatastali.equals=" + UPDATED_DATI_CATASTALI);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByDatiCatastaliIsInShouldWork() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where datiCatastali in DEFAULT_DATI_CATASTALI or UPDATED_DATI_CATASTALI
//        defaultImmobileShouldBeFound("datiCatastali.in=" + DEFAULT_DATI_CATASTALI + "," + UPDATED_DATI_CATASTALI);
//
//        // Get all the immobileList where datiCatastali equals to UPDATED_DATI_CATASTALI
//        defaultImmobileShouldNotBeFound("datiCatastali.in=" + UPDATED_DATI_CATASTALI);
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByDatiCatastaliIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        // Get all the immobileList where datiCatastali is not null
//        defaultImmobileShouldBeFound("datiCatastali.specified=true");
//
//        // Get all the immobileList where datiCatastali is null
//        defaultImmobileShouldNotBeFound("datiCatastali.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByGeolocalizzazioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Geolocalizzazione geolocalizzazione = GeolocalizzazioneResourceIntTest.createEntity(em);
//        em.persist(geolocalizzazione);
//        em.flush();
//        immobile.setGeolocalizzazione(geolocalizzazione);
//        immobileRepository.saveAndFlush(immobile);
//        Long geolocalizzazioneId = geolocalizzazione.getId();
//
//        // Get all the immobileList where geolocalizzazione equals to geolocalizzazioneId
//        defaultImmobileShouldBeFound("geolocalizzazioneId.equals=" + geolocalizzazioneId);
//
//        // Get all the immobileList where geolocalizzazione equals to geolocalizzazioneId + 1
//        defaultImmobileShouldNotBeFound("geolocalizzazioneId.equals=" + (geolocalizzazioneId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByFilesIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Files files = FilesResourceIntTest.createEntity(em);
//        em.persist(files);
//        em.flush();
//        immobile.addFiles(files);
//        immobileRepository.saveAndFlush(immobile);
//        Long filesId = files.getId();
//
//        // Get all the immobileList where files equals to filesId
//        defaultImmobileShouldBeFound("filesId.equals=" + filesId);
//
//        // Get all the immobileList where files equals to filesId + 1
//        defaultImmobileShouldNotBeFound("filesId.equals=" + (filesId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllImmobilesByIncaricoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Incarico incarico = IncaricoResourceIntTest.createEntity(em);
//        em.persist(incarico);
//        em.flush();
//        immobile.addIncarico(incarico);
//        immobileRepository.saveAndFlush(immobile);
//        Long incaricoId = incarico.getId();
//
//        // Get all the immobileList where incarico equals to incaricoId
//        defaultImmobileShouldBeFound("incaricoId.equals=" + incaricoId);
//
//        // Get all the immobileList where incarico equals to incaricoId + 1
//        defaultImmobileShouldNotBeFound("incaricoId.equals=" + (incaricoId + 1));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is returned
//     */
//    private void defaultImmobileShouldBeFound(String filter) throws Exception {
//        restImmobileMockMvc.perform(get("/api/immobiles?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(immobile.getId().intValue())))
//            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
//            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
//            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO.toString())))
//            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP.toString())))
//            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE.toString())))
//            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA.toString())))
//            .andExpect(jsonPath("$.[*].citta").value(hasItem(DEFAULT_CITTA.toString())))
//            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
//            .andExpect(jsonPath("$.[*].pathFolder").value(hasItem(DEFAULT_PATH_FOLDER.toString())))
//            .andExpect(jsonPath("$.[*].datiCatastali").value(hasItem(DEFAULT_DATI_CATASTALI.toString())));
//
//        // Check, that the count call also returns 1
//        restImmobileMockMvc.perform(get("/api/immobiles/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("1"));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is not returned
//     */
//    private void defaultImmobileShouldNotBeFound(String filter) throws Exception {
//        restImmobileMockMvc.perform(get("/api/immobiles?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$").isArray())
//            .andExpect(jsonPath("$").isEmpty());
//
//        // Check, that the count call also returns 0
//        restImmobileMockMvc.perform(get("/api/immobiles/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("0"));
//    }
//
//
//    @Test
//    @Transactional
//    public void getNonExistingImmobile() throws Exception {
//        // Get the immobile
//        restImmobileMockMvc.perform(get("/api/immobiles/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateImmobile() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        int databaseSizeBeforeUpdate = immobileRepository.findAll().size();
//
//        // Update the immobile
//        Immobile updatedImmobile = immobileRepository.findById(immobile.getId()).get();
//        // Disconnect from session so that the updates on updatedImmobile are not directly saved in db
//        em.detach(updatedImmobile);
//        updatedImmobile
//            .codice(UPDATED_CODICE)
//            .descrizione(UPDATED_DESCRIZIONE)
//            .indirizzo(UPDATED_INDIRIZZO)
//            .cap(UPDATED_CAP)
//            .regione(UPDATED_REGIONE)
//            .provincia(UPDATED_PROVINCIA)
//            .citta(UPDATED_CITTA)
//            .note(UPDATED_NOTE)
//            .pathFolder(UPDATED_PATH_FOLDER)
//            .datiCatastali(UPDATED_DATI_CATASTALI);
//        ImmobileDTO immobileDTO = immobileMapper.toDto(updatedImmobile);
//
//        restImmobileMockMvc.perform(put("/api/immobiles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(immobileDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Immobile in the database
//        List<Immobile> immobileList = immobileRepository.findAll();
//        assertThat(immobileList).hasSize(databaseSizeBeforeUpdate);
//        Immobile testImmobile = immobileList.get(immobileList.size() - 1);
//        assertThat(testImmobile.getCodice()).isEqualTo(UPDATED_CODICE);
//        assertThat(testImmobile.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
//        assertThat(testImmobile.getIndirizzo()).isEqualTo(UPDATED_INDIRIZZO);
//        assertThat(testImmobile.getCap()).isEqualTo(UPDATED_CAP);
//        assertThat(testImmobile.getRegione()).isEqualTo(UPDATED_REGIONE);
//        assertThat(testImmobile.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
//        assertThat(testImmobile.getCitta()).isEqualTo(UPDATED_CITTA);
//        assertThat(testImmobile.getNote()).isEqualTo(UPDATED_NOTE);
//        assertThat(testImmobile.getPathFolder()).isEqualTo(UPDATED_PATH_FOLDER);
//        assertThat(testImmobile.getDatiCatastali()).isEqualTo(UPDATED_DATI_CATASTALI);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingImmobile() throws Exception {
//        int databaseSizeBeforeUpdate = immobileRepository.findAll().size();
//
//        // Create the Immobile
//        ImmobileDTO immobileDTO = immobileMapper.toDto(immobile);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restImmobileMockMvc.perform(put("/api/immobiles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(immobileDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Immobile in the database
//        List<Immobile> immobileList = immobileRepository.findAll();
//        assertThat(immobileList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteImmobile() throws Exception {
//        // Initialize the database
//        immobileRepository.saveAndFlush(immobile);
//
//        int databaseSizeBeforeDelete = immobileRepository.findAll().size();
//
//        // Get the immobile
//        restImmobileMockMvc.perform(delete("/api/immobiles/{id}", immobile.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Immobile> immobileList = immobileRepository.findAll();
//        assertThat(immobileList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Immobile.class);
//        Immobile immobile1 = new Immobile();
//        immobile1.setId(1L);
//        Immobile immobile2 = new Immobile();
//        immobile2.setId(immobile1.getId());
//        assertThat(immobile1).isEqualTo(immobile2);
//        immobile2.setId(2L);
//        assertThat(immobile1).isNotEqualTo(immobile2);
//        immobile1.setId(null);
//        assertThat(immobile1).isNotEqualTo(immobile2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(ImmobileDTO.class);
//        ImmobileDTO immobileDTO1 = new ImmobileDTO();
//        immobileDTO1.setId(1L);
//        ImmobileDTO immobileDTO2 = new ImmobileDTO();
//        assertThat(immobileDTO1).isNotEqualTo(immobileDTO2);
//        immobileDTO2.setId(immobileDTO1.getId());
//        assertThat(immobileDTO1).isEqualTo(immobileDTO2);
//        immobileDTO2.setId(2L);
//        assertThat(immobileDTO1).isNotEqualTo(immobileDTO2);
//        immobileDTO1.setId(null);
//        assertThat(immobileDTO1).isNotEqualTo(immobileDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(immobileMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(immobileMapper.fromId(null)).isNull();
//    }
}
