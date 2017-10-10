package org.jhipster.test.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A GrantCash.
 */
@Entity
@Table(name = "grant_cash")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GrantCash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pack_id")
    private Long packId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "use_time")
    private Long useTime;

    @Column(name = "get_time")
    private Long getTime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "pack_name")
    private String packName;

    @Column(name = "pack_price")
    private Double packPrice;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "expiry")
    private Long expiry;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPackId() {
        return packId;
    }

    public GrantCash packId(Long packId) {
        this.packId = packId;
        return this;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
    }

    public Long getUserId() {
        return userId;
    }

    public GrantCash userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUseTime() {
        return useTime;
    }

    public GrantCash useTime(Long useTime) {
        this.useTime = useTime;
        return this;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public Long getGetTime() {
        return getTime;
    }

    public GrantCash getTime(Long getTime) {
        this.getTime = getTime;
        return this;
    }

    public void setGetTime(Long getTime) {
        this.getTime = getTime;
    }

    public Integer getStatus() {
        return status;
    }

    public GrantCash status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPackName() {
        return packName;
    }

    public GrantCash packName(String packName) {
        this.packName = packName;
        return this;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public Double getPackPrice() {
        return packPrice;
    }

    public GrantCash packPrice(Double packPrice) {
        this.packPrice = packPrice;
        return this;
    }

    public void setPackPrice(Double packPrice) {
        this.packPrice = packPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public GrantCash orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getExpiry() {
        return expiry;
    }

    public GrantCash expiry(Long expiry) {
        this.expiry = expiry;
        return this;
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GrantCash grantCash = (GrantCash) o;
        if (grantCash.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grantCash.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrantCash{" +
            "id=" + getId() +
            ", packId='" + getPackId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", useTime='" + getUseTime() + "'" +
            ", getTime='" + getGetTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", packName='" + getPackName() + "'" +
            ", packPrice='" + getPackPrice() + "'" +
            ", orderId='" + getOrderId() + "'" +
            ", expiry='" + getExpiry() + "'" +
            "}";
    }
}
