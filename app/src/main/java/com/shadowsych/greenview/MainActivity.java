package com.shadowsych.greenview;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    public static String userId;
    public static String personName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //hides the system bars and makes the layout on full screen mode
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //initialize the control systems and give permisssion for tbe file builder
        callbackManager = CallbackManager.Factory.create();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        if(Profile.getCurrentProfile() != null && AccessToken.getCurrentAccessToken() != null){
            userId = AccessToken.getCurrentAccessToken().getUserId();

            //get information of the user
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + userId,
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            try {
                                personName = response.getJSONObject().getString("name");
                            } catch (JSONException e) {
                                System.out.println("Error parsing the JSON object for name.");
                            }
                            Intent leaderBoard = new Intent(MainActivity.this,LeaderboardActivity.class);
                            startActivity(leaderBoard);
                        }
                    }
            ).executeAsync();
        } else {
            loginFB();
        }
    }

    private void loginFB() {
        //facebook commands
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                userId = loginResult.getAccessToken().getUserId();

                //get information of the user
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/" + userId,
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                try {
                                    personName = response.getJSONObject().getString("name");
                                } catch (JSONException e) {
                                    System.out.println("Error parsing the JSON object for name.");
                                }
                                Intent leaderBoard = new Intent(MainActivity.this,LeaderboardActivity.class);
                                startActivity(leaderBoard);
                            }
                        }
                ).executeAsync();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    //get facebook result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    //clear the view
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
