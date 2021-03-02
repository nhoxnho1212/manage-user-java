package com.tungntdo.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tungntdo.demo.config.GlobalConfigs;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = GlobalConfigs.TABLE.USER,
    uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id"}),
            @UniqueConstraint(columnNames = {"email"})
    }
)
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -2983769039615473037L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "user_id")
    private String userId;

    @NotBlank
    @Column(name = "first_name", length = 50)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", length = 50)
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", length = 120)
    private String email;

    @NotBlank
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "email_verification_status", nullable = false)
    private Boolean emailVerificationStatus = false;

    @Column(name = "create_at")
    @CreationTimestamp
    private Timestamp createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updateAt;

    // ------ Mapper -----
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<AddressEntity> listAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<TokenEntity> listToken;


    public UserEntity() { }


    // ---- Getter, setter ----


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
}
