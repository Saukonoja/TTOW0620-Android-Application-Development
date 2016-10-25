package fi.jamk.sgkygolf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
            task.execute("http://ptm.fi/jamk/android/golfcourses/golf_courses.json");
            json = FetchDataTask.getJSON();
            golfCourses.initializeData(json);
            mEmployeeList = MyAdapter.golfCourseList;

            // connect recycler view
            mRecyclerView = (RecyclerView) findViewById(R.id.employeeRecyclerView);
            // create layoutmanager
            mLayoutManager = new LinearLayoutManager(this);
            // set manager to recycler view
            mRecyclerView.setLayoutManager(mLayoutManager);
            // create adapter
            mAdapter = new EmployeesAdapter(mEmployeeList);
            // set adapter to recycler view
            mRecyclerView.setAdapter(mAdapter);

            // remove card with swiping left or right
            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    // remove data
                    mEmployeeList.remove(viewHolder.getAdapterPosition());
                    // update adapter
                    mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }

                @Override
                public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                    super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                }
            };

            // connect item touch helper to recycler view
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(mRecyclerView);

            // add a new random card from employees
            FloatingActionButton myFab = (FloatingActionButton)  findViewById(R.id.fab);
            myFab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    employees.addEmployee();
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                }
            });

        }