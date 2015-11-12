package com.example.shreyakothari.sportsapp;

import java.net.*;

public class SportsURL {
    public URL getURLFromSport(String sport){
        URL url = null;
        try{
            if(sport.equals("NBA")) {
                url = new URL("http://sagarin.com/sports/nbasend.htm");
            }
            else if (sport.equals("NHL")) {
                url = new URL("http://sagarin.com/sports/nhlsend.htm");
            }
            else if (sport.equals("College Football")) {
                url = new URL("http://sagarin.com/sports/cfsend.htm");
            }
            else if (sport.equals("College Basketball")) {
                url = new URL("http://sagarin.com/sports/cbsend.htm");
            }
            else if (sport.equals("MLS Soccer")) {
                url = new URL("http://sagarin.com/sports/soccer.htm");
            }
            else {
                // Do nothing
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
