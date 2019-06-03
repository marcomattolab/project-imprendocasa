package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Files;
import it.arancia.domain.Immobile;
import it.arancia.repository.FilesRepository;
import it.arancia.service.FilesService;
import it.arancia.service.dto.FilesDTO;
import it.arancia.service.mapper.FilesMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.FilesCriteria;
import it.arancia.service.FilesQueryService;

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


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FilesResource REST controller.
 *
 * @see FilesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class FilesResourceIntTest {

//    private static final String DEFAULT_NOME = "AAAAAAAAAA";
//    private static final String UPDATED_NOME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DIMENSIONE = "AAAAAAAAAA";
//    private static final String UPDATED_DIMENSIONE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ESTENSIONE = "AAAAAAAAAA";
//    private static final String UPDATED_ESTENSIONE = "BBBBBBBBBB";
//
//    @Autowired
//    private FilesRepository filesRepository;
//
//    @Autowired
//    private FilesMapper filesMapper;
//
//    @Autowired
//    private FilesService filesService;
//
//    @Autowired
//    private FilesQueryService filesQueryService;
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
//    private MockMvc restFilesMockMvc;
//
//    private Files files;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final FilesResource filesResource = new FilesResource(filesService, filesQueryService);
//        this.restFilesMockMvc = MockMvcBuilders.standaloneSetup(filesResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(TestUtil.createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Files createEntity(EntityManager em) {
//        Files files = new Files()
//            .nome(DEFAULT_NOME)
//            .dimensione(DEFAULT_DIMENSIONE)
//            .estensione(DEFAULT_ESTENSIONE);
//        return files;
//    }
//
//    @Before
//    public void initTest() {
//        files = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createFiles() throws Exception {
//        int databaseSizeBeforeCreate = filesRepository.findAll().size();
//
//        // Create the Files
//        FilesDTO filesDTO = filesMapper.toDto(files);
//        restFilesMockMvc.perform(post("/api/files")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Files in the database
//        List<Files> filesList = filesRepository.findAll();
//        assertThat(filesList).hasSize(databaseSizeBeforeCreate + 1);
//        Files testFiles = filesList.get(filesList.size() - 1);
//        assertThat(testFiles.getNome()).isEqualTo(DEFAULT_NOME);
//        assertThat(testFiles.getDimensione()).isEqualTo(DEFAULT_DIMENSIONE);
//        assertThat(testFiles.getEstensione()).isEqualTo(DEFAULT_ESTENSIONE);
//    }
//
//    @Test
//    @Transactional
//    public void createFilesWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = filesRepository.findAll().size();
//
//        // Create the Files with an existing ID
//        files.setId(1L);
//        FilesDTO filesDTO = filesMapper.toDto(files);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restFilesMockMvc.perform(post("/api/files")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Files in the database
//        List<Files> filesList = filesRepository.findAll();
//        assertThat(filesList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void getAllFiles() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList
//        restFilesMockMvc.perform(get("/api/files?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(files.getId().intValue())))
//            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
//            .andExpect(jsonPath("$.[*].dimensione").value(hasItem(DEFAULT_DIMENSIONE.toString())))
//            .andExpect(jsonPath("$.[*].estensione").value(hasItem(DEFAULT_ESTENSIONE.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getFiles() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get the files
//        restFilesMockMvc.perform(get("/api/files/{id}", files.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(files.getId().intValue()))
//            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
//            .andExpect(jsonPath("$.dimensione").value(DEFAULT_DIMENSIONE.toString()))
//            .andExpect(jsonPath("$.estensione").value(DEFAULT_ESTENSIONE.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByNomeIsEqualToSomething() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where nome equals to DEFAULT_NOME
//        defaultFilesShouldBeFound("nome.equals=" + DEFAULT_NOME);
//
//        // Get all the filesList where nome equals to UPDATED_NOME
//        defaultFilesShouldNotBeFound("nome.equals=" + UPDATED_NOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByNomeIsInShouldWork() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where nome in DEFAULT_NOME or UPDATED_NOME
//        defaultFilesShouldBeFound("nome.in=" + DEFAULT_NOME + "," + UPDATED_NOME);
//
//        // Get all the filesList where nome equals to UPDATED_NOME
//        defaultFilesShouldNotBeFound("nome.in=" + UPDATED_NOME);
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByNomeIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where nome is not null
//        defaultFilesShouldBeFound("nome.specified=true");
//
//        // Get all the filesList where nome is null
//        defaultFilesShouldNotBeFound("nome.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByDimensioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where dimensione equals to DEFAULT_DIMENSIONE
//        defaultFilesShouldBeFound("dimensione.equals=" + DEFAULT_DIMENSIONE);
//
//        // Get all the filesList where dimensione equals to UPDATED_DIMENSIONE
//        defaultFilesShouldNotBeFound("dimensione.equals=" + UPDATED_DIMENSIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByDimensioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where dimensione in DEFAULT_DIMENSIONE or UPDATED_DIMENSIONE
//        defaultFilesShouldBeFound("dimensione.in=" + DEFAULT_DIMENSIONE + "," + UPDATED_DIMENSIONE);
//
//        // Get all the filesList where dimensione equals to UPDATED_DIMENSIONE
//        defaultFilesShouldNotBeFound("dimensione.in=" + UPDATED_DIMENSIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByDimensioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where dimensione is not null
//        defaultFilesShouldBeFound("dimensione.specified=true");
//
//        // Get all the filesList where dimensione is null
//        defaultFilesShouldNotBeFound("dimensione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByEstensioneIsEqualToSomething() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where estensione equals to DEFAULT_ESTENSIONE
//        defaultFilesShouldBeFound("estensione.equals=" + DEFAULT_ESTENSIONE);
//
//        // Get all the filesList where estensione equals to UPDATED_ESTENSIONE
//        defaultFilesShouldNotBeFound("estensione.equals=" + UPDATED_ESTENSIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByEstensioneIsInShouldWork() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where estensione in DEFAULT_ESTENSIONE or UPDATED_ESTENSIONE
//        defaultFilesShouldBeFound("estensione.in=" + DEFAULT_ESTENSIONE + "," + UPDATED_ESTENSIONE);
//
//        // Get all the filesList where estensione equals to UPDATED_ESTENSIONE
//        defaultFilesShouldNotBeFound("estensione.in=" + UPDATED_ESTENSIONE);
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByEstensioneIsNullOrNotNull() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        // Get all the filesList where estensione is not null
//        defaultFilesShouldBeFound("estensione.specified=true");
//
//        // Get all the filesList where estensione is null
//        defaultFilesShouldNotBeFound("estensione.specified=false");
//    }
//
//    @Test
//    @Transactional
//    public void getAllFilesByImmobileIsEqualToSomething() throws Exception {
//        // Initialize the database
//        Immobile immobile = ImmobileResourceIntTest.createEntity(em);
//        em.persist(immobile);
//        em.flush();
//        files.setImmobile(immobile);
//        filesRepository.saveAndFlush(files);
//        Long immobileId = immobile.getId();
//
//        // Get all the filesList where immobile equals to immobileId
//        defaultFilesShouldBeFound("immobileId.equals=" + immobileId);
//
//        // Get all the filesList where immobile equals to immobileId + 1
//        defaultFilesShouldNotBeFound("immobileId.equals=" + (immobileId + 1));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is returned
//     */
//    private void defaultFilesShouldBeFound(String filter) throws Exception {
//        restFilesMockMvc.perform(get("/api/files?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(files.getId().intValue())))
//            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
//            .andExpect(jsonPath("$.[*].dimensione").value(hasItem(DEFAULT_DIMENSIONE.toString())))
//            .andExpect(jsonPath("$.[*].estensione").value(hasItem(DEFAULT_ESTENSIONE.toString())));
//
//        // Check, that the count call also returns 1
//        restFilesMockMvc.perform(get("/api/files/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("1"));
//    }
//
//    /**
//     * Executes the search, and checks that the default entity is not returned
//     */
//    private void defaultFilesShouldNotBeFound(String filter) throws Exception {
//        restFilesMockMvc.perform(get("/api/files?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$").isArray())
//            .andExpect(jsonPath("$").isEmpty());
//
//        // Check, that the count call also returns 0
//        restFilesMockMvc.perform(get("/api/files/count?sort=id,desc&" + filter))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(content().string("0"));
//    }
//
//
//    @Test
//    @Transactional
//    public void getNonExistingFiles() throws Exception {
//        // Get the files
//        restFilesMockMvc.perform(get("/api/files/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateFiles() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        int databaseSizeBeforeUpdate = filesRepository.findAll().size();
//
//        // Update the files
//        Files updatedFiles = filesRepository.findById(files.getId()).get();
//        // Disconnect from session so that the updates on updatedFiles are not directly saved in db
//        em.detach(updatedFiles);
//        updatedFiles
//            .nome(UPDATED_NOME)
//            .dimensione(UPDATED_DIMENSIONE)
//            .estensione(UPDATED_ESTENSIONE);
//        FilesDTO filesDTO = filesMapper.toDto(updatedFiles);
//
//        restFilesMockMvc.perform(put("/api/files")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Files in the database
//        List<Files> filesList = filesRepository.findAll();
//        assertThat(filesList).hasSize(databaseSizeBeforeUpdate);
//        Files testFiles = filesList.get(filesList.size() - 1);
//        assertThat(testFiles.getNome()).isEqualTo(UPDATED_NOME);
//        assertThat(testFiles.getDimensione()).isEqualTo(UPDATED_DIMENSIONE);
//        assertThat(testFiles.getEstensione()).isEqualTo(UPDATED_ESTENSIONE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingFiles() throws Exception {
//        int databaseSizeBeforeUpdate = filesRepository.findAll().size();
//
//        // Create the Files
//        FilesDTO filesDTO = filesMapper.toDto(files);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restFilesMockMvc.perform(put("/api/files")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Files in the database
//        List<Files> filesList = filesRepository.findAll();
//        assertThat(filesList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteFiles() throws Exception {
//        // Initialize the database
//        filesRepository.saveAndFlush(files);
//
//        int databaseSizeBeforeDelete = filesRepository.findAll().size();
//
//        // Get the files
//        restFilesMockMvc.perform(delete("/api/files/{id}", files.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Files> filesList = filesRepository.findAll();
//        assertThat(filesList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Files.class);
//        Files files1 = new Files();
//        files1.setId(1L);
//        Files files2 = new Files();
//        files2.setId(files1.getId());
//        assertThat(files1).isEqualTo(files2);
//        files2.setId(2L);
//        assertThat(files1).isNotEqualTo(files2);
//        files1.setId(null);
//        assertThat(files1).isNotEqualTo(files2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(FilesDTO.class);
//        FilesDTO filesDTO1 = new FilesDTO();
//        filesDTO1.setId(1L);
//        FilesDTO filesDTO2 = new FilesDTO();
//        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
//        filesDTO2.setId(filesDTO1.getId());
//        assertThat(filesDTO1).isEqualTo(filesDTO2);
//        filesDTO2.setId(2L);
//        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
//        filesDTO1.setId(null);
//        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(filesMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(filesMapper.fromId(null)).isNull();
//    }
}
