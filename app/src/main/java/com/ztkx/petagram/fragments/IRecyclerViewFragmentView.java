package com.ztkx.petagram.fragments;

import com.ztkx.petagram.adaptador.MascotaAdaptador;
import com.ztkx.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ztkx on 28/10/2016.
 */

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
