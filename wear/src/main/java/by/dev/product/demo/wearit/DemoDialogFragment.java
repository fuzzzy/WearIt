package by.dev.product.demo.wearit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DemoDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_text)
                .setPositiveButton("v", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       // showConfirmation(getString(R.string.confirmation_text));
                    }
                });

        return builder.create();
    }
}