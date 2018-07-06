/**
*
*
*/
package com.musify.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/*People entity class*/
@Entity
public class People extends AbstractEntity{

	@Column
	@Min(value = 12, message = "The number should be equal or higher to 12")
	private Integer years;

	@ManyToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Artist> artists = new ArrayList<>();

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
}
