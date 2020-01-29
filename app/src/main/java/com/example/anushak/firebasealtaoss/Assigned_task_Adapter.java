package com.example.anushak.firebasealtaoss;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Assigned_task_Adapter extends RecyclerView.Adapter<Assigned_task_Adapter.ArtistViewHolder> {

    private Context mCtx;
    private List<Artist> artistList;

  //  public TextView userid,usertype;

    public Assigned_task_Adapter(Context mCtx, List<Artist> artistList) {
        this.mCtx = mCtx;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_artists, parent, false);

        // return null;
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArtistViewHolder holder, int position) {
        final Artist artist = artistList.get(position);
        holder.Name.setText(artist.name);
        holder.Email.setText(artist.email);
        holder.dept.setText(artist.dept);
        holder.title.setText(artist.title);
        holder.Subject.setText(artist.subject);
        holder.Discription.setText(artist.discription);
        holder.empid.setText(artist.empid);
        holder.priority.setText(artist.priority);

        holder.id.setText(artist.id);//for unique id fetch

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String productName = holder.Name.getText().toString();
                String Email = holder.Email.getText().toString();
               //String Phone = holder.Phone.getText().toString();
                String title = holder.title.getText().toString();
                String Subject = holder.Subject.getText().toString();
                String Discription = holder.Discription.getText().toString();
                String Empid = holder.empid.getText().toString();
                String Id =holder.id.getText().toString();
                String dept=holder.dept.getText().toString();
                String priority=holder.priority.getText().toString();

                Toast.makeText(mCtx, productName + " is selected", Toast.LENGTH_SHORT).show();

                Intent i =new Intent(Assigned_task_Adapter.this.mCtx,My_Task_Single.class);
                i.putExtra("name",productName);
                i.putExtra("email",Email);
                i.putExtra("empid",Empid);
                i.putExtra("id",Id);
                i.putExtra("dept",dept);
                i.putExtra("title",title);
                i.putExtra("subject",Subject);
                i.putExtra("discription",Discription);
                i.putExtra("priority",priority);

                mCtx.startActivity(i);

            }
        });

//        ((Assigned_Tickets) mCtx).foo();
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

        TextView Name, Email, Phone, title, dept,empid,Empidd,Subject,Discription,id,priority,userid,usertype;
        RelativeLayout parentLayout;
        String name;

        public ArtistViewHolder(@NonNull final View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            empid=itemView.findViewById(R.id.empid);
            Email = itemView.findViewById(R.id.email);
            title = itemView.findViewById(R.id.title);
            Subject = itemView.findViewById(R.id.subject);
            Discription = itemView.findViewById(R.id.discription);
            dept = itemView.findViewById(R.id.dept);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            Empidd=itemView.findViewById(R.id.empidlist);
            Phone = itemView.findViewById(R.id.phone);
            id =itemView.findViewById(R.id.id);
            priority=itemView.findViewById(R.id.priority);
            userid=itemView.findViewById(R.id.userid);
            usertype=itemView.findViewById(R.id.usertype);
        }





    }


}
