package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    SignInButton Google_Login;

    private static final int RC_SIGN_IN = 1000;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mDatabase;
    String user_id = "";
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        GoogleSignInOptions gso = new
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuth = FirebaseAuth.getInstance();

        Google_Login = findViewById(R.id.Google_Login);
        Google_Login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent signInIntent =
                        Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);


            }

        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                //구글 로그인 성공해서 파베에 인증
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);

                mDatabase = FirebaseDatabase.getInstance().getReference();

                user_id = account.getId();

                UseridData.getInstance().setUser_id(user_id);


                mDatabase.child("User").child(user_id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {


                        } else {
                            try {
                                String result_check_uid = task.getResult().getValue().toString();
                                System.out.println("cxz" + result_check_uid);

                            } catch (Exception e) {

                                System.out.println("rewq");

                                mDatabase = FirebaseDatabase.getInstance().getReference();

                                UserInfoModel userInfoModel = new UserInfoModel();

                                userInfoModel.user_name = account.getDisplayName();
                                String temp = account.getEmail();
                                String[] temp1 = temp.split("@");
                                userInfoModel.user_email = temp1[0];
                                userInfoModel.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                //userInfoModel.personPhoto = account.getPhotoUrl();

                                System.out.println("edc" + user_id);

                                // 게시글 내용 저장
                                mDatabase.child("User").child(user_id).child("Information")
                                        .setValue(userInfoModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });

                                Intent intent1234 = new Intent(MainActivity.this, CategoryActivity.class);
                                intent1234.putExtra("user_id_last", user_id);
                                startActivity(intent1234);
                                finish();
                                System.out.println("gfds");
                            }


                        }


                    }


                });

                mDatabase.child("users").child(user_id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {


                        } else {
                            try {
                                String result_check_uid = task.getResult().getValue().toString();
                                System.out.println("cxz" + result_check_uid);

                            } catch (Exception e) {

                                System.out.println("rewq");

                                mDatabase = FirebaseDatabase.getInstance().getReference();

                                UserInfoModel userInfoModel = new UserInfoModel();
                                    uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                userInfoModel.user_name = account.getDisplayName();
                                String temp = account.getEmail();
                                String[] temp1 = temp.split("@");
                                userInfoModel.user_email = temp1[0];
                                userInfoModel.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                userInfoModel.status = "";


                                //userInfoModel.personPhoto = account.getPhotoUrl();

                                System.out.println("edc" + user_id);

                                // 게시글 내용 저장
                                mDatabase.child("users").child(uid)
                                        .setValue(userInfoModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });

                                String deviceToken = FirebaseInstanceId.getInstance().getToken();

                                mDatabase.child("users").child(uid).child("device_token")
                                        .setValue(deviceToken);


                            }


                        }


                    }


                });

                System.out.println("ewq");
                Intent intent4321 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent4321);
                finish();


            } else {
                //구글 로그인 실패
            }

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "구글 로그인 인증 성공", Toast.LENGTH_SHORT).show();


                        }

                    }

                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}