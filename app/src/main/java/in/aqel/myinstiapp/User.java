package in.aqel.myinstiapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aqel on 1/2/15.
 */
public class User  {
    public User( Context context) {
        this.context = context;
    }

    Context context;
    String name, email, rollNumber, nick, hostel;
    SharedPreferences spUser;

    public void saveUser( ){
        spUser =  context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spUser.edit();
        editor.putString("email", email);
        editor.putString("name", name);
        editor.putString("rollNumber", rollNumber);
        editor.putString("niint loginStatus;ck", nick);
        editor.putString("hostel", hostel);
        editor.commit();
    }

    public void loadUser(){
        email = spUser.getString("email", email);
        name = spUser.getString("name", name);
        rollNumber = spUser.getString("rollNumber", rollNumber);
        nick = spUser.getString("nick", nick);
        hostel = spUser.getString("hostel", hostel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNumberm() {
        return rollNumber;
    }

    public void setRollNumberm(String rollNumberm) {
        this.rollNumber = rollNumberm;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }
}
