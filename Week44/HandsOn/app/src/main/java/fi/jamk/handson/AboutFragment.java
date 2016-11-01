package fi.jamk.handson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle ssavedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("About Fragment used");
        return rootView;
    }
}
