package com.example.analyticssdk_2.endPoints;


import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.analyticssdk_2.AnalyticsSDK;
import com.example.analyticssdk_2.R;
import com.example.analyticssdk_2.models.AppRatingRequest;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRatingsManager {

    public static void showRatingDialog(Activity activity, String userId) {
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.rating_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
        EditText commentEditText = dialogView.findViewById(R.id.commentEditText);
        Button submitButton = dialogView.findViewById(R.id.submitButton);
        TextView noThanksText = dialogView.findViewById(R.id.noThanksText);

        submitButton.setOnClickListener(v -> {
            int rating = (int) ratingBar.getRating();
            String comment = commentEditText.getText().toString();

            rateApp(userId, rating, comment);

            dialog.dismiss();
        });

        noThanksText.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    public static void rateApp(String userId , int rating, String comment) {
        String appId = AnalyticsSDK.getInstance().getAppId();
        AppRatingRequest request = new AppRatingRequest(appId, userId, rating, comment);

        Gson gson = new Gson();
        String jsonPayload = gson.toJson(request);
        System.out.println("Request Payload: " + jsonPayload);

        AnalyticsSDK.getInstance().getApiService().rateAppWithHeaders("application/json", request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println("App rated successfully!");
                    LogManager.sendLog("Other" , "User Rated " + rating+ "Your App ");
                } else {
                    System.err.println("Failed to rate app: " + response.code());
                    System.err.println("Response message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }
        });
    }
}