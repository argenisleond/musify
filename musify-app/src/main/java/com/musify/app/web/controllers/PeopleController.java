/**
*
*
*/
package com.musify.app.web.controllers;

import com.musify.app.entities.People;
import com.musify.app.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController
{
	@Autowired
	private PeopleRepository peopleRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<People> list(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "years", required = false) Integer years
	)
	{
		if(years != null && name != null){
			return peopleRepository.findByNameAndYear(name, years);
		}
		if(name != null){
			return peopleRepository.findByName(name);
		}
		if(years != null){
			return peopleRepository.findByYear(years);
		}
		return peopleRepository.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public People get(@PathVariable Long id)
	{
		return peopleRepository.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public People create(@RequestBody @Valid People people)
	{
		return peopleRepository.save(people);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public People update(@PathVariable Long id, @RequestBody @Valid People people)
	{
		people.setId(id);
		return peopleRepository.save(people);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id)
	{
		peopleRepository.delete(id);
	}
}
