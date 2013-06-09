package com.example.bankcell;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class OverviewActivity extends Activity {
	
	DatabaseHandler database;
	
	public OverviewActivity(){
		database = new DatabaseHandler(null);
	}

	static final String[] letters = new String[] { 
		"Car", "Rent", "Insurance",
		"School", "Food", "Beer"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		setClickHandlers();
		GridView gridView = (GridView)this.findViewById(R.id.grid);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, letters);
 
		gridView.setAdapter(adapter);
	}
	
	public void setClickHandlers(){
		Button btn = (Button)this.findViewById(R.id.home);
		btn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        toHome(v);
		    }
		});
		
		Button newBtn = (Button)this.findViewById(R.id.newBtn);
		newBtn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        newCell();
		    }
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.overview, menu);
		return true;
	}
	
	public void toHome(View v) {
		Intent myIntent=new Intent(this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }
	
	public void newCell(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
 
			// set title
			alertDialogBuilder.setTitle("Create A New Bank Account");
			final EditText input = new EditText(this);
			alertDialogBuilder.setView(input);

			// set dialog message
			alertDialogBuilder
				.setMessage("Click yes to exit!")
				.setCancelable(false)
				.setPositiveButton("Done",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						database.
					}
				  })
				.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});
 
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				alertDialog.show();
	}

}
