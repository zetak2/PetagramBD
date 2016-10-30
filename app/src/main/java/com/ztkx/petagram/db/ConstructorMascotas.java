package com.ztkx.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.widget.ArrayAdapter;

import com.ztkx.petagram.R;
import com.ztkx.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ztkx on 28/10/2016.
 */

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context= context;
    }

    public ArrayList<Mascota> obtenerMascotas(){
        BaseDatos db = new BaseDatos(context);
        ArrayList<Mascota> mascotas=db.obtenerTodasLasMascotas();
        if (mascotas.size()<1){
            insertarMascotas(db);
            mascotas=db.obtenerTodasLasMascotas();
        }
        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerMascotasFavoritas();
    }

    public void insertarMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Bandido");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.bandido);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Pirata");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.pirata);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Coffe");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.coffe);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Donatelo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.donatelo);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Fluffy");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.fluffy);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Lucy");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.lucy);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Negan");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.negan);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Pelusa");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,R.drawable.pelusa);
        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        int mascotaId = mascota.getId();
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA,mascotaId);
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_NUMERO_LIKES,LIKE);
        ContentValues contentValuesFecha = new ContentValues();
        contentValuesFecha.put(ConstantesBaseDatos.TABLE_MASCOTA_FECHA_RANK, new Date().getTime());
        db.insertarLikeMascota(contentValues,contentValuesFecha,mascotaId);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

}
