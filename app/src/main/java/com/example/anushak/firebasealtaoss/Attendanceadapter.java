package com.example.anushak.firebasealtaoss;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Attendanceadapter extends RecyclerView.Adapter<Attendanceadapter.AttendanceViewHolder> {

    private Context mCtx;
    private List<Attendance> attedanceList;


    public Attendanceadapter(Context mCtx, List<Attendance> attedanceList) {
        this.mCtx = mCtx;
        this.attedanceList = attedanceList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.loggedin_list , parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AttendanceViewHolder holder, int position) {
        final Attendance attendance = attedanceList.get(position);

        holder.Intime.setText(attendance.intime);
        holder.Address.setText(attendance.address);

        holder.Lat.setText(attendance.lat);
        holder.Langi.setText(attendance.longi);
        holder.date.setText(attendance.date);

        holder.Outloggedout.setText(attendance.outtime);
        holder.Outaddress.setText(attendance.outaddress);

        holder.Outlat.setText(attendance.outlat);
        holder.Outlongi.setText(attendance.outlongi);

        holder.note.setText(attendance.note);
        holder.outnote.setText(attendance.outnote);
        holder.serviceno.setText(attendance.serviceno);
        holder.modelno.setText(attendance.modelno);

        holder.outserviceno.setText(attendance.outserviceno);
        holder.outmodelno.setText(attendance.outmodelno);

        final double inlatitude = Double.parseDouble(attendance.lat);
        final double inlongitude = Double.parseDouble(attendance.longi);

        final double outlatitude = Double.parseDouble(attendance.outlat);
        final double outlongitude = Double.parseDouble(attendance.outlongi);

        //final Double latitude=13.217096;
        //final Double longitude=79.100677;
        final String head="jpnagar";

        holder.l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUriString="geo:"+inlatitude+","+inlongitude+"?q=("+head+")@"+inlatitude+","+inlongitude;
                Uri geoUri = Uri.parse(geoUriString);
                //  Log.e(TAG, "String: "+geoUriString);
                Intent mapCall  = new Intent(Intent.ACTION_VIEW, geoUri);
                mCtx.startActivity(mapCall);
            }
        });

        if(!(outlatitude == 0.000)) {
            holder.l2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                String geoUriString="geo:"+holder.Outlat+","+holder.Outlongi+"?q=("+head+")@"+holder.Outlat+","+holder.Outlongi;
                    String geoUriString = "geo:" + outlatitude + "," + outlongitude + "?q=(" + head + ")@" + outlatitude + "," + outlongitude;
                    Uri geoUri = Uri.parse(geoUriString);
                    //  Log.e(TAG, "String: "+geoUriString);
                    Intent mapCall = new Intent(Intent.ACTION_VIEW, geoUri);
                    mCtx.startActivity(mapCall);
                }
            });
        }
        else{
            holder.l2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mCtx,"Not logged out",Toast.LENGTH_LONG).show();
                }
            });
        }

     /*   String dateStart = holder.Intime.getText().toString();
        String dateStop = holder.Outloggedout.getText().toString();

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

           *//* System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");*//*


               holder.workinghrs.setText(diffHours + ":" + diffMinutes + ":" + diffSeconds);


        } catch (Exception e) {
            e.printStackTrace();
        }*/


            if(holder.Outloggedout.getText().toString().equalsIgnoreCase("not logged out"))
            {
                holder.workinghrs.setText("not logged out");
            }
            else {
                String startDate = holder.Intime.getText().toString();
                String stopDate = holder.Outloggedout.getText().toString();

                 /*String startDate = "03:00:00 PM";
                 String stopDate = "04:05:05 PM";*/

                SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ss a");
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = parseFormat.parse(startDate);
                    date2 = parseFormat.parse(stopDate);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }

               /* long diff = date1.getTime() - date2.getTime();
                long seconds = diff / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;*/

               /* long days = diff.toDays();
                diff = diff.minusDays(days);
                long hours = diff.toHours();
                diff = diff.minusHours(hours);
                long minutes = diff.toMinutes();
                diff = diff.minusMinutes(minutes);
                long seconds = diff.toSeconds();*/

                long diff = date2.getTime() - date1.getTime();
                long diffHours = diff / (60 * 60 * 1000);
                long diffMinutes = diff / (60 * 1000);
                long diffSeconds = diff / (1000);

                //   holder.workinghrs.setText(diffHours+ "     " + diffMinutes + "   " + diffSeconds);

               holder.workinghrs.setText(diffHours + ":" + diffMinutes + ":" + diffSeconds);
            }

                /*if(holder.Outloggedout.getText().toString().equalsIgnoreCase("not loged out"))
                {
                    holder.workinghrs.setText("not logged out");
                }
                else {
                    holder.workinghrs.setText(diffHours + ":" + diffMinutes + ":" + diffSeconds);
                }*/











        /*Timer updateTimer = new Timer();
        updateTimer.schedule(new TimerTask()
        {
            public void run()
            {
                try
                {
                    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
                    Date date2 = format.parse(holder.Intime.getText().toString());
                    Date date3 = format.parse(holder.Outloggedout.getText().toString());
                    long mills = date2.getTime() - date3.getTime();
                    Log.v("Data1", ""+date2.getTime());
                    Log.v("Data2", ""+date3.getTime());
                    int hours = (int) (mills/(1000 * 60 * 60));
                    int mins = (int) (mills % (1000*60*60));
                    int sec = (int) (mills % (1000*60*60));

                    String diff = hours + ":" + mins; // updated value every1 second
                    holder.workinghrs.setText(diff);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }, 0, 1000);*/


       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              *//*  String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mCtx.startActivity(intent);*//*

                String geoUriString="geo:"+holder.Lat+","+holder.Langi+"?q=("+head+")@"+holder.Lat+","+holder.Langi;
                Uri geoUri = Uri.parse(geoUriString);
              //  Log.e(TAG, "String: "+geoUriString);
                Intent mapCall  = new Intent(Intent.ACTION_VIEW, geoUri);
                mCtx.startActivity(mapCall);

              *//*  String urlAddress = "http://maps.googleapis.com/maps/api/streetview?size=500x500&location=" + latitude  + "," + longitude + "&fov=90&heading=235&pitch=10&sensor=false";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
                mCtx.startActivity(intent);
*//*



              *//*  String productName = holder.Name.getText().toString();
                Toast.makeText(mCtx, productName + " is selected", Toast.LENGTH_SHORT).show();*//*
            }
        });*/


    }

    @Override
    public int getItemCount() {

        return attedanceList.size();

    }

    /*public void setfilter(List<Artist> listitem)
    {
        attedanceList=new ArrayList<>();
        artistList.addAll(listitem);
        notifyDataSetChanged();
    }*/


    class AttendanceViewHolder extends RecyclerView.ViewHolder {

        LinearLayout l1,l2;
        TextView Name, Empid, Intime,Outtime,Address,Lat,Langi,date,Outlat,Outlongi,Outaddress,Outloggedin,Outloggedout,outnote,note,serviceno,modelno,outserviceno,outmodelno,workinghrs;
        String name;

        public AttendanceViewHolder(@NonNull final View itemView) {
            super(itemView);
            Lat = itemView.findViewById(R.id.lat);
            Langi = itemView.findViewById(R.id.langt);
            Intime = itemView.findViewById(R.id.loggedin);
            Address = itemView.findViewById(R.id.address);
            date = itemView.findViewById(R.id.date);

            Outlat = itemView.findViewById(R.id.outlat);
            Outlongi = itemView.findViewById(R.id.outlangt);
            Outaddress = itemView.findViewById(R.id.outaddress);
            Outloggedout = itemView.findViewById(R.id.outloggedout);
            note = itemView.findViewById(R.id.note);
            outnote = itemView.findViewById(R.id.outnote);
            serviceno = itemView.findViewById(R.id.serviceno);
            modelno = itemView.findViewById(R.id.modelno);
            outserviceno = itemView.findViewById(R.id.outserviceno);
            outmodelno = itemView.findViewById(R.id.outmodelno);
            workinghrs = itemView.findViewById(R.id.workinghrs);

            l1 = itemView.findViewById(R.id.l1);
            l2 = itemView.findViewById(R.id.l2);

        }

    }

}
