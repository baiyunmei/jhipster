package org.jhipster.test.service.impl;

import org.jhipster.test.service.CouponService;
import org.jhipster.test.domain.Coupon;
import org.jhipster.test.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Coupon.
 */
@Service
@Transactional
public class CouponServiceImpl implements CouponService{

    private final Logger log = LoggerFactory.getLogger(CouponServiceImpl.class);

    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    /**
     * Save a coupon.
     *
     * @param coupon the entity to save
     * @return the persisted entity
     */
    @Override
    public Coupon save(Coupon coupon) {
        log.debug("Request to save Coupon : {}", coupon);
        return couponRepository.save(coupon);
    }

    /**
     *  Get all the coupons.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Coupon> findAll(Pageable pageable) {
        log.debug("Request to get all Coupons");
        return couponRepository.findAll(pageable);
    }

    /**
     *  Get one coupon by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Coupon findOne(Long id) {
        log.debug("Request to get Coupon : {}", id);
        return couponRepository.findOne(id);
    }

    /**
     *  Delete the  coupon by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Coupon : {}", id);
        couponRepository.delete(id);
    }
}
