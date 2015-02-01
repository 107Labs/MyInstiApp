package in.aqel.myinstiapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Arun Padiyan on 01-Feb-15.
 */
public class AddCourse extends Activity{
    Spinner slots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        slots = (Spinner) findViewById(R.id.slot_spinner);
     /*
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hostels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slots.setAdapter(adapter);  */
        ArrayAdapter<String> adapter =SpinnerAdapter(getBaseContext(),R.array.slots);
        slots.setAdapter(adapter);
        slots.setSelection(adapter.getCount());
    }
    public ArrayAdapter<String> SpinnerAdapter(Context cont,int Array){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(cont, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        String[] array = getResources().getStringArray(Array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for(int i = 0;i<array.length;i++){
            adapter.add(array[i]);
        }
        return adapter;
    }
}
