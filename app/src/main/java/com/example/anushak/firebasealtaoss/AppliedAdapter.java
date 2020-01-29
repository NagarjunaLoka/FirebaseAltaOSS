package com.example.anushak.firebasealtaoss;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppliedAdapter extends RecyclerView.Adapter<AppliedAdapter.LeavesViewHolder> {

    private Context mCtx;
    private List<Project> leavesList;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAueTGKP0:APA91bHo7bOJDKkcUV3MJznbAfOhTVZ1A7IEP9fkBk_-70dM0tIYbis1cVhR4cBr-rV8FVN2FtQ7G9-snLfSHLgG_xB_vvvkNmduv7H91OGQMSNwvsQ0ybcxpNkFmiltRn4mBZfSphVe";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

    String NOTIFy;

    public AppliedAdapter(Context mCtx, List<Project> leavesList) {
        this.mCtx = mCtx;
        this.leavesList = leavesList;
    }

    @NonNull
    @Override
    public AppliedAdapter.LeavesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.applied_list , parent, false);
        return new AppliedAdapter.LeavesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppliedAdapter.LeavesViewHolder holder, int position) {
        final Project project = leavesList.get(position);

        holder.leavetype.setText(project.leavetype);
        holder.empid.setText(project.empid);

        holder.reason.setText(project.reason);
        holder.noofdays.setText(project.noofdays);
        holder.fromdate.setText(project.fromdate);

        holder.todate.setText(project.todate);
        holder.applyto.setText(project.applyto);

        holder.status.setText(project.status);

        holder.date=project.monthandyear;
        holder.emp=project.empid;
        holder.from=project.fromdate;

    }

    @Override
    public int getItemCount() {
        return leavesList.size();
    }

    class LeavesViewHolder extends RecyclerView.ViewHolder {

        ProgressDialog progressDialog;

        TextView empid,applyto,fromdate,todate,noofdays,leavetype,reason,status;
        Button approve,reject;
        DatabaseReference mDatabase;
        String date,emp,from;

        @SuppressLint("ResourceAsColor")
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
             approve = itemView.findViewById(R.id.approve);
             reject = itemView.findViewById(R.id.reject);

            progressDialog=new ProgressDialog(mCtx);

            mDatabase = FirebaseDatabase.getInstance().getReference("Leaves");

            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatabase.child(emp+from+date).child("status").setValue("Approved");
                    if (mCtx instanceof AdminLeaveHistory){
                        ((AdminLeaveHistory)mCtx).callMethod();
                    }


                    TOPIC = "/topics/"+emp; //topic has to match what the receiver subscribed to

                    NOTIFICATION_TITLE = emp.toString();
                    //NOTIFICATION_MESSAGE = state.toString() ;

                    NOTIFy = "Leave Request Status:  "+NOTIFICATION_TITLE;

                    JSONObject notification = new JSONObject();
                    JSONObject notifcationBody = new JSONObject();
                    try {
                        notifcationBody.put("title", NOTIFy);
                        notifcationBody.put("message", "Approved");

                        notification.put("to", TOPIC);
                        notification.put("data", notifcationBody);
                    } catch (JSONException e) {
                        Log.e(TAG, "onCreate: " + e.getMessage() );
                    }
                    sendNotification(notification);

                }
            });

            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDatabase.child(emp+from+date).child("status").setValue("Rejected");
                    if (mCtx instanceof AdminLeaveHistory){
                        ((AdminLeaveHistory)mCtx).callMethod();
                    }

                    TOPIC = "/topics/"+emp; //topic has to match what the receiver subscribed to

                    NOTIFICATION_TITLE = emp.toString();
                    //NOTIFICATION_MESSAGE = state.toString() ;

                    NOTIFy = "Leave Request Status :  "+NOTIFICATION_TITLE;

                    JSONObject notification = new JSONObject();
                    JSONObject notifcationBody = new JSONObject();
                    try {
                        notifcationBody.put("title", NOTIFy);
                        notifcationBody.put("message", "Rejected");

                        notification.put("to", TOPIC);
                        notification.put("data", notifcationBody);
                    } catch (JSONException e) {
                        Log.e(TAG, "onCreate: " + e.getMessage() );
                    }
                    sendNotification(notification);
                }
            });
        }

        private void sendNotification(JSONObject notification) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(TAG, "onResponse: " + response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(mCtx, "Request error", Toast.LENGTH_LONG).show();
                            Log.i(TAG, "onErrorResponse: Didn't work");
                        }
                    })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", serverKey);
                    params.put("Content-Type", contentType);
                    return params;
                }
            };
            MySingleton.getInstance(mCtx).addToRequestQueue(jsonObjectRequest);
        }

    }
}
