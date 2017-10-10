package org.jhipster.test.service.impl;

import org.jhipster.test.service.GrantCashService;
import org.jhipster.test.domain.GrantCash;
import org.jhipster.test.repository.GrantCashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing GrantCash.
 */
@Service
@Transactional
public class GrantCashServiceImpl implements GrantCashService{

    private final Logger log = LoggerFactory.getLogger(GrantCashServiceImpl.class);

    private final GrantCashRepository grantCashRepository;

    public GrantCashServiceImpl(GrantCashRepository grantCashRepository) {
        this.grantCashRepository = grantCashRepository;
    }

    /**
     * Save a grantCash.
     *
     * @param grantCash the entity to save
     * @return the persisted entity
     */
    @Override
    public GrantCash save(GrantCash grantCash) {
        log.debug("Request to save GrantCash : {}", grantCash);
        return grantCashRepository.save(grantCash);
    }

    /**
     *  Get all the grantCashes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GrantCash> findAll(Pageable pageable) {
        log.debug("Request to get all GrantCashes");
        return grantCashRepository.findAll(pageable);
    }

    /**
     *  Get one grantCash by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GrantCash findOne(Long id) {
        log.debug("Request to get GrantCash : {}", id);
        return grantCashRepository.findOne(id);
    }

    /**
     *  Delete the  grantCash by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GrantCash : {}", id);
        grantCashRepository.delete(id);
    }
}
