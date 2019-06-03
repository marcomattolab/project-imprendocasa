package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Cliente;
import it.arancia.domain.ListaContatti;
import it.arancia.domain.Tag;
import it.arancia.domain.Incarico;
import it.arancia.domain.Incarico;
import it.arancia.domain.Incarico;
import it.arancia.domain.Incarico;
import it.arancia.repository.ClienteRepository;
import it.arancia.service.ClienteService;
import it.arancia.service.dto.ClienteDTO;
import it.arancia.service.mapper.ClienteMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.ClienteCriteria;
import it.arancia.service.ClienteQueryService;

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


//import static it.arancia.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.TipoMese;
/**
 * Test class for the ClienteResource REST controller.
 *
 * @see ClienteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class ClienteResourceIntTest {

//    private static final String DEFAULT_NOME = "AAAAAAAAAA";
//    private static final String UPDATED_NOME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
//    private static final String UPDATED_COGNOME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CODICE_FISCALE = "AAAAAAAAAA";
//    private static final String UPDATED_CODICE_FISCALE = "BBBBBBBBBB";
//
//    private static final BooleanStatus DEFAULT_ALERT_COMPLEANNO = BooleanStatus.SI;
//    private static final BooleanStatus UPDATED_ALERT_COMPLEANNO = BooleanStatus.NO;
//
//    private static final LocalDate DEFAULT_DATA_NASCITA = LocalDate.ofEpochDay(0L);
//    private static final LocalDate UPDATED_DATA_NASCITA = LocalDate.now(ZoneId.systemDefault());
//
//    private static final TipoMese DEFAULT_MESE_NASCITA = TipoMese.GENNAIO;
//    private static final TipoMese UPDATED_MESE_NASCITA = TipoMese.FEBBRAIO;
//
//    private static final String DEFAULT_TELEFONO_FISSO = "AAAAAAAAAA";
//    private static final String UPDATED_TELEFONO_FISSO = "BBBBBBBBBB";
//
//    private static final String DEFAULT_TELEFONO_CELLULARE = "AAAAAAAAAA";
//    private static final String UPDATED_TELEFONO_CELLULARE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_EMAIL = "S_@O.j";
//    private static final String UPDATED_EMAIL = "u@;S.GT";
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
//    private static final String DEFAULT_CODICE_ANTIRICICLAGGIO = "AAAAAAAAAA";
//    private static final String UPDATED_CODICE_ANTIRICICLAGGIO = "BBBBBBBBBB";
//
//    private static final Double DEFAULT_IMPORTO_PROVVIGIONI = 1D;
//    private static final Double UPDATED_IMPORTO_PROVVIGIONI = 2D;
//
//    private static final Double DEFAULT_NUMERO_PRATICHE = 1D;
//    private static final Double UPDATED_NUMERO_PRATICHE = 2D;
//
//    private static final Double DEFAULT_NUMERO_SEGNALAZIONI = 1D;
//    private static final Double UPDATED_NUMERO_SEGNALAZIONI = 2D;
//
//    private static final Double DEFAULT_PUNTEGGIO = 1D;
//    private static final Double UPDATED_PUNTEGGIO = 2D;
//
//    private static final Boolean DEFAULT_ABILITATO = false;
//    private static final Boolean UPDATED_ABILITATO = true;
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Mock
//    private ClienteRepository clienteRepositoryMock;
//
//    @Autowired
//    private ClienteMapper clienteMapper;
//
//    @Mock
//    private ClienteService clienteServiceMock;
//
//    @Autowired
//    private ClienteService clienteService;
//
//    @Autowired
//    private ClienteQueryService clienteQueryService;
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
//    private MockMvc restClienteMockMvc;
//
//    private Cliente cliente;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final ClienteResource clienteResource = new ClienteResource(clienteService, clienteQueryService);
//        this.restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteResource)
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
//    public static Cliente createEntity(EntityManager em) {
//        Cliente cliente = new Cliente()
//            .nome(DEFAULT_NOME)
//            .cognome(DEFAULT_COGNOME)
//            .codiceFiscale(DEFAULT_CODICE_FISCALE)
//            .alertCompleanno(DEFAULT_ALERT_COMPLEANNO)
//            .dataNascita(DEFAULT_DATA_NASCITA)
//            .meseNascita(DEFAULT_MESE_NASCITA)
//            .telefonoFisso(DEFAULT_TELEFONO_FISSO)
//            .telefonoCellulare(DEFAULT_TELEFONO_CELLULARE)
//            .email(DEFAULT_EMAIL)
//            .indirizzo(DEFAULT_INDIRIZZO)
//            .cap(DEFAULT_CAP)
//            .regione(DEFAULT_REGIONE)
//            .provincia(DEFAULT_PROVINCIA)
//            .citta(DEFAULT_CITTA)
//            .note(DEFAULT_NOTE)
//            .codiceAntiriciclaggio(DEFAULT_CODICE_ANTIRICICLAGGIO)
//            .importoProvvigioni(DEFAULT_IMPORTO_PROVVIGIONI)
//            .numeroPratiche(DEFAULT_NUMERO_PRATICHE)
//            .numeroSegnalazioni(DEFAULT_NUMERO_SEGNALAZIONI)
//            .punteggio(DEFAULT_PUNTEGGIO)
//            .abilitato(DEFAULT_ABILITATO);
//        return cliente;
//    }
//
//    @Before
//    public void initTest() {
//        cliente = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createCliente() throws Exception {
//        int databaseSizeBeforeCreate = clienteRepository.findAll().size();
//
//        // Create the Cliente
//        ClienteDTO clienteDTO = clienteMapper.toDto(cliente);
//        restClienteMockMvc.perform(post("/api/clientes")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Cliente in the database
//        List<Cliente> clienteList = clienteRepository.findAll();
//        assertThat(clienteList).hasSize(databaseSizeBeforeCreate + 1);
//        Cliente testCliente = clienteList.get(clienteList.size() - 1);
//        assertThat(testCliente.getNome()).isEqualTo(DEFAULT_NOME);
//        assertThat(testCliente.getCognome()).isEqualTo(DEFAULT_COGNOME);
//        assertThat(testCliente.getCodiceFiscale()).isEqualTo(DEFAULT_CODICE_FISCALE);
//        assertThat(testCliente.getAlertCompleanno()).isEqualTo(DEFAULT_ALERT_COMPLEANNO);
//        assertThat(testCliente.getDataNascita()).isEqualTo(DEFAULT_DATA_NASCITA);
//        assertThat(testCliente.getMeseNascita()).isEqualTo(DEFAULT_MESE_NASCITA);
//        assertThat(testCliente.getTelefonoFisso()).isEqualTo(DEFAULT_TELEFONO_FISSO);
//        assertThat(testCliente.getTelefonoCellulare()).isEqualTo(DEFAULT_TELEFONO_CELLULARE);
//        assertThat(testCliente.getEmail()).isEqualTo(DEFAULT_EMAIL);
//        assertThat(testCliente.getIndirizzo()).isEqualTo(DEFAULT_INDIRIZZO);
//        assertThat(testCliente.getCap()).isEqualTo(DEFAULT_CAP);
//        assertThat(testCliente.getRegione()).isEqualTo(DEFAULT_REGIONE);
//        assertThat(testCliente.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
//        assertThat(testCliente.getCitta()).isEqualTo(DEFAULT_CITTA);
//        assertThat(testCliente.getNote()).isEqualTo(DEFAULT_NOTE);
//        assertThat(testCliente.getCodiceAntiriciclaggio()).isEqualTo(DEFAULT_CODICE_ANTIRICICLAGGIO);
//        assertThat(testCliente.getImportoProvvigioni()).isEqualTo(DEFAULT_IMPORTO_PROVVIGIONI);
//        assertThat(testCliente.getNumeroPratiche()).isEqualTo(DEFAULT_NUMERO_PRATICHE);
//        assertThat(testCliente.getNumeroSegnalazioni()).isEqualTo(DEFAULT_NUMERO_SEGNALAZIONI);
//        assertThat(testCliente.getPunteggio()).isEqualTo(DEFAULT_PUNTEGGIO);
//        assertThat(testCliente.isAbilitato()).isEqualTo(DEFAULT_ABILITATO);
//    }
//
//    @Test
//    @Transactional
//    public void createClienteWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = clienteRepository.findAll().size();
//
//        // Create the Cliente with an existing ID
//        cliente.setId(1L);
//        ClienteDTO clienteDTO = clienteMapper.toDto(cliente);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restClienteMockMvc.perform(post("/api/clientes")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Cliente in the database
//        List<Cliente> clienteList = clienteRepository.findAll();
//        assertThat(clienteList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkNomeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = clienteRepository.findAll().size();
//        // set the field null
//        cliente.setNome(null);
//
//        // Create the Cliente, which fails.
//        ClienteDTO clienteDTO = clienteMapper.toDto(cliente);
//
//        restClienteMockMvc.perform(post("/api/clientes")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Cliente> clienteList = clienteRepository.findAll();
//        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkCognomeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = clienteRepository.findAll().size();
//        // set the field null
//        cliente.setCognome(null);
//
//        // Create the Cliente, which fails.
//        ClienteDTO clienteDTO = clienteMapper.toDto(cliente);
//
//        restClienteMockMvc.perform(post("/api/clientes")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Cliente> clienteList = clienteRepository.findAll();
//        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkDataNascitaIsRequired() throws Exception {
//        int databaseSizeBeforeTest = clienteRepository.findAll().size();
//        // set the field null
//        cliente.setDataNascita(null);
//
//        // Create the Cliente, which fails.
//        ClienteDTO clienteDTO = clienteMapper.toDto(cliente);
//
//        restClienteMockMvc.perform(post("/api/clientes")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Cliente> clienteList = clienteRepository.findAll();
//        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientes() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList
//        restClienteMockMvc.perform(get("/api/clientes?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(cliente.getId().intValue())))
//            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
//            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME.toString())))
//            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE.toString())))
//            .andExpect(jsonPath("$.[*].alertCompleanno").value(hasItem(DEFAULT_ALERT_COMPLEANNO.toString())))
//            .andExpect(jsonPath("$.[*].dataNascita").value(hasItem(DEFAULT_DATA_NASCITA.toString())))
//            .andExpect(jsonPath("$.[*].meseNascita").value(hasItem(DEFAULT_MESE_NASCITA.toString())))
//            .andExpect(jsonPath("$.[*].telefonoFisso").value(hasItem(DEFAULT_TELEFONO_FISSO.toString())))
//            .andExpect(jsonPath("$.[*].telefonoCellulare").value(hasItem(DEFAULT_TELEFONO_CELLULARE.toString())))
//            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
//            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO.toString())))
//            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP.toString())))
//            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE.toString())))
//            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA.toString())))
//            .andExpect(jsonPath("$.[*].citta").value(hasItem(DEFAULT_CITTA.toString())))
//            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
//            .andExpect(jsonPath("$.[*].codiceAntiriciclaggio").value(hasItem(DEFAULT_CODICE_ANTIRICICLAGGIO.toString())))
//            .andExpect(jsonPath("$.[*].importoProvvigioni").value(hasItem(DEFAULT_IMPORTO_PROVVIGIONI.doubleValue())))
//            .andExpect(jsonPath("$.[*].numeroPratiche").value(hasItem(DEFAULT_NUMERO_PRATICHE.doubleValue())))
//            .andExpect(jsonPath("$.[*].numeroSegnalazioni").value(hasItem(DEFAULT_NUMERO_SEGNALAZIONI.doubleValue())))
//            .andExpect(jsonPath("$.[*].punteggio").value(hasItem(DEFAULT_PUNTEGGIO.doubleValue())))
//            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));
//    }
//
//    @SuppressWarnings({"unchecked"})
//    public void getAllClientesWithEagerRelationshipsIsEnabled() throws Exception {
//        ClienteResource clienteResource = new ClienteResource(clienteServiceMock, clienteQueryService);
//        when(clienteServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
//
//        MockMvc restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
////            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//
//        restClienteMockMvc.perform(get("/api/clientes?eagerload=true"))
//        .andExpect(status().isOk());
//
//        verify(clienteServiceMock, times(1)).findAllWithEagerRelationships(any());
//    }
//
//    @SuppressWarnings({"unchecked"})
//    public void getAllClientesWithEagerRelationshipsIsNotEnabled() throws Exception {
//        ClienteResource clienteResource = new ClienteResource(clienteServiceMock, clienteQueryService);
//            when(clienteServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
//            MockMvc restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
////            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//
//        restClienteMockMvc.perform(get("/api/clientes?eagerload=true"))
//        .andExpect(status().isOk());
//
//            verify(clienteServiceMock, times(1)).findAllWithEagerRelationships(any());
//    }
//
//    @Test
//    @Transactional
//    public void getCliente() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get the cliente
//        restClienteMockMvc.perform(get("/api/clientes/{id}", cliente.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(cliente.getId().intValue()))
//            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
//            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME.toString()))
//            .andExpect(jsonPath("$.codiceFiscale").value(DEFAULT_CODICE_FISCALE.toString()))
//            .andExpect(jsonPath("$.alertCompleanno").value(DEFAULT_ALERT_COMPLEANNO.toString()))
//            .andExpect(jsonPath("$.dataNascita").value(DEFAULT_DATA_NASCITA.toString()))
//            .andExpect(jsonPath("$.meseNascita").value(DEFAULT_MESE_NASCITA.toString()))
//            .andExpect(jsonPath("$.telefonoFisso").value(DEFAULT_TELEFONO_FISSO.toString()))
//            .andExpect(jsonPath("$.telefonoCellulare").value(DEFAULT_TELEFONO_CELLULARE.toString()))
//            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
//            .andExpect(jsonPath("$.indirizzo").value(DEFAULT_INDIRIZZO.toString()))
//            .andExpect(jsonPath("$.cap").value(DEFAULT_CAP.toString()))
//            .andExpect(jsonPath("$.regione").value(DEFAULT_REGIONE.toString()))
//            .andExpect(jsonPath("$.provincia").value(DEFAULT_PROVINCIA.toString()))
//            .andExpect(jsonPath("$.citta").value(DEFAULT_CITTA.toString()))
//            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
//            .andExpect(jsonPath("$.codiceAntiriciclaggio").value(DEFAULT_CODICE_ANTIRICICLAGGIO.toString()))
//            .andExpect(jsonPath("$.importoProvvigioni").value(DEFAULT_IMPORTO_PROVVIGIONI.doubleValue()))
//            .andExpect(jsonPath("$.numeroPratiche").value(DEFAULT_NUMERO_PRATICHE.doubleValue()))
//            .andExpect(jsonPath("$.numeroSegnalazioni").value(DEFAULT_NUMERO_SEGNALAZIONI.doubleValue()))
//            .andExpect(jsonPath("$.punteggio").value(DEFAULT_PUNTEGGIO.doubleValue()))
//            .andExpect(jsonPath("$.abilitato").value(DEFAULT_ABILITATO.booleanValue()));
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNomeIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where nome equals to DEFAULT_NOME
//        defaultClienteShouldBeFound("nome.equals=" + DEFAULT_NOME);
//
//        // Get all the clienteList where nome equals to UPDATED_NOME
//        defaultClienteShouldNotBeFound("nome.equals=" + UPDATED_NOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNomeIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where nome in DEFAULT_NOME or UPDATED_NOME
//        defaultClienteShouldBeFound("nome.in=" + DEFAULT_NOME + "," + UPDATED_NOME);
//
//        // Get all the clienteList where nome equals to UPDATED_NOME
//        defaultClienteShouldNotBeFound("nome.in=" + UPDATED_NOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNomeIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where nome is not null
//        defaultClienteShouldBeFound("nome.specified=true");
//
//        // Get all the clienteList where nome is null
//        defaultClienteShouldNotBeFound("nome.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCognomeIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where cognome equals to DEFAULT_COGNOME
//        defaultClienteShouldBeFound("cognome.equals=" + DEFAULT_COGNOME);
//
//        // Get all the clienteList where cognome equals to UPDATED_COGNOME
//        defaultClienteShouldNotBeFound("cognome.equals=" + UPDATED_COGNOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCognomeIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where cognome in DEFAULT_COGNOME or UPDATED_COGNOME
//        defaultClienteShouldBeFound("cognome.in=" + DEFAULT_COGNOME + "," + UPDATED_COGNOME);
//
//        // Get all the clienteList where cognome equals to UPDATED_COGNOME
//        defaultClienteShouldNotBeFound("cognome.in=" + UPDATED_COGNOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCognomeIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where cognome is not null
//        defaultClienteShouldBeFound("cognome.specified=true");
//
//        // Get all the clienteList where cognome is null
//        defaultClienteShouldNotBeFound("cognome.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCodiceFiscaleIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where codiceFiscale equals to DEFAULT_CODICE_FISCALE
//        defaultClienteShouldBeFound("codiceFiscale.equals=" + DEFAULT_CODICE_FISCALE);
//
//        // Get all the clienteList where codiceFiscale equals to UPDATED_CODICE_FISCALE
//        defaultClienteShouldNotBeFound("codiceFiscale.equals=" + UPDATED_CODICE_FISCALE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCodiceFiscaleIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where codiceFiscale in DEFAULT_CODICE_FISCALE or UPDATED_CODICE_FISCALE
//        defaultClienteShouldBeFound("codiceFiscale.in=" + DEFAULT_CODICE_FISCALE + "," + UPDATED_CODICE_FISCALE);
//
//        // Get all the clienteList where codiceFiscale equals to UPDATED_CODICE_FISCALE
//        defaultClienteShouldNotBeFound("codiceFiscale.in=" + UPDATED_CODICE_FISCALE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCodiceFiscaleIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where codiceFiscale is not null
//        defaultClienteShouldBeFound("codiceFiscale.specified=true");
//
//        // Get all the clienteList where codiceFiscale is null
//        defaultClienteShouldNotBeFound("codiceFiscale.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByAlertCompleannoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where alertCompleanno equals to DEFAULT_ALERT_COMPLEANNO
//        defaultClienteShouldBeFound("alertCompleanno.equals=" + DEFAULT_ALERT_COMPLEANNO);
//
//        // Get all the clienteList where alertCompleanno equals to UPDATED_ALERT_COMPLEANNO
//        defaultClienteShouldNotBeFound("alertCompleanno.equals=" + UPDATED_ALERT_COMPLEANNO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByAlertCompleannoIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where alertCompleanno in DEFAULT_ALERT_COMPLEANNO or UPDATED_ALERT_COMPLEANNO
//        defaultClienteShouldBeFound("alertCompleanno.in=" + DEFAULT_ALERT_COMPLEANNO + "," + UPDATED_ALERT_COMPLEANNO);
//
//        // Get all the clienteList where alertCompleanno equals to UPDATED_ALERT_COMPLEANNO
//        defaultClienteShouldNotBeFound("alertCompleanno.in=" + UPDATED_ALERT_COMPLEANNO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByAlertCompleannoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where alertCompleanno is not null
//        defaultClienteShouldBeFound("alertCompleanno.specified=true");
//
//        // Get all the clienteList where alertCompleanno is null
//        defaultClienteShouldNotBeFound("alertCompleanno.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByDataNascitaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where dataNascita equals to DEFAULT_DATA_NASCITA
//        defaultClienteShouldBeFound("dataNascita.equals=" + DEFAULT_DATA_NASCITA);
//
//        // Get all the clienteList where dataNascita equals to UPDATED_DATA_NASCITA
//        defaultClienteShouldNotBeFound("dataNascita.equals=" + UPDATED_DATA_NASCITA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByDataNascitaIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where dataNascita in DEFAULT_DATA_NASCITA or UPDATED_DATA_NASCITA
//        defaultClienteShouldBeFound("dataNascita.in=" + DEFAULT_DATA_NASCITA + "," + UPDATED_DATA_NASCITA);
//
//        // Get all the clienteList where dataNascita equals to UPDATED_DATA_NASCITA
//        defaultClienteShouldNotBeFound("dataNascita.in=" + UPDATED_DATA_NASCITA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByDataNascitaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where dataNascita is not null
//        defaultClienteShouldBeFound("dataNascita.specified=true");
//
//        // Get all the clienteList where dataNascita is null
//        defaultClienteShouldNotBeFound("dataNascita.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByDataNascitaIsGreaterThanOrEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where dataNascita greater than or equals to DEFAULT_DATA_NASCITA
//        defaultClienteShouldBeFound("dataNascita.greaterOrEqualThan=" + DEFAULT_DATA_NASCITA);
//
//        // Get all the clienteList where dataNascita greater than or equals to UPDATED_DATA_NASCITA
//        defaultClienteShouldNotBeFound("dataNascita.greaterOrEqualThan=" + UPDATED_DATA_NASCITA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByDataNascitaIsLessThanSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where dataNascita less than or equals to DEFAULT_DATA_NASCITA
//        defaultClienteShouldNotBeFound("dataNascita.lessThan=" + DEFAULT_DATA_NASCITA);
//
//        // Get all the clienteList where dataNascita less than or equals to UPDATED_DATA_NASCITA
//        defaultClienteShouldBeFound("dataNascita.lessThan=" + UPDATED_DATA_NASCITA);
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllClientesByMeseNascitaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where meseNascita equals to DEFAULT_MESE_NASCITA
//        defaultClienteShouldBeFound("meseNascita.equals=" + DEFAULT_MESE_NASCITA);
//
//        // Get all the clienteList where meseNascita equals to UPDATED_MESE_NASCITA
//        defaultClienteShouldNotBeFound("meseNascita.equals=" + UPDATED_MESE_NASCITA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByMeseNascitaIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where meseNascita in DEFAULT_MESE_NASCITA or UPDATED_MESE_NASCITA
//        defaultClienteShouldBeFound("meseNascita.in=" + DEFAULT_MESE_NASCITA + "," + UPDATED_MESE_NASCITA);
//
//        // Get all the clienteList where meseNascita equals to UPDATED_MESE_NASCITA
//        defaultClienteShouldNotBeFound("meseNascita.in=" + UPDATED_MESE_NASCITA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByMeseNascitaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where meseNascita is not null
//        defaultClienteShouldBeFound("meseNascita.specified=true");
//
//        // Get all the clienteList where meseNascita is null
//        defaultClienteShouldNotBeFound("meseNascita.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByTelefonoFissoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where telefonoFisso equals to DEFAULT_TELEFONO_FISSO
//        defaultClienteShouldBeFound("telefonoFisso.equals=" + DEFAULT_TELEFONO_FISSO);
//
//        // Get all the clienteList where telefonoFisso equals to UPDATED_TELEFONO_FISSO
//        defaultClienteShouldNotBeFound("telefonoFisso.equals=" + UPDATED_TELEFONO_FISSO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByTelefonoFissoIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where telefonoFisso in DEFAULT_TELEFONO_FISSO or UPDATED_TELEFONO_FISSO
//        defaultClienteShouldBeFound("telefonoFisso.in=" + DEFAULT_TELEFONO_FISSO + "," + UPDATED_TELEFONO_FISSO);
//
//        // Get all the clienteList where telefonoFisso equals to UPDATED_TELEFONO_FISSO
//        defaultClienteShouldNotBeFound("telefonoFisso.in=" + UPDATED_TELEFONO_FISSO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByTelefonoFissoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where telefonoFisso is not null
//        defaultClienteShouldBeFound("telefonoFisso.specified=true");
//
//        // Get all the clienteList where telefonoFisso is null
//        defaultClienteShouldNotBeFound("telefonoFisso.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByTelefonoCellulareIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where telefonoCellulare equals to DEFAULT_TELEFONO_CELLULARE
//        defaultClienteShouldBeFound("telefonoCellulare.equals=" + DEFAULT_TELEFONO_CELLULARE);
//
//        // Get all the clienteList where telefonoCellulare equals to UPDATED_TELEFONO_CELLULARE
//        defaultClienteShouldNotBeFound("telefonoCellulare.equals=" + UPDATED_TELEFONO_CELLULARE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByTelefonoCellulareIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where telefonoCellulare in DEFAULT_TELEFONO_CELLULARE or UPDATED_TELEFONO_CELLULARE
//        defaultClienteShouldBeFound("telefonoCellulare.in=" + DEFAULT_TELEFONO_CELLULARE + "," + UPDATED_TELEFONO_CELLULARE);
//
//        // Get all the clienteList where telefonoCellulare equals to UPDATED_TELEFONO_CELLULARE
//        defaultClienteShouldNotBeFound("telefonoCellulare.in=" + UPDATED_TELEFONO_CELLULARE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByTelefonoCellulareIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where telefonoCellulare is not null
//        defaultClienteShouldBeFound("telefonoCellulare.specified=true");
//
//        // Get all the clienteList where telefonoCellulare is null
//        defaultClienteShouldNotBeFound("telefonoCellulare.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByEmailIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where email equals to DEFAULT_EMAIL
//        defaultClienteShouldBeFound("email.equals=" + DEFAULT_EMAIL);
//
//        // Get all the clienteList where email equals to UPDATED_EMAIL
//        defaultClienteShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByEmailIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where email in DEFAULT_EMAIL or UPDATED_EMAIL
//        defaultClienteShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);
//
//        // Get all the clienteList where email equals to UPDATED_EMAIL
//        defaultClienteShouldNotBeFound("email.in=" + UPDATED_EMAIL);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByEmailIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where email is not null
//        defaultClienteShouldBeFound("email.specified=true");
//
//        // Get all the clienteList where email is null
//        defaultClienteShouldNotBeFound("email.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByIndirizzoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where indirizzo equals to DEFAULT_INDIRIZZO
//        defaultClienteShouldBeFound("indirizzo.equals=" + DEFAULT_INDIRIZZO);
//
//        // Get all the clienteList where indirizzo equals to UPDATED_INDIRIZZO
//        defaultClienteShouldNotBeFound("indirizzo.equals=" + UPDATED_INDIRIZZO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByIndirizzoIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where indirizzo in DEFAULT_INDIRIZZO or UPDATED_INDIRIZZO
//        defaultClienteShouldBeFound("indirizzo.in=" + DEFAULT_INDIRIZZO + "," + UPDATED_INDIRIZZO);
//
//        // Get all the clienteList where indirizzo equals to UPDATED_INDIRIZZO
//        defaultClienteShouldNotBeFound("indirizzo.in=" + UPDATED_INDIRIZZO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByIndirizzoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where indirizzo is not null
//        defaultClienteShouldBeFound("indirizzo.specified=true");
//
//        // Get all the clienteList where indirizzo is null
//        defaultClienteShouldNotBeFound("indirizzo.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCapIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where cap equals to DEFAULT_CAP
//        defaultClienteShouldBeFound("cap.equals=" + DEFAULT_CAP);
//
//        // Get all the clienteList where cap equals to UPDATED_CAP
//        defaultClienteShouldNotBeFound("cap.equals=" + UPDATED_CAP);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCapIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where cap in DEFAULT_CAP or UPDATED_CAP
//        defaultClienteShouldBeFound("cap.in=" + DEFAULT_CAP + "," + UPDATED_CAP);
//
//        // Get all the clienteList where cap equals to UPDATED_CAP
//        defaultClienteShouldNotBeFound("cap.in=" + UPDATED_CAP);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCapIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where cap is not null
//        defaultClienteShouldBeFound("cap.specified=true");
//
//        // Get all the clienteList where cap is null
//        defaultClienteShouldNotBeFound("cap.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByRegioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where regione equals to DEFAULT_REGIONE
//        defaultClienteShouldBeFound("regione.equals=" + DEFAULT_REGIONE);
//
//        // Get all the clienteList where regione equals to UPDATED_REGIONE
//        defaultClienteShouldNotBeFound("regione.equals=" + UPDATED_REGIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByRegioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where regione in DEFAULT_REGIONE or UPDATED_REGIONE
//        defaultClienteShouldBeFound("regione.in=" + DEFAULT_REGIONE + "," + UPDATED_REGIONE);
//
//        // Get all the clienteList where regione equals to UPDATED_REGIONE
//        defaultClienteShouldNotBeFound("regione.in=" + UPDATED_REGIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByRegioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where regione is not null
//        defaultClienteShouldBeFound("regione.specified=true");
//
//        // Get all the clienteList where regione is null
//        defaultClienteShouldNotBeFound("regione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByProvinciaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where provincia equals to DEFAULT_PROVINCIA
//        defaultClienteShouldBeFound("provincia.equals=" + DEFAULT_PROVINCIA);
//
//        // Get all the clienteList where provincia equals to UPDATED_PROVINCIA
//        defaultClienteShouldNotBeFound("provincia.equals=" + UPDATED_PROVINCIA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByProvinciaIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where provincia in DEFAULT_PROVINCIA or UPDATED_PROVINCIA
//        defaultClienteShouldBeFound("provincia.in=" + DEFAULT_PROVINCIA + "," + UPDATED_PROVINCIA);
//
//        // Get all the clienteList where provincia equals to UPDATED_PROVINCIA
//        defaultClienteShouldNotBeFound("provincia.in=" + UPDATED_PROVINCIA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByProvinciaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where provincia is not null
//        defaultClienteShouldBeFound("provincia.specified=true");
//
//        // Get all the clienteList where provincia is null
//        defaultClienteShouldNotBeFound("provincia.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCittaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where citta equals to DEFAULT_CITTA
//        defaultClienteShouldBeFound("citta.equals=" + DEFAULT_CITTA);
//
//        // Get all the clienteList where citta equals to UPDATED_CITTA
//        defaultClienteShouldNotBeFound("citta.equals=" + UPDATED_CITTA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCittaIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where citta in DEFAULT_CITTA or UPDATED_CITTA
//        defaultClienteShouldBeFound("citta.in=" + DEFAULT_CITTA + "," + UPDATED_CITTA);
//
//        // Get all the clienteList where citta equals to UPDATED_CITTA
//        defaultClienteShouldNotBeFound("citta.in=" + UPDATED_CITTA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCittaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where citta is not null
//        defaultClienteShouldBeFound("citta.specified=true");
//
//        // Get all the clienteList where citta is null
//        defaultClienteShouldNotBeFound("citta.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCodiceAntiriciclaggioIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where codiceAntiriciclaggio equals to DEFAULT_CODICE_ANTIRICICLAGGIO
//        defaultClienteShouldBeFound("codiceAntiriciclaggio.equals=" + DEFAULT_CODICE_ANTIRICICLAGGIO);
//
//        // Get all the clienteList where codiceAntiriciclaggio equals to UPDATED_CODICE_ANTIRICICLAGGIO
//        defaultClienteShouldNotBeFound("codiceAntiriciclaggio.equals=" + UPDATED_CODICE_ANTIRICICLAGGIO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCodiceAntiriciclaggioIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where codiceAntiriciclaggio in DEFAULT_CODICE_ANTIRICICLAGGIO or UPDATED_CODICE_ANTIRICICLAGGIO
//        defaultClienteShouldBeFound("codiceAntiriciclaggio.in=" + DEFAULT_CODICE_ANTIRICICLAGGIO + "," + UPDATED_CODICE_ANTIRICICLAGGIO);
//
//        // Get all the clienteList where codiceAntiriciclaggio equals to UPDATED_CODICE_ANTIRICICLAGGIO
//        defaultClienteShouldNotBeFound("codiceAntiriciclaggio.in=" + UPDATED_CODICE_ANTIRICICLAGGIO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByCodiceAntiriciclaggioIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where codiceAntiriciclaggio is not null
//        defaultClienteShouldBeFound("codiceAntiriciclaggio.specified=true");
//
//        // Get all the clienteList where codiceAntiriciclaggio is null
//        defaultClienteShouldNotBeFound("codiceAntiriciclaggio.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByImportoProvvigioniIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where importoProvvigioni equals to DEFAULT_IMPORTO_PROVVIGIONI
//        defaultClienteShouldBeFound("importoProvvigioni.equals=" + DEFAULT_IMPORTO_PROVVIGIONI);
//
//        // Get all the clienteList where importoProvvigioni equals to UPDATED_IMPORTO_PROVVIGIONI
//        defaultClienteShouldNotBeFound("importoProvvigioni.equals=" + UPDATED_IMPORTO_PROVVIGIONI);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByImportoProvvigioniIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where importoProvvigioni in DEFAULT_IMPORTO_PROVVIGIONI or UPDATED_IMPORTO_PROVVIGIONI
//        defaultClienteShouldBeFound("importoProvvigioni.in=" + DEFAULT_IMPORTO_PROVVIGIONI + "," + UPDATED_IMPORTO_PROVVIGIONI);
//
//        // Get all the clienteList where importoProvvigioni equals to UPDATED_IMPORTO_PROVVIGIONI
//        defaultClienteShouldNotBeFound("importoProvvigioni.in=" + UPDATED_IMPORTO_PROVVIGIONI);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByImportoProvvigioniIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where importoProvvigioni is not null
//        defaultClienteShouldBeFound("importoProvvigioni.specified=true");
//
//        // Get all the clienteList where importoProvvigioni is null
//        defaultClienteShouldNotBeFound("importoProvvigioni.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNumeroPraticheIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where numeroPratiche equals to DEFAULT_NUMERO_PRATICHE
//        defaultClienteShouldBeFound("numeroPratiche.equals=" + DEFAULT_NUMERO_PRATICHE);
//
//        // Get all the clienteList where numeroPratiche equals to UPDATED_NUMERO_PRATICHE
//        defaultClienteShouldNotBeFound("numeroPratiche.equals=" + UPDATED_NUMERO_PRATICHE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNumeroPraticheIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where numeroPratiche in DEFAULT_NUMERO_PRATICHE or UPDATED_NUMERO_PRATICHE
//        defaultClienteShouldBeFound("numeroPratiche.in=" + DEFAULT_NUMERO_PRATICHE + "," + UPDATED_NUMERO_PRATICHE);
//
//        // Get all the clienteList where numeroPratiche equals to UPDATED_NUMERO_PRATICHE
//        defaultClienteShouldNotBeFound("numeroPratiche.in=" + UPDATED_NUMERO_PRATICHE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNumeroPraticheIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where numeroPratiche is not null
//        defaultClienteShouldBeFound("numeroPratiche.specified=true");
//
//        // Get all the clienteList where numeroPratiche is null
//        defaultClienteShouldNotBeFound("numeroPratiche.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNumeroSegnalazioniIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where numeroSegnalazioni equals to DEFAULT_NUMERO_SEGNALAZIONI
//        defaultClienteShouldBeFound("numeroSegnalazioni.equals=" + DEFAULT_NUMERO_SEGNALAZIONI);
//
//        // Get all the clienteList where numeroSegnalazioni equals to UPDATED_NUMERO_SEGNALAZIONI
//        defaultClienteShouldNotBeFound("numeroSegnalazioni.equals=" + UPDATED_NUMERO_SEGNALAZIONI);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNumeroSegnalazioniIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where numeroSegnalazioni in DEFAULT_NUMERO_SEGNALAZIONI or UPDATED_NUMERO_SEGNALAZIONI
//        defaultClienteShouldBeFound("numeroSegnalazioni.in=" + DEFAULT_NUMERO_SEGNALAZIONI + "," + UPDATED_NUMERO_SEGNALAZIONI);
//
//        // Get all the clienteList where numeroSegnalazioni equals to UPDATED_NUMERO_SEGNALAZIONI
//        defaultClienteShouldNotBeFound("numeroSegnalazioni.in=" + UPDATED_NUMERO_SEGNALAZIONI);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByNumeroSegnalazioniIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where numeroSegnalazioni is not null
//        defaultClienteShouldBeFound("numeroSegnalazioni.specified=true");
//
//        // Get all the clienteList where numeroSegnalazioni is null
//        defaultClienteShouldNotBeFound("numeroSegnalazioni.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByPunteggioIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where punteggio equals to DEFAULT_PUNTEGGIO
//        defaultClienteShouldBeFound("punteggio.equals=" + DEFAULT_PUNTEGGIO);
//
//        // Get all the clienteList where punteggio equals to UPDATED_PUNTEGGIO
//        defaultClienteShouldNotBeFound("punteggio.equals=" + UPDATED_PUNTEGGIO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByPunteggioIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where punteggio in DEFAULT_PUNTEGGIO or UPDATED_PUNTEGGIO
//        defaultClienteShouldBeFound("punteggio.in=" + DEFAULT_PUNTEGGIO + "," + UPDATED_PUNTEGGIO);
//
//        // Get all the clienteList where punteggio equals to UPDATED_PUNTEGGIO
//        defaultClienteShouldNotBeFound("punteggio.in=" + UPDATED_PUNTEGGIO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByPunteggioIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where punteggio is not null
//        defaultClienteShouldBeFound("punteggio.specified=true");
//
//        // Get all the clienteList where punteggio is null
//        defaultClienteShouldNotBeFound("punteggio.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByAbilitatoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where abilitato equals to DEFAULT_ABILITATO
//        defaultClienteShouldBeFound("abilitato.equals=" + DEFAULT_ABILITATO);
//
//        // Get all the clienteList where abilitato equals to UPDATED_ABILITATO
//        defaultClienteShouldNotBeFound("abilitato.equals=" + UPDATED_ABILITATO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByAbilitatoIsInShouldWork() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where abilitato in DEFAULT_ABILITATO or UPDATED_ABILITATO
//        defaultClienteShouldBeFound("abilitato.in=" + DEFAULT_ABILITATO + "," + UPDATED_ABILITATO);
//
//        // Get all the clienteList where abilitato equals to UPDATED_ABILITATO
//        defaultClienteShouldNotBeFound("abilitato.in=" + UPDATED_ABILITATO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByAbilitatoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        // Get all the clienteList where abilitato is not null
//        defaultClienteShouldBeFound("abilitato.specified=true");
//
//        // Get all the clienteList where abilitato is null
//        defaultClienteShouldNotBeFound("abilitato.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllClientesByListaContattiIsEqualToSomething() throws Exception {
//        // Initialize the database
//        ListaContatti listaContatti = ListaContattiResourceIntTest.createEntity(em);
//        em.persist(listaContatti);
//        em.flush();
//        cliente.addListaContatti(listaContatti);
//        clienteRepository.saveAndFlush(cliente);
//        Long listaContattiId = listaContatti.getId();
//
//        // Get all the clienteList where listaContatti equals to listaContattiId
//        defaultClienteShouldBeFound("listaContattiId.equals=" + listaContattiId);
//
//        // Get all the clienteList where listaContatti equals to listaContattiId + 1
//        defaultClienteShouldNotBeFound("listaContattiId.equals=" + (listaContattiId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllClientesByTagIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Tag tag = TagResourceIntTest.createEntity(em);
//        em.persist(tag);
//        em.flush();
//        cliente.addTag(tag);
//        clienteRepository.saveAndFlush(cliente);
//        Long tagId = tag.getId();
//
//        // Get all the clienteList where tag equals to tagId
//        defaultClienteShouldBeFound("tagId.equals=" + tagId);
//
//        // Get all the clienteList where tag equals to tagId + 1
//        defaultClienteShouldNotBeFound("tagId.equals=" + (tagId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllClientesByIncaricoCommittenteIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Incarico incaricoCommittente = IncaricoResourceIntTest.createEntity(em);
//        em.persist(incaricoCommittente);
//        em.flush();
//        cliente.addIncaricoCommittente(incaricoCommittente);
//        clienteRepository.saveAndFlush(cliente);
//        Long incaricoCommittenteId = incaricoCommittente.getId();
//
//        // Get all the clienteList where incaricoCommittente equals to incaricoCommittenteId
//        defaultClienteShouldBeFound("incaricoCommittenteId.equals=" + incaricoCommittenteId);
//
//        // Get all the clienteList where incaricoCommittente equals to incaricoCommittenteId + 1
//        defaultClienteShouldNotBeFound("incaricoCommittenteId.equals=" + (incaricoCommittenteId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllClientesByIncaricoProponenteIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Incarico incaricoProponente = IncaricoResourceIntTest.createEntity(em);
//        em.persist(incaricoProponente);
//        em.flush();
//        cliente.addIncaricoProponente(incaricoProponente);
//        clienteRepository.saveAndFlush(cliente);
//        Long incaricoProponenteId = incaricoProponente.getId();
//
//        // Get all the clienteList where incaricoProponente equals to incaricoProponenteId
//        defaultClienteShouldBeFound("incaricoProponenteId.equals=" + incaricoProponenteId);
//
//        // Get all the clienteList where incaricoProponente equals to incaricoProponenteId + 1
//        defaultClienteShouldNotBeFound("incaricoProponenteId.equals=" + (incaricoProponenteId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllClientesByIncaricoAcquirenteLocatarioIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Incarico incaricoAcquirenteLocatario = IncaricoResourceIntTest.createEntity(em);
//        em.persist(incaricoAcquirenteLocatario);
//        em.flush();
//        cliente.addIncaricoAcquirenteLocatario(incaricoAcquirenteLocatario);
//        clienteRepository.saveAndFlush(cliente);
//        Long incaricoAcquirenteLocatarioId = incaricoAcquirenteLocatario.getId();
//
//        // Get all the clienteList where incaricoAcquirenteLocatario equals to incaricoAcquirenteLocatarioId
//        defaultClienteShouldBeFound("incaricoAcquirenteLocatarioId.equals=" + incaricoAcquirenteLocatarioId);
//
//        // Get all the clienteList where incaricoAcquirenteLocatario equals to incaricoAcquirenteLocatarioId + 1
//        defaultClienteShouldNotBeFound("incaricoAcquirenteLocatarioId.equals=" + (incaricoAcquirenteLocatarioId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllClientesByIncaricoSegnalatoreIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Incarico incaricoSegnalatore = IncaricoResourceIntTest.createEntity(em);
//        em.persist(incaricoSegnalatore);
//        em.flush();
//        cliente.addIncaricoSegnalatore(incaricoSegnalatore);
//        clienteRepository.saveAndFlush(cliente);
//        Long incaricoSegnalatoreId = incaricoSegnalatore.getId();
//
//        // Get all the clienteList where incaricoSegnalatore equals to incaricoSegnalatoreId
//        defaultClienteShouldBeFound("incaricoSegnalatoreId.equals=" + incaricoSegnalatoreId);
//
//        // Get all the clienteList where incaricoSegnalatore equals to incaricoSegnalatoreId + 1
//        defaultClienteShouldNotBeFound("incaricoSegnalatoreId.equals=" + (incaricoSegnalatoreId + 1));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is returned
//     */
//    private void defaultClienteShouldBeFound(String filter) throws Exception {
//        restClienteMockMvc.perform(get("/api/clientes?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(cliente.getId().intValue())))
//            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
//            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME.toString())))
//            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE.toString())))
//            .andExpect(jsonPath("$.[*].alertCompleanno").value(hasItem(DEFAULT_ALERT_COMPLEANNO.toString())))
//            .andExpect(jsonPath("$.[*].dataNascita").value(hasItem(DEFAULT_DATA_NASCITA.toString())))
//            .andExpect(jsonPath("$.[*].meseNascita").value(hasItem(DEFAULT_MESE_NASCITA.toString())))
//            .andExpect(jsonPath("$.[*].telefonoFisso").value(hasItem(DEFAULT_TELEFONO_FISSO.toString())))
//            .andExpect(jsonPath("$.[*].telefonoCellulare").value(hasItem(DEFAULT_TELEFONO_CELLULARE.toString())))
//            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
//            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO.toString())))
//            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP.toString())))
//            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE.toString())))
//            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA.toString())))
//            .andExpect(jsonPath("$.[*].citta").value(hasItem(DEFAULT_CITTA.toString())))
//            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
//            .andExpect(jsonPath("$.[*].codiceAntiriciclaggio").value(hasItem(DEFAULT_CODICE_ANTIRICICLAGGIO.toString())))
//            .andExpect(jsonPath("$.[*].importoProvvigioni").value(hasItem(DEFAULT_IMPORTO_PROVVIGIONI.doubleValue())))
//            .andExpect(jsonPath("$.[*].numeroPratiche").value(hasItem(DEFAULT_NUMERO_PRATICHE.doubleValue())))
//            .andExpect(jsonPath("$.[*].numeroSegnalazioni").value(hasItem(DEFAULT_NUMERO_SEGNALAZIONI.doubleValue())))
//            .andExpect(jsonPath("$.[*].punteggio").value(hasItem(DEFAULT_PUNTEGGIO.doubleValue())))
//            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));
//
//        // Check, that the count call also returns 1
//        restClienteMockMvc.perform(get("/api/clientes/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("1"));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is not returned
//     */
//    private void defaultClienteShouldNotBeFound(String filter) throws Exception {
//        restClienteMockMvc.perform(get("/api/clientes?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$").isArray())
//            .andExpect(jsonPath("$").isEmpty());
//
//        // Check, that the count call also returns 0
//        restClienteMockMvc.perform(get("/api/clientes/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("0"));
//    }
//
//
//    @Test
//    @Transactional
//    public void getNonExistingCliente() throws Exception {
//        // Get the cliente
//        restClienteMockMvc.perform(get("/api/clientes/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateCliente() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        int databaseSizeBeforeUpdate = clienteRepository.findAll().size();
//
//        // Update the cliente
//        Cliente updatedCliente = clienteRepository.findById(cliente.getId()).get();
//        // Disconnect from session so that the updates on updatedCliente are not directly saved in db
//        em.detach(updatedCliente);
//        updatedCliente
//            .nome(UPDATED_NOME)
//            .cognome(UPDATED_COGNOME)
//            .codiceFiscale(UPDATED_CODICE_FISCALE)
//            .alertCompleanno(UPDATED_ALERT_COMPLEANNO)
//            .dataNascita(UPDATED_DATA_NASCITA)
//            .meseNascita(UPDATED_MESE_NASCITA)
//            .telefonoFisso(UPDATED_TELEFONO_FISSO)
//            .telefonoCellulare(UPDATED_TELEFONO_CELLULARE)
//            .email(UPDATED_EMAIL)
//            .indirizzo(UPDATED_INDIRIZZO)
//            .cap(UPDATED_CAP)
//            .regione(UPDATED_REGIONE)
//            .provincia(UPDATED_PROVINCIA)
//            .citta(UPDATED_CITTA)
//            .note(UPDATED_NOTE)
//            .codiceAntiriciclaggio(UPDATED_CODICE_ANTIRICICLAGGIO)
//            .importoProvvigioni(UPDATED_IMPORTO_PROVVIGIONI)
//            .numeroPratiche(UPDATED_NUMERO_PRATICHE)
//            .numeroSegnalazioni(UPDATED_NUMERO_SEGNALAZIONI)
//            .punteggio(UPDATED_PUNTEGGIO)
//            .abilitato(UPDATED_ABILITATO);
//        ClienteDTO clienteDTO = clienteMapper.toDto(updatedCliente);
//
//        restClienteMockMvc.perform(put("/api/clientes")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Cliente in the database
//        List<Cliente> clienteList = clienteRepository.findAll();
//        assertThat(clienteList).hasSize(databaseSizeBeforeUpdate);
//        Cliente testCliente = clienteList.get(clienteList.size() - 1);
//        assertThat(testCliente.getNome()).isEqualTo(UPDATED_NOME);
//        assertThat(testCliente.getCognome()).isEqualTo(UPDATED_COGNOME);
//        assertThat(testCliente.getCodiceFiscale()).isEqualTo(UPDATED_CODICE_FISCALE);
//        assertThat(testCliente.getAlertCompleanno()).isEqualTo(UPDATED_ALERT_COMPLEANNO);
//        assertThat(testCliente.getDataNascita()).isEqualTo(UPDATED_DATA_NASCITA);
//        assertThat(testCliente.getMeseNascita()).isEqualTo(UPDATED_MESE_NASCITA);
//        assertThat(testCliente.getTelefonoFisso()).isEqualTo(UPDATED_TELEFONO_FISSO);
//        assertThat(testCliente.getTelefonoCellulare()).isEqualTo(UPDATED_TELEFONO_CELLULARE);
//        assertThat(testCliente.getEmail()).isEqualTo(UPDATED_EMAIL);
//        assertThat(testCliente.getIndirizzo()).isEqualTo(UPDATED_INDIRIZZO);
//        assertThat(testCliente.getCap()).isEqualTo(UPDATED_CAP);
//        assertThat(testCliente.getRegione()).isEqualTo(UPDATED_REGIONE);
//        assertThat(testCliente.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
//        assertThat(testCliente.getCitta()).isEqualTo(UPDATED_CITTA);
//        assertThat(testCliente.getNote()).isEqualTo(UPDATED_NOTE);
//        assertThat(testCliente.getCodiceAntiriciclaggio()).isEqualTo(UPDATED_CODICE_ANTIRICICLAGGIO);
//        assertThat(testCliente.getImportoProvvigioni()).isEqualTo(UPDATED_IMPORTO_PROVVIGIONI);
//        assertThat(testCliente.getNumeroPratiche()).isEqualTo(UPDATED_NUMERO_PRATICHE);
//        assertThat(testCliente.getNumeroSegnalazioni()).isEqualTo(UPDATED_NUMERO_SEGNALAZIONI);
//        assertThat(testCliente.getPunteggio()).isEqualTo(UPDATED_PUNTEGGIO);
//        assertThat(testCliente.isAbilitato()).isEqualTo(UPDATED_ABILITATO);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingCliente() throws Exception {
//        int databaseSizeBeforeUpdate = clienteRepository.findAll().size();
//
//        // Create the Cliente
//        ClienteDTO clienteDTO = clienteMapper.toDto(cliente);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restClienteMockMvc.perform(put("/api/clientes")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(clienteDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Cliente in the database
//        List<Cliente> clienteList = clienteRepository.findAll();
//        assertThat(clienteList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteCliente() throws Exception {
//        // Initialize the database
//        clienteRepository.saveAndFlush(cliente);
//
//        int databaseSizeBeforeDelete = clienteRepository.findAll().size();
//
//        // Get the cliente
//        restClienteMockMvc.perform(delete("/api/clientes/{id}", cliente.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Cliente> clienteList = clienteRepository.findAll();
//        assertThat(clienteList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Cliente.class);
//        Cliente cliente1 = new Cliente();
//        cliente1.setId(1L);
//        Cliente cliente2 = new Cliente();
//        cliente2.setId(cliente1.getId());
//        assertThat(cliente1).isEqualTo(cliente2);
//        cliente2.setId(2L);
//        assertThat(cliente1).isNotEqualTo(cliente2);
//        cliente1.setId(null);
//        assertThat(cliente1).isNotEqualTo(cliente2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(ClienteDTO.class);
//        ClienteDTO clienteDTO1 = new ClienteDTO();
//        clienteDTO1.setId(1L);
//        ClienteDTO clienteDTO2 = new ClienteDTO();
//        assertThat(clienteDTO1).isNotEqualTo(clienteDTO2);
//        clienteDTO2.setId(clienteDTO1.getId());
//        assertThat(clienteDTO1).isEqualTo(clienteDTO2);
//        clienteDTO2.setId(2L);
//        assertThat(clienteDTO1).isNotEqualTo(clienteDTO2);
//        clienteDTO1.setId(null);
//        assertThat(clienteDTO1).isNotEqualTo(clienteDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(clienteMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(clienteMapper.fromId(null)).isNull();
//    }
}
