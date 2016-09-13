package fi.jamk.basicui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        AutoCompleteTextView actv = (AutoCompleteTextView)
                findViewById(R.id.editText); // add stings to control
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]
                        {"Pasi","Juha","Kari","Jouni","Esa","Hannu"});
        actv.setAdapter(aa);

    }

    public void loginButtonClicked(){
        AutoCompleteTextView loginText = (AutoCompleteTextView) FindViewByID(R.id.editText);
        TextView textview = (TextView) FindViewByID(R.id.editText2);

    }

}
