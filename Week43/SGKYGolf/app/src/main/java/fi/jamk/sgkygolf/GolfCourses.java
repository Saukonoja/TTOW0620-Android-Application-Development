package fi.jamk.sgkygolf;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H3694 on 25.10.2016.
 */

public class GolfCourses {

    private JSONArray golfcourses;
    private List<GolfCourse> golfcoureslist;


    // just fake some data
    public void initializeData(JSONObject json) {

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
    }

    // return employee list
    public List<GolfCourse> getGolfcourses() {
        return golfcoureslist;
    }

}
