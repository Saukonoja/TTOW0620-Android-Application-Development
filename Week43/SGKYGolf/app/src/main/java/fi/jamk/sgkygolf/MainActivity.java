package fi.jamk.sgkygolf;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Window;

import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity implements AsyncJsonResponse {
        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        private List<GolfCourse> mGolfList;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_main);

            FetchDataTask task = new FetchDataTask();
            task.response = this;
            task.execute("http://ptm.fi/jamk/android/golfcourses/golf_courses.json");

            mRecyclerView = (RecyclerView) findViewById(R.id.golfRecyclerView);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            CollapsingToolbarLayout collapsingToolbarLayout;
            collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbarLayout.setTitle("SGKY - Kentät");
        }

        @Override
        public void onFetchDataTaskComplete(List<GolfCourse> golfCourseList){
            this.mGolfList = golfCourseList;
            mAdapter = new MyAdapter(this, mGolfList);
            mRecyclerView.setAdapter(mAdapter);
        }
}