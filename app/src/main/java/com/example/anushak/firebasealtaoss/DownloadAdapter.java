package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public  class DownloadAdapter extends FirebaseListAdapter2<User1> {
    private TextView empid, documenttype;
    Button vi,delete;

    String Url,ID;
    private DatabaseReference mDatabase;


    public DownloadAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, User1.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view,  User1 model) {

        mDatabase= FirebaseDatabase.getInstance().getReference("MyDownloads");

        vi=(Button) view.findViewById(R.id.view);
        delete=(Button) view.findViewById(R.id.delete);
        empid = (TextView) view.findViewById(R.id.empid);
        empid.setText(model.getEmpid1().toString());
        documenttype=(TextView) view.findViewById(R.id.type);
        documenttype.setText(model.getType1().toString());
        Url=model.getUrl1().toString();
        ID= model.getId1();

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(Url));
                v.getContext().startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(ID).removeValue();
                Intent i=new Intent(v.getContext(),MyDownloads.class);
                v.getContext().startActivity(i);
                Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
