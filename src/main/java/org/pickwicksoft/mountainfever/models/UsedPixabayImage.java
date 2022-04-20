package org.pickwicksoft.mountainfever.models;

import javax.persistence.*;

@Entity(name = "used_pixabay_images")
@Table(name = "used_pixabay_images")
public class UsedPixabayImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String pixabay_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPixabay_id() {
        return pixabay_id;
    }

    public void setPixabay_id(String pixabay_id) {
        this.pixabay_id = pixabay_id;
    }
}
