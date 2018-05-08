    package com.example.alptekin.bilconnectt;

    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.net.Uri;
    import android.support.annotation.NonNull;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.firestore.DocumentSnapshot;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.storage.FirebaseStorage;
    import com.google.firebase.storage.StorageReference;
    import com.google.firebase.storage.UploadTask;
    import com.theartofdev.edmodo.cropper.CropImage;
    import com.theartofdev.edmodo.cropper.CropImageView;

    import java.io.ByteArrayOutputStream;
    import java.io.File;
    import java.io.IOException;
    import java.text.DateFormat;
    import java.util.Date;

    import id.zelory.compressor.Compressor;

    public class NewPost extends AppCompatActivity {
        public static String post = "Announcement";
        public static String newPostId;
        public static final String TAG = "NewPost_Error";
        private Button chooseImage;
        private boolean compAnnonTouch = false;//this boolean will show which button is touched, compalints or announceents
        private Button announcementsButton;
        private Button complaintsButton;
        private EditText topic;
        private EditText description;
        private Button sendData;
        private Uri postImageUri;
        private FirebaseAuth firebaseAuth;
        private String currentdate = DateFormat.getDateInstance().format(new Date());
        private Button delete;


        private String current_user_id;
        private Bitmap compressedImageFile;
        private StorageReference storageReference;
        private FirebaseFirestore firebaseFirestore;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_post);
            storageReference = FirebaseStorage.getInstance().getReference();
            firebaseFirestore = FirebaseFirestore.getInstance();
            firebaseAuth = FirebaseAuth.getInstance();
            //I do not have login part so i have to give a user id directly for now, delete the user part and code will work for any user
            current_user_id = "9rbiBB3Vl9cfI200I5eutY2vkC32";//firebaseAuth.getCurrentUser().getUid();


           // DocumentSnapshot
            //getting data from database(complaints)

            description = findViewById(R.id.descriptionNew);
            topic = findViewById(R.id.topicNew);

            delete = (Button) findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseFirestore.collection("Announcements").document("2").delete();
                }
            });




            //if user touches announcements button, he can create an announcement
            announcementsButton = (Button) findViewById(R.id.AnnouncementsButton);
            announcementsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    compAnnonTouch = false;
                    post = "Announcements";
                }
            });
            //if user touches complaints button, he can create a complaint
            complaintsButton = (Button) findViewById(R.id.ComplaintsButton);
            complaintsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    compAnnonTouch = true;
                    post = "Complaints";
                }
            });


            chooseImage = (Button) findViewById(R.id.UploadImage);
            //choosing image with Crop Image Library
            chooseImage.setOnClickListener(new View.OnClickListener() {
                @Override
                //upload image part, use cropImage Library

                public void onClick(View view) {
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setMinCropResultSize(512, 512)
                            .setAspectRatio(1, 1)
                            .start(NewPost.this);

                }
            });

            sendData = (Button) findViewById(R.id.SendData);
            //sending post to FireBaseStore
            sendData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //getting an id for the post
                        //getting the last index of announcement/complaint, so we can give the new post a proper id
                        firebaseFirestore.collection("index").document("Announcements").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists())
                                newPostId = documentSnapshot.getString("index");
                                else{
                                    Toast.makeText(NewPost.this, "Document was not found", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, e.toString());
                            }
                        });

                    final String desc = description.getText().toString();
                    final String topicNew = topic.getText().toString();
                    if(!TextUtils.isEmpty(desc) && !TextUtils.isEmpty(topicNew) &&postImageUri != null){



                        // PHOTO UPLOAD
                        //compressed image library, sets image size and quality
                        File newImageFile = new File(postImageUri.getPath());
                        try {

                            compressedImageFile = new Compressor(NewPost.this)
                                    .setMaxHeight(720)
                                    .setMaxWidth(720)
                                    .setQuality(50)
                                    .compressToBitmap(newImageFile);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imageData = baos.toByteArray();

                        // Upload pfoto to firebase Storage
                        UploadTask filePath;
                        if(compAnnonTouch){
                            filePath = storageReference.child("Complaints_Images").child(newPostId + ".jpg").putBytes(imageData);
                        }
                        else{
                            filePath = storageReference.child("Announcement_Images").child(newPostId + ".jpg").putBytes(imageData);
                        }

                        //Upload a thumbnail to firebase Storage
                        filePath.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> task) {

                                final String downloadUri = task.getResult().getDownloadUrl().toString();

                                if(task.isSuccessful()){

                                    File newThumbFile = new File(postImageUri.getPath());
                                    try {
                                        //setting size and quality very low, it is a thumbnail
                                        compressedImageFile = new Compressor(NewPost.this)
                                                .setMaxHeight(100)
                                                .setMaxWidth(100)
                                                .setQuality(1)
                                                .compressToBitmap(newThumbFile);

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                    byte[] thumbData = baos.toByteArray();

                                    UploadTask uploadTask = storageReference.child("Announcement_Images/thumbs")
                                            .child(newPostId + ".jpg").putBytes(thumbData);

                                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            String downloadthumbUri = taskSnapshot.getDownloadUrl().toString();

                                            final String post;
                                            Post newPost = new Announcement(topicNew,desc,current_user_id,downloadthumbUri,downloadUri, currentdate);
                                            if(compAnnonTouch){
                                                post = "Complaints";
                                                newPost = new Complaint(topicNew,desc,current_user_id,downloadthumbUri,downloadUri, currentdate);
                                            }
                                            else{
                                                post = "Announcements";
                                                newPost = new Announcement(topicNew,desc,current_user_id,downloadthumbUri,downloadUri, currentdate);
                                            }

                                            //uploading the post
                                            firebaseFirestore.collection(post).document(newPostId).set(newPost).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                        int temp = Integer.parseInt(newPostId) + 1;
                                                        firebaseFirestore.collection("index").document(""+ post).update( "index" ,temp+ "");
                                                        Toast.makeText(NewPost.this, "Announcement was added", Toast.LENGTH_LONG).show();
                                                        Intent mainIntent = new Intent(NewPost.this, Announcements.class);
                                                        startActivity(mainIntent);
                                                        finish();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(NewPost.this, "Sorry an error Occured", Toast.LENGTH_LONG).show();
                                                    Log.d(TAG, e.toString());
                                                    Intent mainIntent = new Intent(NewPost.this, Announcements.class);
                                                    startActivity(mainIntent);
                                                    finish();
                                                }
                                            });

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Log.d(TAG, e.toString());

                                        }
                                    });
                                }
                            }
                        });

                    } //if there is no image in the post this part will create a post without image
                    else if(!TextUtils.isEmpty(desc) && !TextUtils.isEmpty(topicNew)){
                        Post newPost = new Announcement(topicNew,desc,current_user_id,"","", currentdate);

                       // final String post;
                        if(compAnnonTouch){
                            post = "Complaints";
                            newPost = new Complaint(topicNew,desc,current_user_id,"","", currentdate);
                        }
                        else{
                            post = "Announcements";
                            newPost = new Announcement(topicNew,desc,current_user_id,"","", currentdate);
                        }

                        firebaseFirestore.collection(post).document(newPostId).set(newPost).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                int temp = Integer.parseInt(newPostId) + 1;
                                firebaseFirestore.collection("index").document("" + post).update( "index" ,temp+ "");
                                Toast.makeText(NewPost.this, "Announcement was added", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(NewPost.this, Announcements.class);
                                startActivity(mainIntent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(NewPost.this, "Sorry an error Occured", Toast.LENGTH_LONG).show();
                                Log.d(TAG, e.toString());
                                Intent mainIntent = new Intent(NewPost.this, Announcements.class);
                                startActivity(mainIntent);
                                finish();
                            }
                        });
                    }

                }
            });
        }

        //code for using crop image library
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {

                    postImageUri = result.getUri();

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                    Exception error = result.getError();

                }
            }

        }
    }
