package com.lyne.annexe13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance;
    private SQLiteDatabase database;
    public static GestionBD getInstance(Context context){
        if(instance == null)
            instance = new GestionBD(context);
        return instance;
    }
    public GestionBD(@Nullable Context context) {
        super(context, "annexe13", null, 1);
        database = this.getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE evaluation(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, microbrasserie TEXT, etoile INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS evaluation");
        onCreate(db);
    }
    public void ajouterEvaluation(Evaluation e, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("nom", e.getNom());
        cv.put("microbrasserie", e.getMicrobrasserie());
        cv.put("etoile", e.getEtoile());
        db.insert("evaluation", null, cv);
    }

    public void retournerMeilleure(String nomBiere){
        String[] parameters = {nomBiere};
//        Cursor cursor = database.rawQuery();

    }
}
