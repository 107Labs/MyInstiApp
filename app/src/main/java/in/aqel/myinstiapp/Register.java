package in.aqel.myinstiapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Register extends ActionBarActivity  {

    EditText etEmail;
    Spinner hostels;
    String rollNumber, nick, name, hostel, email;
    Boolean ValidRollNumber;
    Utils utils = new Utils();
    JSONParser jsonParser;
    int loginStatus;
    SharedPreferences spLogin;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spLogin = getSharedPreferences("login", MODE_PRIVATE);
        loginStatus = spLogin.getInt("loginStatus", 0);
        if (loginStatus == 2){
            intent = new Intent(Register.this, NewCourse.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        setContentView(R.layout.activity_register);
        hostels = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
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
        email = accounts[0].name;
        etEmail = (EditText) findViewById(R.id.email);
        etEmail.setText(email);





        (findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollNumber = ((EditText) findViewById(R.id.roll_no)).getText().toString().toUpperCase();
                name = ((EditText) findViewById(R.id.name)).getText().toString();
                nick = ((EditText) findViewById(R.id.nick)).getText().toString();
                hostel = hostels.getSelectedItem().toString();
                ValidRollNumber = utils.RollNumberValidator(rollNumber);

                if ( !rollNumber.isEmpty() && !name.isEmpty() && !nick.isEmpty() && !hostel.equals("Hostel")){
                    if (ValidRollNumber){
                        Log.d("Register", "Cool");
                        new registerUser().execute();
                    } else {
                        Log.d("Register", "wrong roll number");
                        Toast.makeText(Register.this, "Invalid Roll Number", Toast.LENGTH_SHORT);
                    }
                }else{
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT);
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


    public class registerUser extends AsyncTask<Void, Void, Void> {
        JSONObject mainJSON;
        String url;
        int status;
        ProgressDialog pDialog;
        User user;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Loggin in");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... param) {
            jsonParser = new JSONParser();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("hostel", hostel));
            params.add(new BasicNameValuePair("roll_number", rollNumber));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("nick", nick));
            params.add(new BasicNameValuePair("email", email));
            JSONObject mainJSON =  jsonParser.makeHttpRequest("register.php", "POST", params, null);

            try {
                Log.d("JSON", mainJSON.toString());
                status = mainJSON.getInt("status");
                if (status == 1){
                    user = new User(Register.this);
                    user.rollNumber = rollNumber;
                    user.name = name;
                    user.hostel = hostel;
                    user.nick = nick;
                    user.email = email;
                    user.saveUser();
                    SharedPreferences spLogin = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = spLogin.edit();
                    editor.putInt("loginStatus", 2);
                    editor.commit();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();
            if (status == 1){
                intent = new Intent(Register.this, NewCourse.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        }
    };

}