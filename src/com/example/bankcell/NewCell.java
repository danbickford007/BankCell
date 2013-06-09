package com.example.bankcell;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class NewCell extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("THIS IS A TEST")
               .setPositiveButton("TEST BUTTON", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                   }
               })
               .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        return builder.create();

    }
}
