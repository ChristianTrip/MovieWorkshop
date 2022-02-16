package com.example.movieworkshop.repositories;

import com.example.movieworkshop.models.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieRepository {

    private File path = new File("src/main/resources/imdb-data.csv");
    private Scanner fileScanner;


    public MovieRepository(){
        setFileScanner();
    }

    private void setFileScanner(){
        try{
            fileScanner = new Scanner(path);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public ArrayList<Movie> getMovies(){

        ArrayList<Movie> movies = new ArrayList<>();


        fileScanner.nextLine();
        while (fileScanner.hasNextLine()){

            String[] attributes = fileScanner.nextLine().split(";");

            int year = Integer.parseInt(attributes[0]);
            int length = Integer.parseInt(attributes[1]);
            String title = attributes[2];
            String subject = attributes[3];
            int popularity = Integer.parseInt(attributes[4]);
            String awards = attributes[5];

            Movie movie = new Movie(year, length, title, subject, popularity, awards);
            movies.add(movie);
        }
        return movies;
    }
}
