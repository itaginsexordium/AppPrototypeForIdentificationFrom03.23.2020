package com.kg.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class PActivity extends AppCompatActivity {
    private  static String username;
    private static final int PICK_IMAGE_REQUEST = 1;
    private  int maypath;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private StorageReference myStorageRef;
    private UploadTask uploadTask;
    private Uri imageURI;
    private String imageURL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Intent intent =getIntent();
        maypath=intent.getIntExtra("PAYFUN",maypath);
         username =intent.getStringExtra("EXTRA_USERNAME");




        myStorageRef = FirebaseStorage.getInstance().getReference("uploads").child(username);
        myRef=database.getReference("users").child(username).child("information");





    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageURI = data.getData();
            uploadFile(data.getData());
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private   void uploadFile(final Uri curentImage){
        final StorageReference fileReference= myStorageRef.child("PAY"+username+getFileExtension(curentImage));
        uploadTask = fileReference.putFile(curentImage);

        uploadTask.continueWithTask(new Continuation() {
            @Override
            public Object then(@NonNull Task task) throws Exception {
                if (!task.isComplete()) {
                    throw task.getException();
                }
                return fileReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                Toast.makeText(PActivity.this, "complete", Toast.LENGTH_SHORT).show();
                Uri downloadUri = task.getResult();
                if (downloadUri != null) {
                   imageURL  = downloadUri.toString();

                }
            }
        });
    }














    public void Elcard(View view) {
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("https://empay.page.link/C1uproEX8aBFA5Y89"));
        startActivity(browserIntent);
    }

    public void Qiwi(View view) {
        if (maypath==2){
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("https://oplata.qiwi.com/form?invoiceUid=6fb463e5-ddda-4f52-9e52-12b31a92f7ff"));
        startActivity(browserIntent);
    }else if (maypath==1){
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://oplata.qiwi.com/form?invoiceUid=8e06116b-4156-4237-ac95-6e8775366019"));
            startActivity(browserIntent);

    }
}


    public void whatsapp(View view) {
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/996500009984"));
        startActivity(browserIntent);
    }

    public void telegramm(View view) {
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Exordia"));
        startActivity(browserIntent);
    }
}
