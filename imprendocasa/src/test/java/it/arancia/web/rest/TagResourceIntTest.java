package it.arancia.web.rest;

import it.arancia.ImprendocasaApp;

import it.arancia.domain.Tag;
import it.arancia.repository.TagRepository;
import it.arancia.service.TagService;
import it.arancia.service.dto.TagDTO;
import it.arancia.service.mapper.TagMapper;
import it.arancia.web.rest.errors.ExceptionTranslator;
import it.arancia.service.dto.TagCriteria;
import it.arancia.service.TagQueryService;

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
 * Test class for the TagResource REST controller.
 *
 * @see TagResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprendocasaApp.class)
public class TagResourceIntTest {

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMINAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINAZIONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ABILITATO = false;
    private static final Boolean UPDATED_ABILITATO = true;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagQueryService tagQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTagMockMvc;

    private Tag tag;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TagResource tagResource = new TagResource(tagService, tagQueryService);
        this.restTagMockMvc = MockMvcBuilders.standaloneSetup(tagResource)
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
    public static Tag createEntity(EntityManager em) {
        Tag tag = new Tag()
            .codice(DEFAULT_CODICE)
            .denominazione(DEFAULT_DENOMINAZIONE)
            .abilitato(DEFAULT_ABILITATO);
        return tag;
    }

    @Before
    public void initTest() {
        tag = createEntity(em);
    }

    @Test
    @Transactional
    public void createTag() throws Exception {
        int databaseSizeBeforeCreate = tagRepository.findAll().size();

        // Create the Tag
        TagDTO tagDTO = tagMapper.toDto(tag);
        restTagMockMvc.perform(post("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagDTO)))
            .andExpect(status().isCreated());

        // Validate the Tag in the database
        List<Tag> tagList = tagRepository.findAll();
        assertThat(tagList).hasSize(databaseSizeBeforeCreate + 1);
        Tag testTag = tagList.get(tagList.size() - 1);
        assertThat(testTag.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testTag.getDenominazione()).isEqualTo(DEFAULT_DENOMINAZIONE);
        assertThat(testTag.isAbilitato()).isEqualTo(DEFAULT_ABILITATO);
    }

    @Test
    @Transactional
    public void createTagWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tagRepository.findAll().size();

        // Create the Tag with an existing ID
        tag.setId(1L);
        TagDTO tagDTO = tagMapper.toDto(tag);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagMockMvc.perform(post("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tag in the database
        List<Tag> tagList = tagRepository.findAll();
        assertThat(tagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodiceIsRequired() throws Exception {
        int databaseSizeBeforeTest = tagRepository.findAll().size();
        // set the field null
        tag.setCodice(null);

        // Create the Tag, which fails.
        TagDTO tagDTO = tagMapper.toDto(tag);

        restTagMockMvc.perform(post("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagDTO)))
            .andExpect(status().isBadRequest());

        List<Tag> tagList = tagRepository.findAll();
        assertThat(tagList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDenominazioneIsRequired() throws Exception {
        int databaseSizeBeforeTest = tagRepository.findAll().size();
        // set the field null
        tag.setDenominazione(null);

        // Create the Tag, which fails.
        TagDTO tagDTO = tagMapper.toDto(tag);

        restTagMockMvc.perform(post("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagDTO)))
            .andExpect(status().isBadRequest());

        List<Tag> tagList = tagRepository.findAll();
        assertThat(tagList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTags() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList
        restTagMockMvc.perform(get("/api/tags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tag.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].denominazione").value(hasItem(DEFAULT_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTag() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get the tag
        restTagMockMvc.perform(get("/api/tags/{id}", tag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tag.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.denominazione").value(DEFAULT_DENOMINAZIONE.toString()))
            .andExpect(jsonPath("$.abilitato").value(DEFAULT_ABILITATO.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllTagsByCodiceIsEqualToSomething() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where codice equals to DEFAULT_CODICE
        defaultTagShouldBeFound("codice.equals=" + DEFAULT_CODICE);

        // Get all the tagList where codice equals to UPDATED_CODICE
        defaultTagShouldNotBeFound("codice.equals=" + UPDATED_CODICE);
    }

    @Test
    @Transactional
    public void getAllTagsByCodiceIsInShouldWork() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where codice in DEFAULT_CODICE or UPDATED_CODICE
        defaultTagShouldBeFound("codice.in=" + DEFAULT_CODICE + "," + UPDATED_CODICE);

        // Get all the tagList where codice equals to UPDATED_CODICE
        defaultTagShouldNotBeFound("codice.in=" + UPDATED_CODICE);
    }

    @Test
    @Transactional
    public void getAllTagsByCodiceIsNullOrNotNull() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where codice is not null
        defaultTagShouldBeFound("codice.specified=true");

        // Get all the tagList where codice is null
        defaultTagShouldNotBeFound("codice.specified=false");
    }

    @Test
    @Transactional
    public void getAllTagsByDenominazioneIsEqualToSomething() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where denominazione equals to DEFAULT_DENOMINAZIONE
        defaultTagShouldBeFound("denominazione.equals=" + DEFAULT_DENOMINAZIONE);

        // Get all the tagList where denominazione equals to UPDATED_DENOMINAZIONE
        defaultTagShouldNotBeFound("denominazione.equals=" + UPDATED_DENOMINAZIONE);
    }

    @Test
    @Transactional
    public void getAllTagsByDenominazioneIsInShouldWork() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where denominazione in DEFAULT_DENOMINAZIONE or UPDATED_DENOMINAZIONE
        defaultTagShouldBeFound("denominazione.in=" + DEFAULT_DENOMINAZIONE + "," + UPDATED_DENOMINAZIONE);

        // Get all the tagList where denominazione equals to UPDATED_DENOMINAZIONE
        defaultTagShouldNotBeFound("denominazione.in=" + UPDATED_DENOMINAZIONE);
    }

    @Test
    @Transactional
    public void getAllTagsByDenominazioneIsNullOrNotNull() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where denominazione is not null
        defaultTagShouldBeFound("denominazione.specified=true");

        // Get all the tagList where denominazione is null
        defaultTagShouldNotBeFound("denominazione.specified=false");
    }

    @Test
    @Transactional
    public void getAllTagsByAbilitatoIsEqualToSomething() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where abilitato equals to DEFAULT_ABILITATO
        defaultTagShouldBeFound("abilitato.equals=" + DEFAULT_ABILITATO);

        // Get all the tagList where abilitato equals to UPDATED_ABILITATO
        defaultTagShouldNotBeFound("abilitato.equals=" + UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void getAllTagsByAbilitatoIsInShouldWork() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where abilitato in DEFAULT_ABILITATO or UPDATED_ABILITATO
        defaultTagShouldBeFound("abilitato.in=" + DEFAULT_ABILITATO + "," + UPDATED_ABILITATO);

        // Get all the tagList where abilitato equals to UPDATED_ABILITATO
        defaultTagShouldNotBeFound("abilitato.in=" + UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void getAllTagsByAbilitatoIsNullOrNotNull() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        // Get all the tagList where abilitato is not null
        defaultTagShouldBeFound("abilitato.specified=true");

        // Get all the tagList where abilitato is null
        defaultTagShouldNotBeFound("abilitato.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultTagShouldBeFound(String filter) throws Exception {
        restTagMockMvc.perform(get("/api/tags?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tag.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].denominazione").value(hasItem(DEFAULT_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].abilitato").value(hasItem(DEFAULT_ABILITATO.booleanValue())));

        // Check, that the count call also returns 1
        restTagMockMvc.perform(get("/api/tags/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultTagShouldNotBeFound(String filter) throws Exception {
        restTagMockMvc.perform(get("/api/tags?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTagMockMvc.perform(get("/api/tags/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTag() throws Exception {
        // Get the tag
        restTagMockMvc.perform(get("/api/tags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTag() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        int databaseSizeBeforeUpdate = tagRepository.findAll().size();

        // Update the tag
        Tag updatedTag = tagRepository.findById(tag.getId()).get();
        // Disconnect from session so that the updates on updatedTag are not directly saved in db
        em.detach(updatedTag);
        updatedTag
            .codice(UPDATED_CODICE)
            .denominazione(UPDATED_DENOMINAZIONE)
            .abilitato(UPDATED_ABILITATO);
        TagDTO tagDTO = tagMapper.toDto(updatedTag);

        restTagMockMvc.perform(put("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagDTO)))
            .andExpect(status().isOk());

        // Validate the Tag in the database
        List<Tag> tagList = tagRepository.findAll();
        assertThat(tagList).hasSize(databaseSizeBeforeUpdate);
        Tag testTag = tagList.get(tagList.size() - 1);
        assertThat(testTag.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testTag.getDenominazione()).isEqualTo(UPDATED_DENOMINAZIONE);
        assertThat(testTag.isAbilitato()).isEqualTo(UPDATED_ABILITATO);
    }

    @Test
    @Transactional
    public void updateNonExistingTag() throws Exception {
        int databaseSizeBeforeUpdate = tagRepository.findAll().size();

        // Create the Tag
        TagDTO tagDTO = tagMapper.toDto(tag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagMockMvc.perform(put("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tag in the database
        List<Tag> tagList = tagRepository.findAll();
        assertThat(tagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTag() throws Exception {
        // Initialize the database
        tagRepository.saveAndFlush(tag);

        int databaseSizeBeforeDelete = tagRepository.findAll().size();

        // Get the tag
        restTagMockMvc.perform(delete("/api/tags/{id}", tag.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tag> tagList = tagRepository.findAll();
        assertThat(tagList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tag.class);
        Tag tag1 = new Tag();
        tag1.setId(1L);
        Tag tag2 = new Tag();
        tag2.setId(tag1.getId());
        assertThat(tag1).isEqualTo(tag2);
        tag2.setId(2L);
        assertThat(tag1).isNotEqualTo(tag2);
        tag1.setId(null);
        assertThat(tag1).isNotEqualTo(tag2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagDTO.class);
        TagDTO tagDTO1 = new TagDTO();
        tagDTO1.setId(1L);
        TagDTO tagDTO2 = new TagDTO();
        assertThat(tagDTO1).isNotEqualTo(tagDTO2);
        tagDTO2.setId(tagDTO1.getId());
        assertThat(tagDTO1).isEqualTo(tagDTO2);
        tagDTO2.setId(2L);
        assertThat(tagDTO1).isNotEqualTo(tagDTO2);
        tagDTO1.setId(null);
        assertThat(tagDTO1).isNotEqualTo(tagDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tagMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tagMapper.fromId(null)).isNull();
    }
}
