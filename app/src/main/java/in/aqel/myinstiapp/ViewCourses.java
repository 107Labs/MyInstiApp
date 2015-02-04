package in.aqel.myinstiapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import in.aqel.myinstiapp.Utils.Course;
import in.aqel.myinstiapp.Utils.DatabaseHelper;
import in.aqel.myinstiapp.adapter.ViewCoursesAdapter;


public class ViewCourses extends ActionBarActivity {
    ListView list;
    DatabaseHelper data;
    Cursor cur;
    ArrayList<Course> alCourses;
    Course course;
    ViewCoursesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_courses);

        list = (ListView) findViewById(R.id.lvCourses);

        course = new Course(this);
        alCourses =  course.getDataFromCursor();

        adapter = new ViewCoursesAdapter(this, alCourses);
        list.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_veiw_courses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent(ViewCourses.this, NewCourse.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
