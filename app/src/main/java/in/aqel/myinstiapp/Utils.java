package in.aqel.myinstiapp;

import android.util.Log;

/**
 * Created by aqel on 31/1/15.
 */
public class Utils {

    public Boolean RollNumberValidator(String string){
        if (string.length() != 8){
            return false;
        }
        String year = string.substring(2,4);
        String number = string.substring(5,8);
        Log.d("Year " + year, "Number " + number);

        if (year.matches("[0-9]+") == false || number.matches("[0-9]+") == false ){
            return false;
        }

        Boolean valid = false;
        String department = string.substring(0,2).toUpperCase();

        for (int i = 0; i < Constants.departmentsShortForm.length; i++ ){
            if ( Constants.departmentsShortForm[i].equals(department)){
                valid = true;
            }
        }
        return valid;
    }
}
