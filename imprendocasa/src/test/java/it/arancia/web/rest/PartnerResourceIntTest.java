package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Partner;
import it.arancia.domain.Professione;
import it.arancia.domain.Incarico;
import it.arancia.repository.PartnerRepository;
import it.arancia.service.PartnerService;
import it.arancia.service.dto.PartnerDTO;
import it.arancia.service.mapper.PartnerMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.PartnerCriteria;
import it.arancia.service.PartnerQueryService;

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

import it.arancia.domain.enumeration.TipoIndirizzo;
/**
 * Test class for the PartnerResource REST controller.
 *
 * @see PartnerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class PartnerResourceIntTest {

//    private static final String DEFAULT_NOME = "AAAAAAAAAA";
//    private static final String UPDATED_NOME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
//    private static final String UPDATED_COGNOME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CODICE_FISCALE = "AAAAAAAAAA";
//    private static final String UPDATED_CODICE_FISCALE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_TELEFONO_FISSO = "AAAAAAAAAA";
//    private static final String UPDATED_TELEFONO_FISSO = "BBBBBBBBBB";
//
//    private static final String DEFAULT_TELEFONO_CELLULARE = "AAAAAAAAAA";
//    private static final String UPDATED_TELEFONO_CELLULARE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_EMAIL = "({@)~.>";
//    private static final String UPDATED_EMAIL = "q@]\\.Dw";
//
//    private static final TipoIndirizzo DEFAULT_TIPO_INDIRIZZO = TipoIndirizzo.CASA;
//    private static final TipoIndirizzo UPDATED_TIPO_INDIRIZZO = TipoIndirizzo.LAVORO;
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
//    private static final Boolean DEFAULT_ABILITATO = false;
//    private static final Boolean UPDATED_ABILITATO = true;
//
//    @Autowired
//    private PartnerRepository partnerRepository;
//
//    @Autowired
//    private PartnerMapper partnerMapper;
//
//    @Autowired
//    private PartnerService partnerService;
//
//    @Autowired
//    private PartnerQueryService partnerQueryService;
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
//    private MockMvc restPartnerMockMvc;
//
//    private Partner partner;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final PartnerResource partnerResource = new PartnerResource(partnerService, partnerQueryService);
//        this.restPartnerMockMvc = MockMvcBuilders.standaloneSetup(partnerResource)
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
//    public static Partner createEntity(EntityManager em) {
//        Partner partner = new Partner()
//            .nome(DEFAULT_NOME)
//            .cognome(DEFAULT_COGNOME)
//            .codiceFiscale(DEFAULT_CODICE_FISCALE)
//            .telefonoFisso(DEFAULT_TELEFONO_FISSO)
//            .telefonoCellulare(DEFAULT_TELEFONO_CELLULARE)
//            .email(DEFAULT_EMAIL)
//            .tipoIndirizzo(DEFAULT_TIPO_INDIRIZZO)
//            .indirizzo(DEFAULT_INDIRIZZO)
//            .cap(DEFAULT_CAP)
//            .regione(DEFAULT_REGIONE)
//            .provincia(DEFAULT_PROVINCIA)
//            .citta(DEFAULT_CITTA)
//            .note(DEFAULT_NOTE)
//            .abilitato(DEFAULT_ABILITATO);
//        return partner;
//    }
//
//    @Before
//    public void initTest() {
//        partner = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createPartner() throws Exception {
//        int databaseSizeBeforeCreate = partnerRepository.findAll().size();
//
//        // Create the Partner
//        PartnerDTO partnerDTO = partnerMapper.toDto(partner);
//        restPartnerMockMvc.perform(post("/api/partners")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Partner in the database
//        List<Partner> partnerList = partnerRepository.findAll();
//        assertThat(partnerList).hasSize(databaseSizeBeforeCreate + 1);
//        Partner testPartner = partnerList.get(partnerList.size() - 1);
//        assertThat(testPartner.getNome()).isEqualTo(DEFAULT_NOME);
//        assertThat(testPartner.getCognome()).isEqualTo(DEFAULT_COGNOME);
//        assertThat(testPartner.getCodiceFiscale()).isEqualTo(DEFAULT_CODICE_FISCALE);
//        assertThat(testPartner.getTelefonoFisso()).isEqualTo(DEFAULT_TELEFONO_FISSO);
//        assertThat(testPartner.getTelefonoCellulare()).isEqualTo(DEFAULT_TELEFONO_CELLULARE);
//        assertThat(testPartner.getEmail()).isEqualTo(DEFAULT_EMAIL);
//        assertThat(testPartner.getTipoIndirizzo()).isEqualTo(DEFAULT_TIPO_INDIRIZZO);
//        assertThat(testPartner.getIndirizzo()).isEqualTo(DEFAULT_INDIRIZZO);
//        assertThat(testPartner.getCap()).isEqualTo(DEFAULT_CAP);
//        assertThat(testPartner.getRegione()).isEqualTo(DEFAULT_REGIONE);
//        assertThat(testPartner.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
//        assertThat(testPartner.getCitta()).isEqualTo(DEFAULT_CITTA);
//        assertThat(testPartner.getNote()).isEqualTo(DEFAULT_NOTE);
//        assertThat(testPartner.isAbilitato()).isEqualTo(DEFAULT_ABILITATO);
//    }
//
//    @Test
//    @Transactional
//    public void createPartnerWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = partnerRepository.findAll().size();
//
//        // Create the Partner with an existing ID
//        partner.setId(1L);
//        PartnerDTO partnerDTO = partnerMapper.toDto(partner);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restPartnerMockMvc.perform(post("/api/partners")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Partner in the database
//        List<Partner> partnerList = partnerRepository.findAll();
//        assertThat(partnerList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkNomeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = partnerRepository.findAll().size();
//        // set the field null
//        partner.setNome(null);
//
//        // Create the Partner, which fails.
//        PartnerDTO partnerDTO = partnerMapper.toDto(partner);
//
//        restPartnerMockMvc.perform(post("/api/partners")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Partner> partnerList = partnerRepository.findAll();
//        assertThat(partnerList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkCognomeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = partnerRepository.findAll().size();
//        // set the field null
//        partner.setCognome(null);
//
//        // Create the Partner, which fails.
//        PartnerDTO partnerDTO = partnerMapper.toDto(partner);
//
//        restPartnerMockMvc.perform(post("/api/partners")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Partner> partnerList = partnerRepository.findAll();
//        assertThat(partnerList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartners() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList
//        restPartnerMockMvc.perform(get("/api/partners?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(partner.getId().intValue())))
//            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
//            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME.toString())))
//            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE.toString())))
//            .andExpect(jsonPath("$.[*].telefonoFisso").value(hasItem(DEFAULT_TELEFONO_FISSO.toString())))
//            .andExpect(jsonPath("$.[*].telefonoCellulare").value(hasItem(DEFAULT_TELEFONO_CELLULARE.toString())))
//            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
//            .andExpect(jsonPath("$.[*].tipoIndirizzo").value(hasItem(DEFAULT_TIPO_INDIRIZZO.toString())))
//            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO.toString())))
//            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP.toString())))
//            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE.toString())))
//            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA.toString())))
//            .andExpect(jsonPath("$.[*].citta").value(hasItem(DEFAULT_CITTA.toString())))
//            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
//            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));
//    }
//
//    @Test
//    @Transactional
//    public void getPartner() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get the partner
//        restPartnerMockMvc.perform(get("/api/partners/{id}", partner.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(partner.getId().intValue()))
//            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
//            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME.toString()))
//            .andExpect(jsonPath("$.codiceFiscale").value(DEFAULT_CODICE_FISCALE.toString()))
//            .andExpect(jsonPath("$.telefonoFisso").value(DEFAULT_TELEFONO_FISSO.toString()))
//            .andExpect(jsonPath("$.telefonoCellulare").value(DEFAULT_TELEFONO_CELLULARE.toString()))
//            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
//            .andExpect(jsonPath("$.tipoIndirizzo").value(DEFAULT_TIPO_INDIRIZZO.toString()))
//            .andExpect(jsonPath("$.indirizzo").value(DEFAULT_INDIRIZZO.toString()))
//            .andExpect(jsonPath("$.cap").value(DEFAULT_CAP.toString()))
//            .andExpect(jsonPath("$.regione").value(DEFAULT_REGIONE.toString()))
//            .andExpect(jsonPath("$.provincia").value(DEFAULT_PROVINCIA.toString()))
//            .andExpect(jsonPath("$.citta").value(DEFAULT_CITTA.toString()))
//            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
//            .andExpect(jsonPath("$.abilitato").value(DEFAULT_ABILITATO.booleanValue()));
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByNomeIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where nome equals to DEFAULT_NOME
//        defaultPartnerShouldBeFound("nome.equals=" + DEFAULT_NOME);
//
//        // Get all the partnerList where nome equals to UPDATED_NOME
//        defaultPartnerShouldNotBeFound("nome.equals=" + UPDATED_NOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByNomeIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where nome in DEFAULT_NOME or UPDATED_NOME
//        defaultPartnerShouldBeFound("nome.in=" + DEFAULT_NOME + "," + UPDATED_NOME);
//
//        // Get all the partnerList where nome equals to UPDATED_NOME
//        defaultPartnerShouldNotBeFound("nome.in=" + UPDATED_NOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByNomeIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where nome is not null
//        defaultPartnerShouldBeFound("nome.specified=true");
//
//        // Get all the partnerList where nome is null
//        defaultPartnerShouldNotBeFound("nome.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCognomeIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where cognome equals to DEFAULT_COGNOME
//        defaultPartnerShouldBeFound("cognome.equals=" + DEFAULT_COGNOME);
//
//        // Get all the partnerList where cognome equals to UPDATED_COGNOME
//        defaultPartnerShouldNotBeFound("cognome.equals=" + UPDATED_COGNOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCognomeIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where cognome in DEFAULT_COGNOME or UPDATED_COGNOME
//        defaultPartnerShouldBeFound("cognome.in=" + DEFAULT_COGNOME + "," + UPDATED_COGNOME);
//
//        // Get all the partnerList where cognome equals to UPDATED_COGNOME
//        defaultPartnerShouldNotBeFound("cognome.in=" + UPDATED_COGNOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCognomeIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where cognome is not null
//        defaultPartnerShouldBeFound("cognome.specified=true");
//
//        // Get all the partnerList where cognome is null
//        defaultPartnerShouldNotBeFound("cognome.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCodiceFiscaleIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where codiceFiscale equals to DEFAULT_CODICE_FISCALE
//        defaultPartnerShouldBeFound("codiceFiscale.equals=" + DEFAULT_CODICE_FISCALE);
//
//        // Get all the partnerList where codiceFiscale equals to UPDATED_CODICE_FISCALE
//        defaultPartnerShouldNotBeFound("codiceFiscale.equals=" + UPDATED_CODICE_FISCALE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCodiceFiscaleIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where codiceFiscale in DEFAULT_CODICE_FISCALE or UPDATED_CODICE_FISCALE
//        defaultPartnerShouldBeFound("codiceFiscale.in=" + DEFAULT_CODICE_FISCALE + "," + UPDATED_CODICE_FISCALE);
//
//        // Get all the partnerList where codiceFiscale equals to UPDATED_CODICE_FISCALE
//        defaultPartnerShouldNotBeFound("codiceFiscale.in=" + UPDATED_CODICE_FISCALE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCodiceFiscaleIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where codiceFiscale is not null
//        defaultPartnerShouldBeFound("codiceFiscale.specified=true");
//
//        // Get all the partnerList where codiceFiscale is null
//        defaultPartnerShouldNotBeFound("codiceFiscale.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTelefonoFissoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where telefonoFisso equals to DEFAULT_TELEFONO_FISSO
//        defaultPartnerShouldBeFound("telefonoFisso.equals=" + DEFAULT_TELEFONO_FISSO);
//
//        // Get all the partnerList where telefonoFisso equals to UPDATED_TELEFONO_FISSO
//        defaultPartnerShouldNotBeFound("telefonoFisso.equals=" + UPDATED_TELEFONO_FISSO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTelefonoFissoIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where telefonoFisso in DEFAULT_TELEFONO_FISSO or UPDATED_TELEFONO_FISSO
//        defaultPartnerShouldBeFound("telefonoFisso.in=" + DEFAULT_TELEFONO_FISSO + "," + UPDATED_TELEFONO_FISSO);
//
//        // Get all the partnerList where telefonoFisso equals to UPDATED_TELEFONO_FISSO
//        defaultPartnerShouldNotBeFound("telefonoFisso.in=" + UPDATED_TELEFONO_FISSO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTelefonoFissoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where telefonoFisso is not null
//        defaultPartnerShouldBeFound("telefonoFisso.specified=true");
//
//        // Get all the partnerList where telefonoFisso is null
//        defaultPartnerShouldNotBeFound("telefonoFisso.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTelefonoCellulareIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where telefonoCellulare equals to DEFAULT_TELEFONO_CELLULARE
//        defaultPartnerShouldBeFound("telefonoCellulare.equals=" + DEFAULT_TELEFONO_CELLULARE);
//
//        // Get all the partnerList where telefonoCellulare equals to UPDATED_TELEFONO_CELLULARE
//        defaultPartnerShouldNotBeFound("telefonoCellulare.equals=" + UPDATED_TELEFONO_CELLULARE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTelefonoCellulareIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where telefonoCellulare in DEFAULT_TELEFONO_CELLULARE or UPDATED_TELEFONO_CELLULARE
//        defaultPartnerShouldBeFound("telefonoCellulare.in=" + DEFAULT_TELEFONO_CELLULARE + "," + UPDATED_TELEFONO_CELLULARE);
//
//        // Get all the partnerList where telefonoCellulare equals to UPDATED_TELEFONO_CELLULARE
//        defaultPartnerShouldNotBeFound("telefonoCellulare.in=" + UPDATED_TELEFONO_CELLULARE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTelefonoCellulareIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where telefonoCellulare is not null
//        defaultPartnerShouldBeFound("telefonoCellulare.specified=true");
//
//        // Get all the partnerList where telefonoCellulare is null
//        defaultPartnerShouldNotBeFound("telefonoCellulare.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByEmailIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where email equals to DEFAULT_EMAIL
//        defaultPartnerShouldBeFound("email.equals=" + DEFAULT_EMAIL);
//
//        // Get all the partnerList where email equals to UPDATED_EMAIL
//        defaultPartnerShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByEmailIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where email in DEFAULT_EMAIL or UPDATED_EMAIL
//        defaultPartnerShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);
//
//        // Get all the partnerList where email equals to UPDATED_EMAIL
//        defaultPartnerShouldNotBeFound("email.in=" + UPDATED_EMAIL);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByEmailIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where email is not null
//        defaultPartnerShouldBeFound("email.specified=true");
//
//        // Get all the partnerList where email is null
//        defaultPartnerShouldNotBeFound("email.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTipoIndirizzoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where tipoIndirizzo equals to DEFAULT_TIPO_INDIRIZZO
//        defaultPartnerShouldBeFound("tipoIndirizzo.equals=" + DEFAULT_TIPO_INDIRIZZO);
//
//        // Get all the partnerList where tipoIndirizzo equals to UPDATED_TIPO_INDIRIZZO
//        defaultPartnerShouldNotBeFound("tipoIndirizzo.equals=" + UPDATED_TIPO_INDIRIZZO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTipoIndirizzoIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where tipoIndirizzo in DEFAULT_TIPO_INDIRIZZO or UPDATED_TIPO_INDIRIZZO
//        defaultPartnerShouldBeFound("tipoIndirizzo.in=" + DEFAULT_TIPO_INDIRIZZO + "," + UPDATED_TIPO_INDIRIZZO);
//
//        // Get all the partnerList where tipoIndirizzo equals to UPDATED_TIPO_INDIRIZZO
//        defaultPartnerShouldNotBeFound("tipoIndirizzo.in=" + UPDATED_TIPO_INDIRIZZO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByTipoIndirizzoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where tipoIndirizzo is not null
//        defaultPartnerShouldBeFound("tipoIndirizzo.specified=true");
//
//        // Get all the partnerList where tipoIndirizzo is null
//        defaultPartnerShouldNotBeFound("tipoIndirizzo.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByIndirizzoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where indirizzo equals to DEFAULT_INDIRIZZO
//        defaultPartnerShouldBeFound("indirizzo.equals=" + DEFAULT_INDIRIZZO);
//
//        // Get all the partnerList where indirizzo equals to UPDATED_INDIRIZZO
//        defaultPartnerShouldNotBeFound("indirizzo.equals=" + UPDATED_INDIRIZZO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByIndirizzoIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where indirizzo in DEFAULT_INDIRIZZO or UPDATED_INDIRIZZO
//        defaultPartnerShouldBeFound("indirizzo.in=" + DEFAULT_INDIRIZZO + "," + UPDATED_INDIRIZZO);
//
//        // Get all the partnerList where indirizzo equals to UPDATED_INDIRIZZO
//        defaultPartnerShouldNotBeFound("indirizzo.in=" + UPDATED_INDIRIZZO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByIndirizzoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where indirizzo is not null
//        defaultPartnerShouldBeFound("indirizzo.specified=true");
//
//        // Get all the partnerList where indirizzo is null
//        defaultPartnerShouldNotBeFound("indirizzo.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCapIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where cap equals to DEFAULT_CAP
//        defaultPartnerShouldBeFound("cap.equals=" + DEFAULT_CAP);
//
//        // Get all the partnerList where cap equals to UPDATED_CAP
//        defaultPartnerShouldNotBeFound("cap.equals=" + UPDATED_CAP);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCapIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where cap in DEFAULT_CAP or UPDATED_CAP
//        defaultPartnerShouldBeFound("cap.in=" + DEFAULT_CAP + "," + UPDATED_CAP);
//
//        // Get all the partnerList where cap equals to UPDATED_CAP
//        defaultPartnerShouldNotBeFound("cap.in=" + UPDATED_CAP);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCapIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where cap is not null
//        defaultPartnerShouldBeFound("cap.specified=true");
//
//        // Get all the partnerList where cap is null
//        defaultPartnerShouldNotBeFound("cap.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByRegioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where regione equals to DEFAULT_REGIONE
//        defaultPartnerShouldBeFound("regione.equals=" + DEFAULT_REGIONE);
//
//        // Get all the partnerList where regione equals to UPDATED_REGIONE
//        defaultPartnerShouldNotBeFound("regione.equals=" + UPDATED_REGIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByRegioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where regione in DEFAULT_REGIONE or UPDATED_REGIONE
//        defaultPartnerShouldBeFound("regione.in=" + DEFAULT_REGIONE + "," + UPDATED_REGIONE);
//
//        // Get all the partnerList where regione equals to UPDATED_REGIONE
//        defaultPartnerShouldNotBeFound("regione.in=" + UPDATED_REGIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByRegioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where regione is not null
//        defaultPartnerShouldBeFound("regione.specified=true");
//
//        // Get all the partnerList where regione is null
//        defaultPartnerShouldNotBeFound("regione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByProvinciaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where provincia equals to DEFAULT_PROVINCIA
//        defaultPartnerShouldBeFound("provincia.equals=" + DEFAULT_PROVINCIA);
//
//        // Get all the partnerList where provincia equals to UPDATED_PROVINCIA
//        defaultPartnerShouldNotBeFound("provincia.equals=" + UPDATED_PROVINCIA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByProvinciaIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where provincia in DEFAULT_PROVINCIA or UPDATED_PROVINCIA
//        defaultPartnerShouldBeFound("provincia.in=" + DEFAULT_PROVINCIA + "," + UPDATED_PROVINCIA);
//
//        // Get all the partnerList where provincia equals to UPDATED_PROVINCIA
//        defaultPartnerShouldNotBeFound("provincia.in=" + UPDATED_PROVINCIA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByProvinciaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where provincia is not null
//        defaultPartnerShouldBeFound("provincia.specified=true");
//
//        // Get all the partnerList where provincia is null
//        defaultPartnerShouldNotBeFound("provincia.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCittaIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where citta equals to DEFAULT_CITTA
//        defaultPartnerShouldBeFound("citta.equals=" + DEFAULT_CITTA);
//
//        // Get all the partnerList where citta equals to UPDATED_CITTA
//        defaultPartnerShouldNotBeFound("citta.equals=" + UPDATED_CITTA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCittaIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where citta in DEFAULT_CITTA or UPDATED_CITTA
//        defaultPartnerShouldBeFound("citta.in=" + DEFAULT_CITTA + "," + UPDATED_CITTA);
//
//        // Get all the partnerList where citta equals to UPDATED_CITTA
//        defaultPartnerShouldNotBeFound("citta.in=" + UPDATED_CITTA);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByCittaIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where citta is not null
//        defaultPartnerShouldBeFound("citta.specified=true");
//
//        // Get all the partnerList where citta is null
//        defaultPartnerShouldNotBeFound("citta.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByAbilitatoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where abilitato equals to DEFAULT_ABILITATO
//        defaultPartnerShouldBeFound("abilitato.equals=" + DEFAULT_ABILITATO);
//
//        // Get all the partnerList where abilitato equals to UPDATED_ABILITATO
//        defaultPartnerShouldNotBeFound("abilitato.equals=" + UPDATED_ABILITATO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByAbilitatoIsInShouldWork() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where abilitato in DEFAULT_ABILITATO or UPDATED_ABILITATO
//        defaultPartnerShouldBeFound("abilitato.in=" + DEFAULT_ABILITATO + "," + UPDATED_ABILITATO);
//
//        // Get all the partnerList where abilitato equals to UPDATED_ABILITATO
//        defaultPartnerShouldNotBeFound("abilitato.in=" + UPDATED_ABILITATO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByAbilitatoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        // Get all the partnerList where abilitato is not null
//        defaultPartnerShouldBeFound("abilitato.specified=true");
//
//        // Get all the partnerList where abilitato is null
//        defaultPartnerShouldNotBeFound("abilitato.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllPartnersByProfessioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Professione professione = ProfessioneResourceIntTest.createEntity(em);
//        em.persist(professione);
//        em.flush();
//        partner.setProfessione(professione);
//        partnerRepository.saveAndFlush(partner);
//        Long professioneId = professione.getId();
//
//        // Get all the partnerList where professione equals to professioneId
//        defaultPartnerShouldBeFound("professioneId.equals=" + professioneId);
//
//        // Get all the partnerList where professione equals to professioneId + 1
//        defaultPartnerShouldNotBeFound("professioneId.equals=" + (professioneId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllPartnersByIncaricoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Incarico incarico = IncaricoResourceIntTest.createEntity(em);
//        em.persist(incarico);
//        em.flush();
//        partner.addIncarico(incarico);
//        partnerRepository.saveAndFlush(partner);
//        Long incaricoId = incarico.getId();
//
//        // Get all the partnerList where incarico equals to incaricoId
//        defaultPartnerShouldBeFound("incaricoId.equals=" + incaricoId);
//
//        // Get all the partnerList where incarico equals to incaricoId + 1
//        defaultPartnerShouldNotBeFound("incaricoId.equals=" + (incaricoId + 1));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is returned
//     */
//    private void defaultPartnerShouldBeFound(String filter) throws Exception {
//        restPartnerMockMvc.perform(get("/api/partners?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(partner.getId().intValue())))
//            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
//            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME.toString())))
//            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE.toString())))
//            .andExpect(jsonPath("$.[*].telefonoFisso").value(hasItem(DEFAULT_TELEFONO_FISSO.toString())))
//            .andExpect(jsonPath("$.[*].telefonoCellulare").value(hasItem(DEFAULT_TELEFONO_CELLULARE.toString())))
//            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
//            .andExpect(jsonPath("$.[*].tipoIndirizzo").value(hasItem(DEFAULT_TIPO_INDIRIZZO.toString())))
//            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO.toString())))
//            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP.toString())))
//            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE.toString())))
//            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA.toString())))
//            .andExpect(jsonPath("$.[*].citta").value(hasItem(DEFAULT_CITTA.toString())))
//            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
//            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));
//
//        // Check, that the count call also returns 1
//        restPartnerMockMvc.perform(get("/api/partners/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("1"));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is not returned
//     */
//    private void defaultPartnerShouldNotBeFound(String filter) throws Exception {
//        restPartnerMockMvc.perform(get("/api/partners?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$").isArray())
//            .andExpect(jsonPath("$").isEmpty());
//
//        // Check, that the count call also returns 0
//        restPartnerMockMvc.perform(get("/api/partners/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("0"));
//    }
//
//
//    @Test
//    @Transactional
//    public void getNonExistingPartner() throws Exception {
//        // Get the partner
//        restPartnerMockMvc.perform(get("/api/partners/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updatePartner() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        int databaseSizeBeforeUpdate = partnerRepository.findAll().size();
//
//        // Update the partner
//        Partner updatedPartner = partnerRepository.findById(partner.getId()).get();
//        // Disconnect from session so that the updates on updatedPartner are not directly saved in db
//        em.detach(updatedPartner);
//        updatedPartner
//            .nome(UPDATED_NOME)
//            .cognome(UPDATED_COGNOME)
//            .codiceFiscale(UPDATED_CODICE_FISCALE)
//            .telefonoFisso(UPDATED_TELEFONO_FISSO)
//            .telefonoCellulare(UPDATED_TELEFONO_CELLULARE)
//            .email(UPDATED_EMAIL)
//            .tipoIndirizzo(UPDATED_TIPO_INDIRIZZO)
//            .indirizzo(UPDATED_INDIRIZZO)
//            .cap(UPDATED_CAP)
//            .regione(UPDATED_REGIONE)
//            .provincia(UPDATED_PROVINCIA)
//            .citta(UPDATED_CITTA)
//            .note(UPDATED_NOTE)
//            .abilitato(UPDATED_ABILITATO);
//        PartnerDTO partnerDTO = partnerMapper.toDto(updatedPartner);
//
//        restPartnerMockMvc.perform(put("/api/partners")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Partner in the database
//        List<Partner> partnerList = partnerRepository.findAll();
//        assertThat(partnerList).hasSize(databaseSizeBeforeUpdate);
//        Partner testPartner = partnerList.get(partnerList.size() - 1);
//        assertThat(testPartner.getNome()).isEqualTo(UPDATED_NOME);
//        assertThat(testPartner.getCognome()).isEqualTo(UPDATED_COGNOME);
//        assertThat(testPartner.getCodiceFiscale()).isEqualTo(UPDATED_CODICE_FISCALE);
//        assertThat(testPartner.getTelefonoFisso()).isEqualTo(UPDATED_TELEFONO_FISSO);
//        assertThat(testPartner.getTelefonoCellulare()).isEqualTo(UPDATED_TELEFONO_CELLULARE);
//        assertThat(testPartner.getEmail()).isEqualTo(UPDATED_EMAIL);
//        assertThat(testPartner.getTipoIndirizzo()).isEqualTo(UPDATED_TIPO_INDIRIZZO);
//        assertThat(testPartner.getIndirizzo()).isEqualTo(UPDATED_INDIRIZZO);
//        assertThat(testPartner.getCap()).isEqualTo(UPDATED_CAP);
//        assertThat(testPartner.getRegione()).isEqualTo(UPDATED_REGIONE);
//        assertThat(testPartner.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
//        assertThat(testPartner.getCitta()).isEqualTo(UPDATED_CITTA);
//        assertThat(testPartner.getNote()).isEqualTo(UPDATED_NOTE);
//        assertThat(testPartner.isAbilitato()).isEqualTo(UPDATED_ABILITATO);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingPartner() throws Exception {
//        int databaseSizeBeforeUpdate = partnerRepository.findAll().size();
//
//        // Create the Partner
//        PartnerDTO partnerDTO = partnerMapper.toDto(partner);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restPartnerMockMvc.perform(put("/api/partners")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(partnerDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Partner in the database
//        List<Partner> partnerList = partnerRepository.findAll();
//        assertThat(partnerList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deletePartner() throws Exception {
//        // Initialize the database
//        partnerRepository.saveAndFlush(partner);
//
//        int databaseSizeBeforeDelete = partnerRepository.findAll().size();
//
//        // Get the partner
//        restPartnerMockMvc.perform(delete("/api/partners/{id}", partner.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Partner> partnerList = partnerRepository.findAll();
//        assertThat(partnerList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Partner.class);
//        Partner partner1 = new Partner();
//        partner1.setId(1L);
//        Partner partner2 = new Partner();
//        partner2.setId(partner1.getId());
//        assertThat(partner1).isEqualTo(partner2);
//        partner2.setId(2L);
//        assertThat(partner1).isNotEqualTo(partner2);
//        partner1.setId(null);
//        assertThat(partner1).isNotEqualTo(partner2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(PartnerDTO.class);
//        PartnerDTO partnerDTO1 = new PartnerDTO();
//        partnerDTO1.setId(1L);
//        PartnerDTO partnerDTO2 = new PartnerDTO();
//        assertThat(partnerDTO1).isNotEqualTo(partnerDTO2);
//        partnerDTO2.setId(partnerDTO1.getId());
//        assertThat(partnerDTO1).isEqualTo(partnerDTO2);
//        partnerDTO2.setId(2L);
//        assertThat(partnerDTO1).isNotEqualTo(partnerDTO2);
//        partnerDTO1.setId(null);
//        assertThat(partnerDTO1).isNotEqualTo(partnerDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(partnerMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(partnerMapper.fromId(null)).isNull();
//    }
}
