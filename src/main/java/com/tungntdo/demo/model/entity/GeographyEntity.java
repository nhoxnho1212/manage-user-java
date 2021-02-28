package com.tungntdo.demo.model.entity;

import com.tungntdo.demo.config.GlobalConfigs;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(
        name = GlobalConfigs.TABLE.GEOGRAPHY,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"address_id"})
        }
)
public class GeographyEntity implements Serializable {

    private static final long serialVersionUID = -267439060870082921L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @Column
    @CreationTimestamp
    private Timestamp createAt;

    @Column
    @UpdateTimestamp
    private Timestamp updateAt;

    // ---- Mapper ----

    // ---- Getter, Setter ----

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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
