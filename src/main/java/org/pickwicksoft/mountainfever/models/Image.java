package org.pickwicksoft.mountainfever.models;

import javax.persistence.*;

@Entity(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long image_id;
    private byte[] image;
    private Boolean current;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image_image) {
        this.image = image_image;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean image_current) {
        this.current = image_current;
    }

    public Long getImage_id() {
        return image_id;
    }

    public void setImage_id(Long image_id) {
        this.image_id = image_id;
    }
}
