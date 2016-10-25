package fi.jamk.sgkygolf;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<GolfCourse> golfCourseList;

    // adapter constructor, get data from activity
    public MyAdapter(List<GolfCourse> employeeList) {
        this.golfCourseList = employeeList;
    }

    // return the size of employeeList (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return golfCourseList.size();
    }

    // create a view for this card
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.golfcourse_card, parent, false);
        return new ViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    // - get element from employeelist at this position
    // - replace the contents of the view with that element
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GolfCourse employee = golfCourseList.get(position);
        //viewHolder.employeeImageView.setImageDrawable();
        viewHolder.employeeNameTextView.setText(employee.name);
        viewHolder.employeePositionTextView.setText(employee.address);
        viewHolder.employeeEmailTextView.setText(employee.email);
        viewHolder.employeePhoneTextView.setText(employee.phone);
    }

    // view holder class to specify card UI objects
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView employeeImageView;
        public TextView employeeNameTextView;
        public TextView employeePositionTextView;
        public TextView employeeEmailTextView;
        public TextView employeePhoneTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            // get layout ids
            employeeImageView = (ImageView) itemView.findViewById(R.id.employeeImageView);
            employeeNameTextView = (TextView) itemView.findViewById(R.id.employeeNameTextView);
            employeePositionTextView = (TextView) itemView.findViewById(R.id.employeePositionTextView);
            employeeEmailTextView = (TextView) itemView.findViewById(R.id.employeeEmailTextView);
            employeePhoneTextView = (TextView) itemView.findViewById(R.id.employeePhoneTextView);
            // add click listner for a card
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String name = golfCourseList.get(position).name;
                    Toast.makeText(view.getContext(), name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



}
