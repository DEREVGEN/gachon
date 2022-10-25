package com.project.gachon.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MovieServiceTest {

    @Value("${tmdb.api.key}")
    private String api_key;

    String url = "https://api.themoviedb.org/3/search/movie";

    @Test
    public void parsingTest() throws ParseException {
        String apiUrl = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .queryParam("api_key", "{api_key}")
                .queryParam("query", "{query}")
                .encode()
                .toUriString();

        Map<String, String> apiParams = new HashMap<>();
        apiParams.put("api_key", api_key);
        apiParams.put("query", "avengers");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> moviesRes = rt.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class, apiParams);

        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(moviesRes.getBody());

        JSONArray movieResults = (JSONArray) jo.get("results");

        Map<String, Object> moviesData = new HashMap<>();

        for (int i = 0; i < movieResults.size(); i++) {
            JSONObject movieData = (JSONObject) movieResults.get(i);
            moviesData.put((String)movieData.get("original_title"), movieData.get("id"));

            /*JSONObject movieTitle = (JSONObject) movieResults.get(i);
            System.out.println("title: " + (String) movieTitle.get("original_title") + " id: " + movieTitle.get("id"));*/
        }

        for (String key : moviesData.keySet()) {
            System.out.println("title : " + key + " id : " +  moviesData.get(key));
        }
    }

    @Test
    public void test_parsingIdMovie() throws ParseException {
        String apiUrl = UriComponentsBuilder.newInstance()
                .fromHttpUrl("https://api.themoviedb.org/3/movie/9320")
                .queryParam("api_key", "{api_key}")
                .encode()
                .toUriString();

        Map<String, String> apiParams = new HashMap<>();
        apiParams.put("api_key", api_key);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> movieRes = rt.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class, apiParams);

        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(movieRes.getBody());

        JSONArray movieResults = (JSONArray) jo.get("genres");

        List<String> genreList = new ArrayList<>();

        for (int i = 0; i < movieResults.size(); i++) {
            JSONObject movieData = (JSONObject) movieResults.get(i);
            genreList.add((String) movieData.get("name"));
        }

        for (String gerne : genreList) {
            System.out.println(gerne);
        }
    }
}