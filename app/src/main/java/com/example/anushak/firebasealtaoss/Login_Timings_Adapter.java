package com.example.anushak.firebasealtaoss;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class Login_Timings_Adapter extends RecyclerView.Adapter<Login_Timings_Adapter.Loginviewholder> {

    private Context mCtx;
    private List<Login_Timings_Class> logintimingsList;


    public Login_Timings_Adapter(Context mCtx, List<Login_Timings_Class> logintimingsList) {
        this.mCtx = mCtx;
        this.logintimingsList = logintimingsList;
    }

    @NonNull
    @Override
    public Loginviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.login_timings_view, parent, false);
        return new Loginviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Loginviewholder holder, int position) {
        final Login_Timings_Class artist = logintimingsList.get(position);
        holder.Location.setText(artist.location);
        holder.Email.setText(artist.email);
        holder.Time.setText(artist.time);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String Location = holder.Location.getText().toString();
                Toast.makeText(mCtx, Location , Toast.LENGTH_SHORT).show();


              /*  SharedPreferences.Editor editor = mCtx.getSharedPreferences(Location, MODE_PRIVATE).edit();
                editor.putString("Location","Location");
                editor.apply();
*/
/*

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("geo:12.803915,77.514190"));
                        mCtx.startActivity(intent);

*/

                /*Intent i =new Intent(ArtistsAdapter.this.mCtx,Ticket_Raising_Form.class);
                i.putExtra("employeeid",productName);
                mCtx.startActivity(i);*/


            }
        });


    }
    @Override
    public int getItemCount() {

        return logintimingsList.size();

    }

    public void setfilter(List<Login_Timings_Class> listitem)
    {
        logintimingsList=new ArrayList<>();
        logintimingsList.addAll(listitem);
        notifyDataSetChanged();

    }

    class Loginviewholder extends RecyclerView.ViewHolder {

        TextView Email,Location,Time;
        String name;

        public Loginviewholder(@NonNull final View itemView) {
            super(itemView);
            Location = itemView.findViewById(R.id.location);
            Email = itemView.findViewById(R.id.email);
            Time = itemView.findViewById(R.id.time);


        }

    }


}
