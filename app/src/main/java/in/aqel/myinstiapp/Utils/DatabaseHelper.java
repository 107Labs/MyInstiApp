package in.aqel.myinstiapp.Utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "MyInstiApp";
    private static final String DATABASE_TABLE = "Courses";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CLASSROOM = "classroom";
    public static final String KEY_INSTRUCTOR = "instructor";
    public static final String KEY_SLOT = "slot";
    public static final String KEY_CREDITS = "credits";
    public static final String KEY_ISTHEORY = "isTheory";
    public static final String KEY_BUNKS = "bunks";

    String[] columns = new String[] {KEY_ROWID, KEY_NUMBER, KEY_TITLE, KEY_CLASSROOM, KEY_INSTRUCTOR, KEY_SLOT,
            KEY_CREDITS, KEY_ISTHEORY, KEY_BUNKS};



    private static final int DATABASE_VERSION = 2;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub _id INTEGER PRIMARY KEY
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " ( " + KEY_ROWID + " INTEGER " +
                            " PRIMARY KEY , " + KEY_NUMBER + " TEXT NOT NULL UNIQUE , "
                            + KEY_TITLE + " TEXT NOT NULL  , "
                            + KEY_CLASSROOM + " TEXT NOT NULL , " + KEY_INSTRUCTOR +
                            " TEXT , " + KEY_SLOT + " TEXT NOT NULL, " + KEY_CREDITS +
                            " INTEGER NOT NULL , " +
                            KEY_ISTHEORY +" INTEGER NOT NULL , " +
                            KEY_BUNKS +" INTEGER NOT NULL " +
                            " );"
            );



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE  );
            onCreate(db);

        }

    }

    public DatabaseHelper(Context c){
        ourContext = c;
    }

    public DatabaseHelper open(){
        ourHelper = new DbHelper (ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long addCourse(Course course){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NUMBER, course.getNumber());
        cv.put(KEY_TITLE, course.getTitle());
        cv.put(KEY_CLASSROOM, course.getClassRoom());
        cv.put(KEY_INSTRUCTOR, course.getInstructor());
        cv.put(KEY_SLOT, course.getSlot());
        cv.put(KEY_CREDITS, course.getCredits());
        if (course.getIsTheory()){
            cv.put(KEY_ISTHEORY, 1);
        }else {
            cv.put(KEY_ISTHEORY, 0);
        }
        cv.put(KEY_BUNKS, 0);
        long id = ourDatabase.insert(DATABASE_TABLE, null, cv);
        Log.d("id of the added", Long.toString(id));
        return id;
    }

    public Cursor getAllCourses () {
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, KEY_SLOT + " ASC");
        return c;
    }

    public void incrementBunks(int id, int bunks){
        ContentValues cv = new ContentValues();
        cv.put(KEY_BUNKS, bunks);
        ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID+"="+id, null);
    }

 /*   public Cursor getCardById (int id) {
        String[] columns = new String[] {KEY_ROWID, KEY_URL, KEY_TAG, KEY_QCFLAG, KEY_KEYSPACE, KEY_TRUTH, KEY_ANSWERED_OR_NOT, KEY_IMAGE};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + " = " + id, null, null, null,  KEY_ROWID + " ASC");
        return c;
    }

    public Void deleteCardFromQue(int id){
        int count = ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + " = " + id, null);
        Log.d("Number Deleted", Integer.toString(count));
        return null;
    }

*/


}