package org.pickwicksoft.mountainfever.models;

import javax.persistence.*;

@Entity(name = "used_pixabay_images")
public class UsedPixabayImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pixabay_id", nullable = false)
    private Long pixabay_id;

    public Long getPixabay_id() {
        return pixabay_id;
    }

    public void setPixabay_id(Long pixabay_id) {
        this.pixabay_id = pixabay_id;
    }
}
