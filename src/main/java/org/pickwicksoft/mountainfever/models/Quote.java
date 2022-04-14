package org.pickwicksoft.mountainfever.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "quotes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quote_id;
    private String text;
    private String author_name;
    private Integer used_times;


    public Integer getUsed_times() {
        return used_times;
    }

    public void setUsed_times(Integer used_times) {
        this.used_times = used_times;
    }

    public String getText() {
        return text;
    }

    public void setText(String quote_text) {
        this.text = quote_text;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Long getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(Long quote_id) {
        this.quote_id = quote_id;
    }
}
