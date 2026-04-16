package com.lyne.annexe12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestionBD extends SQLiteOpenHelper {
    //instance unique de la classe qui est un singleton
    private static GestionBD instance;
    private SQLiteDatabase database;
    //méthode pour récupérer/créer le singleton
    public static GestionBD getInstance(Context context){
        if(instance == null)
            instance = new GestionBD(context);
        return instance;
    }

    public GestionBD(@Nullable Context context) {
        super(context, "annexe12", null, 1);
        ouvrirConnexionBd(); //creer la database ou on aurait pu le faire dans le oncreate de l'activiré
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE inventeur( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT, origin TEXT, invention TEXT, annee INTEGER)");
    }

    public void ajouterInventeur(Inventaire i, SQLiteDatabase db){
        ContentValues cv = new ContentValues(); //comme une hasmap
        cv.put("nom", i.getNom());
        cv.put("origin", i.getOrigin());
        cv.put("invention", i.getInvention());
        cv.put("annee", i.getAnnee());
        db.insert("inventeur", null, cv);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists inventeur");
        onCreate(db);
    }
    public void ouvrirConnexionBd(){
        database = this.getReadableDatabase();
    }

    public ArrayList<String> retournerInventions(){
        ArrayList<String> listeInventions = new ArrayList<>();
        Cursor cursor = database.rawQuery("select origin, invention from inventeur", null);
        while(cursor.moveToNext()){
            listeInventions.add(cursor.getString(1));
        }
        cursor.close();
        return listeInventions;
    }
    public boolean hasBonneReponse(String nom, String invention){
        String[] parametres = {nom, invention};
        Cursor c = database.rawQuery("select nom, invention FROM inventeur WHERE nom = ? AND invention = ?", parametres);
        boolean reponse = c.moveToFirst();
        c.close();
        return reponse;
    }
}
