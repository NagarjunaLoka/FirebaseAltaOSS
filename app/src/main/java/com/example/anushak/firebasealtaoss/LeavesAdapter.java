package com.example.anushak.firebasealtaoss;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LeavesAdapter extends RecyclerView.Adapter<LeavesAdapter.LeavesViewHolder> {

    private Context mCtx;
    private List<Project> leavesList;

    public LeavesAdapter(Context mCtx, List<Project> leavesList) {
        this.mCtx = mCtx;
        this.leavesList = leavesList;
    }

    @NonNull
    @Override
    public LeavesAdapter.LeavesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.leaves_list , parent, false);
        return new LeavesViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull LeavesViewHolder holder, int position) {
        final Project project = leavesList.get(position);

        holder.leavetype.setText(project.leavetype);
        holder.empid.setText(project.empid);

        holder.reason.setText(project.reason);
        holder.noofdays.setText(project.noofdays);
        holder.fromdate.setText(project.fromdate);

        holder.todate.setText(project.todate);
        holder.applyto.setText(project.applyto);

        holder.status.setText(project.status);

        if(holder.status.getText().toString().equals("Approved"))
        {
            holder.status.setTextColor(Color.parseColor("#00802b"));
        }
        else if(holder.status.getText().toString().equals("Rejected"))
        {
            holder.status.setTextColor(Color.RED);
        }
        else if(holder.status.getText().toString().equals("Leave Request"))
        {
            holder.status.setTextColor(Color.parseColor("#3377ff"));
        }

    }

    @Override
    public int getItemCount() {
        return leavesList.size();
    }

    class LeavesViewHolder extends RecyclerView.ViewHolder {

        TextView empid,applyto,fromdate,todate,noofdays,leavetype,reason,status;

        public LeavesViewHolder(@NonNull final View itemView) {
            super(itemView);
            empid = itemView.findViewById(R.id.empid);
            applyto = itemView.findViewById(R.id.applyto);
            fromdate = itemView.findViewById(R.id.fromdate);
            todate = itemView.findViewById(R.id.todate);
            noofdays = itemView.findViewById(R.id.noofdays);
            leavetype=itemView.findViewById(R.id.leavetype);
            reason = itemView.findViewById(R.id.reason);
            status=itemView.findViewById(R.id.status);

        }
    }
}