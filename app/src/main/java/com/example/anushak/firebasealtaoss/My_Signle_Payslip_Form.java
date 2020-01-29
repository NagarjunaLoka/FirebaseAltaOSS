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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class My_Signle_Payslip_Form extends AppCompatActivity {

    String name, empid, designation, location, pan, payabledays, paiddays, basicsalary, houserentallowence, conveyances, specialallowence,
            otherallowence, medical, epf, transopartation, others, ptd, month_and_year;

    TextView Name, Empid, Designation, Location, Pannum, Payabledays, Paiddays, Basicpay, Houserentallowence, Convencesallowence, Specialallowence, Otherallowences, Medical, Epf, Transportation, Others, Ptd, Monthandyear;

    private Button btn;
    private LinearLayout llScroll;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_my__signle__payslip__form);

        llScroll = findViewById(R.id.my_payslips);

        Name = findViewById(R.id.name);
        Empid = findViewById(R.id.empid);
        Designation = findViewById(R.id.designation);
        Location = findViewById(R.id.location);
        Pannum = findViewById(R.id.pannum);
        Payabledays = findViewById(R.id.payabledays);
        Paiddays = findViewById(R.id.paiddays);
        Basicpay = findViewById(R.id.basicpay);
        Houserentallowence = findViewById(R.id.houserent);
        Convencesallowence = findViewById(R.id.concencespay);
        Specialallowence = findViewById(R.id.specialallowence);
        Otherallowences = findViewById(R.id.otherallowence);
        Medical = findViewById(R.id.medical);
        Epf = findViewById(R.id.pf);
        Transportation = findViewById(R.id.transortation);
        Others = findViewById(R.id.others);
        Ptd = findViewById(R.id.ptd);
        Monthandyear = findViewById(R.id.monthandyear);
        btn = findViewById(R.id.pdf);


        //getting the data from previes activity using intents

        name = getIntent().getStringExtra("Name");
        empid = getIntent().getStringExtra("Empid");
        designation = getIntent().getStringExtra("Designation");
        location = getIntent().getStringExtra("Location");
        pan = getIntent().getStringExtra("Pannum");
        payabledays = getIntent().getStringExtra("Payabledays");
        paiddays = getIntent().getStringExtra("Paiddays");
        basicsalary = getIntent().getStringExtra("Basicpay");
        houserentallowence = getIntent().getStringExtra("Houserent");
        conveyances = getIntent().getStringExtra("Convences");
        specialallowence = getIntent().getStringExtra("Specialallowence");

        otherallowence = getIntent().getStringExtra("Otheralownce");
        medical = getIntent().getStringExtra("Medical");

        transopartation = getIntent().getStringExtra("Transportation");
        others = getIntent().getStringExtra("Others");

        month_and_year = getIntent().getStringExtra("Monthandyear");

        Name.setText(name);
        Empid.setText(empid);
        Designation.setText(designation);
        Location.setText(location);
        Pannum.setText(pan);
        Payabledays.setText(payabledays);
        Paiddays.setText(paiddays);
        Basicpay.setText(basicsalary);
        Houserentallowence.setText(houserentallowence);
        Convencesallowence.setText(conveyances);
        Specialallowence.setText(specialallowence);
        Otherallowences.setText(otherallowence);
        Medical.setText(medical);
        Transportation.setText(transopartation);
        Others.setText(others);
        Monthandyear.setText(month_and_year);



        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {

                    // Toast.makeText(Emp_PaySlips.this, "connected", Toast.LENGTH_LONG).show();
                } else {

                    // Toast.makeText(Emp_PaySlips.this,getDrawable(R.dr), Toast.LENGTH_LONG).show();

                    Toast aa = Toast.makeText(getBaseContext(), "NO INTERNET CONNECTION",Toast.LENGTH_SHORT);
                    ImageView cc = new ImageView(getBaseContext());
                    cc.setImageResource(R.drawable.no_internet);
                    aa.setView(cc);
                    aa.show();


                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

                // System.err.println("Listener was cancelled");
                Toast.makeText(getBaseContext(), "SOMETHING WENT WRONG ",Toast.LENGTH_SHORT);


            }
        });





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("size", " " + llScroll.getWidth() + "  " + llScroll.getWidth());
                bitmap = loadBitmapFromView(llScroll, llScroll.getWidth(), llScroll.getHeight());
                createPdf();
            }
        });





    }

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
                Toast.makeText(My_Signle_Payslip_Form.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
}
