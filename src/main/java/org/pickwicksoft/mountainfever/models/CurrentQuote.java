package org.pickwicksoft.mountainfever.models;

import javax.persistence.*;

@Entity
@Table(name = "current_quote")
public class CurrentQuote {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}