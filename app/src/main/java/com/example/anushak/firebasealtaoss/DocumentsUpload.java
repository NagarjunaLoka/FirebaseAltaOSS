package com.example.anushak.firebasealtaoss;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DocumentsUpload extends AppCompatActivity {

    Spinner documenttype, uploadmonth, uploadyear;
    EditText empid;
    Button selectfile,uploadfile;
    ProgressBar progressBar;

    String Empid,UserId,UserType,Text;

    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;

    final static int PICK_PDF_CODE = 2342;

    public static final String STORAGE_PATH_UPLOADS = "uploads/";
    public static final String DATABASE_PATH_UPLOADS = "uploads";

    TextView text,usertype,userId;

    Animation fromtop;
    ImageView iv;
    TextView dashboard;
    HorizontalScrollView hsv1;
    android.support.v7.widget.Toolbar toolbar;
    public Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_documents_upload);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        documenttype = (Spinner) findViewById(R.id.documenttype);
        uploadmonth = (Spinner) findViewById(R.id.uploadmonth);
        uploadyear = (Spinner) findViewById(R.id.uploadyear);
        empid = (EditText) findViewById(R.id.empid);
        selectfile = (Button) findViewById(R.id.selectfile);
        uploadfile=(Button)findViewById(R.id.uploadfile);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        userId = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);

        iv = (ImageView)findViewById(R.id.iv);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);

        dashboard = (TextView)findViewById(R.id.dashboard);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        hsv1.setAnimation(fromtop);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(DATABASE_PATH_UPLOADS);

        UserId = getIntent().getStringExtra("userId");

        userId.setText(UserId);

        text.setText("Hr Dashboard - Documents Upload");

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocumentsUpload.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Empid = empid.getText().toString();

                if (!TextUtils.isEmpty(Empid)) {
                    getPDF();
                    return;
                }
                else if (TextUtils.isEmpty(Empid)) {
                    empid.setError("Please enter ID");
                    empid.requestFocus();
                    return;
                }
            }
        });
        uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(documenttype.getSelectedItem().toString().equalsIgnoreCase("Payslips")) {
                    if (filePath != null) {
                        //uploading the file
                        uploadFile(filePath);
                    } else {
                        Toast.makeText(DocumentsUpload.this, "No file chosen", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (filePath != null) {
                        //uploading the file
                        uploadFile1(filePath);
                    } else {
                        Toast.makeText(DocumentsUpload.this, "No file chosen", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void getPDF() {

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();

            selectfile.setText("PDF is Selected");
        }
    }

    private void uploadFile(Uri data) {
        progressBar.setVisibility(View.VISIBLE);
        final StorageReference sRef = mStorageReference.child(STORAGE_PATH_UPLOADS).child(documenttype.getSelectedItem().toString()).child(documenttype.getSelectedItem()+empid.getText().toString()+uploadyear.getSelectedItem().toString()+uploadmonth.getSelectedItem().toString()+".pdf");
        sRef.putFile(data)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return sRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    progressBar.setVisibility(View.GONE);
                    uploadfile.setText("File Uploaded Successfully");

                    @SuppressWarnings("VisibleForTests")

                    User upload = new User(documenttype.getSelectedItem().toString(), empid.getText().toString(), uploadmonth.getSelectedItem().toString(), uploadyear.getSelectedItem().toString(), downloadUri.toString());
                    String id =mDatabaseReference.push().getKey();
                    mDatabaseReference.child(documenttype.getSelectedItem().toString()).child(id).setValue(upload);


                } else {
                    Toast.makeText(DocumentsUpload.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void uploadFile1(Uri data) {
        progressBar.setVisibility(View.VISIBLE);
        final StorageReference sRef = mStorageReference.child(STORAGE_PATH_UPLOADS).child(documenttype.getSelectedItem().toString())
                .child(documenttype.getSelectedItem()+empid.getText().toString()+".pdf");
        sRef.putFile(data)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return sRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    progressBar.setVisibility(View.GONE);
                    uploadfile.setText("File Uploaded Successfully");

                    @SuppressWarnings("VisibleForTests")

                    User upload = new User(documenttype.getSelectedItem().toString(), empid.getText().toString(), uploadmonth.getSelectedItem().toString(), uploadyear.getSelectedItem().toString(), downloadUri.toString());
                    String id =mDatabaseReference.push().getKey();
                    mDatabaseReference.child(documenttype.getSelectedItem().toString()).child(id).setValue(upload);


                } else {
                    Toast.makeText(DocumentsUpload.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        UserId = userId.getText().toString();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i=new Intent(DocumentsUpload.this,QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
