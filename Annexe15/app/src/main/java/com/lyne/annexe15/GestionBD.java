package com.lyne.annexe15;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        super(context, "annexe15", null, 1);
        this.contexte = context;
        ouvrirBD();
    }

    public int executerFichier(SQLiteDatabase db, int resourceID) throws IOException {
        int compteur = 0;
        InputStream insertStream = contexte.getResources().openRawResource(resourceID);
        BufferedReader br = new BufferedReader(new InputStreamReader(insertStream));
        while(br.ready()){
            String enonce = br.readLine();
            db.execSQL(enonce);
            compteur++;
        }
        br.close(); //toujours fermer le canal
        return compteur;
    }

    public void ouvrirBD(){
        database = this.getWritableDatabase();
    }
    public  boolean villeExiste(String nomVille){
        String[] tab = {nomVille};
        Cursor c = database.rawQuery("select * from villes_quebec where nom = ?", tab);
        boolean rep = c.moveToFirst();
        c.close();
        return rep;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            executerFichier(db, R.raw.villes);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
