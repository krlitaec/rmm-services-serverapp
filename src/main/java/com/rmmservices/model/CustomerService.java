package com.rmmservices.model;

import javax.persistence.*;
import java.io.ObjectStreamClass;
import java.io.Serializable;

/**
 * Entity class to access the customer_service table
 *
 * @author Karla Aguirre
 * @since 09-06-2019
 */
@Entity
@Table(name = "customer_service", schema = "public")
public class CustomerService extends AuditEntity implements Serializable {

    private static final long serialVersionUID = ObjectStreamClass.lookup(CustomerService.class).getSerialVersionUID();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer_service", unique = true, nullable = false)
    private Integer idCustomerService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer idCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_smart_service")
    private SmartService idSmartService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_device")
    private Device idDevice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    public Integer getIdCustomerService() {
        return idCustomerService;
    }

    public void setIdCustomerService(Integer idCustomerService) {
        this.idCustomerService = idCustomerService;
    }

    public Customer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public SmartService getIdSmartService() {
        return idSmartService;
    }

    public void setIdSmartService(SmartService idSmartService) {
        this.idSmartService = idSmartService;
    }

    public Device getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(Device idDevice) {
        this.idDevice = idDevice;
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
}