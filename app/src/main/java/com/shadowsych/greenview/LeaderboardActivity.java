package com.shadowsych.greenview;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.shadowsych.connection.PHPConnection;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.request.model.PredictRequest;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.Model;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

public class LeaderboardActivity extends Activity {
        String userId = MainActivity.userId;
        String personName = MainActivity.personName;
        PHPConnection phpConn = new PHPConnection();
        final ClarifaiClient client = new ClarifaiBuilder("ae322ffb0b2e4cbba7ecc7d19d123871").buildSync();
        final int REQUEST_CODE = 1;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_leaderboard);
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            createAccount();
            getLeaderBoard();
            getPoints();
            TextView name = (TextView) findViewById(R.id.nameTxT);
            name.setText(personName);

            //whenever user clicks the logout button, then logoff the user
            TextView logOut = (TextView) findViewById(R.id.logOut);
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().logOut();
                    Intent mainBoard = new Intent(LeaderboardActivity.this, MainActivity.class);
                    startActivity(mainBoard);
                }
            });

            //whenever user clicks the camera button, then open the camera
            Button camera = (Button) findViewById(R.id.camera);
            camera.setVisibility(View.VISIBLE);
            camera.setBackgroundColor(Color.TRANSPARENT);
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = getFile();
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(cameraIntent, REQUEST_CODE);
                }
            });
        }

        private void getLeaderBoard() {
            Thread getLeaderBoardsThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //get leaderboards through a run UI thread
                        Map<String,String> sendVariables = new HashMap<String,String>();
                        String results = phpConn.HTTPRequest("https://greenview2017.000webhostapp.com/leaderboard.php","POST", sendVariables);
                        String[] resultsExploded = results.split("&");
                        //get the leaderboards from 0 to 4
                        String[] leaderBoardOne = resultsExploded[0].split("=");
                        String[] leaderBoardTwo = resultsExploded[1].split("=");
                        String[] leaderBoardThree = resultsExploded[2].split("=");
                        String[] leaderBoardFour = resultsExploded[3].split("=");
                        String[] leaderBoardFive = resultsExploded[4].split("=");
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //leaderboard names
                                TextView lead1 = (TextView) findViewById(R.id.lead1);
                                TextView lead2 = (TextView) findViewById(R.id.lead2);
                                TextView lead3 = (TextView) findViewById(R.id.lead3);
                                TextView lead4 = (TextView) findViewById(R.id.lead4);
                                TextView lead5 = (TextView) findViewById(R.id.lead5);
                                lead1.setText(leaderBoardOne[0]);
                                lead2.setText(leaderBoardTwo[0]);
                                lead3.setText(leaderBoardThree[0]);
                                lead4.setText(leaderBoardFour[0]);
                                lead5.setText(leaderBoardFive[0]);
                                //leaderboard scores
                                TextView score1 = (TextView) findViewById(R.id.score1);
                                TextView score2 = (TextView) findViewById(R.id.score2);
                                TextView score3 = (TextView) findViewById(R.id.score3);
                                TextView score4 = (TextView) findViewById(R.id.score4);
                                TextView score5 = (TextView) findViewById(R.id.score5);
                                score1.setText(leaderBoardOne[1]);
                                score2.setText(leaderBoardTwo[1]);
                                score3.setText(leaderBoardThree[1]);
                                score4.setText(leaderBoardFour[1]);
                                score5.setText(leaderBoardFive[1]);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            getLeaderBoardsThread.start();
        }

        private void getPoints() {
            Thread getPointThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //get points through a run UI thread
                                Map<String, String> sendVariables = new HashMap<String, String>();
                                sendVariables.put("userID", userId);
                                String results = phpConn.HTTPRequest("https://greenview2017.000webhostapp.com/read_score.php", "POST", sendVariables);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                TextView points = (TextView) findViewById(R.id.pointsTxT);
                                points.setText(results);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            getPointThread.start();
        }

        private void addPoints() {
            Thread addPointThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //add points
                        Map<String,String> sendVariables = new HashMap<String,String>();
                        sendVariables.put("userID", userId);
                        phpConn.HTTPRequest("https://greenview2017.000webhostapp.com/change_score.php","POST", sendVariables);
                        getPoints();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            addPointThread.start();
        }

        private void createAccount() {
            Thread createAccountThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //php checks if this account was already created
                        Map<String, String> sendVariables = new HashMap<String, String>();
                        sendVariables.put("userID", userId);
                        sendVariables.put("Name", personName);
                        phpConn.HTTPRequest("https://greenview2017.000webhostapp.com/register.php", "POST", sendVariables);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            createAccountThread.start();
        }
        private void analyzeImage(String imageFile) {
            Thread analyzeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try  {
                        Model<Concept> generalModel = client.getDefaultModels().generalModel();
                        PredictRequest<Concept> request = generalModel.predict().withInputs(ClarifaiInput.forImage(new File(imageFile)));
                        List<ClarifaiOutput<Concept>> result = request.executeSync().get();

                        //analyze the results and post a popup back if the item is recycable
                        String dataResults = result.toString().substring(result.toString().indexOf("data=["), result.toString().lastIndexOf(", status="));
                        dataResults = dataResults.replace("data=[", "").replace("]",""); //only receives the Concepts
                        Map<String,String> sendVariables = new HashMap<String,String>();

                        //as long as the dataresults contains the "name=" string, then parse the data name of the item and replace strings to continue the parsing
                        String tempDataResults = dataResults;
                        for(int i = 0; tempDataResults.indexOf("name=") > 0; i++) {
                            sendVariables.put("item"+i, tempDataResults.substring(tempDataResults.indexOf("name="), tempDataResults.indexOf(", createdAt")).replaceFirst("name=",""));
                            tempDataResults = tempDataResults.replaceFirst("name=", "");
                            tempDataResults = tempDataResults.replaceFirst(", createdAt", "");
                        }
                        String results = phpConn.HTTPRequest("https://greenview2017.000webhostapp.com/compare_items.php","POST", sendVariables);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                if(!results.contains("not recyclable")) {
                                    //show popup that the item is recycable and give the person 5 points
                                    Toast.makeText(LeaderboardActivity.this, "It's recyclable! +5 points for your " + results + "!", Toast.LENGTH_LONG).show();
                                    addPoints();
                                } else {
                                    Toast.makeText(LeaderboardActivity.this, "It's NOT recyclable!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            analyzeThread.start();
        }

        private File getFile() {
            //create new file folder
            File folder = new File ("sdcard/greenview");
            if(!folder.exists()) {
                folder.mkdir();
            }
            File imageFile = new File(folder, "cam_image.jpg");
            return imageFile;
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            //check if the request being made is for the camera
            try {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {
                    String imageFile = "sdcard/greenview/cam_image.jpg";
                    analyzeImage(imageFile);
                }
            } catch (Exception ex) {
            }
        }

        //prevent back pressing
        @Override
        public void onBackPressed() {
        }
}
