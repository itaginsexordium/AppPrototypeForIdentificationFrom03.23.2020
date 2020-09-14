package com.kg.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class ChekInformation extends AppCompatActivity {
    //constant
    private static final int PICK_IMAGE_REQUEST = 1;
    private static  int imgLink = 0;


    //intent fragment
    private String username;
    //

    //info card
    private EditText number_indefication_card, date_give_card, end_of_card, MKK,
    //info user

    surname, name, secondName, dateBorn, bornPlace,
    //plase user
    city, region, street, house_and_apartment;


    //photo for identification
    private Uri imageURI;



    private  String  firstUrl;
    private String secondUrl;
    private String curentNameImg;
    private String firstImg="first";
    private String secondImg="second";

    private ProgressBar mProgressBar;


    private  ImageButton btnFirstImage,btnSecondImage;
    private Button btnUpload ,nextButton;
    private TextView alert;


    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private StorageReference myStorageRef;
    private UploadTask uploadTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chek_information);
        Intent intent = getIntent();
        String username =intent.getStringExtra("EXTRA_USERNAME");


        //EDITTEXTS
        mProgressBar=(ProgressBar)findViewById(R.id.mProgressBar);
        ////info card
        number_indefication_card = (EditText) findViewById(R.id.number_indefication_card);
        date_give_card = (EditText) findViewById(R.id.date_give_card);
        end_of_card = findViewById(R.id.end_of_card);
        MKK = findViewById(R.id.MKK);

        //info user

        surname = (EditText) findViewById(R.id.familyi);
        name = (EditText) findViewById(R.id.name);
        secondName = (EditText) findViewById(R.id.secondName);
        dateBorn = (EditText) findViewById(R.id.dateBorn);
        bornPlace = (EditText) findViewById(R.id.bornPlace);

        //plase user
        city = (EditText) findViewById(R.id.city);
        region = (EditText) findViewById(R.id.region);
        street = (EditText) findViewById(R.id.street);
        house_and_apartment = (EditText) findViewById(R.id.house_and_apartment);


        database = FirebaseDatabase.getInstance();
        myStorageRef = FirebaseStorage.getInstance().getReference("uploads").child(username);
        myRef=database.getReference("users").child(username).child("information");

        btnFirstImage = (ImageButton) findViewById(R.id.firstImageButton);
        btnSecondImage= (ImageButton) findViewById(R.id.secondImageButton);

        btnFirstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLink=1;
               curentNameImg=firstImg;
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(ChekInformation.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    openFileChooser();
                }

            }
        });



        btnSecondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               imgLink=2;
                curentNameImg=secondImg;
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(ChekInformation.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    openFileChooser();
                }
            }
        });
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
        final StorageReference fileReference= myStorageRef.child(curentNameImg+getFileExtension(curentImage));
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

                    Toast.makeText(ChekInformation.this, "complete", Toast.LENGTH_SHORT).show();
                    Uri downloadUri = task.getResult();
                    if (downloadUri != null) {
                       if(imgLink==2) {
                           secondUrl = downloadUri.toString();
                       }else if(imgLink==1){
                           firstUrl=downloadUri.toString();
                       }
                    }
                }
            });
    }

    public void nexButton(View view) {
        UserInformationCase userInformation = new UserInformationCase(
                number_indefication_card.getText().toString(),
                date_give_card.getText().toString(),
                end_of_card.getText().toString(),
                MKK.getText().toString(),
                surname.getText().toString(),
                name.getText().toString(),
                secondName.getText().toString(),
                dateBorn.getText().toString(),
                bornPlace.getText().toString(),
                city.getText().toString(),
                region.getText().toString(),
                street.getText().toString(),
                house_and_apartment.getText().toString(),
                firstUrl,
                secondUrl);
        myRef.setValue(userInformation);

        Intent gonext = new Intent(this, PActivity.class);
        gonext.putExtra("PAYFUN",2);
        startActivity(gonext);


    }
}