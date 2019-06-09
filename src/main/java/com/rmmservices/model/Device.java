package com.rmmservices.model;

import javax.persistence.*;
import java.io.ObjectStreamClass;
import java.io.Serializable;

/**
 * Entity class to access the device table
 *
 * @author Karla Aguirre
 * @since 08-06-2019
 */
@Entity
@Table(name = "device", schema = "public")
public class Device extends AuditEntity implements Serializable {

    private static final long serialVersionUID = ObjectStreamClass.lookup(Device.class).getSerialVersionUID();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_device", unique = true, nullable = false)
    private Integer idDevice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(name = "system_name", length = 500)
    private String systemName;

    @Column(name = "type", length = 255)
    private String type;

    public Integer getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(Integer idDevice) {
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

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}