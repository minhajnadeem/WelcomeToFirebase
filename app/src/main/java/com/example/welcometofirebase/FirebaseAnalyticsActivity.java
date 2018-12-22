package com.example.welcometofirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseAnalyticsActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_analytics);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    public void onClickAddToCart(View view) {
        logEventAddToCart();
    }

    private void logEventAddToCart() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,"Item 1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"1");
        bundle.putLong(FirebaseAnalytics.Param.QUANTITY,1);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART,bundle);
    }
}
