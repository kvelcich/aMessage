package me.kvelcich.messaging.application.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.kvelcich.messaging.R;

public class dialogAddress extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View layout = View.inflate(getActivity(), R.layout.dialog_address, null);
        builder.setView(layout);

        builder.setTitle("Change Address");
        final EditText input = (EditText) layout.findViewById(R.id.address);

        //Load value
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String value = sharedPreferences.getString("Address", "");
        input.setText(value, TextView.BufferType.EDITABLE);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Saving the value written in.
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Address", input.getText().toString());
                editor.apply();

                //TODO: Check if valid

                dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}