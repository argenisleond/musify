/**
*
*
*/
package com.musify.app.web.controllers;

import com.musify.app.Application;
import com.musify.app.entities.Artist;
import com.musify.app.entities.People;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PeopleControllerTests
{
	public static final String baseUrl = "http://localhost:8080/api/v1";
	
	@Test
	public void test_list()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/";
		ResponseEntity<People[]> responseEntity = rt.getForEntity(url , People[].class);
		People[] peoples = responseEntity.getBody();
		Assert.assertEquals(3, peoples.length);
	}
	
	@Test
	public void test_get()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/1";
		ResponseEntity<People> responseEntity = rt.getForEntity(url , People.class);
		People people = responseEntity.getBody();
		Assert.assertNotNull(people);
	}
	
	@Test
	public void test_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/";
		People people = new People();
		people.setName("Antonio Pacheco");

		ResponseEntity<People> responseEntity = rt.postForEntity(url , people, People.class);
		people = responseEntity.getBody();
		Assert.assertNotNull(people);
		System.out.println(people.getId());
	}

	@Test
	public void test_invalid_year_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/";
		People people = new People();
		people.setName("Juan Morales");
		people.setYears(10);

		try {
			rt.postForEntity(url , people, People.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_null_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/";
		People people = new People();
		people.setYears(25);

		try {
			rt.postForEntity(url , people, People.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_too_short_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/";
		People people = new People();
		people.setName("A");

		try {
			rt.postForEntity(url , people, People.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_too_long_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/";
		People people = new People();
		people.setName("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859100");

		try {
			rt.postForEntity(url , people, People.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_update()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/1";
		People people = new People();
		people.setName("Miguel Cordero");

		rt.put(url , people, People.class);
		ResponseEntity<People> responseEntity = rt.getForEntity(url , People.class);
		People peopleObtained = responseEntity.getBody();
		Assert.assertEquals(people.getName(), peopleObtained.getName());
	}

	@Test
	public void test_delete()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/people/2";

		rt.delete(url);
		ResponseEntity<People> responseEntity = rt.getForEntity(url , People.class);
		People people = responseEntity.getBody();
		Assert.assertNull(people);
	}
}
