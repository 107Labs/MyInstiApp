package in.aqel.myinstiapp.Utils;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by aqel on 1/2/15.
 */
public class Course {
    String number, title, classRoom, instructor, slot;
    int credits, id;
    Context context;

    public Course() {

    }

    public Course(Context context) {
        this.context = context;
    }

    public int getBunks() {
        return bunks;
    }

    public void setBunks(int bunks) {
        this.bunks = bunks;
    }

    int bunks;
    Boolean isTheory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {


        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Boolean getIsTheory() {
        return isTheory;
    }

    public void setIsTheory(Boolean isTheory) {
        this.isTheory = isTheory;
    }




    public ArrayList<Course> getDataFromCursor (){
        ArrayList<Course> alCourses = new ArrayList<>();

        DatabaseHelper  data = new DatabaseHelper(context);
        data.open();
        Cursor c = data.getAllCourses();
        while (c.moveToNext()){
            Course course = new Course();
            course.setId(c.getInt(0));
            course.setNumber(c.getString(1));
            course.setTitle(c.getString(2));
            course.setClassRoom(c.getString(3));
            course.setInstructor(c.getString(4));
            course.setSlot(c.getString(5));
            course.setCredits(c.getInt(6));
            if (c.getInt(7)==1){
                course.setIsTheory(true);
            }else{
                course.setIsTheory(false);
            }
            course.setBunks(c.getInt(8));

            alCourses.add(course);
        }
        data.close();

        return  alCourses;

    }

}
