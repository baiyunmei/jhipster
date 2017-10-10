package org.jhipster.test.service;

import org.jhipster.test.domain.GrantCash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing GrantCash.
 */
public interface GrantCashService {

    /**
     * Save a grantCash.
     *
     * @param grantCash the entity to save
     * @return the persisted entity
     */
    GrantCash save(GrantCash grantCash);

    /**
     *  Get all the grantCashes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<GrantCash> findAll(Pageable pageable);

    /**
     *  Get the "id" grantCash.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    GrantCash findOne(Long id);

    /**
     *  Delete the "id" grantCash.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
