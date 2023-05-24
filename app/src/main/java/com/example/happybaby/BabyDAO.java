package com.example.happybaby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BabyDAO extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION1 = 1;
    private static final String DATA_BASE_NAME1 = "baby_data";
    private static final String TABLE_NAME1 = "measurements";

    private static final String KEY_ID = "id";
    private static final String BABY_AGE = "baby_age";
    private static final String BABY_HEIGHT = "baby_height";
    private static final String BABY_WEIGHT = "baby_weight";
    private static final String BABY_HEIGHT_AVERAGE = "baby_height_average";
    private static final String BABY_WEIGHT_AVERAGE = "baby_weight_average";

    public BabyDAO(Context context) {
        super(context, DATA_BASE_NAME1, null, DATA_BASE_VERSION1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MEASUREMENTS_TABLE = "CREATE TABLE " + TABLE_NAME1 + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + BABY_AGE + " TEXT," + BABY_HEIGHT + " REAL,"
                + BABY_WEIGHT + " REAL," + BABY_HEIGHT_AVERAGE + " TEXT,"
                + BABY_WEIGHT_AVERAGE + " TEXT" + ")";
        db.execSQL(CREATE_MEASUREMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {
        db1.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db1);
    }

    public void create(Baby baby) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BABY_AGE, baby.getBabyAge());
        values.put(BABY_HEIGHT, baby.getBabyHeight());
        values.put(BABY_WEIGHT, baby.getBabyWeight());
        values.put(BABY_HEIGHT_AVERAGE, baby.getBabyHeightAverage());
        values.put(BABY_WEIGHT_AVERAGE, baby.getBabyWeightAverage());

        db.insert(TABLE_NAME1, null, values);
        db.close();
    }

    public List<Baby> readAll(){
        List<Baby> measurements = new ArrayList<>();
        String quarry = "SELECT * FROM " + TABLE_NAME1;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(quarry,null);

        if(cursor.moveToFirst()){
            do{
                Baby baby = new Baby();
                baby.setId(Integer.parseInt(cursor.getString(0)));
                baby.setBabyAge(cursor.getDouble(1));
                baby.setBabyHeight(cursor.getDouble(2));
                baby.setBabyWeight(cursor.getDouble(3));
                baby.setBabyHeightAverage(cursor.getString(4));
                baby.setBabyWeightAverage(cursor.getString(5));
                measurements.add(baby);
            } while (cursor.moveToNext());
            db.close();
        }
        return measurements;

    }

    public int update (Baby baby){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BABY_AGE, baby.getBabyAge());
        values.put(BABY_HEIGHT, baby.getBabyHeight());
        values.put(BABY_WEIGHT, baby.getBabyWeight());
        values.put(BABY_HEIGHT_AVERAGE, baby.getBabyHeightAverage());
        values.put(BABY_WEIGHT_AVERAGE, baby.getBabyWeightAverage());

        int message = db.update(TABLE_NAME1,values,KEY_ID + " =?",
                new String[]{String.valueOf(baby.getId())});
        db.close();
        return message;
    }

    public void delete (Baby baby){
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete(TABLE_NAME1, KEY_ID + "=?",
                new String[]{String.valueOf(baby.getId())});
        db.close();
    }
}
