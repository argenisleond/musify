/**
*
*
*/
package com.musify.app.web.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.musify.app.entities.People;
import com.musify.app.entities.Style;
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
import com.musify.app.Application;
import com.musify.app.entities.Artist;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ArtistControllerTests
{
	public static final String baseUrl = "http://localhost:8080/api/v1";
	
	@Test
	public void test_list()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/";
		ResponseEntity<Artist[]> responseEntity = rt.getForEntity(url , Artist[].class);
		Artist[] artists = responseEntity.getBody();
		Assert.assertEquals(3, artists.length);
	}
	
	@Test
	public void test_get()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/1";
		ResponseEntity<Artist> responseEntity = rt.getForEntity(url , Artist.class);
		Artist artist = responseEntity.getBody();
		Assert.assertNotNull(artist);
	}
	
	@Test
	public void test_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/";
		Artist artist = new Artist();
		artist.setName("Shakira");
		artist.setYear(35);

		ResponseEntity<Artist> responseEntity = rt.postForEntity(url , artist, Artist.class);
		artist = responseEntity.getBody();
		Assert.assertNotNull(artist);
		System.out.println(artist.getId());
	}

	@Test
	public void test_invalid_year_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/";
		Artist artist = new Artist();
		artist.setName("Michael Jackson");
		artist.setYear(10);

		try {
			rt.postForEntity(url , artist, Artist.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_null_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/";
		Artist artist = new Artist();
		artist.setYear(25);

		try {
			rt.postForEntity(url , artist, Artist.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_too_short_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/";
		Artist artist = new Artist();
		artist.setName("A");

		try {
			rt.postForEntity(url , artist, Artist.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_too_long_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/";
		Artist artist = new Artist();
		artist.setName("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859100");

		try {
			rt.postForEntity(url , artist, Artist.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}


	@Test
	public void test_update()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/1";
		Artist artist = new Artist();
		artist.setName("Alejandra Guzman");

		rt.put(url , artist, Artist.class);
		ResponseEntity<Artist> responseEntity = rt.getForEntity(url , Artist.class);
		Artist artistObtained = responseEntity.getBody();
		Assert.assertEquals(artist.getName(), artistObtained.getName());
	}

	@Test
	public void test_delete()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/artists/2";

		rt.delete(url);
		ResponseEntity<Artist> responseEntity = rt.getForEntity(url , Artist.class);
		Artist artist = responseEntity.getBody();
		Assert.assertNull(artist);
	}
}
