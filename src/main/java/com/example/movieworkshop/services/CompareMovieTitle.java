package com.example.movieworkshop.services;

import com.example.movieworkshop.models.Movie;
import java.util.Comparator;

public class CompareMovieTitle implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        if(o1.getTitle().compareTo(o2.getTitle()) < 0){
            return -1;
        }
        else if (o1.getTitle().compareTo(o2.getTitle()) > 0){
            return 1;
        }
        else{
            return 0;
        }
    }
}
