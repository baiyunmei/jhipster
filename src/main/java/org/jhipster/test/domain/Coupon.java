package org.jhipster.test.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Coupon.
 */
//新增实体类
    //新增抽奖功能
@Entity
@Table(name = "coupon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cash_name")
    private String cashName;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "reason")
    private String reason;

    @Column(name = "jhi_time")
    private Long time;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCashName() {
        return cashName;
    }

    public Coupon cashName(String cashName) {
        this.cashName = cashName;
        return this;
    }

    public void setCashName(String cashName) {
        this.cashName = cashName;
    }

    public Integer getNumber() {
        return number;
    }

    public Coupon number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getReason() {
        return reason;
    }

    public Coupon reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getTime() {
        return time;
    }

    public Coupon time(Long time) {
        this.time = time;
        return this;
    }

    public void setTime(Long time) {
        this.time = time;
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
        Coupon coupon = (Coupon) o;
        if (coupon.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coupon.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Coupon{" +
            "id=" + getId() +
            ", cashName='" + getCashName() + "'" +
            ", number='" + getNumber() + "'" +
            ", reason='" + getReason() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }
}
