package org.pickwicksoft.mountainfever.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "images")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long id;

    private String name;
    private String content_type;
    @Lob
    private byte[] image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String contentType) {
        this.content_type = contentType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image_image) {
        this.image = image_image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long image_id) {
        this.id = image_id;
    }
}
