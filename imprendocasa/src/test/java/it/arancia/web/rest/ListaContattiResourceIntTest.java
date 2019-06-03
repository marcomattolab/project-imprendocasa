package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.ListaContatti;
import it.arancia.domain.Cliente;
import it.arancia.domain.Incarico;
import it.arancia.repository.ListaContattiRepository;
import it.arancia.service.ListaContattiService;
import it.arancia.service.dto.ListaContattiDTO;
import it.arancia.service.mapper.ListaContattiMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.ListaContattiCriteria;
import it.arancia.service.ListaContattiQueryService;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static it.arancia.web.rest.TestUtil.sameInstant;
import static it.arancia.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.arancia.domain.enumeration.EsitoChiamata;
/**
 * Test class for the ListaContattiResource REST controller.
 *
 * @see ListaContattiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class ListaContattiResourceIntTest {
//
//    private static final ZonedDateTime DEFAULT_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
//    private static final ZonedDateTime UPDATED_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
//
//    private static final EsitoChiamata DEFAULT_ESITO = EsitoChiamata.ESEGUITA;
//    private static final EsitoChiamata UPDATED_ESITO = EsitoChiamata.NON_RAGGIUNGIBILE;
//
//    private static final String DEFAULT_MOTIVAZIONE = "AAAAAAAAAA";
//    private static final String UPDATED_MOTIVAZIONE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
//    private static final String UPDATED_NOTE = "BBBBBBBBBB";
//
//    @Autowired
//    private ListaContattiRepository listaContattiRepository;
//
//    @Autowired
//    private ListaContattiMapper listaContattiMapper;
//
//    @Autowired
//    private ListaContattiService listaContattiService;
//
//    @Autowired
//    private ListaContattiQueryService listaContattiQueryService;
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
//    private MockMvc restListaContattiMockMvc;
//
//    private ListaContatti listaContatti;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final ListaContattiResource listaContattiResource = new ListaContattiResource(listaContattiService, listaContattiQueryService);
//        this.restListaContattiMockMvc = MockMvcBuilders.standaloneSetup(listaContattiResource)
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
//    public static ListaContatti createEntity(EntityManager em) {
//        ListaContatti listaContatti = new ListaContatti()
//            .dateTime(DEFAULT_DATE_TIME)
//            .esito(DEFAULT_ESITO)
//            .motivazione(DEFAULT_MOTIVAZIONE)
//            .note(DEFAULT_NOTE);
//        return listaContatti;
//    }
//
//    @Before
//    public void initTest() {
//        listaContatti = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createListaContatti() throws Exception {
//        int databaseSizeBeforeCreate = listaContattiRepository.findAll().size();
//
//        // Create the ListaContatti
//        ListaContattiDTO listaContattiDTO = listaContattiMapper.toDto(listaContatti);
//        restListaContattiMockMvc.perform(post("/api/lista-contattis")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(listaContattiDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the ListaContatti in the database
//        List<ListaContatti> listaContattiList = listaContattiRepository.findAll();
//        assertThat(listaContattiList).hasSize(databaseSizeBeforeCreate + 1);
//        ListaContatti testListaContatti = listaContattiList.get(listaContattiList.size() - 1);
//        assertThat(testListaContatti.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
//        assertThat(testListaContatti.getEsito()).isEqualTo(DEFAULT_ESITO);
//        assertThat(testListaContatti.getMotivazione()).isEqualTo(DEFAULT_MOTIVAZIONE);
//        assertThat(testListaContatti.getNote()).isEqualTo(DEFAULT_NOTE);
//    }
//
//    @Test
//    @Transactional
//    public void createListaContattiWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = listaContattiRepository.findAll().size();
//
//        // Create the ListaContatti with an existing ID
//        listaContatti.setId(1L);
//        ListaContattiDTO listaContattiDTO = listaContattiMapper.toDto(listaContatti);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restListaContattiMockMvc.perform(post("/api/lista-contattis")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(listaContattiDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the ListaContatti in the database
//        List<ListaContatti> listaContattiList = listaContattiRepository.findAll();
//        assertThat(listaContattiList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkDateTimeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = listaContattiRepository.findAll().size();
//        // set the field null
//        listaContatti.setDateTime(null);
//
//        // Create the ListaContatti, which fails.
//        ListaContattiDTO listaContattiDTO = listaContattiMapper.toDto(listaContatti);
//
//        restListaContattiMockMvc.perform(post("/api/lista-contattis")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(listaContattiDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<ListaContatti> listaContattiList = listaContattiRepository.findAll();
//        assertThat(listaContattiList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkEsitoIsRequired() throws Exception {
//        int databaseSizeBeforeTest = listaContattiRepository.findAll().size();
//        // set the field null
//        listaContatti.setEsito(null);
//
//        // Create the ListaContatti, which fails.
//        ListaContattiDTO listaContattiDTO = listaContattiMapper.toDto(listaContatti);
//
//        restListaContattiMockMvc.perform(post("/api/lista-contattis")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(listaContattiDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<ListaContatti> listaContattiList = listaContattiRepository.findAll();
//        assertThat(listaContattiList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkMotivazioneIsRequired() throws Exception {
//        int databaseSizeBeforeTest = listaContattiRepository.findAll().size();
//        // set the field null
//        listaContatti.setMotivazione(null);
//
//        // Create the ListaContatti, which fails.
//        ListaContattiDTO listaContattiDTO = listaContattiMapper.toDto(listaContatti);
//
//        restListaContattiMockMvc.perform(post("/api/lista-contattis")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(listaContattiDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<ListaContatti> listaContattiList = listaContattiRepository.findAll();
//        assertThat(listaContattiList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattis() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList
//        restListaContattiMockMvc.perform(get("/api/lista-contattis?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(listaContatti.getId().intValue())))
//            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(sameInstant(DEFAULT_DATE_TIME))))
//            .andExpect(jsonPath("$.[*].esito").value(hasItem(DEFAULT_ESITO.toString())))
//            .andExpect(jsonPath("$.[*].motivazione").value(hasItem(DEFAULT_MOTIVAZIONE.toString())))
//            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getListaContatti() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get the listaContatti
//        restListaContattiMockMvc.perform(get("/api/lista-contattis/{id}", listaContatti.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(listaContatti.getId().intValue()))
//            .andExpect(jsonPath("$.dateTime").value(sameInstant(DEFAULT_DATE_TIME)))
//            .andExpect(jsonPath("$.esito").value(DEFAULT_ESITO.toString()))
//            .andExpect(jsonPath("$.motivazione").value(DEFAULT_MOTIVAZIONE.toString()))
//            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByDateTimeIsEqualToSomething() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where dateTime equals to DEFAULT_DATE_TIME
//        defaultListaContattiShouldBeFound("dateTime.equals=" + DEFAULT_DATE_TIME);
//
//        // Get all the listaContattiList where dateTime equals to UPDATED_DATE_TIME
//        defaultListaContattiShouldNotBeFound("dateTime.equals=" + UPDATED_DATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByDateTimeIsInShouldWork() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where dateTime in DEFAULT_DATE_TIME or UPDATED_DATE_TIME
//        defaultListaContattiShouldBeFound("dateTime.in=" + DEFAULT_DATE_TIME + "," + UPDATED_DATE_TIME);
//
//        // Get all the listaContattiList where dateTime equals to UPDATED_DATE_TIME
//        defaultListaContattiShouldNotBeFound("dateTime.in=" + UPDATED_DATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByDateTimeIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where dateTime is not null
//        defaultListaContattiShouldBeFound("dateTime.specified=true");
//
//        // Get all the listaContattiList where dateTime is null
//        defaultListaContattiShouldNotBeFound("dateTime.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByDateTimeIsGreaterThanOrEqualToSomething() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where dateTime greater than or equals to DEFAULT_DATE_TIME
//        defaultListaContattiShouldBeFound("dateTime.greaterOrEqualThan=" + DEFAULT_DATE_TIME);
//
//        // Get all the listaContattiList where dateTime greater than or equals to UPDATED_DATE_TIME
//        defaultListaContattiShouldNotBeFound("dateTime.greaterOrEqualThan=" + UPDATED_DATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByDateTimeIsLessThanSomething() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where dateTime less than or equals to DEFAULT_DATE_TIME
//        defaultListaContattiShouldNotBeFound("dateTime.lessThan=" + DEFAULT_DATE_TIME);
//
//        // Get all the listaContattiList where dateTime less than or equals to UPDATED_DATE_TIME
//        defaultListaContattiShouldBeFound("dateTime.lessThan=" + UPDATED_DATE_TIME);
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByEsitoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where esito equals to DEFAULT_ESITO
//        defaultListaContattiShouldBeFound("esito.equals=" + DEFAULT_ESITO);
//
//        // Get all the listaContattiList where esito equals to UPDATED_ESITO
//        defaultListaContattiShouldNotBeFound("esito.equals=" + UPDATED_ESITO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByEsitoIsInShouldWork() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where esito in DEFAULT_ESITO or UPDATED_ESITO
//        defaultListaContattiShouldBeFound("esito.in=" + DEFAULT_ESITO + "," + UPDATED_ESITO);
//
//        // Get all the listaContattiList where esito equals to UPDATED_ESITO
//        defaultListaContattiShouldNotBeFound("esito.in=" + UPDATED_ESITO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByEsitoIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where esito is not null
//        defaultListaContattiShouldBeFound("esito.specified=true");
//
//        // Get all the listaContattiList where esito is null
//        defaultListaContattiShouldNotBeFound("esito.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByMotivazioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where motivazione equals to DEFAULT_MOTIVAZIONE
//        defaultListaContattiShouldBeFound("motivazione.equals=" + DEFAULT_MOTIVAZIONE);
//
//        // Get all the listaContattiList where motivazione equals to UPDATED_MOTIVAZIONE
//        defaultListaContattiShouldNotBeFound("motivazione.equals=" + UPDATED_MOTIVAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByMotivazioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where motivazione in DEFAULT_MOTIVAZIONE or UPDATED_MOTIVAZIONE
//        defaultListaContattiShouldBeFound("motivazione.in=" + DEFAULT_MOTIVAZIONE + "," + UPDATED_MOTIVAZIONE);
//
//        // Get all the listaContattiList where motivazione equals to UPDATED_MOTIVAZIONE
//        defaultListaContattiShouldNotBeFound("motivazione.in=" + UPDATED_MOTIVAZIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByMotivazioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        // Get all the listaContattiList where motivazione is not null
//        defaultListaContattiShouldBeFound("motivazione.specified=true");
//
//        // Get all the listaContattiList where motivazione is null
//        defaultListaContattiShouldNotBeFound("motivazione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByClienteIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Cliente cliente = ClienteResourceIntTest.createEntity(em);
//        em.persist(cliente);
//        em.flush();
//        listaContatti.setCliente(cliente);
//        listaContattiRepository.saveAndFlush(listaContatti);
//        Long clienteId = cliente.getId();
//
//        // Get all the listaContattiList where cliente equals to clienteId
//        defaultListaContattiShouldBeFound("clienteId.equals=" + clienteId);
//
//        // Get all the listaContattiList where cliente equals to clienteId + 1
//        defaultListaContattiShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllListaContattisByIncaricoIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Incarico incarico = IncaricoResourceIntTest.createEntity(em);
//        em.persist(incarico);
//        em.flush();
//        listaContatti.setIncarico(incarico);
//        listaContattiRepository.saveAndFlush(listaContatti);
//        Long incaricoId = incarico.getId();
//
//        // Get all the listaContattiList where incarico equals to incaricoId
//        defaultListaContattiShouldBeFound("incaricoId.equals=" + incaricoId);
//
//        // Get all the listaContattiList where incarico equals to incaricoId + 1
//        defaultListaContattiShouldNotBeFound("incaricoId.equals=" + (incaricoId + 1));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is returned
//     */
//    private void defaultListaContattiShouldBeFound(String filter) throws Exception {
//        restListaContattiMockMvc.perform(get("/api/lista-contattis?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(listaContatti.getId().intValue())))
//            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(sameInstant(DEFAULT_DATE_TIME))))
//            .andExpect(jsonPath("$.[*].esito").value(hasItem(DEFAULT_ESITO.toString())))
//            .andExpect(jsonPath("$.[*].motivazione").value(hasItem(DEFAULT_MOTIVAZIONE.toString())))
//            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())));
//
//        // Check, that the count call also returns 1
//        restListaContattiMockMvc.perform(get("/api/lista-contattis/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("1"));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is not returned
//     */
//    private void defaultListaContattiShouldNotBeFound(String filter) throws Exception {
//        restListaContattiMockMvc.perform(get("/api/lista-contattis?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$").isArray())
//            .andExpect(jsonPath("$").isEmpty());
//
//        // Check, that the count call also returns 0
//        restListaContattiMockMvc.perform(get("/api/lista-contattis/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("0"));
//    }
//
//
//    @Test
//    @Transactional
//    public void getNonExistingListaContatti() throws Exception {
//        // Get the listaContatti
//        restListaContattiMockMvc.perform(get("/api/lista-contattis/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateListaContatti() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        int databaseSizeBeforeUpdate = listaContattiRepository.findAll().size();
//
//        // Update the listaContatti
//        ListaContatti updatedListaContatti = listaContattiRepository.findById(listaContatti.getId()).get();
//        // Disconnect from session so that the updates on updatedListaContatti are not directly saved in db
//        em.detach(updatedListaContatti);
//        updatedListaContatti
//            .dateTime(UPDATED_DATE_TIME)
//            .esito(UPDATED_ESITO)
//            .motivazione(UPDATED_MOTIVAZIONE)
//            .note(UPDATED_NOTE);
//        ListaContattiDTO listaContattiDTO = listaContattiMapper.toDto(updatedListaContatti);
//
//        restListaContattiMockMvc.perform(put("/api/lista-contattis")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(listaContattiDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the ListaContatti in the database
//        List<ListaContatti> listaContattiList = listaContattiRepository.findAll();
//        assertThat(listaContattiList).hasSize(databaseSizeBeforeUpdate);
//        ListaContatti testListaContatti = listaContattiList.get(listaContattiList.size() - 1);
//        assertThat(testListaContatti.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
//        assertThat(testListaContatti.getEsito()).isEqualTo(UPDATED_ESITO);
//        assertThat(testListaContatti.getMotivazione()).isEqualTo(UPDATED_MOTIVAZIONE);
//        assertThat(testListaContatti.getNote()).isEqualTo(UPDATED_NOTE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingListaContatti() throws Exception {
//        int databaseSizeBeforeUpdate = listaContattiRepository.findAll().size();
//
//        // Create the ListaContatti
//        ListaContattiDTO listaContattiDTO = listaContattiMapper.toDto(listaContatti);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restListaContattiMockMvc.perform(put("/api/lista-contattis")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(listaContattiDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the ListaContatti in the database
//        List<ListaContatti> listaContattiList = listaContattiRepository.findAll();
//        assertThat(listaContattiList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteListaContatti() throws Exception {
//        // Initialize the database
//        listaContattiRepository.saveAndFlush(listaContatti);
//
//        int databaseSizeBeforeDelete = listaContattiRepository.findAll().size();
//
//        // Get the listaContatti
//        restListaContattiMockMvc.perform(delete("/api/lista-contattis/{id}", listaContatti.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<ListaContatti> listaContattiList = listaContattiRepository.findAll();
//        assertThat(listaContattiList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(ListaContatti.class);
//        ListaContatti listaContatti1 = new ListaContatti();
//        listaContatti1.setId(1L);
//        ListaContatti listaContatti2 = new ListaContatti();
//        listaContatti2.setId(listaContatti1.getId());
//        assertThat(listaContatti1).isEqualTo(listaContatti2);
//        listaContatti2.setId(2L);
//        assertThat(listaContatti1).isNotEqualTo(listaContatti2);
//        listaContatti1.setId(null);
//        assertThat(listaContatti1).isNotEqualTo(listaContatti2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(ListaContattiDTO.class);
//        ListaContattiDTO listaContattiDTO1 = new ListaContattiDTO();
//        listaContattiDTO1.setId(1L);
//        ListaContattiDTO listaContattiDTO2 = new ListaContattiDTO();
//        assertThat(listaContattiDTO1).isNotEqualTo(listaContattiDTO2);
//        listaContattiDTO2.setId(listaContattiDTO1.getId());
//        assertThat(listaContattiDTO1).isEqualTo(listaContattiDTO2);
//        listaContattiDTO2.setId(2L);
//        assertThat(listaContattiDTO1).isNotEqualTo(listaContattiDTO2);
//        listaContattiDTO1.setId(null);
//        assertThat(listaContattiDTO1).isNotEqualTo(listaContattiDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(listaContattiMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(listaContattiMapper.fromId(null)).isNull();
//    }
}
