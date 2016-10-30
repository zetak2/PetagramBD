package com.ztkx.petagram.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztkx.petagram.R;
import com.ztkx.petagram.adaptador.MascotaPerfilAdaptador;
import com.ztkx.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilMascotaFragment extends Fragment {

    ArrayList<Mascota> fotosMascota;
    MascotaPerfilAdaptador adaptador;
    private RecyclerView listaFotosMascota;

    public PerfilMascotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_perfil_mascota, container, false);
        listaFotosMascota= (RecyclerView) v.findViewById(R.id.rvMascotaImages);
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        listaFotosMascota.setLayoutManager(glm);
        inicializarFotosMascota();
        inicializarAdaptador();

        return v;
    }

    public void inicializarAdaptador(){
        adaptador = new MascotaPerfilAdaptador(fotosMascota,getActivity());
        listaFotosMascota.setAdapter(adaptador);
    }

    public void inicializarFotosMascota(){
        fotosMascota = new ArrayList<Mascota>();

        Random rnd = new Random();
        for (int i = 0; i < 15; i++) {
            fotosMascota.add(new Mascota(R.drawable.bandido,(int)(rnd.nextDouble() * 10), "Bandido"));
        }

    }

}
