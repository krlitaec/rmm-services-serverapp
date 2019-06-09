package com.rmmservices.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entity class to access the smart_service table
 *
 * @author Karla Aguirre
 * @since 08-06-2019
 */
@Entity
@Table(name = "smart_service", schema = "public")
public class SmartService extends AuditEntity implements Serializable {

    private static final long serialVersionUID = ObjectStreamClass.lookup(SmartService.class).getSerialVersionUID();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_smart_service", unique = true, nullable = false)
    private Integer idSmartService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "monthly_cost", nullable = false)
    private BigDecimal monthly_cost;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "description", length = 500)
    private String description;

    public Integer getIdSmartService() {
        return idSmartService;
    }

    public void setIdSmartService(Integer idSmartService) {
        this.idSmartService = idSmartService;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMonthly_cost() {
        return monthly_cost;
    }

    public void setMonthly_cost(BigDecimal monthly_cost) {
        this.monthly_cost = monthly_cost;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}