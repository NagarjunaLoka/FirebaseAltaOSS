package com.example.anushak.firebasealtaoss;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PaySlips_Adapter extends RecyclerView.Adapter<PaySlips_Adapter.ArtistViewHolder> {

    private Context mCtx;
    private List<Salary> artistList;




    public PaySlips_Adapter(Context mCtx, List<Salary> artistList) {
        this.mCtx = mCtx;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.payslips_view, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArtistViewHolder holder, int position) {
        final Salary salary = artistList.get(position);
        holder.name.setText(salary.name);
        holder.empid.setText(salary.empid);
        holder.designation.setText(salary.designation);
        holder.location.setText(salary.location);
        holder.pannum.setText(salary.pan);
        holder.payabledays.setText(salary.payabledays);
        holder.paiddays.setText(salary.paiddays);
        holder.basicpay.setText(salary.basicsalary);
        holder.houserent.setText(salary.houserentallowence);
        holder.convences.setText(salary.conveyances);
        holder.specialalownce.setText(salary.specialallowence);
        holder.otheralownce.setText(salary.atherallowence);
        holder.medical.setText(salary.medical);
        holder.gross.setText(salary.gross);//id not declared.............................................

        holder.pf.setText(salary.epf);
        holder.transportation.setText(salary.transopartation);
        holder.others.setText(salary.others);
        holder.monthandyear.setText(salary.month_and_year);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Name = holder.name.getText().toString();
                String Empid = holder.empid.getText().toString();
                String Designation = holder.designation.getText().toString();
                String Location = holder.location.getText().toString();
                String Pannum = holder.pannum.getText().toString();
                String Payabledays = holder.payabledays.getText().toString();
                String Paiddays = holder.paiddays.getText().toString();
                String Basicpay = holder.basicpay.getText().toString();
                String Houserent = holder.houserent.getText().toString();
                String Convences = holder.convences.getText().toString();
                String Specialallowence = holder.specialalownce.getText().toString();
                String Otheralownce = holder.otheralownce.getText().toString();
                String Medical = holder.medical.getText().toString();
                String Gross = holder.gross.getText().toString();
                String Pf = holder.pf.getText().toString();
                String Transportation = holder.transportation.getText().toString();
                String Others = holder.others.getText().toString();
                String Monthandyear = holder.monthandyear.getText().toString();


                Toast.makeText(mCtx, Monthandyear + " is selected", Toast.LENGTH_SHORT).show();

                Intent i=new Intent(PaySlips_Adapter.this.mCtx,My_Signle_Payslip_Form.class);

                i.putExtra("Name",Name);
                i.putExtra("Empid",Empid);
                i.putExtra("Designation",Designation);
                i.putExtra("Location",Location);
                i.putExtra("Pannum",Pannum);
                i.putExtra("Payabledays",Payabledays);
                i.putExtra("Paiddays",Paiddays);
                i.putExtra("Basicpay",Basicpay);
                i.putExtra("Houserent",Houserent);
                i.putExtra("Convences",Convences);
                i.putExtra("Specialallowence",Specialallowence);
                i.putExtra("Otheralownce",Otheralownce);
                i.putExtra("Medical",Medical);
                i.putExtra("Transportation",Transportation);
                i.putExtra("Others",Others);
                i.putExtra("Monthandyear",Monthandyear);

                mCtx.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {

        return artistList.size();

    }

    public void setfilter(List<Salary> listitem)
    {
        artistList=new ArrayList<>();
        artistList.addAll(listitem);
        notifyDataSetChanged();
    }


    class ArtistViewHolder extends RecyclerView.ViewHolder {
        TextView basicpay,houserent,convences,specialalownce,otheralownce,medical,gross,pf,transportation,others,totaldeductions,netsalary;
        Button pdf;
        private LinearLayout linearLayout;

        TextView name,empid,designation,location,pannum,payabledays,paiddays,monthandyear;


        public ArtistViewHolder(@NonNull final View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            empid=itemView.findViewById(R.id.empid);
            designation=itemView.findViewById(R.id.designation);
            location=itemView.findViewById(R.id.location);
            pannum=itemView.findViewById(R.id.pannum);
            payabledays=itemView.findViewById(R.id.payabledays);
            paiddays=itemView.findViewById(R.id.paiddays);
            pdf=itemView.findViewById(R.id.pdf);
            linearLayout=itemView.findViewById(R.id.my_payslips);
            basicpay = itemView.findViewById(R.id.basicpay);
            houserent=itemView.findViewById(R.id.houserent);
            convences=itemView.findViewById(R.id.concencespay);
            specialalownce=itemView.findViewById(R.id.specialallowence);
            otheralownce=itemView.findViewById(R.id.otherallowence);
            medical=itemView.findViewById(R.id.medical);
            gross=itemView.findViewById(R.id.gross);
            pf=itemView.findViewById(R.id.pf);
            transportation=itemView.findViewById(R.id.transortation);
            others=itemView.findViewById(R.id.others);
            totaldeductions=itemView.findViewById(R.id.totaldeductions);
            netsalary=itemView.findViewById(R.id.netsalary);
            monthandyear=itemView.findViewById(R.id.monthandyear);


        }

    }

}
