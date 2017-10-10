package org.jhipster.test.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.test.domain.GrantCash;
import org.jhipster.test.service.GrantCashService;
import org.jhipster.test.web.rest.util.HeaderUtil;
import org.jhipster.test.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing GrantCash.
 */
@RestController
@RequestMapping("/api")
public class GrantCashResource {

    private final Logger log = LoggerFactory.getLogger(GrantCashResource.class);

    private static final String ENTITY_NAME = "grantCash";

    private final GrantCashService grantCashService;

    public GrantCashResource(GrantCashService grantCashService) {
        this.grantCashService = grantCashService;
    }

    /**
     * POST  /grant-cashes : Create a new grantCash.
     *
     * @param grantCash the grantCash to create
     * @return the ResponseEntity with status 201 (Created) and with body the new grantCash, or with status 400 (Bad Request) if the grantCash has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/grant-cashes")
    @Timed
    public ResponseEntity<GrantCash> createGrantCash(@RequestBody GrantCash grantCash) throws URISyntaxException {
        log.debug("REST request to save GrantCash : {}", grantCash);
        if (grantCash.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new grantCash cannot already have an ID")).body(null);
        }
        GrantCash result = grantCashService.save(grantCash);
        return ResponseEntity.created(new URI("/api/grant-cashes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /grant-cashes : Updates an existing grantCash.
     *
     * @param grantCash the grantCash to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated grantCash,
     * or with status 400 (Bad Request) if the grantCash is not valid,
     * or with status 500 (Internal Server Error) if the grantCash couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/grant-cashes")
    @Timed
    public ResponseEntity<GrantCash> updateGrantCash(@RequestBody GrantCash grantCash) throws URISyntaxException {
        log.debug("REST request to update GrantCash : {}", grantCash);
        if (grantCash.getId() == null) {
            return createGrantCash(grantCash);
        }
        GrantCash result = grantCashService.save(grantCash);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, grantCash.getId().toString()))
            .body(result);
    }

    /**
     * GET  /grant-cashes : get all the grantCashes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of grantCashes in body
     */
    @GetMapping("/grant-cashes")
    @Timed
    public ResponseEntity<List<GrantCash>> getAllGrantCashes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of GrantCashes");
        Page<GrantCash> page = grantCashService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/grant-cashes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /grant-cashes/:id : get the "id" grantCash.
     *
     * @param id the id of the grantCash to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the grantCash, or with status 404 (Not Found)
     */
    @GetMapping("/grant-cashes/{id}")
    @Timed
    public ResponseEntity<GrantCash> getGrantCash(@PathVariable Long id) {
        log.debug("REST request to get GrantCash : {}", id);
        GrantCash grantCash = grantCashService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(grantCash));
    }

    /**
     * DELETE  /grant-cashes/:id : delete the "id" grantCash.
     *
     * @param id the id of the grantCash to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/grant-cashes/{id}")
    @Timed
    public ResponseEntity<Void> deleteGrantCash(@PathVariable Long id) {
        log.debug("REST request to delete GrantCash : {}", id);
        grantCashService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
