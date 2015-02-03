package in.aqel.myinstiapp.Utils;

/**
 * Created by aqel on 1/2/15.
 */
public class Course {
    String number, title, classRoom, instructor, slot;
    int credits;
    Boolean isTheory;

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
}
