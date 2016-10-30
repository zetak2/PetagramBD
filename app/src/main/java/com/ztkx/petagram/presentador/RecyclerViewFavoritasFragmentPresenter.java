package com.ztkx.petagram.presentador;

import android.content.Context;

import com.ztkx.petagram.db.ConstructorMascotas;
import com.ztkx.petagram.fragments.IRecyclerViewFragmentView;
import com.ztkx.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ztkx on 28/10/2016.
 */

public class RecyclerViewFavoritasFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFavoritasFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotas();
    }

    @Override
    public void obtenerMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerMascotasFavoritas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
