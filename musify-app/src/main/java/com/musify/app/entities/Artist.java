package com.musify.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*Artist entity class*/
@Entity
public class Artist extends AbstractEntity {

    @Column
    @Min(value = 12, message = "The number should be equal or higher to 12")
    private Integer year;

    @ManyToMany
    @JsonIgnoreProperties("artists")
    private List<Style> styles;

    @ManyToMany
    private List<People> members;

    @ManyToMany
    @JsonIgnoreProperties("{styles, members, related}")
    private List<Artist> related;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public List<People> getMembers() {
        return members;
    }

    public void setMembers(List<People> members) {
        this.members = members;
    }

    public List<Artist> getRelated() {
        return related;
    }

    public void setRelated(List<Artist> related) {
        this.related = related;
    }
}
