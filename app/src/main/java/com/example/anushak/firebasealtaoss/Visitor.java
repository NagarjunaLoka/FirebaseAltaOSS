package com.example.anushak.firebasealtaoss;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
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

import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Visitor extends AppCompatActivity {

    EditText fname, mname, lname, email, phone, contactperson, empid, companyname, companybranch, dateofvisit, reasonforvisit, personalid;
    Spinner sppersonalid, typeofvisit;
    ImageView dov;
    Button save;
    FirebaseDatabase database;
    DatabaseReference reference;
    VisUser user;
    DatePickerDialog datePickerDialog;
    CircleImageView profileImageView;
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private static final int CAMERA_PIC_REQUEST = 1111;
    Uri FilePathUri;
    String Storage_Path = "Visitor Pictures/";
    StorageReference storageReference;

    HorizontalScrollView hsv1,hsv2,hsv3;
    TextView text,userid,usertype;
    String Text,UserId,UserType;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list,list1,list2;
    android.support.v7.widget.Toolbar toolbar;
    Animation fromtop;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_visitor);

        fname = findViewById(R.id.firstname);
        mname = findViewById(R.id.middlename);
        lname = findViewById(R.id.lastname);
        email = findViewById(R.id.edit_text_email);
        phone = findViewById(R.id.edit_text_phone);
        contactperson = findViewById(R.id.contactperson);
        empid = findViewById(R.id.empid);
        companyname = findViewById(R.id.comname);
        companybranch = findViewById(R.id.combranch);
        dateofvisit = findViewById(R.id.dov);
        dov = findViewById(R.id.date_dob);
        reasonforvisit = findViewById(R.id.reason);
        personalid = findViewById(R.id.edit_text_personal);
        sppersonalid = findViewById(R.id.spidentity);
        typeofvisit = findViewById(R.id.spitypevisit);
        save = findViewById(R.id.button_register);
        profileImageView=(CircleImageView)findViewById(R.id.profileImageView);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.text);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("VisitorDetails");
        storageReference = FirebaseStorage.getInstance().getReference();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnregister();
            }
        });
        dov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Visitor.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateofvisit.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating intent.
                Intent intent = new Intent();
                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), REQUEST_PICK_PHOTO);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                profileImageView.setImageBitmap(bitmap);

                // After selecting image change choose button above text.

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }

    private void btnregister() {
        final String Fname = fname.getText().toString().trim();
        final String Mname = mname.getText().toString().trim();
        final String Lname = lname.getText().toString().trim();
        final String Email = email.getText().toString().trim();
        final String Phone = phone.getText().toString().trim();
        final String Contactperson = contactperson.getText().toString().trim();
        final String Empid = empid.getText().toString().trim();
        final String Companyname = companyname.getText().toString().trim();
        final String Companybranch = companybranch.getText().toString().trim();
        final String Dateofvisit = dateofvisit.getText().toString().trim();
        final String Reasonforvisit = reasonforvisit.getText().toString().trim();
        final String Sppersonal = sppersonalid.getSelectedItem().toString().trim();
        final String Personal = personalid.getText().toString().trim();
        final String Typeofvisit = typeofvisit.getSelectedItem().toString().trim();


        if (!TextUtils.isEmpty(Fname)) {
            if (FilePathUri != null) {
                final StorageReference storageReference2nd = storageReference.child(Storage_Path + Fname + "." + GetFileExtension(FilePathUri));
                storageReference2nd.putFile(FilePathUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return storageReference2nd.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        final Uri downloadUri = task.getResult();
                        Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                        @SuppressWarnings("VisibleForTests")
                        String VisID = reference.push().getKey();


                        VisUser user = new VisUser(VisID, Fname, Mname, Lname, Email, Phone, Contactperson, Empid, Companyname, Companybranch, Dateofvisit, Reasonforvisit, Sppersonal, Personal, Typeofvisit,downloadUri.toString());
                        reference.child(VisID).setValue(user);

                        Intent intent = new Intent(Visitor.this, VisitorDashboard.class);

                        startActivity(intent);
                        Toast.makeText(Visitor.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        } else {
            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.queryform,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(Visitor.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }*/
}

