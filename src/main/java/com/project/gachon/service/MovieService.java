package com.project.gachon.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {

    public Map<Long, String> getMovies(String url, String api_key, String title) throws ParseException {
        String apiUrl = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .queryParam("api_key", "{api_key}")
                .queryParam("query", "{query}")
                .encode()
                .toUriString();

        Map<String, String> apiParams = new HashMap<>();
        apiParams.put("api_key", api_key);
        apiParams.put("query", title);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> moviesRes = rt.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class, apiParams);

        return parsingMovies(moviesRes.getBody());
    }

    public Map<Long, String> parsingMovies(String moviesRes) throws ParseException {
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(moviesRes);

        JSONArray movieResults = (JSONArray) jo.get("results");

        Map<Long, String> moviesData = new HashMap<>();

        for (int i = 0; i < movieResults.size(); i++) {
            JSONObject movieData = (JSONObject) movieResults.get(i);
            moviesData.put((Long)movieData.get("id"), (String)movieData.get("original_title"));
        }

        return moviesData;
    }

    public List<String> getIdMovie(String url, String api_key) throws ParseException {
        String apiUrl = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
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

        return genreList;
    }
}
