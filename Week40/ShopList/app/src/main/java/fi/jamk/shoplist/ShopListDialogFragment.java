package fi.jamk.shoplist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ShopListDialogFragment extends DialogFragment {

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String product, int count, float price);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    DialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // create a new AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View dialogView = inflater.inflate(R.layout.dialog, null);
        builder.setView(dialogView)
                .setTitle("Add a new Highscore")
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // find a name and score
                        EditText editName = (EditText) dialogView.findViewById(R.id.product);
                        String name = editName.getText().toString();
                        EditText editScore = (EditText) dialogView.findViewById(R.id.count);
                        int score = Integer.valueOf(editScore.getText().toString());
                        // Send the positive button event back to the host activity
                        mListener.onDialogPositiveClick(ShopListDialogFragment.this,product,count,price);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        mListener.onDialogNegativeClick(ShopListDialogFragment.this);
                    }
                });
        return builder.create();
    }
}
