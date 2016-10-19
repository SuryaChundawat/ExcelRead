package com.example.chari.excelread;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Chari on 10/7/2016.
 */

public class SqlLiteDbHelper extends SQLiteOpenHelper
{

    // All Static variables
// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Excel.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;
    private SQLiteDatabase database;


    private static final String COMMON_ID = "_id";

    private static final String CITYNAME_TABLE = "city_table";


    private static final String CITYNAME_TABLE_CITYID = "city_cityid";
    private static final String CITYNAME_TABLE_CITYNAME = "city_cityname";
    private static final String CITYNAME_TABLE_STATEID = "city_stateid";
    private String cityName;
    private int cityid;
    private String Stateid;


    public SqlLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }

    private static final String CREATE_TABLE_CITY = "CREATE TABLE "
            + CITYNAME_TABLE + "(" + COMMON_ID + " INTEGER PRIMARY KEY,"
            + CITYNAME_TABLE_CITYID + " INTEGER,"
            + CITYNAME_TABLE_CITYNAME + " TEXT,"
            + CITYNAME_TABLE_STATEID + " TEXT);";



    public void open() {
        database = this.getWritableDatabase();
    }

    public void close() {
        database.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_CITY);
        Log.e("Database","Created");

        //getdata();

    }


    public ArrayList<String> getdata()
    {

        SQLiteDatabase db = this.getReadableDatabase();

        open();

        Cursor cursor = db.rawQuery("SELECT * FROM Database", null);

        ContentValues cv = new ContentValues();
        ArrayList<String> getcityname = new ArrayList<String>();
        long long_insert_row_index;

        if (cursor != null && cursor.moveToFirst()) {


            // Contact cont = new Contact(cursor.getColumnIndex("CITY_CODE"),cursor.getString(cursor.getColumnIndex("CITY_NAME")), cursor.getString(cursor.getColumnIndex("STATE_CODE")));

                 /* for (int i=0; i<cursor.getCount();i++) {*/
                     /* do {*/

            cursor.moveToFirst();


            String cumnname = cursor.getColumnName(0);
            String cumnname1 = cursor.getColumnName(1);
            String cumnname2 = cursor.getColumnName(2);
            Log.e("coumnname", cumnname);
            Log.e("coumnname1", cumnname1);
            Log.e("coumnname2", cumnname2);


            Cursor cursor2 = db.rawQuery("SELECT * FROM Database WHERE city_id", null);
            cursor2.moveToFirst();

            do {

                cityid = Integer.parseInt(cursor2.getString(0));
                cityName = cursor2.getString(1);
                Stateid = cursor2.getString(2);

                cv.put(CITYNAME_TABLE_CITYID, cityid);
                cv.put(CITYNAME_TABLE_CITYNAME, cityName);
                cv.put(CITYNAME_TABLE_STATEID, Stateid);

                long_insert_row_index = db.insert(CITYNAME_TABLE, null, cv);

                Log.e("Log databaseInsert",""+long_insert_row_index);


                Log.e("cityid", "" + cityid);
                Log.e("cityName", cityName);
                Log.e("Stateid", Stateid);

               /* for (int i = 0; i < 3; i++) {


                    String newV = cursor2.getString(i);
                    Log.e("newV", "" + newV);
                }
*/

                // int newV = cursor2.getColumnIndex(cumnname);

                getcityname.add(cityName);







            } while (cursor2.moveToNext());

            cursor.close();
            db.close();

            close();

            // return cityName;






        }






        return getcityname;
    }



    public String getCityId(String cityName)
    {


        open();

        Cursor cursor;

        String cityId = "";
        String stateId = "";

        Log.e("CityName in Db",cityName);


        cursor = database.query(CITYNAME_TABLE, null, CITYNAME_TABLE_CITYNAME + "=? ", new String[]{cityName}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            cityId= cursor.getString(cursor.getColumnIndex(CITYNAME_TABLE_CITYID));
            stateId= cursor.getString(cursor.getColumnIndex(CITYNAME_TABLE_STATEID));
            cursor.close();
        }
        cursor.moveToFirst();


        Log.e("cityId",cityId);
        Log.e("stateid",stateId);
        // Log.e("Verification password is",password1.toString());
        cursor.close();


        return cityId;





    }






    // Getting single contact
    public Contact Get_ContactDetails() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Database", null);

        Contact contact;

        ContentValues cv = new ContentValues();
        long long_insert_row_index;

        //>>>>>>>> you can change here of cursor position ...i set last value of database >>>>>>>>>>>

        if (cursor != null && cursor.moveToFirst())
        {




            // Contact cont = new Contact(cursor.getColumnIndex("CITY_CODE"),cursor.getString(cursor.getColumnIndex("CITY_NAME")), cursor.getString(cursor.getColumnIndex("STATE_CODE")));

                 /* for (int i=0; i<cursor.getCount();i++) {*/
                     /* do {*/

            cursor.moveToFirst();


            String cumnname =cursor.getColumnName(0);
            String cumnname1 =cursor.getColumnName(1);
            String cumnname2 =cursor.getColumnName(2);
            Log.e("coumnname",cumnname);
            Log.e("coumnname1",cumnname1);
            Log.e("coumnname2",cumnname2);



            Cursor cursor2 = db.rawQuery("SELECT * FROM Database WHERE city_code", null);
            cursor2.moveToFirst();

            do
            {

                int cityid= Integer.parseInt(cursor2.getString(0));
                String cityName=cursor2.getString(1);
                String Stateid=cursor2.getString(2);

                cv.put(CITYNAME_TABLE_CITYID, cityid);
                cv.put(CITYNAME_TABLE_CITYNAME, cityName);
                cv.put(CITYNAME_TABLE_STATEID, Stateid);

                long_insert_row_index= db.insert(CITYNAME_TABLE, null, cv);

                contact = new Contact(cityid,cityName,Stateid);

                Log.e("cityid",""+cityid);
                Log.e("cityName",cityName);
                Log.e("Stateid",Stateid);

                for (int i=0; i<3; i++)
                {


                    String newV=  cursor2.getString(i);
                    Log.e("newV",""+newV);
                }


                // int newV = cursor2.getColumnIndex(cumnname);


            }while (cursor2.moveToNext());




            String abc =cursor.getString(cursor.getColumnIndex(cumnname));
            String abc1 =cursor.getString(cursor.getColumnIndex(cumnname1));
            String abc2 =cursor.getString(cursor.getColumnIndex(cumnname2));


             /* int abc =cursor.getInt(0);
              String abc1 =cursor.getString(1);
              String abc2 =cursor.getString(2);*/
            //cont = new Contact(cursor.getColumnIndexOrThrow("0"), cursor.getString(2), cursor.getString(2));
            //cont = new Contact(cursor.getInt(2), cursor.getString(2), cursor.getString(2));
            Log.e("NewCount value",abc);
            Log.e("NewCount value1",abc1);
            Log.e("NewCount value2",abc2);
            //Log.e("NewCount value", cont.mobileNo);


                     /* } while (cursor.moveToNext());*/
                 /* }*/

// return contact
            cursor.close();
            db.close();

            return contact;

        }
        return null;
    }

    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);

// Path to the just created empty db
        String outFileName = getDatabasePath();

// if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

// Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

// transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

// Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CITY);
            onCreate(db);
        }

    }






}
