package com.lyne.travailfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GestionBD extends SQLiteOpenHelper {
    Context contexte;
    private static GestionBD instance;
    private SQLiteDatabase database;
    public static GestionBD getInstance(Context context){
        if(instance == null)
            instance = new GestionBD(context);
        return instance;
    }
    public GestionBD(@Nullable Context context) {
        super(context, "travailfinal", null, 1);
        this.contexte = context;
        ouvrirBD();
    }
    public void ouvrirBD(){
        database = this.getWritableDatabase();
    }
    public int executerFichier(SQLiteDatabase db, int resourceID) throws IOException{
        int compteur = 0;
        InputStream insertStream = contexte.getResources().openRawResource(resourceID);
        BufferedReader br = new BufferedReader(new InputStreamReader(insertStream));
        while (br.ready()){
            String enonce = br.readLine();
            db.execSQL(enonce);
            compteur++;
        }
        br.close();
        return compteur;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE lexique(ortho TEXT,`phon` TEXT," +
                    "`lemme` TEXT,`cgram` TEXT,`genre` TEXT,`nombre` TEXT," +
                    "`freqlemfilms` REAL,`freqlemlivres` REAL,`freqfilms` REAL,`" +
                    "freqlivres` REAL,`infover` TEXT,`nbhomogr` INTEGER," +
                    "`nbhomoph` INTEGER,`islem` INTEGER,`nblettres` INTEGER," +
                    "`nbphons` INTEGER,`cvcv` TEXT,`p_cvcv` TEXT,`voisorth` INTEGER," +
                    "`voisphon` INTEGER,`puorth` INTEGER,`puphon` INTEGER,`syll` TEXT," +
                    "`nbsyll` INTEGER,`cv_cv` TEXT,`orthrenv` TEXT,`phonrenv` TEXT,`orthosyll` TEXT )");
            executerFichier(db, R.raw.data);
            db.execSQL("CREATE TABLE pointage(point INTEGER, date TEXT)");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void ajouterPointage(Pointage p){
        ContentValues cv = new ContentValues();
        Date dateBrute = p.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateString = sdf.format(dateBrute);
        cv.put("point", p.getPoint());
        cv.put("date", dateString);
        database.insert("pointage", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
