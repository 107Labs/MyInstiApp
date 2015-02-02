package in.aqel.myinstiapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class NewCourse extends ActionBarActivity {
    Spinner spClassRoom, spSlot, spCourse, spIsTheory;
    ArrayAdapter<String> adapterSlot, adapterIsTheoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        initialize();
        spSlot.setAdapter(adapterSlot);
        spIsTheory.setAdapter(adapterIsTheoy);
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
