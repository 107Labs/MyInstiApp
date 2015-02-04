package in.aqel.myinstiapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.aqel.myinstiapp.R;
import in.aqel.myinstiapp.Utils.Course;
import in.aqel.myinstiapp.Utils.DatabaseHelper;


public class ViewCoursesAdapter extends BaseAdapter {

    public final static String TAG = "TEST_ADAPTER";

    private Context context;
    private ArrayList<Course> items;
    String strBunks;



    public ViewCoursesAdapter(Context context, ArrayList<Course> items) {

        this.context = context;
        this.items = items;

    }

    

    @Override
    public int getCount() {
        return this.items.size();
    }

    
    @Override
    public Course getItem(int position) {
        return this.items.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Course course = getItem(position);
        final TextView tvSlot, tvTitle, tvNumber,tvBunks;

        Log.d("Course title", course.getTitle());

        View view = convertView;

        if (view == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.single_item_course, parent, false);
            tvSlot = (TextView) view.findViewById(R.id.tvSlot);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvNumber = (TextView) view.findViewById(R.id.tvNumber);
            tvBunks = (TextView) view.findViewById(R.id.tvBunks);

            tvSlot.setText(course.getSlot());
            tvTitle.setText(course.getTitle());
            tvNumber.setText(course.getNumber());

            if (course.getBunks() >9){
                strBunks = Integer.toString(course.getBunks());
            }else {
                strBunks = "0"+Integer.toString(course.getBunks());
            }
            tvBunks.setText(strBunks);

            Button bAdd = (Button) view.findViewById(R.id.bAddBunks);
            bAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper data = new DatabaseHelper(context);
                    data.open();
                    data.incrementBunks(course.getId(), course.getBunks()+1);
                    data.close();
                    course.setBunks(course.getBunks()+1);
                    if (course.getBunks()  >9){
                        strBunks = Integer.toString(course.getBunks());
                    }else {
                        strBunks = "0"+Integer.toString(course.getBunks());
                    }
                    tvBunks.setText(strBunks);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("CLick", "Some one clicked long");
                    return false;
                }
            });
        } else {
            Log.d("null", "view is not null");
            //holder = (ViewHolder) view.getTag();
        }


        return view;
    }

}
