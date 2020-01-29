package com.example.anushak.firebasealtaoss;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder> {

    private Context mCtx;
    private List<Artist> artistList;

    public ArtistsAdapter(Context mCtx, List<Artist> artistList) {
        this.mCtx = mCtx;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_artists, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArtistViewHolder holder, int position) {
        final Artist artist = artistList.get(position);
        holder.Name.setText(artist.name);
        holder.empid.setText(artist.empid);
        holder.title.setText(artist.title);
        holder.Email.setText(artist.email);
        holder.Phone.setText(artist.phone);
        holder.dept.setText(artist.subject);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String productName = holder.Name.getText().toString();
                Toast.makeText(mCtx, productName + " is selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return artistList.size();

    }

    public void setfilter(List<Artist> listitem)
    {
        artistList=new ArrayList<>();
        artistList.addAll(listitem);
        notifyDataSetChanged();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {

        TextView Name, Email, Phone, dept,empid,Empidd,title;
        RelativeLayout parentLayout;
        String name;

        public ArtistViewHolder(@NonNull final View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Email = itemView.findViewById(R.id.email);
            title = itemView.findViewById(R.id.title);
            Phone = itemView.findViewById(R.id.phone);
            dept = itemView.findViewById(R.id.dept);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            empid=itemView.findViewById(R.id.empid);
            Empidd=itemView.findViewById(R.id.empidlist);


        }

    }

}
