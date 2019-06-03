package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Geolocalizzazione;
import it.arancia.domain.Immobile;
import it.arancia.repository.GeolocalizzazioneRepository;
import it.arancia.service.GeolocalizzazioneService;
import it.arancia.service.dto.GeolocalizzazioneDTO;
import it.arancia.service.mapper.GeolocalizzazioneMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.GeolocalizzazioneCriteria;
import it.arancia.service.GeolocalizzazioneQueryService;

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


//import static it.arancia.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GeolocalizzazioneResource REST controller.
 *
 * @see GeolocalizzazioneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class GeolocalizzazioneResourceIntTest {

//    private static final String DEFAULT_IMMOBILE = "AAAAAAAAAA";
//    private static final String UPDATED_IMMOBILE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_LATITUDINE = "AAAAAAAAAA";
//    private static final String UPDATED_LATITUDINE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_LONGITUDINE = "AAAAAAAAAA";
//    private static final String UPDATED_LONGITUDINE = "BBBBBBBBBB";
//
//    @Autowired
//    private GeolocalizzazioneRepository geolocalizzazioneRepository;
//
//    @Autowired
//    private GeolocalizzazioneMapper geolocalizzazioneMapper;
//
//    @Autowired
//    private GeolocalizzazioneService geolocalizzazioneService;
//
//    @Autowired
//    private GeolocalizzazioneQueryService geolocalizzazioneQueryService;
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
//    private MockMvc restGeolocalizzazioneMockMvc;
//
//    private Geolocalizzazione geolocalizzazione;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final GeolocalizzazioneResource geolocalizzazioneResource = new GeolocalizzazioneResource(geolocalizzazioneService, geolocalizzazioneQueryService);
//        this.restGeolocalizzazioneMockMvc = MockMvcBuilders.standaloneSetup(geolocalizzazioneResource)
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
//    public static Geolocalizzazione createEntity(EntityManager em) {
//        Geolocalizzazione geolocalizzazione = new Geolocalizzazione()
//            .immobile(DEFAULT_IMMOBILE)
//            .latitudine(DEFAULT_LATITUDINE)
//            .longitudine(DEFAULT_LONGITUDINE);
//        return geolocalizzazione;
//    }
//
//    @Before
//    public void initTest() {
//        geolocalizzazione = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createGeolocalizzazione() throws Exception {
//        int databaseSizeBeforeCreate = geolocalizzazioneRepository.findAll().size();
//
//        // Create the Geolocalizzazione
//        GeolocalizzazioneDTO geolocalizzazioneDTO = geolocalizzazioneMapper.toDto(geolocalizzazione);
//        restGeolocalizzazioneMockMvc.perform(post("/api/geolocalizzaziones")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(geolocalizzazioneDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Geolocalizzazione in the database
//        List<Geolocalizzazione> geolocalizzazioneList = geolocalizzazioneRepository.findAll();
//        assertThat(geolocalizzazioneList).hasSize(databaseSizeBeforeCreate + 1);
//        Geolocalizzazione testGeolocalizzazione = geolocalizzazioneList.get(geolocalizzazioneList.size() - 1);
//        assertThat(testGeolocalizzazione.getImmobile()).isEqualTo(DEFAULT_IMMOBILE);
//        assertThat(testGeolocalizzazione.getLatitudine()).isEqualTo(DEFAULT_LATITUDINE);
//        assertThat(testGeolocalizzazione.getLongitudine()).isEqualTo(DEFAULT_LONGITUDINE);
//    }
//
//    @Test
//    @Transactional
//    public void createGeolocalizzazioneWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = geolocalizzazioneRepository.findAll().size();
//
//        // Create the Geolocalizzazione with an existing ID
//        geolocalizzazione.setId(1L);
//        GeolocalizzazioneDTO geolocalizzazioneDTO = geolocalizzazioneMapper.toDto(geolocalizzazione);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restGeolocalizzazioneMockMvc.perform(post("/api/geolocalizzaziones")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(geolocalizzazioneDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Geolocalizzazione in the database
//        List<Geolocalizzazione> geolocalizzazioneList = geolocalizzazioneRepository.findAll();
//        assertThat(geolocalizzazioneList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzaziones() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList
//        restGeolocalizzazioneMockMvc.perform(get("/api/geolocalizzaziones?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(geolocalizzazione.getId().intValue())))
//            .andExpect(jsonPath("$.[*].immobile").value(hasItem(DEFAULT_IMMOBILE.toString())))
//            .andExpect(jsonPath("$.[*].latitudine").value(hasItem(DEFAULT_LATITUDINE.toString())))
//            .andExpect(jsonPath("$.[*].longitudine").value(hasItem(DEFAULT_LONGITUDINE.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getGeolocalizzazione() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get the geolocalizzazione
//        restGeolocalizzazioneMockMvc.perform(get("/api/geolocalizzaziones/{id}", geolocalizzazione.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(geolocalizzazione.getId().intValue()))
//            .andExpect(jsonPath("$.immobile").value(DEFAULT_IMMOBILE.toString()))
//            .andExpect(jsonPath("$.latitudine").value(DEFAULT_LATITUDINE.toString()))
//            .andExpect(jsonPath("$.longitudine").value(DEFAULT_LONGITUDINE.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByImmobileIsEqualToSomething() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where immobile equals to DEFAULT_IMMOBILE
//        defaultGeolocalizzazioneShouldBeFound("immobile.equals=" + DEFAULT_IMMOBILE);
//
//        // Get all the geolocalizzazioneList where immobile equals to UPDATED_IMMOBILE
//        defaultGeolocalizzazioneShouldNotBeFound("immobile.equals=" + UPDATED_IMMOBILE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByImmobileIsInShouldWork() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where immobile in DEFAULT_IMMOBILE or UPDATED_IMMOBILE
//        defaultGeolocalizzazioneShouldBeFound("immobile.in=" + DEFAULT_IMMOBILE + "," + UPDATED_IMMOBILE);
//
//        // Get all the geolocalizzazioneList where immobile equals to UPDATED_IMMOBILE
//        defaultGeolocalizzazioneShouldNotBeFound("immobile.in=" + UPDATED_IMMOBILE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByImmobileIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where immobile is not null
//        defaultGeolocalizzazioneShouldBeFound("immobile.specified=true");
//
//        // Get all the geolocalizzazioneList where immobile is null
//        defaultGeolocalizzazioneShouldNotBeFound("immobile.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByLatitudineIsEqualToSomething() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where latitudine equals to DEFAULT_LATITUDINE
//        defaultGeolocalizzazioneShouldBeFound("latitudine.equals=" + DEFAULT_LATITUDINE);
//
//        // Get all the geolocalizzazioneList where latitudine equals to UPDATED_LATITUDINE
//        defaultGeolocalizzazioneShouldNotBeFound("latitudine.equals=" + UPDATED_LATITUDINE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByLatitudineIsInShouldWork() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where latitudine in DEFAULT_LATITUDINE or UPDATED_LATITUDINE
//        defaultGeolocalizzazioneShouldBeFound("latitudine.in=" + DEFAULT_LATITUDINE + "," + UPDATED_LATITUDINE);
//
//        // Get all the geolocalizzazioneList where latitudine equals to UPDATED_LATITUDINE
//        defaultGeolocalizzazioneShouldNotBeFound("latitudine.in=" + UPDATED_LATITUDINE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByLatitudineIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where latitudine is not null
//        defaultGeolocalizzazioneShouldBeFound("latitudine.specified=true");
//
//        // Get all the geolocalizzazioneList where latitudine is null
//        defaultGeolocalizzazioneShouldNotBeFound("latitudine.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByLongitudineIsEqualToSomething() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where longitudine equals to DEFAULT_LONGITUDINE
//        defaultGeolocalizzazioneShouldBeFound("longitudine.equals=" + DEFAULT_LONGITUDINE);
//
//        // Get all the geolocalizzazioneList where longitudine equals to UPDATED_LONGITUDINE
//        defaultGeolocalizzazioneShouldNotBeFound("longitudine.equals=" + UPDATED_LONGITUDINE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByLongitudineIsInShouldWork() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where longitudine in DEFAULT_LONGITUDINE or UPDATED_LONGITUDINE
//        defaultGeolocalizzazioneShouldBeFound("longitudine.in=" + DEFAULT_LONGITUDINE + "," + UPDATED_LONGITUDINE);
//
//        // Get all the geolocalizzazioneList where longitudine equals to UPDATED_LONGITUDINE
//        defaultGeolocalizzazioneShouldNotBeFound("longitudine.in=" + UPDATED_LONGITUDINE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByLongitudineIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        // Get all the geolocalizzazioneList where longitudine is not null
//        defaultGeolocalizzazioneShouldBeFound("longitudine.specified=true");
//
//        // Get all the geolocalizzazioneList where longitudine is null
//        defaultGeolocalizzazioneShouldNotBeFound("longitudine.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllGeolocalizzazionesByPosizioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Immobile posizione = ImmobileResourceIntTest.createEntity(em);
//        em.persist(posizione);
//        em.flush();
//        geolocalizzazione.setPosizione(posizione);
//        posizione.setGeolocalizzazione(geolocalizzazione);
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//        Long posizioneId = posizione.getId();
//
//        // Get all the geolocalizzazioneList where posizione equals to posizioneId
//        defaultGeolocalizzazioneShouldBeFound("posizioneId.equals=" + posizioneId);
//
//        // Get all the geolocalizzazioneList where posizione equals to posizioneId + 1
//        defaultGeolocalizzazioneShouldNotBeFound("posizioneId.equals=" + (posizioneId + 1));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is returned
//     */
//    private void defaultGeolocalizzazioneShouldBeFound(String filter) throws Exception {
//        restGeolocalizzazioneMockMvc.perform(get("/api/geolocalizzaziones?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(geolocalizzazione.getId().intValue())))
//            .andExpect(jsonPath("$.[*].immobile").value(hasItem(DEFAULT_IMMOBILE.toString())))
//            .andExpect(jsonPath("$.[*].latitudine").value(hasItem(DEFAULT_LATITUDINE.toString())))
//            .andExpect(jsonPath("$.[*].longitudine").value(hasItem(DEFAULT_LONGITUDINE.toString())));
//
//        // Check, that the count call also returns 1
//        restGeolocalizzazioneMockMvc.perform(get("/api/geolocalizzaziones/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("1"));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is not returned
//     */
//    private void defaultGeolocalizzazioneShouldNotBeFound(String filter) throws Exception {
//        restGeolocalizzazioneMockMvc.perform(get("/api/geolocalizzaziones?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$").isArray())
//            .andExpect(jsonPath("$").isEmpty());
//
//        // Check, that the count call also returns 0
//        restGeolocalizzazioneMockMvc.perform(get("/api/geolocalizzaziones/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("0"));
//    }
//
//
//    @Test
//    @Transactional
//    public void getNonExistingGeolocalizzazione() throws Exception {
//        // Get the geolocalizzazione
//        restGeolocalizzazioneMockMvc.perform(get("/api/geolocalizzaziones/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateGeolocalizzazione() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        int databaseSizeBeforeUpdate = geolocalizzazioneRepository.findAll().size();
//
//        // Update the geolocalizzazione
//        Geolocalizzazione updatedGeolocalizzazione = geolocalizzazioneRepository.findById(geolocalizzazione.getId()).get();
//        // Disconnect from session so that the updates on updatedGeolocalizzazione are not directly saved in db
//        em.detach(updatedGeolocalizzazione);
//        updatedGeolocalizzazione
//            .immobile(UPDATED_IMMOBILE)
//            .latitudine(UPDATED_LATITUDINE)
//            .longitudine(UPDATED_LONGITUDINE);
//        GeolocalizzazioneDTO geolocalizzazioneDTO = geolocalizzazioneMapper.toDto(updatedGeolocalizzazione);
//
//        restGeolocalizzazioneMockMvc.perform(put("/api/geolocalizzaziones")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(geolocalizzazioneDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Geolocalizzazione in the database
//        List<Geolocalizzazione> geolocalizzazioneList = geolocalizzazioneRepository.findAll();
//        assertThat(geolocalizzazioneList).hasSize(databaseSizeBeforeUpdate);
//        Geolocalizzazione testGeolocalizzazione = geolocalizzazioneList.get(geolocalizzazioneList.size() - 1);
//        assertThat(testGeolocalizzazione.getImmobile()).isEqualTo(UPDATED_IMMOBILE);
//        assertThat(testGeolocalizzazione.getLatitudine()).isEqualTo(UPDATED_LATITUDINE);
//        assertThat(testGeolocalizzazione.getLongitudine()).isEqualTo(UPDATED_LONGITUDINE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingGeolocalizzazione() throws Exception {
//        int databaseSizeBeforeUpdate = geolocalizzazioneRepository.findAll().size();
//
//        // Create the Geolocalizzazione
//        GeolocalizzazioneDTO geolocalizzazioneDTO = geolocalizzazioneMapper.toDto(geolocalizzazione);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restGeolocalizzazioneMockMvc.perform(put("/api/geolocalizzaziones")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(geolocalizzazioneDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Geolocalizzazione in the database
//        List<Geolocalizzazione> geolocalizzazioneList = geolocalizzazioneRepository.findAll();
//        assertThat(geolocalizzazioneList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteGeolocalizzazione() throws Exception {
//        // Initialize the database
//        geolocalizzazioneRepository.saveAndFlush(geolocalizzazione);
//
//        int databaseSizeBeforeDelete = geolocalizzazioneRepository.findAll().size();
//
//        // Get the geolocalizzazione
//        restGeolocalizzazioneMockMvc.perform(delete("/api/geolocalizzaziones/{id}", geolocalizzazione.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Geolocalizzazione> geolocalizzazioneList = geolocalizzazioneRepository.findAll();
//        assertThat(geolocalizzazioneList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Geolocalizzazione.class);
//        Geolocalizzazione geolocalizzazione1 = new Geolocalizzazione();
//        geolocalizzazione1.setId(1L);
//        Geolocalizzazione geolocalizzazione2 = new Geolocalizzazione();
//        geolocalizzazione2.setId(geolocalizzazione1.getId());
//        assertThat(geolocalizzazione1).isEqualTo(geolocalizzazione2);
//        geolocalizzazione2.setId(2L);
//        assertThat(geolocalizzazione1).isNotEqualTo(geolocalizzazione2);
//        geolocalizzazione1.setId(null);
//        assertThat(geolocalizzazione1).isNotEqualTo(geolocalizzazione2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(GeolocalizzazioneDTO.class);
//        GeolocalizzazioneDTO geolocalizzazioneDTO1 = new GeolocalizzazioneDTO();
//        geolocalizzazioneDTO1.setId(1L);
//        GeolocalizzazioneDTO geolocalizzazioneDTO2 = new GeolocalizzazioneDTO();
//        assertThat(geolocalizzazioneDTO1).isNotEqualTo(geolocalizzazioneDTO2);
//        geolocalizzazioneDTO2.setId(geolocalizzazioneDTO1.getId());
//        assertThat(geolocalizzazioneDTO1).isEqualTo(geolocalizzazioneDTO2);
//        geolocalizzazioneDTO2.setId(2L);
//        assertThat(geolocalizzazioneDTO1).isNotEqualTo(geolocalizzazioneDTO2);
//        geolocalizzazioneDTO1.setId(null);
//        assertThat(geolocalizzazioneDTO1).isNotEqualTo(geolocalizzazioneDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(geolocalizzazioneMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(geolocalizzazioneMapper.fromId(null)).isNull();
//    }
}
