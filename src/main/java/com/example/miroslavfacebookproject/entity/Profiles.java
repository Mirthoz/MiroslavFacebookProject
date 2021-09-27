package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class Profiles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "is_full_name_public")
    private boolean isFullNamePublic;

    @Column(name = "other_info")
    private String otherInfo;

    @Column(name = "image_id")
    private Long imageId;

    public Profiles() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isFullNamePublic() {
        return isFullNamePublic;
    }

    public void setFullNamePublic(boolean fullNamePublic) {
        isFullNamePublic = fullNamePublic;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
