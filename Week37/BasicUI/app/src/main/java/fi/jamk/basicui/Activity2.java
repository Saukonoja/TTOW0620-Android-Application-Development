package fi.jamk.basicui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

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

    public void loginButtonClicked(View view){
        AutoCompleteTextView loginText = (AutoCompleteTextView) findViewById(R.id.editText);
        EditText passText = (EditText) findViewById(R.id.editText2);

        String uname = loginText.getText().toString();
        String pass = passText.getText().toString();

        Toast.makeText(getApplicationContext(), uname +  " " + pass, Toast.LENGTH_LONG).show();


    }

}
