package com.example.shreyakothari.sportsapp;

import android.os.AsyncTask;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class ParseURL extends AsyncTask<URL, Void, ArrayList<Team>> {

    private final String m_HTMLTag = "<[^>]+>";
    private final String m_Line = "____________________________________________________________________________________________________________________________________";

    private String deleteTag(String html) {
        Pattern p_html = Pattern.compile(m_HTMLTag);
        Matcher m_html = p_html.matcher(html);
        html = m_html.replaceAll("");
        return html;
    }

    private void writeTeamsToFile(ArrayList<Team> teams, String outputFileName)
            throws IOException {

        try {
            File file = new File(outputFileName);

            // use FileWriter to write file
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            if (!file.exists()) {
                file.createNewFile();
            }

            for (Team tempTeam : teams) {
                bw.write(Integer.toString(tempTeam.getRanking()));
                bw.write('\t');
                bw.write(tempTeam.getName());
                bw.write('\t');
                bw.write(Float.toString(tempTeam.getRating()));
                bw.write('\t');
                bw.write(Integer.toString(tempTeam.getWin()));
                bw.write('\t');
                bw.write(Integer.toString(tempTeam.getLoss()));
                bw.write('\n');
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected ArrayList<Team> doInBackground(URL... urls) {

        ArrayList<String> stringsFromURL = new ArrayList<String>();

        try {
            InputStream str = urls[0].openStream();
            InputStreamReader isr = new InputStreamReader(str);
            BufferedReader in = new BufferedReader(isr);

            boolean flag = false;

            String temp;
            while ((temp = in.readLine()) != null) {
                temp = deleteTag(temp);

                if (temp.equals(m_Line))
                    temp = "";
                if (temp.compareTo("By Division") == 0)
                    flag = true;
                if (temp.compareTo("endfile") == 0)
                    flag = false;
                if (flag) {
                    stringsFromURL.add(temp);
                }
            }
            in.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        ParseList listParser = new ParseList();
        ArrayList<Team> teams = listParser.getTeams(stringsFromURL);

        return teams;
    }
}
