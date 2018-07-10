package com.chetana.ramesh.chetana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orm.SugarRecord;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News extends SugarRecord implements Serializable {

    String title;
    String description;
    String slug;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


}