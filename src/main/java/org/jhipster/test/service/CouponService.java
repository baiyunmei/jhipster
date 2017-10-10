package org.jhipster.test.service;

import org.jhipster.test.domain.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Coupon.
 */
public interface CouponService {

    /**
     * Save a coupon.
     *
     * @param coupon the entity to save
     * @return the persisted entity
     */
    Coupon save(Coupon coupon);

    /**
     *  Get all the coupons.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Coupon> findAll(Pageable pageable);

    /**
     *  Get the "id" coupon.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Coupon findOne(Long id);

    /**
     *  Delete the "id" coupon.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
