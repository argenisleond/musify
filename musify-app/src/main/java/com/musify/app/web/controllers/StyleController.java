/**
*
*
*/
package com.musify.app.web.controllers;

import com.musify.app.entities.Style;
import com.musify.app.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/styles")
public class StyleController
{
	@Autowired
	private StyleRepository styleRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Style> list(
			@RequestParam(name = "name", required = false) String name)
	{
		if(name != null) {
			return styleRepository.findByName(name);
		}
		return styleRepository.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Style get(@PathVariable Long id)
	{
		return styleRepository.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Style create(@RequestBody @Valid Style style)
	{
		return styleRepository.save(style);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Style update(@PathVariable Long id, @RequestBody @Valid Style style)
	{
		style.setId(id);
		return styleRepository.save(style);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		styleRepository.delete(id);
	}
}
