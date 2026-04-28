package com.lyne.examen3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance;
    private SQLiteDatabase database;
    public static GestionBD getInstance(Context context){
        if(instance == null)
            instance = new GestionBD(context);
        return instance;
    }
    public GestionBD(@Nullable Context context) {
        super(context, "Examen3", null, 1);
        ouvrirConnexionBd();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cegep(_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, nbrEquipe INTEGER, adresse TEXT)");
        ajouterCegep(new Cegep("Cavaliers de Bois-de-Boulogne", 5, "10555, avenue de Bois-de-Boulogne Montréal, QC"), db);
        ajouterCegep(new Cegep("Cheminots de St-Jérôme", 12, "455 Rue Fournier, Saint-Jérôme, QC"), db);
        ajouterCegep(new Cegep("Diablos de Trois-Rivières", 14, "3500 Rue de Courval, Trois-Rivières, QC"), db);
        ajouterCegep(new Cegep("Spartiates du Vieux Montréal", 9, "255, rue Ontario Est, Montréal, QC"), db);
    }
    public void ajouterCegep(Cegep c, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("nom", c.getNom());
        cv.put("nbrEquipe", c.getNbrEquipe());
        cv.put("adresse", c.getAdresse());
        db.insert("cegep", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cegep");
        onCreate(db);
    }
    public void ouvrirConnexionBd(){
        database = this.getWritableDatabase();
    }
    public ArrayList<String> retournerEquipes(){
        ArrayList<String> listeEquipes = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT nom FROM cegep", null);
        while (cursor.moveToNext()){
            listeEquipes.add(cursor.getString(0));
        }
        cursor.close();
        return listeEquipes;
    }
    public Cegep retournerCegep(String nom){
        String[] parametres = {nom};
        Cegep cegep = null;
        Cursor c = database.rawQuery("SELECT * FROM cegep WHERE nom = ?", parametres);
        if(c.moveToFirst()){
            cegep = new Cegep(c.getString(1).toString(), c.getInt(2), c.getString(3).toString());
        }
        c.close();
        return cegep;
    }


}
