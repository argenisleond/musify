package com.musify.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/*Style entity class*/
@Entity
public class Style extends AbstractEntity{

    @ManyToMany(mappedBy = "styles", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("styles")
    private List<Artist> artists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
