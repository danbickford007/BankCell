package com.example.bankcell;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

public class Prompt {
	
	public void create(Context context, final DatabaseHandler database){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		 
		alertDialogBuilder.setTitle("Create A New Bank Account");
		final EditText input = new EditText(context);
		alertDialogBuilder.setView(input);

		alertDialogBuilder
			.setMessage("Click yes to exit!")
			.setCancelable(false)
			.setPositiveButton("Done",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					database.createCell(input.getText());
					//finish();
					//startActivity(getIntent());
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
