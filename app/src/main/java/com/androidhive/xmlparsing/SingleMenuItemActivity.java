package com.androidhive.xmlparsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SingleMenuItemActivity  extends Activity {
	
	// XML node keys
	static final String KEY_NAME = "name";
	static final String KEY_COST = "cost";
	static final String KEY_DESC = "description";

    //Tehtävään lisätyt muuttujat
    static final String team1_name = "home_team";
    static final String team2_name = "visitor_team";
    static final String goal1_amount = "home_goals";
    static final String goal2_amount = "visitor_goals";




	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String team1_string = in.getStringExtra(team1_name);
        String team2_string = in.getStringExtra(team2_name);
        String goal1_string = in.getStringExtra(goal1_amount);
        String goal2_string = in.getStringExtra(goal2_amount);
        
        // Displaying all values on the screen
        TextView team1 = (TextView) findViewById(R.id.home_team);
        TextView team2 = (TextView) findViewById(R.id.visitor_team);
        TextView goal1 = (TextView) findViewById(R.id.team1_goal_amount);
        TextView goal2 = (TextView) findViewById(R.id.team2_goal_amount);
        
        team1.setText(team1_string);
        team2.setText(team2_string);
        goal1.setText(goal1_string);
        goal2.setText(goal2_string);

        Log.d("SingleMenu", team1_string);
    }
}
