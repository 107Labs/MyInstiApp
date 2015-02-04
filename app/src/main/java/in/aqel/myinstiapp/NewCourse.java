package in.aqel.myinstiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import in.aqel.myinstiapp.Utils.Constants;
import in.aqel.myinstiapp.Utils.Course;
import in.aqel.myinstiapp.Utils.DatabaseHelper;


public class NewCourse extends ActionBarActivity {
    Spinner spClassRoom, spSlot, spCourse, spIsTheory;
    ArrayAdapter<String> adapterSlot, adapterIsTheoy, adapterClassRoom, adapterDept;
    Button bNext;
    EditText etTitle, etCourse, etCredits, etProf, etClass;
    String title, courseNumber, prof, classRoom, slot;
    int credits;
    Boolean isTheory = true;
    Course course ;
    Boolean validArgs;
    DatabaseHelper data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        initialize();
        spSlot.setAdapter(adapterSlot);
        spIsTheory.setAdapter(adapterIsTheoy);
        spCourse.setAdapter(adapterDept);
        spClassRoom.setAdapter(adapterClassRoom);

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getArguments();
                validArgs = validateArgs();
                if (validArgs){
                    credits = Integer.parseInt(etCredits.getText().toString());
                          //  Integer.getInteger(etCredits.getText().toString());
                    setObject();
                    data = new DatabaseHelper(NewCourse.this);
                    data.open();
                    data.addCourse(course);
                    data.close();
                    SharedPreferences spLogin = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = spLogin.edit();
                    editor.putInt("loginStatus", 1);
                    editor.commit();
                    Intent intent = new Intent(NewCourse.this, ViewCourses.class);
                    startActivity(intent);
                    finish();
                    System.exit(0);

                }else{
                    Toast.makeText(NewCourse.this, "Fill every fieldS", Toast.LENGTH_SHORT);
                }
            }
        });

    }

    private void setObject() {
        course = new Course();
        course.setNumber(courseNumber);
        course.setTitle(title);
        course.setClassRoom(classRoom);
        course.setInstructor(prof);
        course.setCredits(credits);
        course.setIsTheory(isTheory);
        course.setSlot(slot);
    }



    private void getArguments() {
        courseNumber = spCourse.getSelectedItem().toString() + " " + etCourse.getText().toString();
        title = etTitle.getText().toString();
        classRoom = spClassRoom.getSelectedItem().toString() + " " + etClass.getText().toString();
        prof = etProf.getText().toString();
        slot = spSlot.getSelectedItem().toString();

        if (spIsTheory.getSelectedItem().toString().equals("Theory")){
            isTheory = true;
        }else {
            isTheory = false;
        }
    }

    private Boolean validateArgs() {
        if ((spSlot.getSelectedItem().toString().equals("Slot")) || title.isEmpty() || etCredits.getText().toString().isEmpty()
                || spClassRoom.getSelectedItem().toString().isEmpty()) {
            return  false;
        }else return true;
    }

    private void initialize() {
        spClassRoom = (Spinner) findViewById(R.id.spClassRoom);
        spSlot = (Spinner) findViewById(R.id.spSlot);
        spCourse = (Spinner) findViewById(R.id.spCourse);
        String[] array = getResources().getStringArray(R.array.slots);
        adapterSlot = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array);
        spIsTheory = (Spinner) findViewById(R.id.spIsTheory);
        String[] arrayIsTheory = {"Theory", "Lab"};
        adapterIsTheoy = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayIsTheory);
        String[] arrayBuilding = Constants.buildings;
        adapterClassRoom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayBuilding);
        String[] arrayDept = Constants.departmentsCourses;
        adapterDept = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayDept);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etCourse = (EditText) findViewById(R.id.etCourse);
        etCredits = (EditText) findViewById(R.id.etCredits);
        etProf = (EditText) findViewById(R.id.etProf);
        etClass = (EditText) findViewById(R.id.etClass);
        bNext = (Button) findViewById(R.id.bNext);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
