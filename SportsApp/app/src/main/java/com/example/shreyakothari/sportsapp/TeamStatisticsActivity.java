package com.example.shreyakothari.sportsapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;


public class TeamStatisticsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_statistics);

        ArrayList<Team> teams = getIntent().getParcelableArrayListExtra("teams");
        fillDetails(teams);
    }

    private void fillDetails(ArrayList<Team> teams) {
        try {
            TextView txtRank1 = (TextView)findViewById(R.id.txtViewRank1);
            txtRank1.setText(Integer.toString(teams.get(0).getRanking()));

            TextView txtName1 = (TextView)findViewById(R.id.txtViewTeam1);
            String team1 = teams.get(0).getName();
            txtName1.setText(team1);

            TextView txtWin1 = (TextView)findViewById(R.id.txtViewWins1);
            txtWin1.setText(Integer.toString(teams.get(0).getWin()));

            TextView txtLoss1 = (TextView)findViewById(R.id.txtViewLoss1);
            txtLoss1.setText(Integer.toString(teams.get(0).getLoss()));

            float winPercentageT1 = calculateWinPercent(teams.get(0).getWin(), teams.get(0).getLoss());

            TextView txtRank2 = (TextView)findViewById(R.id.txtViewRank2);
            txtRank2.setText(Integer.toString(teams.get(1).getRanking()));

            TextView txtName2 = (TextView)findViewById(R.id.txtViewTeam2);
            String team2 = teams.get(1).getName();
            txtName2.setText(team2);

            TextView txtWin2 = (TextView)findViewById(R.id.txtViewWins2);
            txtWin2.setText(Integer.toString(teams.get(1).getWin()));

            TextView txtLoss2 = (TextView)findViewById(R.id.txtViewLoss2);
            txtLoss2.setText(Integer.toString(teams.get(1).getLoss()));

            float winPercentageT2 = calculateWinPercent(teams.get(1).getWin(), teams.get(1).getLoss());

            String winningTeam;
            String losingTeam;

            if(winPercentageT1 > winPercentageT2){
                winningTeam = team1;
                losingTeam = team2;
            }

            else {
                winningTeam = team2;
                losingTeam = team1;
            }

            String msg = "Statistics say " + winningTeam + " will win if they play against " + losingTeam;
            TextView txtResult = (TextView)findViewById(R.id.txtViewResult);
            txtResult.setText(msg);
        } catch (UnknownError e) {
            e.printStackTrace();
        }

    }

    private float calculateWinPercent(int win, int loss) {
        return ((float)win / (win + loss)) * 100;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_statistics, menu);
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
