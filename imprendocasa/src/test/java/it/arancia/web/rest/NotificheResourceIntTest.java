package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Notifiche;
import it.arancia.repository.NotificheRepository;
import it.arancia.service.NotificheService;
import it.arancia.service.dto.NotificheDTO;
import it.arancia.service.mapper.NotificheMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.NotificheCriteria;
import it.arancia.service.NotificheQueryService;

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

import it.arancia.domain.enumeration.CanaleNotifica;
import it.arancia.domain.enumeration.TipoNotifica;
import it.arancia.domain.enumeration.CategoriaNotifica;
import it.arancia.domain.enumeration.TipoEsito;
/**
 * Test class for the NotificheResource REST controller.
 *
 * @see NotificheResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class NotificheResourceIntTest {

    private static final CanaleNotifica DEFAULT_CANALE_NOTIFICA = CanaleNotifica.MAIL;
    private static final CanaleNotifica UPDATED_CANALE_NOTIFICA = CanaleNotifica.SMS;

    private static final TipoNotifica DEFAULT_TIPO_NOTIFICA = TipoNotifica.MANUALE;
    private static final TipoNotifica UPDATED_TIPO_NOTIFICA = TipoNotifica.AUTOMATICO;

    private static final CategoriaNotifica DEFAULT_CATEGORIA_NOTIFICA = CategoriaNotifica.COMPLEANNO;
    private static final CategoriaNotifica UPDATED_CATEGORIA_NOTIFICA = CategoriaNotifica.FATTURA;

    private static final String DEFAULT_OGGETTO_NOTIFICA = "AAAAAAAAAA";
    private static final String UPDATED_OGGETTO_NOTIFICA = "BBBBBBBBBB";

    private static final String DEFAULT_TESTO_NOTIFICA = "AAAAAAAAAA";
    private static final String UPDATED_TESTO_NOTIFICA = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_DESTINATARI = 1;
    private static final Integer UPDATED_NUMERO_DESTINATARI = 2;

    private static final String DEFAULT_DESTINATARI_NOTIFICA = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATARI_NOTIFICA = "BBBBBBBBBB";

    private static final TipoEsito DEFAULT_ESITO_NOTIFICA = TipoEsito.DA_INVIARE;
    private static final TipoEsito UPDATED_ESITO_NOTIFICA = TipoEsito.INVIATA;

    private static final String DEFAULT_DETTAGLI_ESITO = "AAAAAAAAAA";
    private static final String UPDATED_DETTAGLI_ESITO = "BBBBBBBBBB";

    @Autowired
    private NotificheRepository notificheRepository;

    @Autowired
    private NotificheMapper notificheMapper;

    @Autowired
    private NotificheService notificheService;

    @Autowired
    private NotificheQueryService notificheQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNotificheMockMvc;

    private Notifiche notifiche;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificheResource notificheResource = new NotificheResource(notificheService, notificheQueryService);
        this.restNotificheMockMvc = MockMvcBuilders.standaloneSetup(notificheResource)
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
    public static Notifiche createEntity(EntityManager em) {
        Notifiche notifiche = new Notifiche()
            .canaleNotifica(DEFAULT_CANALE_NOTIFICA)
            .tipoNotifica(DEFAULT_TIPO_NOTIFICA)
            .categoriaNotifica(DEFAULT_CATEGORIA_NOTIFICA)
            .oggettoNotifica(DEFAULT_OGGETTO_NOTIFICA)
            .testoNotifica(DEFAULT_TESTO_NOTIFICA)
            .numeroDestinatari(DEFAULT_NUMERO_DESTINATARI)
            .destinatariNotifica(DEFAULT_DESTINATARI_NOTIFICA)
            .esitoNotifica(DEFAULT_ESITO_NOTIFICA)
            .dettagliEsito(DEFAULT_DETTAGLI_ESITO);
        return notifiche;
    }

    @Before
    public void initTest() {
        notifiche = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotifiche() throws Exception {
        int databaseSizeBeforeCreate = notificheRepository.findAll().size();

        // Create the Notifiche
        NotificheDTO notificheDTO = notificheMapper.toDto(notifiche);
        restNotificheMockMvc.perform(post("/api/notifiches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))
            .andExpect(status().isCreated());

        // Validate the Notifiche in the database
        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeCreate + 1);
        Notifiche testNotifiche = notificheList.get(notificheList.size() - 1);
        assertThat(testNotifiche.getCanaleNotifica()).isEqualTo(DEFAULT_CANALE_NOTIFICA);
        assertThat(testNotifiche.getTipoNotifica()).isEqualTo(DEFAULT_TIPO_NOTIFICA);
        assertThat(testNotifiche.getCategoriaNotifica()).isEqualTo(DEFAULT_CATEGORIA_NOTIFICA);
        assertThat(testNotifiche.getOggettoNotifica()).isEqualTo(DEFAULT_OGGETTO_NOTIFICA);
        assertThat(testNotifiche.getTestoNotifica()).isEqualTo(DEFAULT_TESTO_NOTIFICA);
        assertThat(testNotifiche.getNumeroDestinatari()).isEqualTo(DEFAULT_NUMERO_DESTINATARI);
        assertThat(testNotifiche.getDestinatariNotifica()).isEqualTo(DEFAULT_DESTINATARI_NOTIFICA);
        assertThat(testNotifiche.getEsitoNotifica()).isEqualTo(DEFAULT_ESITO_NOTIFICA);
        assertThat(testNotifiche.getDettagliEsito()).isEqualTo(DEFAULT_DETTAGLI_ESITO);
    }

    @Test
    @Transactional
    public void createNotificheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificheRepository.findAll().size();

        // Create the Notifiche with an existing ID
        notifiche.setId(1L);
        NotificheDTO notificheDTO = notificheMapper.toDto(notifiche);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificheMockMvc.perform(post("/api/notifiches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notifiche in the database
        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCanaleNotificaIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificheRepository.findAll().size();
        // set the field null
        notifiche.setCanaleNotifica(null);

        // Create the Notifiche, which fails.
        NotificheDTO notificheDTO = notificheMapper.toDto(notifiche);

        restNotificheMockMvc.perform(post("/api/notifiches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))
            .andExpect(status().isBadRequest());

        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoNotificaIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificheRepository.findAll().size();
        // set the field null
        notifiche.setTipoNotifica(null);

        // Create the Notifiche, which fails.
        NotificheDTO notificheDTO = notificheMapper.toDto(notifiche);

        restNotificheMockMvc.perform(post("/api/notifiches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))
            .andExpect(status().isBadRequest());

        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoriaNotificaIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificheRepository.findAll().size();
        // set the field null
        notifiche.setCategoriaNotifica(null);

        // Create the Notifiche, which fails.
        NotificheDTO notificheDTO = notificheMapper.toDto(notifiche);

        restNotificheMockMvc.perform(post("/api/notifiches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))
            .andExpect(status().isBadRequest());

        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsitoNotificaIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificheRepository.findAll().size();
        // set the field null
        notifiche.setEsitoNotifica(null);

        // Create the Notifiche, which fails.
        NotificheDTO notificheDTO = notificheMapper.toDto(notifiche);

        restNotificheMockMvc.perform(post("/api/notifiches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))
            .andExpect(status().isBadRequest());

        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotifiches() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList
        restNotificheMockMvc.perform(get("/api/notifiches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifiche.getId().intValue())))
            .andExpect(jsonPath("$.[*].canaleNotifica").value(hasItem(DEFAULT_CANALE_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].tipoNotifica").value(hasItem(DEFAULT_TIPO_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].categoriaNotifica").value(hasItem(DEFAULT_CATEGORIA_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].oggettoNotifica").value(hasItem(DEFAULT_OGGETTO_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].testoNotifica").value(hasItem(DEFAULT_TESTO_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].numeroDestinatari").value(hasItem(DEFAULT_NUMERO_DESTINATARI)))
            .andExpect(jsonPath("$.[*].destinatariNotifica").value(hasItem(DEFAULT_DESTINATARI_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].esitoNotifica").value(hasItem(DEFAULT_ESITO_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].dettagliEsito").value(hasItem(DEFAULT_DETTAGLI_ESITO.toString())));
    }
    
    @Test
    @Transactional
    public void getNotifiche() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get the notifiche
        restNotificheMockMvc.perform(get("/api/notifiches/{id}", notifiche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notifiche.getId().intValue()))
            .andExpect(jsonPath("$.canaleNotifica").value(DEFAULT_CANALE_NOTIFICA.toString()))
            .andExpect(jsonPath("$.tipoNotifica").value(DEFAULT_TIPO_NOTIFICA.toString()))
            .andExpect(jsonPath("$.categoriaNotifica").value(DEFAULT_CATEGORIA_NOTIFICA.toString()))
            .andExpect(jsonPath("$.oggettoNotifica").value(DEFAULT_OGGETTO_NOTIFICA.toString()))
            .andExpect(jsonPath("$.testoNotifica").value(DEFAULT_TESTO_NOTIFICA.toString()))
            .andExpect(jsonPath("$.numeroDestinatari").value(DEFAULT_NUMERO_DESTINATARI))
            .andExpect(jsonPath("$.destinatariNotifica").value(DEFAULT_DESTINATARI_NOTIFICA.toString()))
            .andExpect(jsonPath("$.esitoNotifica").value(DEFAULT_ESITO_NOTIFICA.toString()))
            .andExpect(jsonPath("$.dettagliEsito").value(DEFAULT_DETTAGLI_ESITO.toString()));
    }

    @Test
    @Transactional
    public void getAllNotifichesByCanaleNotificaIsEqualToSomething() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where canaleNotifica equals to DEFAULT_CANALE_NOTIFICA
        defaultNotificheShouldBeFound("canaleNotifica.equals=" + DEFAULT_CANALE_NOTIFICA);

        // Get all the notificheList where canaleNotifica equals to UPDATED_CANALE_NOTIFICA
        defaultNotificheShouldNotBeFound("canaleNotifica.equals=" + UPDATED_CANALE_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByCanaleNotificaIsInShouldWork() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where canaleNotifica in DEFAULT_CANALE_NOTIFICA or UPDATED_CANALE_NOTIFICA
        defaultNotificheShouldBeFound("canaleNotifica.in=" + DEFAULT_CANALE_NOTIFICA + "," + UPDATED_CANALE_NOTIFICA);

        // Get all the notificheList where canaleNotifica equals to UPDATED_CANALE_NOTIFICA
        defaultNotificheShouldNotBeFound("canaleNotifica.in=" + UPDATED_CANALE_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByCanaleNotificaIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where canaleNotifica is not null
        defaultNotificheShouldBeFound("canaleNotifica.specified=true");

        // Get all the notificheList where canaleNotifica is null
        defaultNotificheShouldNotBeFound("canaleNotifica.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotifichesByTipoNotificaIsEqualToSomething() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where tipoNotifica equals to DEFAULT_TIPO_NOTIFICA
        defaultNotificheShouldBeFound("tipoNotifica.equals=" + DEFAULT_TIPO_NOTIFICA);

        // Get all the notificheList where tipoNotifica equals to UPDATED_TIPO_NOTIFICA
        defaultNotificheShouldNotBeFound("tipoNotifica.equals=" + UPDATED_TIPO_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByTipoNotificaIsInShouldWork() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where tipoNotifica in DEFAULT_TIPO_NOTIFICA or UPDATED_TIPO_NOTIFICA
        defaultNotificheShouldBeFound("tipoNotifica.in=" + DEFAULT_TIPO_NOTIFICA + "," + UPDATED_TIPO_NOTIFICA);

        // Get all the notificheList where tipoNotifica equals to UPDATED_TIPO_NOTIFICA
        defaultNotificheShouldNotBeFound("tipoNotifica.in=" + UPDATED_TIPO_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByTipoNotificaIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where tipoNotifica is not null
        defaultNotificheShouldBeFound("tipoNotifica.specified=true");

        // Get all the notificheList where tipoNotifica is null
        defaultNotificheShouldNotBeFound("tipoNotifica.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotifichesByCategoriaNotificaIsEqualToSomething() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where categoriaNotifica equals to DEFAULT_CATEGORIA_NOTIFICA
        defaultNotificheShouldBeFound("categoriaNotifica.equals=" + DEFAULT_CATEGORIA_NOTIFICA);

        // Get all the notificheList where categoriaNotifica equals to UPDATED_CATEGORIA_NOTIFICA
        defaultNotificheShouldNotBeFound("categoriaNotifica.equals=" + UPDATED_CATEGORIA_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByCategoriaNotificaIsInShouldWork() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where categoriaNotifica in DEFAULT_CATEGORIA_NOTIFICA or UPDATED_CATEGORIA_NOTIFICA
        defaultNotificheShouldBeFound("categoriaNotifica.in=" + DEFAULT_CATEGORIA_NOTIFICA + "," + UPDATED_CATEGORIA_NOTIFICA);

        // Get all the notificheList where categoriaNotifica equals to UPDATED_CATEGORIA_NOTIFICA
        defaultNotificheShouldNotBeFound("categoriaNotifica.in=" + UPDATED_CATEGORIA_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByCategoriaNotificaIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where categoriaNotifica is not null
        defaultNotificheShouldBeFound("categoriaNotifica.specified=true");

        // Get all the notificheList where categoriaNotifica is null
        defaultNotificheShouldNotBeFound("categoriaNotifica.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotifichesByOggettoNotificaIsEqualToSomething() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where oggettoNotifica equals to DEFAULT_OGGETTO_NOTIFICA
        defaultNotificheShouldBeFound("oggettoNotifica.equals=" + DEFAULT_OGGETTO_NOTIFICA);

        // Get all the notificheList where oggettoNotifica equals to UPDATED_OGGETTO_NOTIFICA
        defaultNotificheShouldNotBeFound("oggettoNotifica.equals=" + UPDATED_OGGETTO_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByOggettoNotificaIsInShouldWork() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where oggettoNotifica in DEFAULT_OGGETTO_NOTIFICA or UPDATED_OGGETTO_NOTIFICA
        defaultNotificheShouldBeFound("oggettoNotifica.in=" + DEFAULT_OGGETTO_NOTIFICA + "," + UPDATED_OGGETTO_NOTIFICA);

        // Get all the notificheList where oggettoNotifica equals to UPDATED_OGGETTO_NOTIFICA
        defaultNotificheShouldNotBeFound("oggettoNotifica.in=" + UPDATED_OGGETTO_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByOggettoNotificaIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where oggettoNotifica is not null
        defaultNotificheShouldBeFound("oggettoNotifica.specified=true");

        // Get all the notificheList where oggettoNotifica is null
        defaultNotificheShouldNotBeFound("oggettoNotifica.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotifichesByNumeroDestinatariIsEqualToSomething() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where numeroDestinatari equals to DEFAULT_NUMERO_DESTINATARI
        defaultNotificheShouldBeFound("numeroDestinatari.equals=" + DEFAULT_NUMERO_DESTINATARI);

        // Get all the notificheList where numeroDestinatari equals to UPDATED_NUMERO_DESTINATARI
        defaultNotificheShouldNotBeFound("numeroDestinatari.equals=" + UPDATED_NUMERO_DESTINATARI);
    }

    @Test
    @Transactional
    public void getAllNotifichesByNumeroDestinatariIsInShouldWork() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where numeroDestinatari in DEFAULT_NUMERO_DESTINATARI or UPDATED_NUMERO_DESTINATARI
        defaultNotificheShouldBeFound("numeroDestinatari.in=" + DEFAULT_NUMERO_DESTINATARI + "," + UPDATED_NUMERO_DESTINATARI);

        // Get all the notificheList where numeroDestinatari equals to UPDATED_NUMERO_DESTINATARI
        defaultNotificheShouldNotBeFound("numeroDestinatari.in=" + UPDATED_NUMERO_DESTINATARI);
    }

    @Test
    @Transactional
    public void getAllNotifichesByNumeroDestinatariIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where numeroDestinatari is not null
        defaultNotificheShouldBeFound("numeroDestinatari.specified=true");

        // Get all the notificheList where numeroDestinatari is null
        defaultNotificheShouldNotBeFound("numeroDestinatari.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotifichesByNumeroDestinatariIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where numeroDestinatari greater than or equals to DEFAULT_NUMERO_DESTINATARI
        defaultNotificheShouldBeFound("numeroDestinatari.greaterOrEqualThan=" + DEFAULT_NUMERO_DESTINATARI);

        // Get all the notificheList where numeroDestinatari greater than or equals to UPDATED_NUMERO_DESTINATARI
        defaultNotificheShouldNotBeFound("numeroDestinatari.greaterOrEqualThan=" + UPDATED_NUMERO_DESTINATARI);
    }

    @Test
    @Transactional
    public void getAllNotifichesByNumeroDestinatariIsLessThanSomething() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where numeroDestinatari less than or equals to DEFAULT_NUMERO_DESTINATARI
        defaultNotificheShouldNotBeFound("numeroDestinatari.lessThan=" + DEFAULT_NUMERO_DESTINATARI);

        // Get all the notificheList where numeroDestinatari less than or equals to UPDATED_NUMERO_DESTINATARI
        defaultNotificheShouldBeFound("numeroDestinatari.lessThan=" + UPDATED_NUMERO_DESTINATARI);
    }


    @Test
    @Transactional
    public void getAllNotifichesByEsitoNotificaIsEqualToSomething() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where esitoNotifica equals to DEFAULT_ESITO_NOTIFICA
        defaultNotificheShouldBeFound("esitoNotifica.equals=" + DEFAULT_ESITO_NOTIFICA);

        // Get all the notificheList where esitoNotifica equals to UPDATED_ESITO_NOTIFICA
        defaultNotificheShouldNotBeFound("esitoNotifica.equals=" + UPDATED_ESITO_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByEsitoNotificaIsInShouldWork() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where esitoNotifica in DEFAULT_ESITO_NOTIFICA or UPDATED_ESITO_NOTIFICA
        defaultNotificheShouldBeFound("esitoNotifica.in=" + DEFAULT_ESITO_NOTIFICA + "," + UPDATED_ESITO_NOTIFICA);

        // Get all the notificheList where esitoNotifica equals to UPDATED_ESITO_NOTIFICA
        defaultNotificheShouldNotBeFound("esitoNotifica.in=" + UPDATED_ESITO_NOTIFICA);
    }

    @Test
    @Transactional
    public void getAllNotifichesByEsitoNotificaIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        // Get all the notificheList where esitoNotifica is not null
        defaultNotificheShouldBeFound("esitoNotifica.specified=true");

        // Get all the notificheList where esitoNotifica is null
        defaultNotificheShouldNotBeFound("esitoNotifica.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNotificheShouldBeFound(String filter) throws Exception {
        restNotificheMockMvc.perform(get("/api/notifiches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifiche.getId().intValue())))
            .andExpect(jsonPath("$.[*].canaleNotifica").value(hasItem(DEFAULT_CANALE_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].tipoNotifica").value(hasItem(DEFAULT_TIPO_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].categoriaNotifica").value(hasItem(DEFAULT_CATEGORIA_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].oggettoNotifica").value(hasItem(DEFAULT_OGGETTO_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].testoNotifica").value(hasItem(DEFAULT_TESTO_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].numeroDestinatari").value(hasItem(DEFAULT_NUMERO_DESTINATARI)))
            .andExpect(jsonPath("$.[*].destinatariNotifica").value(hasItem(DEFAULT_DESTINATARI_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].esitoNotifica").value(hasItem(DEFAULT_ESITO_NOTIFICA.toString())))
            .andExpect(jsonPath("$.[*].dettagliEsito").value(hasItem(DEFAULT_DETTAGLI_ESITO.toString())));

        // Check, that the count call also returns 1
        restNotificheMockMvc.perform(get("/api/notifiches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNotificheShouldNotBeFound(String filter) throws Exception {
        restNotificheMockMvc.perform(get("/api/notifiches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNotificheMockMvc.perform(get("/api/notifiches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNotifiche() throws Exception {
        // Get the notifiche
        restNotificheMockMvc.perform(get("/api/notifiches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotifiche() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        int databaseSizeBeforeUpdate = notificheRepository.findAll().size();

        // Update the notifiche
        Notifiche updatedNotifiche = notificheRepository.findById(notifiche.getId()).get();
        // Disconnect from session so that the updates on updatedNotifiche are not directly saved in db
        em.detach(updatedNotifiche);
        updatedNotifiche
            .canaleNotifica(UPDATED_CANALE_NOTIFICA)
            .tipoNotifica(UPDATED_TIPO_NOTIFICA)
            .categoriaNotifica(UPDATED_CATEGORIA_NOTIFICA)
            .oggettoNotifica(UPDATED_OGGETTO_NOTIFICA)
            .testoNotifica(UPDATED_TESTO_NOTIFICA)
            .numeroDestinatari(UPDATED_NUMERO_DESTINATARI)
            .destinatariNotifica(UPDATED_DESTINATARI_NOTIFICA)
            .esitoNotifica(UPDATED_ESITO_NOTIFICA)
            .dettagliEsito(UPDATED_DETTAGLI_ESITO);
        NotificheDTO notificheDTO = notificheMapper.toDto(updatedNotifiche);

        restNotificheMockMvc.perform(put("/api/notifiches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))
            .andExpect(status().isOk());

        // Validate the Notifiche in the database
        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeUpdate);
        Notifiche testNotifiche = notificheList.get(notificheList.size() - 1);
        assertThat(testNotifiche.getCanaleNotifica()).isEqualTo(UPDATED_CANALE_NOTIFICA);
        assertThat(testNotifiche.getTipoNotifica()).isEqualTo(UPDATED_TIPO_NOTIFICA);
        assertThat(testNotifiche.getCategoriaNotifica()).isEqualTo(UPDATED_CATEGORIA_NOTIFICA);
        assertThat(testNotifiche.getOggettoNotifica()).isEqualTo(UPDATED_OGGETTO_NOTIFICA);
        assertThat(testNotifiche.getTestoNotifica()).isEqualTo(UPDATED_TESTO_NOTIFICA);
        assertThat(testNotifiche.getNumeroDestinatari()).isEqualTo(UPDATED_NUMERO_DESTINATARI);
        assertThat(testNotifiche.getDestinatariNotifica()).isEqualTo(UPDATED_DESTINATARI_NOTIFICA);
        assertThat(testNotifiche.getEsitoNotifica()).isEqualTo(UPDATED_ESITO_NOTIFICA);
        assertThat(testNotifiche.getDettagliEsito()).isEqualTo(UPDATED_DETTAGLI_ESITO);
    }

    @Test
    @Transactional
    public void updateNonExistingNotifiche() throws Exception {
        int databaseSizeBeforeUpdate = notificheRepository.findAll().size();

        // Create the Notifiche
        NotificheDTO notificheDTO = notificheMapper.toDto(notifiche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificheMockMvc.perform(put("/api/notifiches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notifiche in the database
        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotifiche() throws Exception {
        // Initialize the database
        notificheRepository.saveAndFlush(notifiche);

        int databaseSizeBeforeDelete = notificheRepository.findAll().size();

        // Get the notifiche
        restNotificheMockMvc.perform(delete("/api/notifiches/{id}", notifiche.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Notifiche> notificheList = notificheRepository.findAll();
        assertThat(notificheList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Notifiche.class);
        Notifiche notifiche1 = new Notifiche();
        notifiche1.setId(1L);
        Notifiche notifiche2 = new Notifiche();
        notifiche2.setId(notifiche1.getId());
        assertThat(notifiche1).isEqualTo(notifiche2);
        notifiche2.setId(2L);
        assertThat(notifiche1).isNotEqualTo(notifiche2);
        notifiche1.setId(null);
        assertThat(notifiche1).isNotEqualTo(notifiche2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificheDTO.class);
        NotificheDTO notificheDTO1 = new NotificheDTO();
        notificheDTO1.setId(1L);
        NotificheDTO notificheDTO2 = new NotificheDTO();
        assertThat(notificheDTO1).isNotEqualTo(notificheDTO2);
        notificheDTO2.setId(notificheDTO1.getId());
        assertThat(notificheDTO1).isEqualTo(notificheDTO2);
        notificheDTO2.setId(2L);
        assertThat(notificheDTO1).isNotEqualTo(notificheDTO2);
        notificheDTO1.setId(null);
        assertThat(notificheDTO1).isNotEqualTo(notificheDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(notificheMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(notificheMapper.fromId(null)).isNull();
    }
}
