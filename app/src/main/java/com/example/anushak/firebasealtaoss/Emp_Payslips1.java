package com.example.anushak.firebasealtaoss;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Emp_Payslips1 extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PaySlips_Adapter adapter;
    private List<Salary> artistList;
    DatabaseReference dbArtists;

    TextView empid_date_time;

    String Empid, Year, Month;
    String hi;

    private Button btn;
    private LinearLayout llScroll;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp__payslips1);

        recyclerView = findViewById(R.id.recyclerView);
        empid_date_time = findViewById(R.id.empid_date_time);
        btn = findViewById(R.id.download);
        llScroll = findViewById(R.id.llscroll);


        Empid = getIntent().getStringExtra("empid");
        Year = getIntent().getStringExtra("year");
        Month = getIntent().getStringExtra("month");

        empid_date_time.setText(Empid + Year + Month);

        hi = empid_date_time.getText().toString();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList = new ArrayList<>();
        adapter = new PaySlips_Adapter(this, artistList);
        recyclerView.setAdapter(adapter);

        //1. SELECT * FROM Artists
        // dbArtists = FirebaseDatabase.getInstance().getReference("Tickets");

        //2. SELECT * FROM Artists WHERE id = "-LAJ7xKNj4UdBjaYr8Ju"
        Query query = FirebaseDatabase.getInstance().getReference("Salary Details")
                .orderByChild("empid_month_and_year")
                .equalTo(hi);

        query.addListenerForSingleValueEvent(valueEventListener);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("size", " " + llScroll.getWidth() + "  " + llScroll.getWidth());
                bitmap = loadBitmapFromView(llScroll, llScroll.getWidth(), llScroll.getHeight());
                createPdf();
            }
        });


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Salary salary = snapshot.getValue(Salary.class);
                    artistList.add(salary);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/AltaPayslip.pdf";

        //  String destPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constants.SAVE_FOLDER_NAME;
        //File destFile = new File(destPath);

        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF of Payslip is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

       /* Intent i=new Intent(ScrollActivity.this,Pdf_View.class);
        i.putExtra("bitmap",bitmap);
        startActivity(i);*/
    }

    private void openGeneratedPDF() {
        File file = new File("/sdcard/AltaPayslip.pdf");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(Emp_Payslips1.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
}
