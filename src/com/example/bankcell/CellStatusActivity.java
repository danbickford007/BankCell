package com.example.bankcell;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CellStatusActivity extends Activity {
	
	public DatabaseHandler database;
	public String cellTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cell_status);
		TextView title = (TextView)this.findViewById(R.id.title);
		cellTitle = getIntent().getExtras().getString("title");
		title.setText(cellTitle);
		setClickHandlers();
		database = new DatabaseHandler(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cell_status, menu);
		return true;
	}
	
	public void setClickHandlers(){
		Button back = (Button)this.findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        back();
		    }
		});
		Button delete = (Button)this.findViewById(R.id.delete);
		delete.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        delete();
		    }
		});
	}
	
	public void back(){
		Intent myIntent=new Intent(this, OverviewActivity.class);
        startActivity(myIntent);
        finish();
	}
	
	public void delete(){
		database.destroyCell(cellTitle);
		Intent myIntent=new Intent(this, OverviewActivity.class);
        startActivity(myIntent);
        finish();
	}

}
