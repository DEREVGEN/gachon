package com.project.gachon.service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WeatherService {

    @Value("${openweather.api.key}")
    private String api_key;

    private String url = "https://api.openweathermap.org/data/2.5/weather";

    public String getWeather(double lat, double lon) throws ParseException {
        String apiUrl = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .queryParam("lat", "{lat}")
                .queryParam("lon", "{lon}")
                .queryParam("appid", "{appid}")
                .queryParam("units", "{units}")
                .encode()
                .toUriString();

        Map<String, String> apiParams = new LinkedHashMap<>();
        apiParams.put("lat", Double.toString(lat));
        apiParams.put("lon", Double.toString(lon));
        apiParams.put("appid", api_key);
        apiParams.put("units", "metric");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> weatherRes = rt.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class, apiParams);

        return parsing_weather(weatherRes.getBody());
    }

    public String parsing_weather(String weahterData) throws ParseException {
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(weahterData);

        JSONArray ja = (JSONArray) jo.get("weather");

        jo = (JSONObject) ja.get(0);

        return ((String) jo.get("main"));
    }
}
