package fi.jamk.sgkygolf;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchDataTask extends AsyncTask<String, Void, JSONObject> {

    JSONObject json = null;
    private JSONArray golfcourses;
    private List<GolfCourse> golfcoureslist;


    public AsyncJsonResponse delegate = null;



    @Override
    protected JSONObject doInBackground(String... urls) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            json = new JSONObject(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }
        return json;
    }

    protected void onPostExecute(JSONObject json) {
        try {
            golfcourses = json.getJSONArray("kentat");
            golfcoureslist = new ArrayList<>();
            for (int i=0;i < golfcourses.length();i++) {
                JSONObject hs = golfcourses.getJSONObject(i);

                golfcoureslist.add(new GolfCourse(hs.getString("Kentta"), hs.getString("Osoite"), hs.getString("Sahkoposti"), hs.getString("Puhelin"), hs.getString("Webbi"), hs.getString("Kuva")));

            }

        } catch (JSONException e) {
            Log.e("JSON", "Error getting data.");
        }

        delegate.onFetchDataTaskComplete(golfcoureslist);
    }

}