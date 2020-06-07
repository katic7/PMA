package ftn.tim34.weplay.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class LocationDialog extends AlertDialog.Builder {

    private Integer which;

    public LocationDialog(@NonNull Context context) {
        super(context);
        if (which == null) {
            setUpDialog(1);
        } else {
            setUpDialog(which);
        }

    }


    private void setUpDialog(Integer w) {
        setTitle("Lokacija");

        switch (w) {
            case 1:
                setMessage("Molim Vas da odobrite lokaciju");
            case 2:
                setMessage("Molim Vas da odobrite lokaciju");
        }


        setCancelable(false);

        setPositiveButton("Potvrdi", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getContext().startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                dialog.dismiss();
            }
        });

        setNegativeButton("Odbij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

    }

    public AlertDialog prepareDialog(Integer i) {

        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }


}