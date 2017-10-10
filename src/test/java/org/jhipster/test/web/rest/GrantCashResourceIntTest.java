package org.jhipster.test.web.rest;

import org.jhipster.test.JhipsterApp;

import org.jhipster.test.domain.GrantCash;
import org.jhipster.test.repository.GrantCashRepository;
import org.jhipster.test.service.GrantCashService;
import org.jhipster.test.web.rest.errors.ExceptionTranslator;

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
 * Test class for the GrantCashResource REST controller.
 *
 * @see GrantCashResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class GrantCashResourceIntTest {

    private static final Long DEFAULT_PACK_ID = 1L;
    private static final Long UPDATED_PACK_ID = 2L;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Long DEFAULT_USE_TIME = 1L;
    private static final Long UPDATED_USE_TIME = 2L;

    private static final Long DEFAULT_GET_TIME = 1L;
    private static final Long UPDATED_GET_TIME = 2L;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_PACK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PACK_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PACK_PRICE = 1D;
    private static final Double UPDATED_PACK_PRICE = 2D;

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final Long DEFAULT_EXPIRY = 1L;
    private static final Long UPDATED_EXPIRY = 2L;

    @Autowired
    private GrantCashRepository grantCashRepository;

    @Autowired
    private GrantCashService grantCashService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGrantCashMockMvc;

    private GrantCash grantCash;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrantCashResource grantCashResource = new GrantCashResource(grantCashService);
        this.restGrantCashMockMvc = MockMvcBuilders.standaloneSetup(grantCashResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrantCash createEntity(EntityManager em) {
        GrantCash grantCash = new GrantCash()
            .packId(DEFAULT_PACK_ID)
            .userId(DEFAULT_USER_ID)
            .useTime(DEFAULT_USE_TIME)
            .getTime(DEFAULT_GET_TIME)
            .status(DEFAULT_STATUS)
            .packName(DEFAULT_PACK_NAME)
            .packPrice(DEFAULT_PACK_PRICE)
            .orderId(DEFAULT_ORDER_ID)
            .expiry(DEFAULT_EXPIRY);
        return grantCash;
    }

    @Before
    public void initTest() {
        grantCash = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrantCash() throws Exception {
        int databaseSizeBeforeCreate = grantCashRepository.findAll().size();

        // Create the GrantCash
        restGrantCashMockMvc.perform(post("/api/grant-cashes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grantCash)))
            .andExpect(status().isCreated());

        // Validate the GrantCash in the database
        List<GrantCash> grantCashList = grantCashRepository.findAll();
        assertThat(grantCashList).hasSize(databaseSizeBeforeCreate + 1);
        GrantCash testGrantCash = grantCashList.get(grantCashList.size() - 1);
        assertThat(testGrantCash.getPackId()).isEqualTo(DEFAULT_PACK_ID);
        assertThat(testGrantCash.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testGrantCash.getUseTime()).isEqualTo(DEFAULT_USE_TIME);
        assertThat(testGrantCash.getGetTime()).isEqualTo(DEFAULT_GET_TIME);
        assertThat(testGrantCash.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGrantCash.getPackName()).isEqualTo(DEFAULT_PACK_NAME);
        assertThat(testGrantCash.getPackPrice()).isEqualTo(DEFAULT_PACK_PRICE);
        assertThat(testGrantCash.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testGrantCash.getExpiry()).isEqualTo(DEFAULT_EXPIRY);
    }

    @Test
    @Transactional
    public void createGrantCashWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grantCashRepository.findAll().size();

        // Create the GrantCash with an existing ID
        grantCash.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrantCashMockMvc.perform(post("/api/grant-cashes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grantCash)))
            .andExpect(status().isBadRequest());

        // Validate the GrantCash in the database
        List<GrantCash> grantCashList = grantCashRepository.findAll();
        assertThat(grantCashList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGrantCashes() throws Exception {
        // Initialize the database
        grantCashRepository.saveAndFlush(grantCash);

        // Get all the grantCashList
        restGrantCashMockMvc.perform(get("/api/grant-cashes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grantCash.getId().intValue())))
            .andExpect(jsonPath("$.[*].packId").value(hasItem(DEFAULT_PACK_ID.intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].useTime").value(hasItem(DEFAULT_USE_TIME.intValue())))
            .andExpect(jsonPath("$.[*].getTime").value(hasItem(DEFAULT_GET_TIME.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].packName").value(hasItem(DEFAULT_PACK_NAME.toString())))
            .andExpect(jsonPath("$.[*].packPrice").value(hasItem(DEFAULT_PACK_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].expiry").value(hasItem(DEFAULT_EXPIRY.intValue())));
    }

    @Test
    @Transactional
    public void getGrantCash() throws Exception {
        // Initialize the database
        grantCashRepository.saveAndFlush(grantCash);

        // Get the grantCash
        restGrantCashMockMvc.perform(get("/api/grant-cashes/{id}", grantCash.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grantCash.getId().intValue()))
            .andExpect(jsonPath("$.packId").value(DEFAULT_PACK_ID.intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.useTime").value(DEFAULT_USE_TIME.intValue()))
            .andExpect(jsonPath("$.getTime").value(DEFAULT_GET_TIME.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.packName").value(DEFAULT_PACK_NAME.toString()))
            .andExpect(jsonPath("$.packPrice").value(DEFAULT_PACK_PRICE.doubleValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.expiry").value(DEFAULT_EXPIRY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGrantCash() throws Exception {
        // Get the grantCash
        restGrantCashMockMvc.perform(get("/api/grant-cashes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrantCash() throws Exception {
        // Initialize the database
        grantCashService.save(grantCash);

        int databaseSizeBeforeUpdate = grantCashRepository.findAll().size();

        // Update the grantCash
        GrantCash updatedGrantCash = grantCashRepository.findOne(grantCash.getId());
        updatedGrantCash
            .packId(UPDATED_PACK_ID)
            .userId(UPDATED_USER_ID)
            .useTime(UPDATED_USE_TIME)
            .getTime(UPDATED_GET_TIME)
            .status(UPDATED_STATUS)
            .packName(UPDATED_PACK_NAME)
            .packPrice(UPDATED_PACK_PRICE)
            .orderId(UPDATED_ORDER_ID)
            .expiry(UPDATED_EXPIRY);

        restGrantCashMockMvc.perform(put("/api/grant-cashes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGrantCash)))
            .andExpect(status().isOk());

        // Validate the GrantCash in the database
        List<GrantCash> grantCashList = grantCashRepository.findAll();
        assertThat(grantCashList).hasSize(databaseSizeBeforeUpdate);
        GrantCash testGrantCash = grantCashList.get(grantCashList.size() - 1);
        assertThat(testGrantCash.getPackId()).isEqualTo(UPDATED_PACK_ID);
        assertThat(testGrantCash.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testGrantCash.getUseTime()).isEqualTo(UPDATED_USE_TIME);
        assertThat(testGrantCash.getGetTime()).isEqualTo(UPDATED_GET_TIME);
        assertThat(testGrantCash.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGrantCash.getPackName()).isEqualTo(UPDATED_PACK_NAME);
        assertThat(testGrantCash.getPackPrice()).isEqualTo(UPDATED_PACK_PRICE);
        assertThat(testGrantCash.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testGrantCash.getExpiry()).isEqualTo(UPDATED_EXPIRY);
    }

    @Test
    @Transactional
    public void updateNonExistingGrantCash() throws Exception {
        int databaseSizeBeforeUpdate = grantCashRepository.findAll().size();

        // Create the GrantCash

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGrantCashMockMvc.perform(put("/api/grant-cashes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grantCash)))
            .andExpect(status().isCreated());

        // Validate the GrantCash in the database
        List<GrantCash> grantCashList = grantCashRepository.findAll();
        assertThat(grantCashList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGrantCash() throws Exception {
        // Initialize the database
        grantCashService.save(grantCash);

        int databaseSizeBeforeDelete = grantCashRepository.findAll().size();

        // Get the grantCash
        restGrantCashMockMvc.perform(delete("/api/grant-cashes/{id}", grantCash.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GrantCash> grantCashList = grantCashRepository.findAll();
        assertThat(grantCashList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrantCash.class);
        GrantCash grantCash1 = new GrantCash();
        grantCash1.setId(1L);
        GrantCash grantCash2 = new GrantCash();
        grantCash2.setId(grantCash1.getId());
        assertThat(grantCash1).isEqualTo(grantCash2);
        grantCash2.setId(2L);
        assertThat(grantCash1).isNotEqualTo(grantCash2);
        grantCash1.setId(null);
        assertThat(grantCash1).isNotEqualTo(grantCash2);
    }
}
