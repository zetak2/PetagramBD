package com.ztkx.petagram.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztkx.petagram.db.ConstructorMascotas;
import com.ztkx.petagram.pojo.Mascota;
import com.ztkx.petagram.R;

import java.util.ArrayList;

/**
 * Created by ztkx on 14/10/2016.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override//Inflar el layout y lo pasara al viewholder para obtener los views
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, final int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvFavs.setText(String.valueOf(mascota.getFavs()));


        mascotaViewHolder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mascota.setFavs(mascota.getFavs()+1);
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                mascotaViewHolder.tvFavs.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvFavs;
        private ImageButton btnFav;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre    = (TextView) itemView.findViewById(R.id.tvNombre);
            tvFavs      = (TextView) itemView.findViewById(R.id.tvfavs);
            btnFav     = (ImageButton) itemView.findViewById(R.id.btnLike);

        }
    }
}
