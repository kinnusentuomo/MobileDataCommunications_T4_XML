package com.androidhive.xmlparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AndroidXMLParsingActivity extends ListActivity {

	// All static variables
	//static final String URL = "http://api.androidhive.info/pizza/?format=xml";
	static final String URL = "http://172.20.240.4:7002/";
	// XML node keys
	static final String KEY_ITEM = "item"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";
	static final String KEY_COST = "cost";
	static final String KEY_DESC = "description";



	//Tehtävään lisätyt muuttujat
	static final String team1_name = "home_team";
	static final String team2_name = "visitor_team";
	static final String goal1_amount = "home_goals";
	static final String goal2_amount = "visitor_goals";
	static final String KEY_MATCH = "match";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_MATCH);
		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			/*
			map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
			map.put(KEY_COST, "Rs." + parser.getValue(e, KEY_COST));
			map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
			*/

			map.put(team1_name, parser.getValue(e, team1_name));
			map.put(team2_name, parser.getValue(e, team2_name));
			map.put(goal1_amount, parser.getValue(e, goal1_amount));
			map.put(goal2_amount, parser.getValue(e, goal2_amount));

			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems,
				R.layout.list_item,
				//new String[] { KEY_NAME, KEY_DESC, KEY_COST }, new int[] {
				new String[] { team1_name, team2_name, goal1_amount, goal2_amount }, new int[] {
						R.id.team1, R.id.team2, R.id.goal1, R.id.goal2 });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String team1_name_string = ((TextView) view.findViewById(R.id.team1)).getText().toString();
				String team2_name_string = ((TextView) view.findViewById(R.id.team2)).getText().toString();
				String team1_goal_string = ((TextView) view.findViewById(R.id.goal1)).getText().toString();
				String team2_goal_string = ((TextView) view.findViewById(R.id.goal2)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				/*
				in.putExtra(KEY_NAME, name);
				in.putExtra(KEY_COST, cost);
				in.putExtra(KEY_DESC, description);
				*/

				in.putExtra(team1_name, team1_name_string);
				in.putExtra(team2_name, team2_name_string);
				in.putExtra(goal1_amount, team1_goal_string);
				in.putExtra(goal2_amount, team2_goal_string);


				Log.d(team1_name, team2_name);
				startActivity(in);

			}
		});
	}
}