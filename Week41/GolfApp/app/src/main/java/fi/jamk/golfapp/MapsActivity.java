package fi.jamk.golfapp;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static fi.jamk.golfapp.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private JSONArray golfcourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);

        FetchDataTask task = new FetchDataTask();
        task.execute("http://ptm.fi/jamk/android/golf_courses.json");
        mapFragment.getMapAsync(this);

        // Setting a custom info window adapter for the google map



    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
        //mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);



    }

    class FetchDataTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            JSONObject json = null;
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
                for (int i=0;i < golfcourses.length();i++) {
                    JSONObject hs = golfcourses.getJSONObject(i);
                    LatLng latlng = new LatLng(hs.getDouble("lat"),hs.getDouble("lng"));
                    final Marker markers = mMap.addMarker(new MarkerOptions()
                            .position(latlng)
                            .title(hs.getString("Kentta")+"\n" + hs.getString("Osoite") +"\n"+ hs.getString("Puhelin") +"\n"+ hs.getString("Sahkoposti") ));

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

                }
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        View v = getLayoutInflater().inflate(R.layout.popup, null);

                        TextView addr= (TextView) v.findViewById(R.id.osoite);

                        TextView phone= (TextView) v.findViewById(R.id.puhelin);

                        TextView email= (TextView) v.findViewById(R.id.sposti);

                        addr.setText(marker.getSnippet());

                        phone.setText(marker.getSnippet());
                        email.setText(marker.getSnippet());

                        return v;
                    }
                });


            } catch (JSONException e) {
                Log.e("JSON", "Error getting data.");
            }

        }
    }


}
