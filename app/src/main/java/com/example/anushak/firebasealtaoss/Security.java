package com.example.anushak.firebasealtaoss;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Security extends AppCompatActivity {

    private ImageView mFingerprintImage;
    private TextView mParaLabel;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    private KeyStore keyStore;
    private Cipher cipher;
    private String KEY_NAME = "AndroidKey";

    TextView text;
    Button finger;
    String Text;

    TextView name, email, empid;
    String Name, Email, Empid;

    DatabaseReference reference;
    User user;

    public static final String KEY_FNAME="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_security);

        text = (TextView) findViewById(R.id.text);
        finger = (Button) findViewById(R.id.finger);

        Text = getIntent().getStringExtra("empid");
        text.setText(Text);

        mFingerprintImage = (ImageView) findViewById(R.id.fingerprintImage);
        mParaLabel = (TextView) findViewById(R.id.paraLabel);

        finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text = text.getText().toString();
                Name = name.getText().toString();
                Email = email.getText().toString();
                Empid = empid.getText().toString();
                foo();
            }
        });

        name = findViewById(R.id.profilename);
        email = findViewById(R.id.profileemail);
        empid = findViewById(R.id.profileempid);

        reference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Email = dataSnapshot.child("email").getValue().toString();
                 Empid = dataSnapshot.child("empid").getValue().toString();
                 Name = dataSnapshot.child("name").getValue().toString();

                email.setText(Email);
                empid.setText(Empid);
                name.setText(Name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {

                mParaLabel.setText("Fingerprint Scanner not detected in Device");

            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

                mParaLabel.setText("Permission not granted to use Fingerprint Scanner");

            } else if (!keyguardManager.isKeyguardSecure()) {

                mParaLabel.setText("Add Lock to your Phone in Settings");

            } else if (!fingerprintManager.hasEnrolledFingerprints()) {

                mParaLabel.setText("You should add atleast 1 Fingerprint to use this Feature");

            } else {

                mParaLabel.setText("Place your Finger on Scanner to Access the App.");

                generateKey();

                if (cipherInit()) {

                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                    fingerprintHandler.startAuth(fingerprintManager, cryptoObject);
                }
            }
        }





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




    }


    @TargetApi(Build.VERSION_CODES.M)
    private void generateKey() {

        try {

            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();

        } catch (KeyStoreException | IOException | CertificateException
                | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | NoSuchProviderException e) {

            e.printStackTrace();

        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }


        try {

            keyStore.load(null);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            return true;

        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }

    }

    public void foo() {
        if ((Text.equalsIgnoreCase("Employee")) && ((empid.getText().toString().startsWith("L")) || (empid.getText().toString().startsWith("H")) || (empid.getText().toString().startsWith("A")))) {
            Toast.makeText(getApplicationContext(), "Employee Dashboard", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Security.this, EmployeeDashboard.class);
            intent.putExtra("empid",Empid);
            startActivity(intent);
        }

        if ((Text.equalsIgnoreCase("HR")) && ((empid.getText().toString().startsWith("L")) || (empid.getText().toString().startsWith("H")))) {
            Toast.makeText(getApplicationContext(), "HR Dashboard", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Security.this, HrDashboard.class);
            intent.putExtra("empid",Empid);
            startActivity(intent);
        }

        if ((Text.equalsIgnoreCase("Admin")) && ((empid.getText().toString().startsWith("L")))) {
            Toast.makeText(getApplicationContext(), "Admin Dashboard", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Security.this, AdminDashboard.class);
            intent.putExtra("empid",Empid);
            startActivity(intent);
        }
        if((Text.equalsIgnoreCase("Admin")) && ((empid.getText().toString().startsWith("A")) || (empid.getText().toString().startsWith("H")))) {
            Toast.makeText(getApplicationContext(), "You are not Admin", Toast.LENGTH_LONG).show();
            mParaLabel.setVisibility(View.GONE);
        }

        if((Text.equalsIgnoreCase("HR")) && ((empid.getText().toString().startsWith("A")))) {
            Toast.makeText(getApplicationContext(), "You are not HR", Toast.LENGTH_LONG).show();
            mParaLabel.setVisibility(View.GONE);
        }
    }
}
