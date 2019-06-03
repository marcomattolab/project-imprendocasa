package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Incarico;
import it.arancia.domain.ListaContatti;
import it.arancia.domain.Immobile;
import it.arancia.domain.Partner;
import it.arancia.domain.Cliente;
import it.arancia.domain.Cliente;
import it.arancia.domain.Cliente;
import it.arancia.domain.Cliente;
import it.arancia.repository.IncaricoRepository;
import it.arancia.service.IncaricoService;
import it.arancia.service.dto.IncaricoDTO;
import it.arancia.service.mapper.IncaricoMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.IncaricoCriteria;
import it.arancia.service.IncaricoQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static it.arancia.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.arancia.domain.enumeration.TipoNegoziazione;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.BooleanStatus;
/**
 * Test class for the IncaricoResource REST controller.
 *
 * @see IncaricoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class IncaricoResourceIntTest {

//    private static final String DEFAULT_RIFERIMENTO = "AAAAAAAAAA";
//    private static final String UPDATED_RIFERIMENTO = "BBBBBBBBBB";
//
//    private static final TipoNegoziazione DEFAULT_TIPO = TipoNegoziazione.COMPRAVENDITA;
//    private static final TipoNegoziazione UPDATED_TIPO = TipoNegoziazione.LOCAZIONE;
//
//    private static final StatoIncarico DEFAULT_STATO = StatoIncarico.BOZZA;
//    private static final StatoIncarico UPDATED_STATO = StatoIncarico.ATTIVO;
//
//    private static final LocalDate DEFAULT_DATA_SCADENZA = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_DATA_SCADENZA = LocalDate.now(ZoneId.systemDefault());
//
//    private static final String DEFAULT_AGENTE = "AAAAAAAAAA";
//    private static final String UPDATED_AGENTE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_AGENTE_DI_CONTATTO = "AAAAAAAAAA";
//    private static final String UPDATED_AGENTE_DI_CONTATTO = "BBBBBBBBBB";
//
//    private static final LocalDate DEFAULT_DATA_CONTATTO = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_DATA_CONTATTO = LocalDate.now(ZoneId.systemDefault());
//
//    private static final String DEFAULT_NOTE_TRATTATIVA = "AAAAAAAAAA";
//    private static final String UPDATED_NOTE_TRATTATIVA = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DATI_FISCALI = "AAAAAAAAAA";
//    private static final String UPDATED_DATI_FISCALI = "BBBBBBBBBB";
//
//    private static final BooleanStatus DEFAULT_ALERT_FISCALI = BooleanStatus.SI;
//    private static final BooleanStatus UPDATED_ALERT_FISCALI = BooleanStatus.NO;
//
//    private static final BooleanStatus DEFAULT_ALERT_CORTESIA = BooleanStatus.SI;
//    private static final BooleanStatus UPDATED_ALERT_CORTESIA = BooleanStatus.NO;
//
//    private static final Boolean DEFAULT_PRIVACY = false;
//    private static final Boolean UPDATED_PRIVACY = true;
//
//    private static final Boolean DEFAULT_ANTIRICICLAGGIO = false;
//    private static final Boolean UPDATED_ANTIRICICLAGGIO = true;
//
//    private static final Double DEFAULT_PREZZO_RICHIESTA = 1D;
//    private static final Double UPDATED_PREZZO_RICHIESTA = 2D;
//
//    private static final Double DEFAULT_PREZZO_MINIMO = 1D;
//    private static final Double UPDATED_PREZZO_MINIMO = 2D;
//
//    private static final Double DEFAULT_PREZZO_ACQUISTO = 1D;
//    private static final Double UPDATED_PREZZO_ACQUISTO = 2D;
//
//    private static final Double DEFAULT_IMPORTO_PROVVIGIONE = 1D;
//    private static final Double UPDATED_IMPORTO_PROVVIGIONE = 2D;
//
//    private static final Boolean DEFAULT_OSCURA_INCARICO = false;
//    private static final Boolean UPDATED_OSCURA_INCARICO = true;
//
//    private static final Boolean DEFAULT_FLAG_LOCATO = false;
//    private static final Boolean UPDATED_FLAG_LOCATO = true;
//
//    private static final String DEFAULT_NOME_CONDUTTORE = "AAAAAAAAAA";
//    private static final String UPDATED_NOME_CONDUTTORE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_COGNOME_CONDUTTORE = "AAAAAAAAAA";
//    private static final String UPDATED_COGNOME_CONDUTTORE = "BBBBBBBBBB";
//
//    private static final LocalDate DEFAULT_DATA_INIZIO_LOCAZIONE = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_DATA_INIZIO_LOCAZIONE = LocalDate.now(ZoneId.systemDefault());
//
//    private static final LocalDate DEFAULT_DATA_FINE_LOCAZIONE = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_DATA_FINE_LOCAZIONE = LocalDate.now(ZoneId.systemDefault());
//
//    private static final String DEFAULT_NOTE_LOCAZIONE = "AAAAAAAAAA";
//    private static final String UPDATED_NOTE_LOCAZIONE = "BBBBBBBBBB";
//
//    @Autowired
//    private IncaricoRepository incaricoRepository;
//
//    @Mock
//    private IncaricoRepository incaricoRepositoryMock;
//
//    @Autowired
//    private IncaricoMapper incaricoMapper;
//
//    @Mock
//    private IncaricoService incaricoServiceMock;
//
//    @Autowired
//    private IncaricoService incaricoService;
//
//    @Autowired
//    private IncaricoQueryService incaricoQueryService;
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
//    private MockMvc restIncaricoMockMvc;
//
//    private Incarico incarico;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final IncaricoResource incaricoResource = new IncaricoResource(incaricoService, incaricoQueryService);
//        this.restIncaricoMockMvc = MockMvcBuilders.standaloneSetup(incaricoResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Incarico createEntity(EntityManager em) {
//        Incarico incarico = new Incarico()
//            .riferimento(DEFAULT_RIFERIMENTO)
//            .tipo(DEFAULT_TIPO)
//            .stato(DEFAULT_STATO)
//            .dataScadenza(DEFAULT_DATA_SCADENZA)
//            .agente(DEFAULT_AGENTE)
//            .agenteDiContatto(DEFAULT_AGENTE_DI_CONTATTO)
//            .dataContatto(DEFAULT_DATA_CONTATTO)
//            .noteTrattativa(DEFAULT_NOTE_TRATTATIVA)
//            .datiFiscali(DEFAULT_DATI_FISCALI)
//            .alertFiscali(DEFAULT_ALERT_FISCALI)
//            .alertCortesia(DEFAULT_ALERT_CORTESIA)
//            .privacy(DEFAULT_PRIVACY)
//            .antiriciclaggio(DEFAULT_ANTIRICICLAGGIO)
//            .prezzoRichiesta(DEFAULT_PREZZO_RICHIESTA)
//            .prezzoMinimo(DEFAULT_PREZZO_MINIMO)
//            .prezzoAcquisto(DEFAULT_PREZZO_ACQUISTO)
//            .importoProvvigione(DEFAULT_IMPORTO_PROVVIGIONE)
//            .oscuraIncarico(DEFAULT_OSCURA_INCARICO)
//            .flagLocato(DEFAULT_FLAG_LOCATO)
//            .nomeConduttore(DEFAULT_NOME_CONDUTTORE)
//            .cognomeConduttore(DEFAULT_COGNOME_CONDUTTORE)
//            .dataInizioLocazione(DEFAULT_DATA_INIZIO_LOCAZIONE)
//            .dataFineLocazione(DEFAULT_DATA_FINE_LOCAZIONE)
//            .noteLocazione(DEFAULT_NOTE_LOCAZIONE);
//        return incarico;
//    }
//
//    @Before
//    public void initTest() {
//        incarico = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createIncarico() throws Exception {
//        int databaseSizeBeforeCreate = incaricoRepository.findAll().size();
//
//        // Create the Incarico
//        IncaricoDTO incaricoDTO = incaricoMapper.toDto(incarico);
//        restIncaricoMockMvc.perform(post("/api/incaricos")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(incaricoDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Incarico in the database
//        List<Incarico> incaricoList = incaricoRepository.findAll();
//        assertThat(incaricoList).hasSize(databaseSizeBeforeCreate + 1);
//        Incarico testIncarico = incaricoList.get(incaricoList.size() - 1);
//        assertThat(testIncarico.getRiferimento()).isEqualTo(DEFAULT_RIFERIMENTO);
//        assertThat(testIncarico.getTipo()).isEqualTo(DEFAULT_TIPO);
//        assertThat(testIncarico.getStato()).isEqualTo(DEFAULT_STATO);
//        assertThat(testIncarico.getDataScadenza()).isEqualTo(DEFAULT_DATA_SCADENZA);
//        assertThat(testIncarico.getAgente()).isEqualTo(DEFAULT_AGENTE);
//        assertThat(testIncarico.getAgenteDiContatto()).isEqualTo(DEFAULT_AGENTE_DI_CONTATTO);
//        assertThat(testIncarico.getDataContatto()).isEqualTo(DEFAULT_DATA_CONTATTO);
//        assertThat(testIncarico.getNoteTrattativa()).isEqualTo(DEFAULT_NOTE_TRATTATIVA);
//        assertThat(testIncarico.getDatiFiscali()).isEqualTo(DEFAULT_DATI_FISCALI);
//        assertThat(testIncarico.getAlertFiscali()).isEqualTo(DEFAULT_ALERT_FISCALI);
//        assertThat(testIncarico.getAlertCortesia()).isEqualTo(DEFAULT_ALERT_CORTESIA);
//        assertThat(testIncarico.isPrivacy()).isEqualTo(DEFAULT_PRIVACY);
//        assertThat(testIncarico.isAntiriciclaggio()).isEqualTo(DEFAULT_ANTIRICICLAGGIO);
//        assertThat(testIncarico.getPrezzoRichiesta()).isEqualTo(DEFAULT_PREZZO_RICHIESTA);
//        assertThat(testIncarico.getPrezzoMinimo()).isEqualTo(DEFAULT_PREZZO_MINIMO);
//        assertThat(testIncarico.getPrezzoAcquisto()).isEqualTo(DEFAULT_PREZZO_ACQUISTO);
//        assertThat(testIncarico.getImportoProvvigione()).isEqualTo(DEFAULT_IMPORTO_PROVVIGIONE);
//        assertThat(testIncarico.isOscuraIncarico()).isEqualTo(DEFAULT_OSCURA_INCARICO);
//        assertThat(testIncarico.isFlagLocato()).isEqualTo(DEFAULT_FLAG_LOCATO);
//        assertThat(testIncarico.getNomeConduttore()).isEqualTo(DEFAULT_NOME_CONDUTTORE);
//        assertThat(testIncarico.getCognomeConduttore()).isEqualTo(DEFAULT_COGNOME_CONDUTTORE);
//        assertThat(testIncarico.getDataInizioLocazione()).isEqualTo(DEFAULT_DATA_INIZIO_LOCAZIONE);
//        assertThat(testIncarico.getDataFineLocazione()).isEqualTo(DEFAULT_DATA_FINE_LOCAZIONE);
//        assertThat(testIncarico.getNoteLocazione()).isEqualTo(DEFAULT_NOTE_LOCAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void createIncaricoWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = incaricoRepository.findAll().size();
//
//        // Create the Incarico with an existing ID
//        incarico.setId(1L);
//        IncaricoDTO incaricoDTO = incaricoMapper.toDto(incarico);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restIncaricoMockMvc.perform(post("/api/incaricos")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(incaricoDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Incarico in the database
//        List<Incarico> incaricoList = incaricoRepository.findAll();
//        assertThat(incaricoList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkRiferimentoIsRequired() throws Exception {
//        int databaseSizeBeforeTest = incaricoRepository.findAll().size();
//        // set the field null
//        incarico.setRiferimento(null);
//
//        // Create the Incarico, which fails.
//        IncaricoDTO incaricoDTO = incaricoMapper.toDto(incarico);
//
//        restIncaricoMockMvc.perform(post("/api/incaricos")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(incaricoDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Incarico> incaricoList = incaricoRepository.findAll();
//        assertThat(incaricoList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkTipoIsRequired() throws Exception {
//        int databaseSizeBeforeTest = incaricoRepository.findAll().size();
//        // set the field null
//        incarico.setTipo(null);
//
//        // Create the Incarico, which fails.
//        IncaricoDTO incaricoDTO = incaricoMapper.toDto(incarico);
//
//        restIncaricoMockMvc.perform(post("/api/incaricos")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(incaricoDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Incarico> incaricoList = incaricoRepository.findAll();
//        assertThat(incaricoList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricos() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList
//        restIncaricoMockMvc.perform(get("/api/incaricos?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(incarico.getId().intValue())))
//            .andExpect(jsonPath("$.[*].riferimento").value(hasItem(DEFAULT_RIFERIMENTO.toString())))
//            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
//            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.toString())))
//            .andExpect(jsonPath("$.[*].dataScadenza").value(hasItem(DEFAULT_DATA_SCADENZA.toString())))
//            .andExpect(jsonPath("$.[*].agente").value(hasItem(DEFAULT_AGENTE.toString())))
//            .andExpect(jsonPath("$.[*].agenteDiContatto").value(hasItem(DEFAULT_AGENTE_DI_CONTATTO.toString())))
//            .andExpect(jsonPath("$.[*].dataContatto").value(hasItem(DEFAULT_DATA_CONTATTO.toString())))
//            .andExpect(jsonPath("$.[*].noteTrattativa").value(hasItem(DEFAULT_NOTE_TRATTATIVA.toString())))
//            .andExpect(jsonPath("$.[*].datiFiscali").value(hasItem(DEFAULT_DATI_FISCALI.toString())))
//            .andExpect(jsonPath("$.[*].alertFiscali").value(hasItem(DEFAULT_ALERT_FISCALI.toString())))
//            .andExpect(jsonPath("$.[*].alertCortesia").value(hasItem(DEFAULT_ALERT_CORTESIA.toString())))
//            .andExpect(jsonPath("$.[*].privacy").value(hasItem(DEFAULT_PRIVACY.booleanValue())))
//            .andExpect(jsonPath("$.[*].antiriciclaggio").value(hasItem(DEFAULT_ANTIRICICLAGGIO.booleanValue())))
//            .andExpect(jsonPath("$.[*].prezzoRichiesta").value(hasItem(DEFAULT_PREZZO_RICHIESTA.doubleValue())))
//            .andExpect(jsonPath("$.[*].prezzoMinimo").value(hasItem(DEFAULT_PREZZO_MINIMO.doubleValue())))
//            .andExpect(jsonPath("$.[*].prezzoAcquisto").value(hasItem(DEFAULT_PREZZO_ACQUISTO.doubleValue())))
//            .andExpect(jsonPath("$.[*].importoProvvigione").value(hasItem(DEFAULT_IMPORTO_PROVVIGIONE.doubleValue())))
//            .andExpect(jsonPath("$.[*].oscuraIncarico").value(hasItem(DEFAULT_OSCURA_INCARICO.booleanValue())))
//            .andExpect(jsonPath("$.[*].flagLocato").value(hasItem(DEFAULT_FLAG_LOCATO.booleanValue())))
//            .andExpect(jsonPath("$.[*].nomeConduttore").value(hasItem(DEFAULT_NOME_CONDUTTORE.toString())))
//            .andExpect(jsonPath("$.[*].cognomeConduttore").value(hasItem(DEFAULT_COGNOME_CONDUTTORE.toString())))
//            .andExpect(jsonPath("$.[*].dataInizioLocazione").value(hasItem(DEFAULT_DATA_INIZIO_LOCAZIONE.toString())))
//            .andExpect(jsonPath("$.[*].dataFineLocazione").value(hasItem(DEFAULT_DATA_FINE_LOCAZIONE.toString())))
//            .andExpect(jsonPath("$.[*].noteLocazione").value(hasItem(DEFAULT_NOTE_LOCAZIONE.toString())));
//    }
//
//    @SuppressWarnings({"unchecked"})
//    public void getAllIncaricosWithEagerRelationshipsIsEnabled() throws Exception {
//        IncaricoResource incaricoResource = new IncaricoResource(incaricoServiceMock, incaricoQueryService);
//        when(incaricoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
//
//        MockMvc restIncaricoMockMvc = MockMvcBuilders.standaloneSetup(incaricoResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//
//        restIncaricoMockMvc.perform(get("/api/incaricos?eagerload=true"))
//        .andExpect(status().isOk());
//
//        verify(incaricoServiceMock, times(1)).findAllWithEagerRelationships(any());
//    }
//
//    @SuppressWarnings({"unchecked"})
//    public void getAllIncaricosWithEagerRelationshipsIsNotEnabled() throws Exception {
//        IncaricoResource incaricoResource = new IncaricoResource(incaricoServiceMock, incaricoQueryService);
//            when(incaricoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
//            MockMvc restIncaricoMockMvc = MockMvcBuilders.standaloneSetup(incaricoResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//
//        restIncaricoMockMvc.perform(get("/api/incaricos?eagerload=true"))
//        .andExpect(status().isOk());
//
//            verify(incaricoServiceMock, times(1)).findAllWithEagerRelationships(any());
//    }
//
//    @Test
//    @Transactional
//    public void getIncarico() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get the incarico
//        restIncaricoMockMvc.perform(get("/api/incaricos/{id}", incarico.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(incarico.getId().intValue()))
//            .andExpect(jsonPath("$.riferimento").value(DEFAULT_RIFERIMENTO.toString()))
//            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
//            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO.toString()))
//            .andExpect(jsonPath("$.dataScadenza").value(DEFAULT_DATA_SCADENZA.toString()))
//            .andExpect(jsonPath("$.agente").value(DEFAULT_AGENTE.toString()))
//            .andExpect(jsonPath("$.agenteDiContatto").value(DEFAULT_AGENTE_DI_CONTATTO.toString()))
//            .andExpect(jsonPath("$.dataContatto").value(DEFAULT_DATA_CONTATTO.toString()))
//            .andExpect(jsonPath("$.noteTrattativa").value(DEFAULT_NOTE_TRATTATIVA.toString()))
//            .andExpect(jsonPath("$.datiFiscali").value(DEFAULT_DATI_FISCALI.toString()))
//            .andExpect(jsonPath("$.alertFiscali").value(DEFAULT_ALERT_FISCALI.toString()))
//            .andExpect(jsonPath("$.alertCortesia").value(DEFAULT_ALERT_CORTESIA.toString()))
//            .andExpect(jsonPath("$.privacy").value(DEFAULT_PRIVACY.booleanValue()))
//            .andExpect(jsonPath("$.antiriciclaggio").value(DEFAULT_ANTIRICICLAGGIO.booleanValue()))
//            .andExpect(jsonPath("$.prezzoRichiesta").value(DEFAULT_PREZZO_RICHIESTA.doubleValue()))
//            .andExpect(jsonPath("$.prezzoMinimo").value(DEFAULT_PREZZO_MINIMO.doubleValue()))
//            .andExpect(jsonPath("$.prezzoAcquisto").value(DEFAULT_PREZZO_ACQUISTO.doubleValue()))
//            .andExpect(jsonPath("$.importoProvvigione").value(DEFAULT_IMPORTO_PROVVIGIONE.doubleValue()))
//            .andExpect(jsonPath("$.oscuraIncarico").value(DEFAULT_OSCURA_INCARICO.booleanValue()))
//            .andExpect(jsonPath("$.flagLocato").value(DEFAULT_FLAG_LOCATO.booleanValue()))
//            .andExpect(jsonPath("$.nomeConduttore").value(DEFAULT_NOME_CONDUTTORE.toString()))
//            .andExpect(jsonPath("$.cognomeConduttore").value(DEFAULT_COGNOME_CONDUTTORE.toString()))
//            .andExpect(jsonPath("$.dataInizioLocazione").value(DEFAULT_DATA_INIZIO_LOCAZIONE.toString()))
//            .andExpect(jsonPath("$.dataFineLocazione").value(DEFAULT_DATA_FINE_LOCAZIONE.toString()))
//            .andExpect(jsonPath("$.noteLocazione").value(DEFAULT_NOTE_LOCAZIONE.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByRiferimentoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where riferimento equals to DEFAULT_RIFERIMENTO
//        defaultIncaricoShouldBeFound("riferimento.equals=" + DEFAULT_RIFERIMENTO);
//
//        // Get all the incaricoList where riferimento equals to UPDATED_RIFERIMENTO
//        defaultIncaricoShouldNotBeFound("riferimento.equals=" + UPDATED_RIFERIMENTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByRiferimentoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where riferimento in DEFAULT_RIFERIMENTO or UPDATED_RIFERIMENTO
//        defaultIncaricoShouldBeFound("riferimento.in=" + DEFAULT_RIFERIMENTO + "," + UPDATED_RIFERIMENTO);
//
//        // Get all the incaricoList where riferimento equals to UPDATED_RIFERIMENTO
//        defaultIncaricoShouldNotBeFound("riferimento.in=" + UPDATED_RIFERIMENTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByRiferimentoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where riferimento is not null
//        defaultIncaricoShouldBeFound("riferimento.specified=true");
//
//        // Get all the incaricoList where riferimento is null
//        defaultIncaricoShouldNotBeFound("riferimento.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByTipoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where tipo equals to DEFAULT_TIPO
//        defaultIncaricoShouldBeFound("tipo.equals=" + DEFAULT_TIPO);
//
//        // Get all the incaricoList where tipo equals to UPDATED_TIPO
//        defaultIncaricoShouldNotBeFound("tipo.equals=" + UPDATED_TIPO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByTipoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where tipo in DEFAULT_TIPO or UPDATED_TIPO
//        defaultIncaricoShouldBeFound("tipo.in=" + DEFAULT_TIPO + "," + UPDATED_TIPO);
//
//        // Get all the incaricoList where tipo equals to UPDATED_TIPO
//        defaultIncaricoShouldNotBeFound("tipo.in=" + UPDATED_TIPO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByTipoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where tipo is not null
//        defaultIncaricoShouldBeFound("tipo.specified=true");
//
//        // Get all the incaricoList where tipo is null
//        defaultIncaricoShouldNotBeFound("tipo.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByStatoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where stato equals to DEFAULT_STATO
//        defaultIncaricoShouldBeFound("stato.equals=" + DEFAULT_STATO);
//
//        // Get all the incaricoList where stato equals to UPDATED_STATO
//        defaultIncaricoShouldNotBeFound("stato.equals=" + UPDATED_STATO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByStatoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where stato in DEFAULT_STATO or UPDATED_STATO
//        defaultIncaricoShouldBeFound("stato.in=" + DEFAULT_STATO + "," + UPDATED_STATO);
//
//        // Get all the incaricoList where stato equals to UPDATED_STATO
//        defaultIncaricoShouldNotBeFound("stato.in=" + UPDATED_STATO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByStatoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where stato is not null
//        defaultIncaricoShouldBeFound("stato.specified=true");
//
//        // Get all the incaricoList where stato is null
//        defaultIncaricoShouldNotBeFound("stato.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataScadenzaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataScadenza equals to DEFAULT_DATA_SCADENZA
//        defaultIncaricoShouldBeFound("dataScadenza.equals=" + DEFAULT_DATA_SCADENZA);
//
//        // Get all the incaricoList where dataScadenza equals to UPDATED_DATA_SCADENZA
//        defaultIncaricoShouldNotBeFound("dataScadenza.equals=" + UPDATED_DATA_SCADENZA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataScadenzaIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataScadenza in DEFAULT_DATA_SCADENZA or UPDATED_DATA_SCADENZA
//        defaultIncaricoShouldBeFound("dataScadenza.in=" + DEFAULT_DATA_SCADENZA + "," + UPDATED_DATA_SCADENZA);
//
//        // Get all the incaricoList where dataScadenza equals to UPDATED_DATA_SCADENZA
//        defaultIncaricoShouldNotBeFound("dataScadenza.in=" + UPDATED_DATA_SCADENZA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataScadenzaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataScadenza is not null
//        defaultIncaricoShouldBeFound("dataScadenza.specified=true");
//
//        // Get all the incaricoList where dataScadenza is null
//        defaultIncaricoShouldNotBeFound("dataScadenza.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataScadenzaIsGreaterThanOrEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataScadenza greater than or equals to DEFAULT_DATA_SCADENZA
//        defaultIncaricoShouldBeFound("dataScadenza.greaterOrEqualThan=" + DEFAULT_DATA_SCADENZA);
//
//        // Get all the incaricoList where dataScadenza greater than or equals to UPDATED_DATA_SCADENZA
//        defaultIncaricoShouldNotBeFound("dataScadenza.greaterOrEqualThan=" + UPDATED_DATA_SCADENZA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataScadenzaIsLessThanSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataScadenza less than or equals to DEFAULT_DATA_SCADENZA
//        defaultIncaricoShouldNotBeFound("dataScadenza.lessThan=" + DEFAULT_DATA_SCADENZA);
//
//        // Get all the incaricoList where dataScadenza less than or equals to UPDATED_DATA_SCADENZA
//        defaultIncaricoShouldBeFound("dataScadenza.lessThan=" + UPDATED_DATA_SCADENZA);
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAgenteIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where agente equals to DEFAULT_AGENTE
//        defaultIncaricoShouldBeFound("agente.equals=" + DEFAULT_AGENTE);
//
//        // Get all the incaricoList where agente equals to UPDATED_AGENTE
//        defaultIncaricoShouldNotBeFound("agente.equals=" + UPDATED_AGENTE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAgenteIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where agente in DEFAULT_AGENTE or UPDATED_AGENTE
//        defaultIncaricoShouldBeFound("agente.in=" + DEFAULT_AGENTE + "," + UPDATED_AGENTE);
//
//        // Get all the incaricoList where agente equals to UPDATED_AGENTE
//        defaultIncaricoShouldNotBeFound("agente.in=" + UPDATED_AGENTE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAgenteIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where agente is not null
//        defaultIncaricoShouldBeFound("agente.specified=true");
//
//        // Get all the incaricoList where agente is null
//        defaultIncaricoShouldNotBeFound("agente.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAgenteDiContattoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where agenteDiContatto equals to DEFAULT_AGENTE_DI_CONTATTO
//        defaultIncaricoShouldBeFound("agenteDiContatto.equals=" + DEFAULT_AGENTE_DI_CONTATTO);
//
//        // Get all the incaricoList where agenteDiContatto equals to UPDATED_AGENTE_DI_CONTATTO
//        defaultIncaricoShouldNotBeFound("agenteDiContatto.equals=" + UPDATED_AGENTE_DI_CONTATTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAgenteDiContattoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where agenteDiContatto in DEFAULT_AGENTE_DI_CONTATTO or UPDATED_AGENTE_DI_CONTATTO
//        defaultIncaricoShouldBeFound("agenteDiContatto.in=" + DEFAULT_AGENTE_DI_CONTATTO + "," + UPDATED_AGENTE_DI_CONTATTO);
//
//        // Get all the incaricoList where agenteDiContatto equals to UPDATED_AGENTE_DI_CONTATTO
//        defaultIncaricoShouldNotBeFound("agenteDiContatto.in=" + UPDATED_AGENTE_DI_CONTATTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAgenteDiContattoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where agenteDiContatto is not null
//        defaultIncaricoShouldBeFound("agenteDiContatto.specified=true");
//
//        // Get all the incaricoList where agenteDiContatto is null
//        defaultIncaricoShouldNotBeFound("agenteDiContatto.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataContattoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataContatto equals to DEFAULT_DATA_CONTATTO
//        defaultIncaricoShouldBeFound("dataContatto.equals=" + DEFAULT_DATA_CONTATTO);
//
//        // Get all the incaricoList where dataContatto equals to UPDATED_DATA_CONTATTO
//        defaultIncaricoShouldNotBeFound("dataContatto.equals=" + UPDATED_DATA_CONTATTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataContattoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataContatto in DEFAULT_DATA_CONTATTO or UPDATED_DATA_CONTATTO
//        defaultIncaricoShouldBeFound("dataContatto.in=" + DEFAULT_DATA_CONTATTO + "," + UPDATED_DATA_CONTATTO);
//
//        // Get all the incaricoList where dataContatto equals to UPDATED_DATA_CONTATTO
//        defaultIncaricoShouldNotBeFound("dataContatto.in=" + UPDATED_DATA_CONTATTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataContattoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataContatto is not null
//        defaultIncaricoShouldBeFound("dataContatto.specified=true");
//
//        // Get all the incaricoList where dataContatto is null
//        defaultIncaricoShouldNotBeFound("dataContatto.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataContattoIsGreaterThanOrEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataContatto greater than or equals to DEFAULT_DATA_CONTATTO
//        defaultIncaricoShouldBeFound("dataContatto.greaterOrEqualThan=" + DEFAULT_DATA_CONTATTO);
//
//        // Get all the incaricoList where dataContatto greater than or equals to UPDATED_DATA_CONTATTO
//        defaultIncaricoShouldNotBeFound("dataContatto.greaterOrEqualThan=" + UPDATED_DATA_CONTATTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataContattoIsLessThanSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataContatto less than or equals to DEFAULT_DATA_CONTATTO
//        defaultIncaricoShouldNotBeFound("dataContatto.lessThan=" + DEFAULT_DATA_CONTATTO);
//
//        // Get all the incaricoList where dataContatto less than or equals to UPDATED_DATA_CONTATTO
//        defaultIncaricoShouldBeFound("dataContatto.lessThan=" + UPDATED_DATA_CONTATTO);
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAlertFiscaliIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where alertFiscali equals to DEFAULT_ALERT_FISCALI
//        defaultIncaricoShouldBeFound("alertFiscali.equals=" + DEFAULT_ALERT_FISCALI);
//
//        // Get all the incaricoList where alertFiscali equals to UPDATED_ALERT_FISCALI
//        defaultIncaricoShouldNotBeFound("alertFiscali.equals=" + UPDATED_ALERT_FISCALI);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAlertFiscaliIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where alertFiscali in DEFAULT_ALERT_FISCALI or UPDATED_ALERT_FISCALI
//        defaultIncaricoShouldBeFound("alertFiscali.in=" + DEFAULT_ALERT_FISCALI + "," + UPDATED_ALERT_FISCALI);
//
//        // Get all the incaricoList where alertFiscali equals to UPDATED_ALERT_FISCALI
//        defaultIncaricoShouldNotBeFound("alertFiscali.in=" + UPDATED_ALERT_FISCALI);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAlertFiscaliIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where alertFiscali is not null
//        defaultIncaricoShouldBeFound("alertFiscali.specified=true");
//
//        // Get all the incaricoList where alertFiscali is null
//        defaultIncaricoShouldNotBeFound("alertFiscali.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAlertCortesiaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where alertCortesia equals to DEFAULT_ALERT_CORTESIA
//        defaultIncaricoShouldBeFound("alertCortesia.equals=" + DEFAULT_ALERT_CORTESIA);
//
//        // Get all the incaricoList where alertCortesia equals to UPDATED_ALERT_CORTESIA
//        defaultIncaricoShouldNotBeFound("alertCortesia.equals=" + UPDATED_ALERT_CORTESIA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAlertCortesiaIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where alertCortesia in DEFAULT_ALERT_CORTESIA or UPDATED_ALERT_CORTESIA
//        defaultIncaricoShouldBeFound("alertCortesia.in=" + DEFAULT_ALERT_CORTESIA + "," + UPDATED_ALERT_CORTESIA);
//
//        // Get all the incaricoList where alertCortesia equals to UPDATED_ALERT_CORTESIA
//        defaultIncaricoShouldNotBeFound("alertCortesia.in=" + UPDATED_ALERT_CORTESIA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAlertCortesiaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where alertCortesia is not null
//        defaultIncaricoShouldBeFound("alertCortesia.specified=true");
//
//        // Get all the incaricoList where alertCortesia is null
//        defaultIncaricoShouldNotBeFound("alertCortesia.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrivacyIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where privacy equals to DEFAULT_PRIVACY
//        defaultIncaricoShouldBeFound("privacy.equals=" + DEFAULT_PRIVACY);
//
//        // Get all the incaricoList where privacy equals to UPDATED_PRIVACY
//        defaultIncaricoShouldNotBeFound("privacy.equals=" + UPDATED_PRIVACY);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrivacyIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where privacy in DEFAULT_PRIVACY or UPDATED_PRIVACY
//        defaultIncaricoShouldBeFound("privacy.in=" + DEFAULT_PRIVACY + "," + UPDATED_PRIVACY);
//
//        // Get all the incaricoList where privacy equals to UPDATED_PRIVACY
//        defaultIncaricoShouldNotBeFound("privacy.in=" + UPDATED_PRIVACY);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrivacyIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where privacy is not null
//        defaultIncaricoShouldBeFound("privacy.specified=true");
//
//        // Get all the incaricoList where privacy is null
//        defaultIncaricoShouldNotBeFound("privacy.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAntiriciclaggioIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where antiriciclaggio equals to DEFAULT_ANTIRICICLAGGIO
//        defaultIncaricoShouldBeFound("antiriciclaggio.equals=" + DEFAULT_ANTIRICICLAGGIO);
//
//        // Get all the incaricoList where antiriciclaggio equals to UPDATED_ANTIRICICLAGGIO
//        defaultIncaricoShouldNotBeFound("antiriciclaggio.equals=" + UPDATED_ANTIRICICLAGGIO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAntiriciclaggioIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where antiriciclaggio in DEFAULT_ANTIRICICLAGGIO or UPDATED_ANTIRICICLAGGIO
//        defaultIncaricoShouldBeFound("antiriciclaggio.in=" + DEFAULT_ANTIRICICLAGGIO + "," + UPDATED_ANTIRICICLAGGIO);
//
//        // Get all the incaricoList where antiriciclaggio equals to UPDATED_ANTIRICICLAGGIO
//        defaultIncaricoShouldNotBeFound("antiriciclaggio.in=" + UPDATED_ANTIRICICLAGGIO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAntiriciclaggioIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where antiriciclaggio is not null
//        defaultIncaricoShouldBeFound("antiriciclaggio.specified=true");
//
//        // Get all the incaricoList where antiriciclaggio is null
//        defaultIncaricoShouldNotBeFound("antiriciclaggio.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoRichiestaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoRichiesta equals to DEFAULT_PREZZO_RICHIESTA
//        defaultIncaricoShouldBeFound("prezzoRichiesta.equals=" + DEFAULT_PREZZO_RICHIESTA);
//
//        // Get all the incaricoList where prezzoRichiesta equals to UPDATED_PREZZO_RICHIESTA
//        defaultIncaricoShouldNotBeFound("prezzoRichiesta.equals=" + UPDATED_PREZZO_RICHIESTA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoRichiestaIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoRichiesta in DEFAULT_PREZZO_RICHIESTA or UPDATED_PREZZO_RICHIESTA
//        defaultIncaricoShouldBeFound("prezzoRichiesta.in=" + DEFAULT_PREZZO_RICHIESTA + "," + UPDATED_PREZZO_RICHIESTA);
//
//        // Get all the incaricoList where prezzoRichiesta equals to UPDATED_PREZZO_RICHIESTA
//        defaultIncaricoShouldNotBeFound("prezzoRichiesta.in=" + UPDATED_PREZZO_RICHIESTA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoRichiestaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoRichiesta is not null
//        defaultIncaricoShouldBeFound("prezzoRichiesta.specified=true");
//
//        // Get all the incaricoList where prezzoRichiesta is null
//        defaultIncaricoShouldNotBeFound("prezzoRichiesta.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoMinimoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoMinimo equals to DEFAULT_PREZZO_MINIMO
//        defaultIncaricoShouldBeFound("prezzoMinimo.equals=" + DEFAULT_PREZZO_MINIMO);
//
//        // Get all the incaricoList where prezzoMinimo equals to UPDATED_PREZZO_MINIMO
//        defaultIncaricoShouldNotBeFound("prezzoMinimo.equals=" + UPDATED_PREZZO_MINIMO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoMinimoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoMinimo in DEFAULT_PREZZO_MINIMO or UPDATED_PREZZO_MINIMO
//        defaultIncaricoShouldBeFound("prezzoMinimo.in=" + DEFAULT_PREZZO_MINIMO + "," + UPDATED_PREZZO_MINIMO);
//
//        // Get all the incaricoList where prezzoMinimo equals to UPDATED_PREZZO_MINIMO
//        defaultIncaricoShouldNotBeFound("prezzoMinimo.in=" + UPDATED_PREZZO_MINIMO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoMinimoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoMinimo is not null
//        defaultIncaricoShouldBeFound("prezzoMinimo.specified=true");
//
//        // Get all the incaricoList where prezzoMinimo is null
//        defaultIncaricoShouldNotBeFound("prezzoMinimo.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoAcquistoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoAcquisto equals to DEFAULT_PREZZO_ACQUISTO
//        defaultIncaricoShouldBeFound("prezzoAcquisto.equals=" + DEFAULT_PREZZO_ACQUISTO);
//
//        // Get all the incaricoList where prezzoAcquisto equals to UPDATED_PREZZO_ACQUISTO
//        defaultIncaricoShouldNotBeFound("prezzoAcquisto.equals=" + UPDATED_PREZZO_ACQUISTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoAcquistoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoAcquisto in DEFAULT_PREZZO_ACQUISTO or UPDATED_PREZZO_ACQUISTO
//        defaultIncaricoShouldBeFound("prezzoAcquisto.in=" + DEFAULT_PREZZO_ACQUISTO + "," + UPDATED_PREZZO_ACQUISTO);
//
//        // Get all the incaricoList where prezzoAcquisto equals to UPDATED_PREZZO_ACQUISTO
//        defaultIncaricoShouldNotBeFound("prezzoAcquisto.in=" + UPDATED_PREZZO_ACQUISTO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPrezzoAcquistoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where prezzoAcquisto is not null
//        defaultIncaricoShouldBeFound("prezzoAcquisto.specified=true");
//
//        // Get all the incaricoList where prezzoAcquisto is null
//        defaultIncaricoShouldNotBeFound("prezzoAcquisto.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByImportoProvvigioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where importoProvvigione equals to DEFAULT_IMPORTO_PROVVIGIONE
//        defaultIncaricoShouldBeFound("importoProvvigione.equals=" + DEFAULT_IMPORTO_PROVVIGIONE);
//
//        // Get all the incaricoList where importoProvvigione equals to UPDATED_IMPORTO_PROVVIGIONE
//        defaultIncaricoShouldNotBeFound("importoProvvigione.equals=" + UPDATED_IMPORTO_PROVVIGIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByImportoProvvigioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where importoProvvigione in DEFAULT_IMPORTO_PROVVIGIONE or UPDATED_IMPORTO_PROVVIGIONE
//        defaultIncaricoShouldBeFound("importoProvvigione.in=" + DEFAULT_IMPORTO_PROVVIGIONE + "," + UPDATED_IMPORTO_PROVVIGIONE);
//
//        // Get all the incaricoList where importoProvvigione equals to UPDATED_IMPORTO_PROVVIGIONE
//        defaultIncaricoShouldNotBeFound("importoProvvigione.in=" + UPDATED_IMPORTO_PROVVIGIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByImportoProvvigioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where importoProvvigione is not null
//        defaultIncaricoShouldBeFound("importoProvvigione.specified=true");
//
//        // Get all the incaricoList where importoProvvigione is null
//        defaultIncaricoShouldNotBeFound("importoProvvigione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByOscuraIncaricoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where oscuraIncarico equals to DEFAULT_OSCURA_INCARICO
//        defaultIncaricoShouldBeFound("oscuraIncarico.equals=" + DEFAULT_OSCURA_INCARICO);
//
//        // Get all the incaricoList where oscuraIncarico equals to UPDATED_OSCURA_INCARICO
//        defaultIncaricoShouldNotBeFound("oscuraIncarico.equals=" + UPDATED_OSCURA_INCARICO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByOscuraIncaricoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where oscuraIncarico in DEFAULT_OSCURA_INCARICO or UPDATED_OSCURA_INCARICO
//        defaultIncaricoShouldBeFound("oscuraIncarico.in=" + DEFAULT_OSCURA_INCARICO + "," + UPDATED_OSCURA_INCARICO);
//
//        // Get all the incaricoList where oscuraIncarico equals to UPDATED_OSCURA_INCARICO
//        defaultIncaricoShouldNotBeFound("oscuraIncarico.in=" + UPDATED_OSCURA_INCARICO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByOscuraIncaricoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where oscuraIncarico is not null
//        defaultIncaricoShouldBeFound("oscuraIncarico.specified=true");
//
//        // Get all the incaricoList where oscuraIncarico is null
//        defaultIncaricoShouldNotBeFound("oscuraIncarico.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByFlagLocatoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where flagLocato equals to DEFAULT_FLAG_LOCATO
//        defaultIncaricoShouldBeFound("flagLocato.equals=" + DEFAULT_FLAG_LOCATO);
//
//        // Get all the incaricoList where flagLocato equals to UPDATED_FLAG_LOCATO
//        defaultIncaricoShouldNotBeFound("flagLocato.equals=" + UPDATED_FLAG_LOCATO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByFlagLocatoIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where flagLocato in DEFAULT_FLAG_LOCATO or UPDATED_FLAG_LOCATO
//        defaultIncaricoShouldBeFound("flagLocato.in=" + DEFAULT_FLAG_LOCATO + "," + UPDATED_FLAG_LOCATO);
//
//        // Get all the incaricoList where flagLocato equals to UPDATED_FLAG_LOCATO
//        defaultIncaricoShouldNotBeFound("flagLocato.in=" + UPDATED_FLAG_LOCATO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByFlagLocatoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where flagLocato is not null
//        defaultIncaricoShouldBeFound("flagLocato.specified=true");
//
//        // Get all the incaricoList where flagLocato is null
//        defaultIncaricoShouldNotBeFound("flagLocato.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByNomeConduttoreIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where nomeConduttore equals to DEFAULT_NOME_CONDUTTORE
//        defaultIncaricoShouldBeFound("nomeConduttore.equals=" + DEFAULT_NOME_CONDUTTORE);
//
//        // Get all the incaricoList where nomeConduttore equals to UPDATED_NOME_CONDUTTORE
//        defaultIncaricoShouldNotBeFound("nomeConduttore.equals=" + UPDATED_NOME_CONDUTTORE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByNomeConduttoreIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where nomeConduttore in DEFAULT_NOME_CONDUTTORE or UPDATED_NOME_CONDUTTORE
//        defaultIncaricoShouldBeFound("nomeConduttore.in=" + DEFAULT_NOME_CONDUTTORE + "," + UPDATED_NOME_CONDUTTORE);
//
//        // Get all the incaricoList where nomeConduttore equals to UPDATED_NOME_CONDUTTORE
//        defaultIncaricoShouldNotBeFound("nomeConduttore.in=" + UPDATED_NOME_CONDUTTORE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByNomeConduttoreIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where nomeConduttore is not null
//        defaultIncaricoShouldBeFound("nomeConduttore.specified=true");
//
//        // Get all the incaricoList where nomeConduttore is null
//        defaultIncaricoShouldNotBeFound("nomeConduttore.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByCognomeConduttoreIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where cognomeConduttore equals to DEFAULT_COGNOME_CONDUTTORE
//        defaultIncaricoShouldBeFound("cognomeConduttore.equals=" + DEFAULT_COGNOME_CONDUTTORE);
//
//        // Get all the incaricoList where cognomeConduttore equals to UPDATED_COGNOME_CONDUTTORE
//        defaultIncaricoShouldNotBeFound("cognomeConduttore.equals=" + UPDATED_COGNOME_CONDUTTORE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByCognomeConduttoreIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where cognomeConduttore in DEFAULT_COGNOME_CONDUTTORE or UPDATED_COGNOME_CONDUTTORE
//        defaultIncaricoShouldBeFound("cognomeConduttore.in=" + DEFAULT_COGNOME_CONDUTTORE + "," + UPDATED_COGNOME_CONDUTTORE);
//
//        // Get all the incaricoList where cognomeConduttore equals to UPDATED_COGNOME_CONDUTTORE
//        defaultIncaricoShouldNotBeFound("cognomeConduttore.in=" + UPDATED_COGNOME_CONDUTTORE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByCognomeConduttoreIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where cognomeConduttore is not null
//        defaultIncaricoShouldBeFound("cognomeConduttore.specified=true");
//
//        // Get all the incaricoList where cognomeConduttore is null
//        defaultIncaricoShouldNotBeFound("cognomeConduttore.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataInizioLocazioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataInizioLocazione equals to DEFAULT_DATA_INIZIO_LOCAZIONE
//        defaultIncaricoShouldBeFound("dataInizioLocazione.equals=" + DEFAULT_DATA_INIZIO_LOCAZIONE);
//
//        // Get all the incaricoList where dataInizioLocazione equals to UPDATED_DATA_INIZIO_LOCAZIONE
//        defaultIncaricoShouldNotBeFound("dataInizioLocazione.equals=" + UPDATED_DATA_INIZIO_LOCAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataInizioLocazioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataInizioLocazione in DEFAULT_DATA_INIZIO_LOCAZIONE or UPDATED_DATA_INIZIO_LOCAZIONE
//        defaultIncaricoShouldBeFound("dataInizioLocazione.in=" + DEFAULT_DATA_INIZIO_LOCAZIONE + "," + UPDATED_DATA_INIZIO_LOCAZIONE);
//
//        // Get all the incaricoList where dataInizioLocazione equals to UPDATED_DATA_INIZIO_LOCAZIONE
//        defaultIncaricoShouldNotBeFound("dataInizioLocazione.in=" + UPDATED_DATA_INIZIO_LOCAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataInizioLocazioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataInizioLocazione is not null
//        defaultIncaricoShouldBeFound("dataInizioLocazione.specified=true");
//
//        // Get all the incaricoList where dataInizioLocazione is null
//        defaultIncaricoShouldNotBeFound("dataInizioLocazione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataInizioLocazioneIsGreaterThanOrEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataInizioLocazione greater than or equals to DEFAULT_DATA_INIZIO_LOCAZIONE
//        defaultIncaricoShouldBeFound("dataInizioLocazione.greaterOrEqualThan=" + DEFAULT_DATA_INIZIO_LOCAZIONE);
//
//        // Get all the incaricoList where dataInizioLocazione greater than or equals to UPDATED_DATA_INIZIO_LOCAZIONE
//        defaultIncaricoShouldNotBeFound("dataInizioLocazione.greaterOrEqualThan=" + UPDATED_DATA_INIZIO_LOCAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataInizioLocazioneIsLessThanSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataInizioLocazione less than or equals to DEFAULT_DATA_INIZIO_LOCAZIONE
//        defaultIncaricoShouldNotBeFound("dataInizioLocazione.lessThan=" + DEFAULT_DATA_INIZIO_LOCAZIONE);
//
//        // Get all the incaricoList where dataInizioLocazione less than or equals to UPDATED_DATA_INIZIO_LOCAZIONE
//        defaultIncaricoShouldBeFound("dataInizioLocazione.lessThan=" + UPDATED_DATA_INIZIO_LOCAZIONE);
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataFineLocazioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataFineLocazione equals to DEFAULT_DATA_FINE_LOCAZIONE
//        defaultIncaricoShouldBeFound("dataFineLocazione.equals=" + DEFAULT_DATA_FINE_LOCAZIONE);
//
//        // Get all the incaricoList where dataFineLocazione equals to UPDATED_DATA_FINE_LOCAZIONE
//        defaultIncaricoShouldNotBeFound("dataFineLocazione.equals=" + UPDATED_DATA_FINE_LOCAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataFineLocazioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataFineLocazione in DEFAULT_DATA_FINE_LOCAZIONE or UPDATED_DATA_FINE_LOCAZIONE
//        defaultIncaricoShouldBeFound("dataFineLocazione.in=" + DEFAULT_DATA_FINE_LOCAZIONE + "," + UPDATED_DATA_FINE_LOCAZIONE);
//
//        // Get all the incaricoList where dataFineLocazione equals to UPDATED_DATA_FINE_LOCAZIONE
//        defaultIncaricoShouldNotBeFound("dataFineLocazione.in=" + UPDATED_DATA_FINE_LOCAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataFineLocazioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataFineLocazione is not null
//        defaultIncaricoShouldBeFound("dataFineLocazione.specified=true");
//
//        // Get all the incaricoList where dataFineLocazione is null
//        defaultIncaricoShouldNotBeFound("dataFineLocazione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataFineLocazioneIsGreaterThanOrEqualToSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataFineLocazione greater than or equals to DEFAULT_DATA_FINE_LOCAZIONE
//        defaultIncaricoShouldBeFound("dataFineLocazione.greaterOrEqualThan=" + DEFAULT_DATA_FINE_LOCAZIONE);
//
//        // Get all the incaricoList where dataFineLocazione greater than or equals to UPDATED_DATA_FINE_LOCAZIONE
//        defaultIncaricoShouldNotBeFound("dataFineLocazione.greaterOrEqualThan=" + UPDATED_DATA_FINE_LOCAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByDataFineLocazioneIsLessThanSomething() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        // Get all the incaricoList where dataFineLocazione less than or equals to DEFAULT_DATA_FINE_LOCAZIONE
//        defaultIncaricoShouldNotBeFound("dataFineLocazione.lessThan=" + DEFAULT_DATA_FINE_LOCAZIONE);
//
//        // Get all the incaricoList where dataFineLocazione less than or equals to UPDATED_DATA_FINE_LOCAZIONE
//        defaultIncaricoShouldBeFound("dataFineLocazione.lessThan=" + UPDATED_DATA_FINE_LOCAZIONE);
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByListaContattiIsEqualToSomething() throws Exception {
//        // Initialize the database
//        ListaContatti listaContatti = ListaContattiResourceIntTest.createEntity(em);
//        em.persist(listaContatti);
//        em.flush();
//        incarico.addListaContatti(listaContatti);
//        incaricoRepository.saveAndFlush(incarico);
//        Long listaContattiId = listaContatti.getId();
//
//        // Get all the incaricoList where listaContatti equals to listaContattiId
//        defaultIncaricoShouldBeFound("listaContattiId.equals=" + listaContattiId);
//
//        // Get all the incaricoList where listaContatti equals to listaContattiId + 1
//        defaultIncaricoShouldNotBeFound("listaContattiId.equals=" + (listaContattiId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByImmobileIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Immobile immobile = ImmobileResourceIntTest.createEntity(em);
//        em.persist(immobile);
//        em.flush();
//        incarico.setImmobile(immobile);
//        incaricoRepository.saveAndFlush(incarico);
//        Long immobileId = immobile.getId();
//
//        // Get all the incaricoList where immobile equals to immobileId
//        defaultIncaricoShouldBeFound("immobileId.equals=" + immobileId);
//
//        // Get all the incaricoList where immobile equals to immobileId + 1
//        defaultIncaricoShouldNotBeFound("immobileId.equals=" + (immobileId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByPartnerIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Partner partner = PartnerResourceIntTest.createEntity(em);
//        em.persist(partner);
//        em.flush();
//        incarico.addPartner(partner);
//        incaricoRepository.saveAndFlush(incarico);
//        Long partnerId = partner.getId();
//
//        // Get all the incaricoList where partner equals to partnerId
//        defaultIncaricoShouldBeFound("partnerId.equals=" + partnerId);
//
//        // Get all the incaricoList where partner equals to partnerId + 1
//        defaultIncaricoShouldNotBeFound("partnerId.equals=" + (partnerId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByCommittenteIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Cliente committente = ClienteResourceIntTest.createEntity(em);
//        em.persist(committente);
//        em.flush();
//        incarico.addCommittente(committente);
//        incaricoRepository.saveAndFlush(incarico);
//        Long committenteId = committente.getId();
//
//        // Get all the incaricoList where committente equals to committenteId
//        defaultIncaricoShouldBeFound("committenteId.equals=" + committenteId);
//
//        // Get all the incaricoList where committente equals to committenteId + 1
//        defaultIncaricoShouldNotBeFound("committenteId.equals=" + (committenteId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByProponenteIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Cliente proponente = ClienteResourceIntTest.createEntity(em);
//        em.persist(proponente);
//        em.flush();
//        incarico.addProponente(proponente);
//        incaricoRepository.saveAndFlush(incarico);
//        Long proponenteId = proponente.getId();
//
//        // Get all the incaricoList where proponente equals to proponenteId
//        defaultIncaricoShouldBeFound("proponenteId.equals=" + proponenteId);
//
//        // Get all the incaricoList where proponente equals to proponenteId + 1
//        defaultIncaricoShouldNotBeFound("proponenteId.equals=" + (proponenteId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosByAcquirenteLocatarioIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Cliente acquirenteLocatario = ClienteResourceIntTest.createEntity(em);
//        em.persist(acquirenteLocatario);
//        em.flush();
//        incarico.addAcquirenteLocatario(acquirenteLocatario);
//        incaricoRepository.saveAndFlush(incarico);
//        Long acquirenteLocatarioId = acquirenteLocatario.getId();
//
//        // Get all the incaricoList where acquirenteLocatario equals to acquirenteLocatarioId
//        defaultIncaricoShouldBeFound("acquirenteLocatarioId.equals=" + acquirenteLocatarioId);
//
//        // Get all the incaricoList where acquirenteLocatario equals to acquirenteLocatarioId + 1
//        defaultIncaricoShouldNotBeFound("acquirenteLocatarioId.equals=" + (acquirenteLocatarioId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllIncaricosBySegnalatoreIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Cliente segnalatore = ClienteResourceIntTest.createEntity(em);
//        em.persist(segnalatore);
//        em.flush();
//        incarico.addSegnalatore(segnalatore);
//        incaricoRepository.saveAndFlush(incarico);
//        Long segnalatoreId = segnalatore.getId();
//
//        // Get all the incaricoList where segnalatore equals to segnalatoreId
//        defaultIncaricoShouldBeFound("segnalatoreId.equals=" + segnalatoreId);
//
//        // Get all the incaricoList where segnalatore equals to segnalatoreId + 1
//        defaultIncaricoShouldNotBeFound("segnalatoreId.equals=" + (segnalatoreId + 1));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is returned
//     */
//    private void defaultIncaricoShouldBeFound(String filter) throws Exception {
//        restIncaricoMockMvc.perform(get("/api/incaricos?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(incarico.getId().intValue())))
//            .andExpect(jsonPath("$.[*].riferimento").value(hasItem(DEFAULT_RIFERIMENTO.toString())))
//            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
//            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.toString())))
//            .andExpect(jsonPath("$.[*].dataScadenza").value(hasItem(DEFAULT_DATA_SCADENZA.toString())))
//            .andExpect(jsonPath("$.[*].agente").value(hasItem(DEFAULT_AGENTE.toString())))
//            .andExpect(jsonPath("$.[*].agenteDiContatto").value(hasItem(DEFAULT_AGENTE_DI_CONTATTO.toString())))
//            .andExpect(jsonPath("$.[*].dataContatto").value(hasItem(DEFAULT_DATA_CONTATTO.toString())))
//            .andExpect(jsonPath("$.[*].noteTrattativa").value(hasItem(DEFAULT_NOTE_TRATTATIVA.toString())))
//            .andExpect(jsonPath("$.[*].datiFiscali").value(hasItem(DEFAULT_DATI_FISCALI.toString())))
//            .andExpect(jsonPath("$.[*].alertFiscali").value(hasItem(DEFAULT_ALERT_FISCALI.toString())))
//            .andExpect(jsonPath("$.[*].alertCortesia").value(hasItem(DEFAULT_ALERT_CORTESIA.toString())))
//            .andExpect(jsonPath("$.[*].privacy").value(hasItem(DEFAULT_PRIVACY.booleanValue())))
//            .andExpect(jsonPath("$.[*].antiriciclaggio").value(hasItem(DEFAULT_ANTIRICICLAGGIO.booleanValue())))
//            .andExpect(jsonPath("$.[*].prezzoRichiesta").value(hasItem(DEFAULT_PREZZO_RICHIESTA.doubleValue())))
//            .andExpect(jsonPath("$.[*].prezzoMinimo").value(hasItem(DEFAULT_PREZZO_MINIMO.doubleValue())))
//            .andExpect(jsonPath("$.[*].prezzoAcquisto").value(hasItem(DEFAULT_PREZZO_ACQUISTO.doubleValue())))
//            .andExpect(jsonPath("$.[*].importoProvvigione").value(hasItem(DEFAULT_IMPORTO_PROVVIGIONE.doubleValue())))
//            .andExpect(jsonPath("$.[*].oscuraIncarico").value(hasItem(DEFAULT_OSCURA_INCARICO.booleanValue())))
//            .andExpect(jsonPath("$.[*].flagLocato").value(hasItem(DEFAULT_FLAG_LOCATO.booleanValue())))
//            .andExpect(jsonPath("$.[*].nomeConduttore").value(hasItem(DEFAULT_NOME_CONDUTTORE.toString())))
//            .andExpect(jsonPath("$.[*].cognomeConduttore").value(hasItem(DEFAULT_COGNOME_CONDUTTORE.toString())))
//            .andExpect(jsonPath("$.[*].dataInizioLocazione").value(hasItem(DEFAULT_DATA_INIZIO_LOCAZIONE.toString())))
//            .andExpect(jsonPath("$.[*].dataFineLocazione").value(hasItem(DEFAULT_DATA_FINE_LOCAZIONE.toString())))
//            .andExpect(jsonPath("$.[*].noteLocazione").value(hasItem(DEFAULT_NOTE_LOCAZIONE.toString())));
//
//        // Check, that the count call also returns 1
//        restIncaricoMockMvc.perform(get("/api/incaricos/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("1"));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is not returned
//     */
//    private void defaultIncaricoShouldNotBeFound(String filter) throws Exception {
//        restIncaricoMockMvc.perform(get("/api/incaricos?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$").isArray())
//            .andExpect(jsonPath("$").isEmpty());
//
//        // Check, that the count call also returns 0
//        restIncaricoMockMvc.perform(get("/api/incaricos/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("0"));
//    }
//
//
//    @Test
//    @Transactional
//    public void getNonExistingIncarico() throws Exception {
//        // Get the incarico
//        restIncaricoMockMvc.perform(get("/api/incaricos/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateIncarico() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        int databaseSizeBeforeUpdate = incaricoRepository.findAll().size();
//
//        // Update the incarico
//        Incarico updatedIncarico = incaricoRepository.findById(incarico.getId()).get();
//        // Disconnect from session so that the updates on updatedIncarico are not directly saved in db
//        em.detach(updatedIncarico);
//        updatedIncarico
//            .riferimento(UPDATED_RIFERIMENTO)
//            .tipo(UPDATED_TIPO)
//            .stato(UPDATED_STATO)
//            .dataScadenza(UPDATED_DATA_SCADENZA)
//            .agente(UPDATED_AGENTE)
//            .agenteDiContatto(UPDATED_AGENTE_DI_CONTATTO)
//            .dataContatto(UPDATED_DATA_CONTATTO)
//            .noteTrattativa(UPDATED_NOTE_TRATTATIVA)
//            .datiFiscali(UPDATED_DATI_FISCALI)
//            .alertFiscali(UPDATED_ALERT_FISCALI)
//            .alertCortesia(UPDATED_ALERT_CORTESIA)
//            .privacy(UPDATED_PRIVACY)
//            .antiriciclaggio(UPDATED_ANTIRICICLAGGIO)
//            .prezzoRichiesta(UPDATED_PREZZO_RICHIESTA)
//            .prezzoMinimo(UPDATED_PREZZO_MINIMO)
//            .prezzoAcquisto(UPDATED_PREZZO_ACQUISTO)
//            .importoProvvigione(UPDATED_IMPORTO_PROVVIGIONE)
//            .oscuraIncarico(UPDATED_OSCURA_INCARICO)
//            .flagLocato(UPDATED_FLAG_LOCATO)
//            .nomeConduttore(UPDATED_NOME_CONDUTTORE)
//            .cognomeConduttore(UPDATED_COGNOME_CONDUTTORE)
//            .dataInizioLocazione(UPDATED_DATA_INIZIO_LOCAZIONE)
//            .dataFineLocazione(UPDATED_DATA_FINE_LOCAZIONE)
//            .noteLocazione(UPDATED_NOTE_LOCAZIONE);
//        IncaricoDTO incaricoDTO = incaricoMapper.toDto(updatedIncarico);
//
//        restIncaricoMockMvc.perform(put("/api/incaricos")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(incaricoDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Incarico in the database
//        List<Incarico> incaricoList = incaricoRepository.findAll();
//        assertThat(incaricoList).hasSize(databaseSizeBeforeUpdate);
//        Incarico testIncarico = incaricoList.get(incaricoList.size() - 1);
//        assertThat(testIncarico.getRiferimento()).isEqualTo(UPDATED_RIFERIMENTO);
//        assertThat(testIncarico.getTipo()).isEqualTo(UPDATED_TIPO);
//        assertThat(testIncarico.getStato()).isEqualTo(UPDATED_STATO);
//        assertThat(testIncarico.getDataScadenza()).isEqualTo(UPDATED_DATA_SCADENZA);
//        assertThat(testIncarico.getAgente()).isEqualTo(UPDATED_AGENTE);
//        assertThat(testIncarico.getAgenteDiContatto()).isEqualTo(UPDATED_AGENTE_DI_CONTATTO);
//        assertThat(testIncarico.getDataContatto()).isEqualTo(UPDATED_DATA_CONTATTO);
//        assertThat(testIncarico.getNoteTrattativa()).isEqualTo(UPDATED_NOTE_TRATTATIVA);
//        assertThat(testIncarico.getDatiFiscali()).isEqualTo(UPDATED_DATI_FISCALI);
//        assertThat(testIncarico.getAlertFiscali()).isEqualTo(UPDATED_ALERT_FISCALI);
//        assertThat(testIncarico.getAlertCortesia()).isEqualTo(UPDATED_ALERT_CORTESIA);
//        assertThat(testIncarico.isPrivacy()).isEqualTo(UPDATED_PRIVACY);
//        assertThat(testIncarico.isAntiriciclaggio()).isEqualTo(UPDATED_ANTIRICICLAGGIO);
//        assertThat(testIncarico.getPrezzoRichiesta()).isEqualTo(UPDATED_PREZZO_RICHIESTA);
//        assertThat(testIncarico.getPrezzoMinimo()).isEqualTo(UPDATED_PREZZO_MINIMO);
//        assertThat(testIncarico.getPrezzoAcquisto()).isEqualTo(UPDATED_PREZZO_ACQUISTO);
//        assertThat(testIncarico.getImportoProvvigione()).isEqualTo(UPDATED_IMPORTO_PROVVIGIONE);
//        assertThat(testIncarico.isOscuraIncarico()).isEqualTo(UPDATED_OSCURA_INCARICO);
//        assertThat(testIncarico.isFlagLocato()).isEqualTo(UPDATED_FLAG_LOCATO);
//        assertThat(testIncarico.getNomeConduttore()).isEqualTo(UPDATED_NOME_CONDUTTORE);
//        assertThat(testIncarico.getCognomeConduttore()).isEqualTo(UPDATED_COGNOME_CONDUTTORE);
//        assertThat(testIncarico.getDataInizioLocazione()).isEqualTo(UPDATED_DATA_INIZIO_LOCAZIONE);
//        assertThat(testIncarico.getDataFineLocazione()).isEqualTo(UPDATED_DATA_FINE_LOCAZIONE);
//        assertThat(testIncarico.getNoteLocazione()).isEqualTo(UPDATED_NOTE_LOCAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingIncarico() throws Exception {
//        int databaseSizeBeforeUpdate = incaricoRepository.findAll().size();
//
//        // Create the Incarico
//        IncaricoDTO incaricoDTO = incaricoMapper.toDto(incarico);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restIncaricoMockMvc.perform(put("/api/incaricos")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(incaricoDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Incarico in the database
//        List<Incarico> incaricoList = incaricoRepository.findAll();
//        assertThat(incaricoList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteIncarico() throws Exception {
//        // Initialize the database
//        incaricoRepository.saveAndFlush(incarico);
//
//        int databaseSizeBeforeDelete = incaricoRepository.findAll().size();
//
//        // Get the incarico
//        restIncaricoMockMvc.perform(delete("/api/incaricos/{id}", incarico.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Incarico> incaricoList = incaricoRepository.findAll();
//        assertThat(incaricoList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Incarico.class);
//        Incarico incarico1 = new Incarico();
//        incarico1.setId(1L);
//        Incarico incarico2 = new Incarico();
//        incarico2.setId(incarico1.getId());
//        assertThat(incarico1).isEqualTo(incarico2);
//        incarico2.setId(2L);
//        assertThat(incarico1).isNotEqualTo(incarico2);
//        incarico1.setId(null);
//        assertThat(incarico1).isNotEqualTo(incarico2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(IncaricoDTO.class);
//        IncaricoDTO incaricoDTO1 = new IncaricoDTO();
//        incaricoDTO1.setId(1L);
//        IncaricoDTO incaricoDTO2 = new IncaricoDTO();
//        assertThat(incaricoDTO1).isNotEqualTo(incaricoDTO2);
//        incaricoDTO2.setId(incaricoDTO1.getId());
//        assertThat(incaricoDTO1).isEqualTo(incaricoDTO2);
//        incaricoDTO2.setId(2L);
//        assertThat(incaricoDTO1).isNotEqualTo(incaricoDTO2);
//        incaricoDTO1.setId(null);
//        assertThat(incaricoDTO1).isNotEqualTo(incaricoDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(incaricoMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(incaricoMapper.fromId(null)).isNull();
//    }
}
