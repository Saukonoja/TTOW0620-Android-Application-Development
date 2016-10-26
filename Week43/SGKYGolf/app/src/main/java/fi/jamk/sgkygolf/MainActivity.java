package fi.jamk.sgkygolf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity {
        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        private List<GolfCourses> mEmployeeList;
        private JSONObject json;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // fake data
            final GolfCourses golfCourses = new GolfCourses();
            FetchDataTask task = new FetchDataTask();
            task.delegate = (AsyncJsonResponse) this;
            task.execute("http://ptm.fi/jamk/android/golfcourses/golf_courses.json");

            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);


        }
}