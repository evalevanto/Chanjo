package eva.example.com.chanjo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by eva on 10/4/16.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 11;
    //first table for registeration
    private static final String DATABASE_NAME = "Chanjo.db";
    private static final String DATABASE_TABLE = "Registeration";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_USER = "Username";
    private static final String COLUMN_PHONE = "Phone";
    private static final String COLUMN_PASS = "Pass";

    //second table for kids
    private static final String DATABASE_TBLE = "Kids";
    private static final String COLUMN_ID2 = "Id";
    private static final String COLUMN_JINA= "JinaLaMtoto";
    private static final String COLUMN_DOB = "DOB";
    private static final String COLUMN_MOB = "MOB";
    private static final String COLUMN_YOB = "YOB";
    private static final String COLUMN_PHNE = "NambariYaSimu";

    SQLiteDatabase db;
    //query for table registeration
    public static final String CreateTable = "create table "+DATABASE_TABLE+"( "+COLUMN_ID+" integer primary key autoincrement not null, "
            +COLUMN_USER+ " text not null, "+COLUMN_PHONE+" text not null, "+COLUMN_PASS+" text not null);";

    //query for table Kids
    public static final String CreateTbl = "create table "+DATABASE_TBLE+" ("+COLUMN_ID2+" integer primary key autoincrement not null, "+COLUMN_JINA+" text not null, "+COLUMN_DOB+" integer not null, "+COLUMN_MOB+" integer not null, "+COLUMN_YOB+" integer not null, "+COLUMN_PHNE+" text not null);";


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CreateTable);
        db.execSQL(CreateTbl);

    }
//insert into table registeration
    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER,c.getUser());
        values.put(COLUMN_PHONE,c.getPhone());
        values.put(COLUMN_PASS,c.getPass());
        db.insert(DATABASE_TABLE,null,values);
        db.close();
    }

    //compare log in details
    public String searchPass(String Phne){
        db = this.getReadableDatabase();
        String query = "select Phone, Pass from "+DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(Phne)){
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }



    ContKids c;
//insert into table kids
    public void insertContact(ContKids c, String Phone){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_JINA,c.getJina());
        values.put(COLUMN_YOB,c.getYOB());
        values.put(COLUMN_PHNE,Phone);
        values.put(COLUMN_MOB,c.getMOB());
        values.put(COLUMN_DOB,c.getDOB());
        db.insert(DATABASE_TBLE,null,values);
        db.close();
    }


//select kids' name from user phone
    public ArrayList<String> getArrayCat(String Phone){
        ArrayList<String> dataArry = new ArrayList<String>();
        c=new ContKids();
        String q = "select "+COLUMN_JINA+" from "+DATABASE_TBLE+" where "+COLUMN_PHNE+" = '"+Phone+"'";
        db=this.getReadableDatabase();
        Log.d("Database error","here");

        Cursor cursor = db.rawQuery(q,null);
        cursor.moveToFirst();

        int i=0;
        Log.d("Database demo","Loop"+q);

            while(cursor.moveToNext()){
                String ad = cursor.getString(cursor
                        .getColumnIndex(COLUMN_JINA));
                dataArry.add(ad);
                i++;
            }
        cursor.close();
        return dataArry;

    }

    //get D.O.B of kid selected
    public String getDate(String name){

        String s = "select "+COLUMN_DOB+", "+COLUMN_MOB+", "+COLUMN_YOB+" from "+DATABASE_TBLE+" where "+COLUMN_JINA+" = '"+name+"'";

        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(s,null);
        String ad = "";

        int i = 0;
        while(c.moveToNext()){
             ad = c.getString(c.getColumnIndex(COLUMN_DOB))+"#"+c.getString(c.getColumnIndex(COLUMN_MOB))+"#"+c.getString(c.getColumnIndex(COLUMN_YOB));

            i++;
        }

        return ad;
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+DATABASE_TABLE;
        String q = "DROP TABLE IF EXISTS "+DATABASE_TBLE;
        db.execSQL(q);
        db.execSQL(query);
        this.onCreate(db);


    }
}
