package com.tungntdo.demo.model.entity;

import com.tungntdo.demo.config.GlobalConfigs;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = GlobalConfigs.TABLE.ADDRESS)
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 2011400409546232661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "street")
    private String street;

    @NotBlank
    @Column(name = "city")
    private String city;

    @NotBlank
    @Column(name = "zipcode")
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity users;

    @Column
    @CreationTimestamp
    private Timestamp createAt;

    @Column
    @UpdateTimestamp
    private Timestamp updateAt;

    // ---- Mapper ----

    @OneToOne(fetch = FetchType.EAGER, mappedBy = GlobalConfigs.TABLE.ADDRESS, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private GeographyEntity geography;

    // ---- Getter, setter ----


    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public UserEntity getUser() {
        return users;
    }

    public void setUser(UserEntity user) {
        this.users = user;
    }
}
