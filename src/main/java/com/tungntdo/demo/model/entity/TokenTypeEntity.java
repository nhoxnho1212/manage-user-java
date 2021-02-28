package com.tungntdo.demo.model.entity;

import com.tungntdo.demo.config.GlobalConfigs;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = GlobalConfigs.TABLE.TOKEN_TYPE)
public class TokenTypeEntity implements Serializable {

    private static final long serialVersionUID = 4871541567928188070L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String typeName;

    @Column
    @CreationTimestamp
    private Timestamp createAt;

    @Column
    @UpdateTimestamp
    private Timestamp updateAt;

    // ---- Mapper ----

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tokenType")
    private List<TokenEntity> listToken;

    // ---- Getter, Setter ----

    public void setId(long id) {
        this.id = id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getTypeName() {
        return typeName;
    }
}
