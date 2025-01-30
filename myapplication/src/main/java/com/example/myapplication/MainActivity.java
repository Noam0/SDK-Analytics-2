package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.analyticssdk_2.AnalyticsSDK;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_crash_nullpointer).setOnClickListener(view -> testNullPointerException());
        findViewById(R.id.btn_crash_index).setOnClickListener(view -> testIndexOutOfBoundsException());
        findViewById(R.id.btn_crash_illegal_arg).setOnClickListener(view -> testIllegalArgumentException());
        //findViewById(R.id.btn_crash_stackoverflow).setOnClickListener(view -> testStackOverflowError());
        findViewById(R.id.btn_crash_outofmemory).setOnClickListener(view -> testOutOfMemoryError());
        findViewById(R.id.btn_crash_arithmetic).setOnClickListener(view -> testArithmeticException());
        findViewById(R.id.btn_crash_security).setOnClickListener(view -> testSecurityException());


    }


    // Crash test methods
    private void testNullPointerException() {
        String test = null;
        test.length();
    }

    private void testIndexOutOfBoundsException() {
        List<Integer> list = new ArrayList<>();
        int value = list.get(10);
    }

    private void testIllegalArgumentException() {
        throw new IllegalArgumentException("Testing IllegalArgumentException");
    }
    /*
    private void testStackOverflowError() {
        testStackOverflowError();
    }
    */

    private void testOutOfMemoryError() {
        int[] memoryFill = new int[Integer.MAX_VALUE];
    }

    private void testArithmeticException() {
        int result = 10 / 0;
    }

    private void testSecurityException() {
        throw new SecurityException("Testing SecurityException");
    }
}