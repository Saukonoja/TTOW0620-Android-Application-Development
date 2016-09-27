package fi.jamk.handson39;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button asyncTaskButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find our button
        asyncTaskButton = (Button) findViewById(R.id.button);
        // find our progressbar
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);

    }

    public void asynctaskButtonClicked(View v) {
        new MyTask().execute();
    }

    public void button2Clicked(View v){
        Intent intent = new Intent(this,SecondaryActivity.class);
        startActivity(intent);
    }

    private class MyTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute(){
            // do something before background thread is launched
            // you can modify UI from here
            asyncTaskButton.setEnabled(false);
        }

        protected Void doInBackground(Void... params){
            // "loop" to demnostrate "loading" with progressbar
            for (int i=1; i<=100;i++){
                // we want to show this number in progressbar
                publishProgress(i);
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    Log.e("HANDSON39", "Background thread is interrupted");
                }
            }
            return null;
        }
        // Here you can modify UI
        @Override
        protected void onProgressUpdate(Integer... params){
            progressBar.setProgress(params[0]);
        }


        // called after doInBackground is finished
        protected void onPostExecute(Void params) {
            asyncTaskButton.setEnabled(true);
        }
    }
}
