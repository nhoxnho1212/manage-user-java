package com.tungntdo.demo.model.entity;

import com.tungntdo.demo.config.GlobalConfigs;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = GlobalConfigs.TABLE.TOKEN)
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = -9072436635839799813L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String tokenId;

    @ManyToOne
    @JoinColumn(name = "token_type_id", nullable = false)
    private TokenTypeEntity tokenType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Boolean revoked;

    @Column
    private String scopes;

    @Column(nullable = false)
    private Timestamp expiresAt;

    @Column
    @CreationTimestamp
    private Timestamp createAt;

    @Column
    @UpdateTimestamp
    private Timestamp updateAt;

    // ---- Mapper ----

    // ---- Getter, Setter ----

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public TokenTypeEntity getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenTypeEntity tokenType) {
        this.tokenType = tokenType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
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
}
