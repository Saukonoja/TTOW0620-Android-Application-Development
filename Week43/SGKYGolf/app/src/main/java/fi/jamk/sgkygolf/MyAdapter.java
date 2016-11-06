package fi.jamk.sgkygolf;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<GolfCourse> golfCourseList;
    private Activity context;

    public MyAdapter(Activity context, List<GolfCourse> golfCourseList) {
        this.golfCourseList = golfCourseList;
        this.context = context;
    }


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

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GolfCourse golfCourse = golfCourseList.get(position);
        Glide.with(this.context)
                .load("http://ptm.fi/jamk/android/golfcourses/" + golfCourse.photoId)
                .override(200,170)
                .into(viewHolder.golfImageView);
        viewHolder.golfNameTextView.setText(golfCourse.name);
        viewHolder.golfAddressTextView.setText(golfCourse.address);
        viewHolder.golfEmailTextView.setText(golfCourse.email);
        viewHolder.golfPhoneTextView.setText(golfCourse.phone);
    }

    // view holder class to specify card UI objects
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView golfImageView;
        public TextView golfNameTextView;
        public TextView golfAddressTextView;
        public TextView golfEmailTextView;
        public TextView golfPhoneTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            // get layout ids
            golfImageView = (ImageView) itemView.findViewById(R.id.golfImageView);
            golfNameTextView = (TextView) itemView.findViewById(R.id.golfNameTextView);
            golfAddressTextView = (TextView) itemView.findViewById(R.id.golfAddressTextView);
            golfEmailTextView = (TextView) itemView.findViewById(R.id.golfEmailTextView);
            golfPhoneTextView = (TextView) itemView.findViewById(R.id.golfPhoneTextView);
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
