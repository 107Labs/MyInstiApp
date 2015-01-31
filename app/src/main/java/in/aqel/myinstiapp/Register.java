package in.aqel.myinstiapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Register extends ActionBarActivity  {

    static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
    EditText etNick, etName, etEmail, etRollNumber;
    Spinner hostels;
    String rollNumber, nick, name;
    Boolean ValidRollNumber;
    Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        hostels = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hostels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hostels.setAdapter(adapter);

        Account[] accounts = AccountManager.get(this).getAccounts();
/*       Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;

                Log.d("Emails are ", possibleEmail);
            }
        }
        */
        String email = accounts[0].name;
        etEmail = (EditText) findViewById(R.id.email);
        etEmail.setText(email);



        (findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollNumber = ((EditText) findViewById(R.id.roll_no)).getText().toString();
                ValidRollNumber = utils.RollNumberValidator(rollNumber);
                Log.d("Roll Number", rollNumber);
                if (ValidRollNumber){
                    Toast.makeText(Register.this, "Valid Roll Number", Toast.LENGTH_SHORT);
                    Log.d("Roll Number", "Correct");
                } else {
                    Toast.makeText(Register.this, "Invalid Roll Number", Toast.LENGTH_SHORT);
                    Log.d("Roll Number", "Wrong");

                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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