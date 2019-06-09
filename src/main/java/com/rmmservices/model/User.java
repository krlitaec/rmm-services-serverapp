package com.rmmservices.model;


import javax.persistence.*;
import java.io.ObjectStreamClass;
import java.io.Serializable;

/**
 * Entity class to access the user table
 *
 * @author Karla
 * @since 08-06-2019
 */
@Entity
@Table(name = "user", schema = "public")
public class User implements Serializable {

    private static final long serialVersionUID = ObjectStreamClass.lookup(User.class).getSerialVersionUID();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", unique = true, nullable = false)
    private Integer idUser;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "status")
    private Boolean status;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}