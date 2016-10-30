package com.ztkx.petagram.pojo;

/**
 * Created by ztkx on 14/10/2016.
 */

public class Mascota {
    private int foto;
    private int favs;
    private int id;
    private String nombre;

    public Mascota(int foto, int favs, String nombre) {
        this.foto   = foto;
        this.favs   = favs;
        this.nombre = nombre;
    }

    public Mascota() {

    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFavs() {
        return favs;
    }

    public void setFavs(int favs) {
        this.favs = favs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
