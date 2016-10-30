package com.ztkx.petagram.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztkx.petagram.R;
import com.ztkx.petagram.adaptador.MascotaAdaptador;
import com.ztkx.petagram.pojo.Mascota;
import com.ztkx.petagram.presentador.IRecyclerViewFragmentPresenter;
import com.ztkx.petagram.presentador.RecyclerViewFavoritasFragmentPresenter;
import com.ztkx.petagram.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class MascotasFavoritasFragment extends Fragment implements IRecyclerViewFragmentView {

    MascotaAdaptador adaptador;
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_mascotas_favoritas, container, false);
        listaMascotas= (RecyclerView) v.findViewById(R.id.rvMascota);
        presenter = new RecyclerViewFavoritasFragmentPresenter(this,getContext());
        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        adaptador = new MascotaAdaptador(mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}

