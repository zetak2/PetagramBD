package com.ztkx.petagram.db;

/**
 * Created by ztkx on 29/10/2016.
 */

public final class ConstantesBaseDatos {
    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTA                = "mascota";
    public static final String TABLE_MASCOTA_ID             = "id";
    public static final String TABLE_MASCOTA_NOMBRE         = "nombre";
    public static final String TABLE_MASCOTA_FOTO           = "foto";
    public static final String TABLE_MASCOTA_FECHA_RANK     = "fecha_rank";

    public static final String TABLE_LIKES                  = "likes";
    public static final String TABLE_LIKES_ID               = "id";
    public static final String TABLE_LIKES_ID_MASCOTA        = "idMascota";
    public static final String TABLE_LIKES_NUMERO_LIKES     = "numero_likes";



}
