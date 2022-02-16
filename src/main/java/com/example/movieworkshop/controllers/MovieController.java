package com.example.movieworkshop.controllers;

import com.example.movieworkshop.models.Movie;
import com.example.movieworkshop.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MovieController {

    private MovieService MovieService = new MovieService();


    @GetMapping("/welcome")
    public String index(@RequestParam(defaultValue = "unknown person") String name){
        return "Greatings " + name + ", This application analysis a relatively small list of movies from the Imdb database";
    }

    @GetMapping("/getFirst")
    public String getFirst(){
        return MovieService.getFirstMovie();
    }

    @GetMapping("/getRandom")
    public String getRandom(){
        return MovieService.getRandomMovie();
    }

    @GetMapping("/getTenSortedByPopularity")
    public ArrayList<Movie> getTenSortedByPopularity(){
        ArrayList<Movie> randomMovies = MovieService.getRandomMovieList(10);
        return MovieService.sortByPopularity(randomMovies);
    }

    @GetMapping("/howManyWonAnAward")
    public String howManyWonAnAward(){
        int totalNumberOfMovies = MovieService.getMovies().size();
        int countWonAward = MovieService.getMovieAwardsNumber(true);
        String asString = String.valueOf(countWonAward);
        return "The number of movies that won an award: " + asString + " out of " + totalNumberOfMovies + " in total.";
    }

    @GetMapping("/filter")
    public ArrayList<Movie> filter(@RequestParam char letter, @RequestParam int amount){

        ArrayList<Movie> filteredList = MovieService.filterByLetterAndAmount(letter, amount);

        return filteredList;
    }

    @GetMapping("/longest")
    public String longest(@RequestParam String subject1, @RequestParam String subject2){

        String SubjectWithLongestRuntime = MovieService.getSubjectWithLongestRuntime(subject1, subject2);

        return SubjectWithLongestRuntime;
    }

    @GetMapping("/viewAll")
    public String viewAll(){

        String allMovies = MovieService.viewAllMoviesSortedByTitle();

        return allMovies;
    }

}
