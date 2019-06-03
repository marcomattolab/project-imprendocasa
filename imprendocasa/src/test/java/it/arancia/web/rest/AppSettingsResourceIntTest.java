package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.AppSettings;
import it.arancia.repository.AppSettingsRepository;
import it.arancia.service.AppSettingsService;
import it.arancia.service.dto.AppSettingsDTO;
import it.arancia.service.mapper.AppSettingsMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.AppSettingsCriteria;
import it.arancia.service.AppSettingsQueryService;

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
 * Test class for the AppSettingsResource REST controller.
 *
 * @see AppSettingsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class AppSettingsResourceIntTest {

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_CHIAVE = "AAAAAAAAAA";
    private static final String UPDATED_CHIAVE = "BBBBBBBBBB";

    private static final String DEFAULT_VALORE = "AAAAAAAAAA";
    private static final String UPDATED_VALORE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ABILITATO = false;
    private static final Boolean UPDATED_ABILITATO = true;

    @Autowired
    private AppSettingsRepository appSettingsRepository;

    @Autowired
    private AppSettingsMapper appSettingsMapper;

    @Autowired
    private AppSettingsService appSettingsService;

    @Autowired
    private AppSettingsQueryService appSettingsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAppSettingsMockMvc;

    private AppSettings appSettings;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppSettingsResource appSettingsResource = new AppSettingsResource(appSettingsService, appSettingsQueryService);
        this.restAppSettingsMockMvc = MockMvcBuilders.standaloneSetup(appSettingsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppSettings createEntity(EntityManager em) {
        AppSettings appSettings = new AppSettings()
            .categoria(DEFAULT_CATEGORIA)
            .chiave(DEFAULT_CHIAVE)
            .valore(DEFAULT_VALORE)
            .abilitato(DEFAULT_ABILITATO);
        return appSettings;
    }

    @Before
    public void initTest() {
        appSettings = createEntity(em);
    }

//    @Test
//    @Transactional
//    public void createAppSettings() throws Exception {
//        int databaseSizeBeforeCreate = appSettingsRepository.findAll().size();
//
//        // Create the AppSettings
//        AppSettingsDTO appSettingsDTO = appSettingsMapper.toDto(appSettings);
//        restAppSettingsMockMvc.perform(post("/api/app-settings")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(appSettingsDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the AppSettings in the database
//        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
//        assertThat(appSettingsList).hasSize(databaseSizeBeforeCreate + 1);
//        AppSettings testAppSettings = appSettingsList.get(appSettingsList.size() - 1);
//        assertThat(testAppSettings.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
//        assertThat(testAppSettings.getChiave()).isEqualTo(DEFAULT_CHIAVE);
//        assertThat(testAppSettings.getValore()).isEqualTo(DEFAULT_VALORE);
//        assertThat(testAppSettings.isAbilitato()).isEqualTo(DEFAULT_ABILITATO);
//    }

//    @Test
//    @Transactional
//    public void createAppSettingsWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = appSettingsRepository.findAll().size();
//
//        // Create the AppSettings with an existing ID
//        appSettings.setId(1L);
//        AppSettingsDTO appSettingsDTO = appSettingsMapper.toDto(appSettings);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restAppSettingsMockMvc.perform(post("/api/app-settings")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(appSettingsDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the AppSettings in the database
//        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
//        assertThat(appSettingsList).hasSize(databaseSizeBeforeCreate);
//    }

//    @Test
//    @Transactional
//    public void checkCategoriaIsRequired() throws Exception {
//        int databaseSizeBeforeTest = appSettingsRepository.findAll().size();
//        // set the field null
//        appSettings.setCategoria(null);
//
//        // Create the AppSettings, which fails.
//        AppSettingsDTO appSettingsDTO = appSettingsMapper.toDto(appSettings);
//
//        restAppSettingsMockMvc.perform(post("/api/app-settings")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(appSettingsDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
//        assertThat(appSettingsList).hasSize(databaseSizeBeforeTest);
//    }

//    @Test
//    @Transactional
//    public void checkChiaveIsRequired() throws Exception {
//        int databaseSizeBeforeTest = appSettingsRepository.findAll().size();
//        // set the field null
//        appSettings.setChiave(null);
//
//        // Create the AppSettings, which fails.
//        AppSettingsDTO appSettingsDTO = appSettingsMapper.toDto(appSettings);
//
//        restAppSettingsMockMvc.perform(post("/api/app-settings")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(appSettingsDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
//        assertThat(appSettingsList).hasSize(databaseSizeBeforeTest);
//    }

//    @Test
//    @Transactional
//    public void checkValoreIsRequired() throws Exception {
//        int databaseSizeBeforeTest = appSettingsRepository.findAll().size();
//        // set the field null
//        appSettings.setValore(null);
//
//        // Create the AppSettings, which fails.
//        AppSettingsDTO appSettingsDTO = appSettingsMapper.toDto(appSettings);
//
//        restAppSettingsMockMvc.perform(post("/api/app-settings")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(appSettingsDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
//        assertThat(appSettingsList).hasSize(databaseSizeBeforeTest);
//    }

    @Test
    @Transactional
    public void getAllAppSettings() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList
        restAppSettingsMockMvc.perform(get("/api/app-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].chiave").value(hasItem(DEFAULT_CHIAVE.toString())))
            .andExpect(jsonPath("$.[*].valore").value(hasItem(DEFAULT_VALORE.toString())))
            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAppSettings() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get the appSettings
        restAppSettingsMockMvc.perform(get("/api/app-settings/{id}", appSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(appSettings.getId().intValue()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.chiave").value(DEFAULT_CHIAVE.toString()))
            .andExpect(jsonPath("$.valore").value(DEFAULT_VALORE.toString()))
            .andExpect(jsonPath("$.abilitato").value(DEFAULT_ABILITATO.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllAppSettingsByCategoriaIsEqualToSomething() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where categoria equals to DEFAULT_CATEGORIA
        defaultAppSettingsShouldBeFound("categoria.equals=" + DEFAULT_CATEGORIA);

        // Get all the appSettingsList where categoria equals to UPDATED_CATEGORIA
        defaultAppSettingsShouldNotBeFound("categoria.equals=" + UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByCategoriaIsInShouldWork() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where categoria in DEFAULT_CATEGORIA or UPDATED_CATEGORIA
        defaultAppSettingsShouldBeFound("categoria.in=" + DEFAULT_CATEGORIA + "," + UPDATED_CATEGORIA);

        // Get all the appSettingsList where categoria equals to UPDATED_CATEGORIA
        defaultAppSettingsShouldNotBeFound("categoria.in=" + UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByCategoriaIsNullOrNotNull() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where categoria is not null
        defaultAppSettingsShouldBeFound("categoria.specified=true");

        // Get all the appSettingsList where categoria is null
        defaultAppSettingsShouldNotBeFound("categoria.specified=false");
    }

    @Test
    @Transactional
    public void getAllAppSettingsByChiaveIsEqualToSomething() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where chiave equals to DEFAULT_CHIAVE
        defaultAppSettingsShouldBeFound("chiave.equals=" + DEFAULT_CHIAVE);

        // Get all the appSettingsList where chiave equals to UPDATED_CHIAVE
        defaultAppSettingsShouldNotBeFound("chiave.equals=" + UPDATED_CHIAVE);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByChiaveIsInShouldWork() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where chiave in DEFAULT_CHIAVE or UPDATED_CHIAVE
        defaultAppSettingsShouldBeFound("chiave.in=" + DEFAULT_CHIAVE + "," + UPDATED_CHIAVE);

        // Get all the appSettingsList where chiave equals to UPDATED_CHIAVE
        defaultAppSettingsShouldNotBeFound("chiave.in=" + UPDATED_CHIAVE);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByChiaveIsNullOrNotNull() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where chiave is not null
        defaultAppSettingsShouldBeFound("chiave.specified=true");

        // Get all the appSettingsList where chiave is null
        defaultAppSettingsShouldNotBeFound("chiave.specified=false");
    }

    @Test
    @Transactional
    public void getAllAppSettingsByValoreIsEqualToSomething() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where valore equals to DEFAULT_VALORE
        defaultAppSettingsShouldBeFound("valore.equals=" + DEFAULT_VALORE);

        // Get all the appSettingsList where valore equals to UPDATED_VALORE
        defaultAppSettingsShouldNotBeFound("valore.equals=" + UPDATED_VALORE);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByValoreIsInShouldWork() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where valore in DEFAULT_VALORE or UPDATED_VALORE
        defaultAppSettingsShouldBeFound("valore.in=" + DEFAULT_VALORE + "," + UPDATED_VALORE);

        // Get all the appSettingsList where valore equals to UPDATED_VALORE
        defaultAppSettingsShouldNotBeFound("valore.in=" + UPDATED_VALORE);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByValoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where valore is not null
        defaultAppSettingsShouldBeFound("valore.specified=true");

        // Get all the appSettingsList where valore is null
        defaultAppSettingsShouldNotBeFound("valore.specified=false");
    }

    @Test
    @Transactional
    public void getAllAppSettingsByAbilitatoIsEqualToSomething() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where abilitato equals to DEFAULT_ABILITATO
        defaultAppSettingsShouldBeFound("abilitato.equals=" + DEFAULT_ABILITATO);

        // Get all the appSettingsList where abilitato equals to UPDATED_ABILITATO
        defaultAppSettingsShouldNotBeFound("abilitato.equals=" + UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByAbilitatoIsInShouldWork() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where abilitato in DEFAULT_ABILITATO or UPDATED_ABILITATO
        defaultAppSettingsShouldBeFound("abilitato.in=" + DEFAULT_ABILITATO + "," + UPDATED_ABILITATO);

        // Get all the appSettingsList where abilitato equals to UPDATED_ABILITATO
        defaultAppSettingsShouldNotBeFound("abilitato.in=" + UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void getAllAppSettingsByAbilitatoIsNullOrNotNull() throws Exception {
        // Initialize the database
        appSettingsRepository.saveAndFlush(appSettings);

        // Get all the appSettingsList where abilitato is not null
        defaultAppSettingsShouldBeFound("abilitato.specified=true");

        // Get all the appSettingsList where abilitato is null
        defaultAppSettingsShouldNotBeFound("abilitato.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAppSettingsShouldBeFound(String filter) throws Exception {
        restAppSettingsMockMvc.perform(get("/api/app-settings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].chiave").value(hasItem(DEFAULT_CHIAVE.toString())))
            .andExpect(jsonPath("$.[*].valore").value(hasItem(DEFAULT_VALORE.toString())))
            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));

        // Check, that the count call also returns 1
        restAppSettingsMockMvc.perform(get("/api/app-settings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAppSettingsShouldNotBeFound(String filter) throws Exception {
        restAppSettingsMockMvc.perform(get("/api/app-settings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAppSettingsMockMvc.perform(get("/api/app-settings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAppSettings() throws Exception {
        // Get the appSettings
        restAppSettingsMockMvc.perform(get("/api/app-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

//    @Test
//    @Transactional
//    public void updateAppSettings() throws Exception {
//        // Initialize the database
//        appSettingsRepository.saveAndFlush(appSettings);
//
//        int databaseSizeBeforeUpdate = appSettingsRepository.findAll().size();
//
//        // Update the appSettings
//        AppSettings updatedAppSettings = appSettingsRepository.findById(appSettings.getId()).get();
//        // Disconnect from session so that the updates on updatedAppSettings are not directly saved in db
//        em.detach(updatedAppSettings);
//        updatedAppSettings
//            .categoria(UPDATED_CATEGORIA)
//            .chiave(UPDATED_CHIAVE)
//            .valore(UPDATED_VALORE)
//            .abilitato(UPDATED_ABILITATO);
//        AppSettingsDTO appSettingsDTO = appSettingsMapper.toDto(updatedAppSettings);
//
//        restAppSettingsMockMvc.perform(put("/api/app-settings")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(appSettingsDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the AppSettings in the database
//        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
//        assertThat(appSettingsList).hasSize(databaseSizeBeforeUpdate);
//        AppSettings testAppSettings = appSettingsList.get(appSettingsList.size() - 1);
//        assertThat(testAppSettings.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
//        assertThat(testAppSettings.getChiave()).isEqualTo(UPDATED_CHIAVE);
//        assertThat(testAppSettings.getValore()).isEqualTo(UPDATED_VALORE);
//        assertThat(testAppSettings.isAbilitato()).isEqualTo(UPDATED_ABILITATO);
//    }

//    @Test
//    @Transactional
//    public void updateNonExistingAppSettings() throws Exception {
//        int databaseSizeBeforeUpdate = appSettingsRepository.findAll().size();
//
//        // Create the AppSettings
//        AppSettingsDTO appSettingsDTO = appSettingsMapper.toDto(appSettings);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restAppSettingsMockMvc.perform(put("/api/app-settings")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(appSettingsDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the AppSettings in the database
//        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
//        assertThat(appSettingsList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteAppSettings() throws Exception {
//        // Initialize the database
//        appSettingsRepository.saveAndFlush(appSettings);
//
//        int databaseSizeBeforeDelete = appSettingsRepository.findAll().size();
//
//        // Get the appSettings
//        restAppSettingsMockMvc.perform(delete("/api/app-settings/{id}", appSettings.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<AppSettings> appSettingsList = appSettingsRepository.findAll();
//        assertThat(appSettingsList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(AppSettings.class);
//        AppSettings appSettings1 = new AppSettings();
//        appSettings1.setId(1L);
//        AppSettings appSettings2 = new AppSettings();
//        appSettings2.setId(appSettings1.getId());
//        assertThat(appSettings1).isEqualTo(appSettings2);
//        appSettings2.setId(2L);
//        assertThat(appSettings1).isNotEqualTo(appSettings2);
//        appSettings1.setId(null);
//        assertThat(appSettings1).isNotEqualTo(appSettings2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(AppSettingsDTO.class);
//        AppSettingsDTO appSettingsDTO1 = new AppSettingsDTO();
//        appSettingsDTO1.setId(1L);
//        AppSettingsDTO appSettingsDTO2 = new AppSettingsDTO();
//        assertThat(appSettingsDTO1).isNotEqualTo(appSettingsDTO2);
//        appSettingsDTO2.setId(appSettingsDTO1.getId());
//        assertThat(appSettingsDTO1).isEqualTo(appSettingsDTO2);
//        appSettingsDTO2.setId(2L);
//        assertThat(appSettingsDTO1).isNotEqualTo(appSettingsDTO2);
//        appSettingsDTO1.setId(null);
//        assertThat(appSettingsDTO1).isNotEqualTo(appSettingsDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(appSettingsMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(appSettingsMapper.fromId(null)).isNull();
//    }
}
