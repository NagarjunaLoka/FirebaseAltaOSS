package com.example.anushak.firebasealtaoss;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyProjectAdapter extends RecyclerView.Adapter<MyProjectAdapter.ArtistViewHolder> {

    private Context mCtx;
    private List<Artist> artistList;

    private Context context;



    public MyProjectAdapter(Context mCtx, List<Artist> artistList) {
        this.mCtx = mCtx;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.emp_pro_list, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArtistViewHolder holder,final int position) {
        final Artist artist = artistList.get(position);

        holder.prefproject.setText(artist.prefproject);
        holder.empid.setText(artist.empid);

        //holder.checkBox.isClickable();


    }

    @Override
    public int getItemCount() {

        return artistList.size();

    }



    class ArtistViewHolder extends RecyclerView.ViewHolder {

        TextView prefproject,empid;

        private View view;

        public ArtistViewHolder(@NonNull final View itemView) {
            super(itemView);

            view = itemView;

            prefproject = itemView.findViewById(R.id.projectname);

            empid = itemView.findViewById(R.id.empid);
        }
    }



}
