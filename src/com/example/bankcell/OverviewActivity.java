package com.example.bankcell;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class OverviewActivity extends Activity implements
AdapterView.OnItemClickListener {
	
	DatabaseHandler database;
	public String[] cells;
	
	public OverviewActivity(){
		database = new DatabaseHandler(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		setClickHandlers();
		setCells();
		setBalance();
			
	}
	
	public void setBalance(){
		TextView balance = (TextView)this.findViewById(R.id.transactionHistory);
		balance.setText(database.getTotalBalance());
	}
	
	public void setCells(){
		GridView gridView = (GridView)this.findViewById(R.id.grid);
		
		cells = database.getCells();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, cells);
 
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);

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
		
		Button depBtn = (Button)this.findViewById(R.id.depositButton);
		depBtn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        deposit();
		    }
		});
		
	}
	
	public void deposit(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		 
		alertDialogBuilder.setTitle("Deposit Money");
		final EditText input = new EditText(this);
		alertDialogBuilder.setView(input);

		alertDialogBuilder
			.setMessage("Deposit money into general account")
			.setCancelable(false)
			.setPositiveButton("Done",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					database.createDeposit(Float.valueOf(input.getText().toString()));
					finish();
					startActivity(getIntent());
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
 
			alertDialogBuilder.setTitle("Create A New Bank Account");
			final EditText input = new EditText(this);
			alertDialogBuilder.setView(input);

			alertDialogBuilder
				.setMessage("You will be able to transfer money to and from this account.")
				.setCancelable(false)
				.setPositiveButton("Done",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						database.createCell(input.getText());
						finish();
						startActivity(getIntent());
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Intent myIntent=new Intent(this, CellStatusActivity.class);
		myIntent.putExtra("title", cells[position]);
        startActivity(myIntent);
        finish();
		
	}

}
