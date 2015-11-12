package com.example.shreyakothari.sportsapp;

import java.util.ArrayList;

public class ParseList {

    private Team getTeam(String teamString) {
        Team t = new Team();

        String[] record = teamString.trim().split("\\|");

        String nameAndRank = record[0].split("=")[0].trim();
        String teamDetails = record[0].split("=")[1].trim();

        String[] temp = nameAndRank.split(" +");
        String[] temp1 = teamDetails.replaceAll("\\s+", " ").split(" ");

        String tempName = "";
        for (int i = 1; i < temp.length; i++) {
            tempName = tempName + temp[i] + " ";
        }

        tempName = tempName.replaceAll("\\s+$", "");

        String tempRating = temp1[0];
        String tempWins = temp1[1];
        String tempLoss = temp1[2];

        t.setName(tempName);
        t.setRanking(Integer.parseInt(temp[0]));
        t.setRating(Float.parseFloat(tempRating));
        t.setWin(Integer.parseInt(tempWins));
        t.setLoss(Integer.parseInt(tempLoss));

        return t;
    }

    public ArrayList<Team> getTeams(ArrayList<String> teamInfo) {

        ArrayList<Team> teams = new ArrayList<Team>();
        boolean bFlag = false;

        int nCounter = 1;
        for (String text : teamInfo) {

            if (bFlag && nCounter <= 5) {
                teams.add(getTeam(text));
                nCounter++;
            }

            if (nCounter > 5)
                bFlag = false;

            String[] textArray = text.split(" +");

            for (String temp : textArray) {
                if (temp.equals("HOME")) {
                    bFlag = true;
                    nCounter = 1;
                    break;
                }
            }
        }
        return teams;
    }

    private String extract(String str) {
        String[] record = str.trim().split("\\|");
        String ans = record[0].split("=")[0].trim() + "=";
        ans += record[0].split("=")[1].trim().split(" ")[0];
        ans += record[2] + "|" + record[3] + "|" + record[4];
        return ans;
    }
}
