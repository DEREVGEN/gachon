package com.project.gachon.controller;

import com.project.gachon.service.MovieService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Value("${tmdb.api.key}")
    private String api_key;

    @GetMapping("/searchMovie")
    public String search_movie(@RequestParam(value = "title", required = false) String title, Model model) {
        String movie_title = title.replace(' ', '+');
        String url = "https://api.themoviedb.org/3/search/movie";

        Map<Long, String> moviesData = null;

        try {
            moviesData = movieService.getMovies(url, api_key, movie_title);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        model.addAttribute("movies",moviesData);
        return "movieListPage";
    }

    @GetMapping("/seachIdMovie")
    @ResponseBody
    public String search_id_movie(@RequestParam(value="movieId", required = false) String movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId;

        List<String> genreList = null;

        try {
            genreList = movieService.getIdMovie(url, api_key);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return genreList.toString();
    }
}
