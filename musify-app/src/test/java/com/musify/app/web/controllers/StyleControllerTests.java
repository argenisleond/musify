/**
*
*
*/
package com.musify.app.web.controllers;

import com.musify.app.Application;
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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StyleControllerTests
{
	public static final String baseUrl = "http://localhost:8080/api/v1";
	
	@Test
	public void test_list()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/styles/";
		ResponseEntity<Style[]> responseEntity = rt.getForEntity(url , Style[].class);
		Style[] styles = responseEntity.getBody();
		Assert.assertEquals(3, styles.length);
	}
	
	@Test
	public void test_get()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/styles/1";
		ResponseEntity<Style> responseEntity = rt.getForEntity(url , Style.class);
		Style style = responseEntity.getBody();
		Assert.assertNotNull(style);
	}
	
	@Test
	public void test_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/styles/";
		Style style = new Style();
		style.setName("Rock and Roll");

		ResponseEntity<Style> responseEntity = rt.postForEntity(url , style, Style.class);
		style = responseEntity.getBody();
		Assert.assertNotNull(style);
		System.out.println(style.getId());
	}

	@Test
	public void test_null_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/styles/";
		Style style = new Style();

		try {
			rt.postForEntity(url , style, Style.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_too_short_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/styles/";
		Style style = new Style();
		style.setName("A");

		try {
			rt.postForEntity(url , style, Style.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_too_long_name_create()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/styles/";
		Style style = new Style();
		style.setName("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859100");

		try {
			rt.postForEntity(url , style, Style.class);
		}catch(HttpClientErrorException he) {
			Assert.assertTrue(he.getStatusCode().is4xxClientError());
		}
	}

	@Test
	public void test_update()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/styles/1";
		Style style = new Style();
		style.setName("Salsa");

		rt.put(url , style, Style.class);
		ResponseEntity<Style> responseEntity = rt.getForEntity(url , Style.class);
		Style styleObtained = responseEntity.getBody();
		Assert.assertEquals(style.getName(), styleObtained.getName());
	}

	@Test
	public void test_delete()
	{
		RestTemplate rt = new RestTemplate();
		String url = baseUrl+"/styles/2";

		rt.delete(url);
		ResponseEntity<Style> responseEntity = rt.getForEntity(url , Style.class);
		Style style = responseEntity.getBody();
		Assert.assertNull(style);
	}
}
