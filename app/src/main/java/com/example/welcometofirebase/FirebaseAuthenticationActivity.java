package com.example.welcometofirebase;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Arrays;
import java.util.List;

public class FirebaseAuthenticationActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_authentication);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        tvInfo = findViewById(R.id.tvInfo);

        setupUI();
    }

    public void onClickStartSignIn(View view) {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build());

// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTosAndPrivacyPolicyUrls(
                                "https://example.com/terms.html",
                                "https://example.com/privacy.html")
                        .build(),
                RC_SIGN_IN);
    }

    public void onClickLogout(View view) {
        firebaseAuth.signOut();
        setupUI();
    }

    private void setupUI() {
        String info = "";
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null){
            //no user sign-in
            tvInfo.setText("signIn info:");
        }else {
            //set UI
            switch (firebaseUser.getProviderData().get(1).getProviderId()){
                case PhoneAuthProvider.PROVIDER_ID:
                    info = firebaseUser.getPhoneNumber() + "\n";
                    info += firebaseUser.getUid() + "\n";
                    info += firebaseUser.getProviderId() + "\n";

                    tvInfo.setText(info);
                    break;
                case GoogleAuthProvider.PROVIDER_ID:
                    info = firebaseUser.getPhoneNumber() + "\n";
                    info += firebaseUser.getUid() + "\n";
                    info += firebaseUser.getProviderId() + "\n";
                    info += firebaseUser.getDisplayName() + "\n";
                    info += firebaseUser.getEmail() + "\n";
                    info += firebaseUser.getPhotoUrl() + "\n";

                    tvInfo.setText(info);
                    break;
                case EmailAuthProvider.PROVIDER_ID:
                    info = firebaseUser.getPhoneNumber() + "\n";
                    info += firebaseUser.getUid() + "\n";
                    info += firebaseUser.getProviderId() + "\n";
                    info += firebaseUser.getDisplayName() + "\n";
                    info += firebaseUser.getEmail() + "\n";
                    info += firebaseUser.getPhotoUrl() + "\n";

                    tvInfo.setText(info);
                    break;
                    default:
                        tvInfo.setText("No data found");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                setupUI();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
