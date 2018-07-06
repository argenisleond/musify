/**
*
*
*/
package com.musify.app.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.musify.app.entities.Artist;
import com.musify.app.repositories.ArtistRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController
{
	@Autowired
	private ArtistRepository artistRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Artist> list(@RequestParam(name = "name", required = false) String name,
							  @RequestParam(name = "year", required = false) Integer year)
	{
		if(year != null && name != null){
			return artistRepository.findByNameAndYear(name, year);
		}
		if(name != null){
			return artistRepository.findByName(name);
		}
		if(year != null){
			return artistRepository.findByYear(year);
		}
		return artistRepository.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Artist get(@PathVariable Long id)
	{
		return artistRepository.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Artist create(@RequestBody @Valid Artist artist)
	{
		return artistRepository.save(artist);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Artist update(@PathVariable Long id, @RequestBody @Valid Artist artist)
	{
		artist.setId(id);
		return artistRepository.save(artist);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		artistRepository.delete(id);
	}
}
