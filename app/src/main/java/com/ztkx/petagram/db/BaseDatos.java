package com.ztkx.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ztkx.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ztkx on 29/10/2016.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA +"(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE +" TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO +" INTEGER," +
                ConstantesBaseDatos.TABLE_MASCOTA_FECHA_RANK +" INTEGER" +
                ")";

        String queryCrearTablaLikes = "CREATE TABLE "+ ConstantesBaseDatos.TABLE_LIKES+"(" +
                ConstantesBaseDatos.TABLE_LIKES_ID          +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA +" INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_NUMERO_LIKES+" INTEGER, "+
                "FOREIGN KEY ("+ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA +
                ") REFERENCES "+ConstantesBaseDatos.TABLE_MASCOTA +
                "(" + ConstantesBaseDatos.TABLE_MASCOTA_ID +"))";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST "+ConstantesBaseDatos.TABLE_LIKES);
        onCreate(db);

    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList <Mascota> mascotas=new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while(registros.moveToNext()){
            Mascota mascota= new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setFoto(registros.getInt(2));
            mascota.setFavs(obtenerLikesMascota(mascota));

            mascotas.add(mascota);
        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null,contentValues);
        db.close();

    }

    public void insertarLikeMascota(ContentValues contentValues,ContentValues contentValuesFecha,int mascotaId){
        SQLiteDatabase db = this.getWritableDatabase();
        String where=ConstantesBaseDatos.TABLE_MASCOTA_ID+" = "+mascotaId;
        db.insert(ConstantesBaseDatos.TABLE_LIKES,null,contentValues);
        db.update(ConstantesBaseDatos.TABLE_MASCOTA,contentValuesFecha,where,null);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes=0;
        String query ="SELECT COUNT(*) FROM "+ConstantesBaseDatos.TABLE_LIKES+" WHERE "+
                ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA +"="+mascota.getId();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            likes= registros.getInt(0);
        }

        db.close();

        return likes;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas(){
        ArrayList<Mascota> mascotas= new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTA+
                " ORDER BY "+ConstantesBaseDatos.TABLE_MASCOTA_FECHA_RANK+" DESC LIMIT 5";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while(registros.moveToNext()){
            Mascota mascota= new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setFoto(registros.getInt(2));
            mascota.setFavs(obtenerLikesMascota(mascota));

            mascotas.add(mascota);
        }

        db.close();


        return mascotas;
    }


}
