package com.example.shreyakothari.sportsapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.util.concurrent.ExecutionException;

public class SportsAppActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sportSelectionSpinner;
    private Spinner team1SelectionSpinner;
    private Spinner team2SelectionSpinner;
    private ArrayList<Team> teams = null;
    private String selectTeamString = "SELECT TEAM";
    private String selectSportString = "SELECT SPORT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_app);
        addItemsToSportSpinner();
    }

    // Add items to Spinner dynamically
    public void addItemsToTeamSpinner(ArrayList<Team> teams) {

        team1SelectionSpinner = (Spinner)findViewById(R.id.team1Spinner);
        team2SelectionSpinner = (Spinner)findViewById(R.id.team2Spinner);

        List<String> teamNames = new ArrayList<String>();

        teamNames.add(selectTeamString);
        for (int i = 0; i < teams.size(); i++){
            teamNames.add(teams.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, teamNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        team1SelectionSpinner.setAdapter(dataAdapter);
        team2SelectionSpinner.setAdapter(dataAdapter);
    }

    public void addItemsToSportSpinner() {
        sportSelectionSpinner = (Spinner)findViewById(R.id.sportSpinner);
        List<String> sportList = new ArrayList<String>();
        sportList.add(selectSportString);
        sportList.add("NBA");
        sportList.add("NHL");
        sportList.add("College Football");
        sportList.add("College Basketball");
        sportList.add("MLS Soccer");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sportList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sportSelectionSpinner.setAdapter(dataAdapter);

        addListenerOnSpinnerItemSelection();
    }

    public void addListenerOnSpinnerItemSelection() {
        sportSelectionSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

        String selectedSport = parent.getItemAtPosition(pos).toString();

        if (!selectedSport.equals("SELECT SPORT")) {
            SportsURL su = new SportsURL();
            URL url = su.getURLFromSport(selectedSport);

            try {
                teams = new ParseURL().execute(url).get();
                addItemsToTeamSpinner(teams);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else {

            Toast.makeText(getApplicationContext(), "Please select sport of your interest !!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onButtonClick(View v) {
        if(teams != null) {
            String team1 = team1SelectionSpinner.getSelectedItem().toString();
            String team2 = team2SelectionSpinner.getSelectedItem().toString();

            if(!team1.equals(selectTeamString) && !team2.equals(selectTeamString)) {

                if(!team1.equals(team2)) {
                    ArrayList<Team> tempTeams = new ArrayList<Team>();
                    for (int i = 0; i < teams.size(); i++) {
                        String str = teams.get(i).getName();
                        if (str.equals(team1) || str.equals(team2)) {
                            tempTeams.add(teams.get(i));
                        }
                    }

                    try {
                        Intent i = new Intent(getApplicationContext(), TeamStatisticsActivity.class);
                        i.putParcelableArrayListExtra("teams", tempTeams);
                        startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else {
                    Toast.makeText(getApplicationContext(), "Team names cannot be same. Select a different team!!",
                            Toast.LENGTH_LONG).show();
                }
            }

            else {
                Toast.makeText(getApplicationContext(), "Please select teams !!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sports_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
