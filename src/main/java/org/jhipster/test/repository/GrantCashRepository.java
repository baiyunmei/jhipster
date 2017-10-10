package org.jhipster.test.repository;

import org.jhipster.test.domain.GrantCash;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the GrantCash entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrantCashRepository extends JpaRepository<GrantCash, Long> {

}
