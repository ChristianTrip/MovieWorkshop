package com.example.movieworkshop.services;

import com.example.movieworkshop.models.Movie;
import com.example.movieworkshop.repositories.MovieRepository;
import java.util.ArrayList;
import java.util.Collections;


public class MovieService  {

    private ArrayList<Movie> movies;


    public MovieService(){
        MovieRepository rep = new MovieRepository();
        this.movies = rep.getMovies();
    }


    public String getFirstMovie(){
        return movies.get(0).toString();
    }

    public String getRandomMovie(){
        int movieListSize = movies.size();
        int randomNumber = getRandomNumber(0, movieListSize);
        return movies.get(randomNumber).toString();
    }

    public ArrayList<Movie> sortByPopularity(ArrayList<Movie> movieList){

        Collections.sort(movieList, new CompareMoviePopularity());
        return movieList;
    }

    public ArrayList<Movie> sortByTitle(ArrayList<Movie> movieList){

        Collections.sort(movieList, new CompareMovieTitle());
        return movieList;
    }

    public ArrayList<Movie> sortBySubject(ArrayList<Movie> movieList){

        Collections.sort(movieList, new CompareMovieGenre());
        return movieList;
    }

    public ArrayList<Movie> sortByYear(ArrayList<Movie> movieList){

        Collections.sort(movieList, new CompareMovieYear());
        return movieList;
    }

    public ArrayList<Movie> sortByLength(ArrayList<Movie> movieList){

        Collections.sort(movieList, new CompareMovieLength());
        return movieList;
    }

    public int getMovieAwardsNumber(boolean hasWonAward){

        int countGotAward = 0;
        int countDidNotGetAward = 0;

        for (Movie movie : movies) {
            if (movie.isHasWonAward()){
                countGotAward ++;
            }
            else{
                countDidNotGetAward ++;
            }
        }

        if(hasWonAward){
            return countGotAward;
        }

        return countDidNotGetAward;
    }

    public ArrayList<Movie> getRandomMovieList(int size){

        ArrayList<Movie> randomMovieList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int randomNumber = getRandomNumber(0, movies.size());
            Movie randomMovie = movies.get(randomNumber);
            if (randomMovieList.contains(randomMovie)){
                continue;
            }
            else{
                randomMovieList.add(randomMovie);
            }
        }

        return randomMovieList;
    }

    private int getRandomNumber(int minValue, int maxValue){
        return (int)(Math.random() * maxValue - minValue) + minValue;
    }

    public ArrayList<Movie> filterByLetterAndAmount(char letter, int amount){

        ArrayList<Movie> filteredMovieList = new ArrayList<>();

        String upperCaseLetter = Character.toString(letter).toUpperCase();
        String lowerCaseLetter = Character.toString(letter).toLowerCase();

        for (Movie movie : movies) {
            String movieTitle = movie.getTitle();
            int movieTitleLength = movieTitle.length();
            int letterCount = 0;
            for (int i = 0; i < movieTitleLength; i++) {
                if (movieTitle.substring(i, i +1).equals(upperCaseLetter) || movieTitle.substring(i, i +1).equals(lowerCaseLetter)){
                    letterCount ++;
                }
            }
            if (letterCount == amount){
                filteredMovieList.add(movie);
            }
        }

        return filteredMovieList;
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }

    public String getSubjectWithLongestRuntime(String sub1, String sub2){

        if (sub1.equals(sub2)){
            return sub1 + " & " + sub2 + " are the same subject/genre";
        }

        ArrayList<Movie> subjectOneList = new ArrayList<>();
        ArrayList<Movie> subjectTwoList = new ArrayList<>();

        for (Movie movie : movies) {
            String subject = movie.getSubject();
            if (subject.equals(sub1)){
                subjectOneList.add(movie);
            }
            else if (subject.equals(sub2)){
                subjectTwoList.add(movie);
            }
        }

        double subjectOneAverageRuntime = getAverageRuntimeFromList(subjectOneList);
        double subjectTwoAverageRuntime = getAverageRuntimeFromList(subjectTwoList);
        String sub1Formatted = String.format("%.2f", subjectOneAverageRuntime);
        String sub2Formatted = String.format("%.2f", subjectTwoAverageRuntime);

        if (subjectOneAverageRuntime < subjectTwoAverageRuntime){
            return "The genre '" + sub2 + "' has on average the longest runtime, that is: " + sub2Formatted + " minutes, against " + sub1Formatted + " minutes" + " for '" + sub1 + "'";
        }
        else if (subjectOneAverageRuntime > subjectTwoAverageRuntime){
            return "The genre '" + sub1 + "' has on average the longest runtime, that is: " + sub1Formatted + " minutes, against " + sub2Formatted + " minutes"  + " for '" + sub2 + "'";
        }
        else {
            return "Wow, " + sub1 + " & " + sub2 + " have the same runtime, that is: " + sub1Formatted + " minutes";
        }

    }

    private double getAverageRuntimeFromList(ArrayList<Movie> movieList){

        double totalRuntime = 0;

        for (Movie movie : movieList) {
            totalRuntime += movie.getLength();
        }

        double averageRuntime = totalRuntime / movieList.size();

        return averageRuntime;
    }

    public String viewAllMoviesSortedByTitle(){
        String allMovies = "";
        allMovies += "\n____________________________________________________________________________________________________________________________________\n";
        allMovies += "| MOVIE DATABASE SORTED BY TITLE\n| Total number of movies: " + movies.size();
        allMovies += "\n____________________________________________________________________________________________________________________________________\n";
        allMovies += String.format("| %s | %-9s | %-70s | %-15s | %-10s | %s |", "Year", "Length", "Title", "Subject/Genre", "Popularity", "Award");
        allMovies += "\n------------------------------------------------------------------------------------------------------------------------------------\n";
        ArrayList<Movie> sorted = movies;
        Collections.sort(sorted, new CompareMovieTitle());
        for (Movie movie : sorted) {
            allMovies += movie.toString() + "\n";
        }
        System.out.println(allMovies);

        return allMovies;
    }


}
