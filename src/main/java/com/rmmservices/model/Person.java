package com.rmmservices.model;


import javax.persistence.*;
import java.io.ObjectStreamClass;
import java.io.Serializable;

/**
 * Entity class to access the person table
 *
 * @author Karla
 * @since 08-06-2019
 */
@Entity
@Table(name = "person", schema = "public")
public class Person extends AuditEntity implements Serializable {

    private static final long serialVersionUID = ObjectStreamClass.lookup(Person.class).getSerialVersionUID();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", unique = true, nullable = false)
    private Integer idPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "second_name", length = 100)
    private String secondName;

    @Column(name = "last_name", length = 200, nullable = false)
    private String lastName;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "cell_number")
    private Integer cellNumber;

    @Column(name = "number_identification", length = 25, nullable = false)
    private String numberIdentification;

    @Column(name = "email", length = 500, nullable = false)
    private String email;

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(Integer cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getNumberIdentification() {
        return numberIdentification;
    }

    public void setNumberIdentification(String numberIdentification) {
        this.numberIdentification = numberIdentification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}