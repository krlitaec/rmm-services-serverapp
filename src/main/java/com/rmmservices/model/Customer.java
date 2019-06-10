package com.rmmservices.model;

import javax.persistence.*;
import java.io.ObjectStreamClass;
import java.io.Serializable;

/**
 * Entity class to access the customer table
 *
 * @author Karla Aguirre
 * @since 08-06-2019
 */
@Entity
@Table(name = "customer", schema = "public")
public class Customer extends AuditEntity implements Serializable {

    private static final long serialVersionUID = ObjectStreamClass.lookup(Customer.class).getSerialVersionUID();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer", unique = true, nullable = false)
    private Integer idCustomer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
    private Person idPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(name = "code", length = 10)
    private String code;

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}