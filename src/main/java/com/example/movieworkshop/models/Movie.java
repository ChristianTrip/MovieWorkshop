package com.example.movieworkshop.models;

public class Movie {

    private int year;
    private int length;
    private String title;
    private String subject;
    private int popularity;
    private boolean hasWonAward;

    public Movie(int year, int length, String title, String subject, int popularity, String hasWonAward) {
        this.year = year;
        this.length = length;
        this.title = title;
        this.subject = subject;
        this.popularity = popularity;

        if (hasWonAward.equals("Yes")){
            this.hasWonAward = true;
        }
        else if (hasWonAward.equals("No")){
            this.hasWonAward = false;
        }
    }


    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public int getPopularity() {
        return popularity;
    }

    public boolean isHasWonAward() {
        return hasWonAward;
    }

    public String getLengthInHours(){

        int hours = length / 60;
        int minutes = length % 60;

        String pluralHour = "hours";
        if(hours == 1){
            pluralHour = "hour ";
        }
        String pluralMinute = "minutes";
        if (minutes == 1){
            pluralMinute = "minute ";
        }

        return String.format("%02d %s %02d %s", hours, "h", minutes, "m");
    }

    @Override
    public String toString(){
        return String.format("| %d | %-9s | %-70s | %-15s | %02d         | %-5b |", year, getLengthInHours(), title, subject, popularity, hasWonAward);
    }

}
