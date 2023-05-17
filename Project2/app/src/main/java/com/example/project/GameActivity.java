package com.example.project;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    //private FrameLayout frame;
    List<String[]> rows;
    //private PaintView paintView;
    public static String[][] board;
    public static String[] board2;
    //public static TextView[][] testBoard;
    private static Term[] terms;
    GridView coursesGV;
    TextView tv;
    int arrows[];
    String[] arrowDir;
    EditText letter;
    String[] letters;
    GridAdapter gridAdapter;
    int numRows;
    int numCol;
    int[] bgColor;
    MainActivity ma;
    TextView timerText;
    boolean timerStarted=false;
    Timer timer;
    TimerTask timerTask;
    double time= 0.0;
    int score=0;
    String gameTime;
    String userName;
    Intent intent2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        terms= new Term[35];
        coursesGV=(GridView) findViewById(R.id.grdView);
        tv=(TextView)findViewById(R.id.idTVCourse);
        letter=findViewById(R.id.setLetter);
        timerText=findViewById(R.id.timer);
        timerStarted=true;
        timer= new Timer();
        numRows=18;
        numCol=8;
        board = new String[numRows][numCol];
        board2= new String[144];
        arrows=new int[7];
        arrows[0]=R.drawable.arrow_down;
        arrows[1]=R.drawable.arrow_upright;
        arrows[2]= R.drawable.arrow_upleft;
        arrows[3]=R.drawable.arrow_right;
        arrows[4]=R.drawable.arrow_downleft;
        arrows[5]=R.drawable.arrow_downrightcol;
        arrows[6]=R.drawable.arrow_uprightrow;
        arrowDir=new String[144];
        letters=new String[144];
        bgColor=new int[144];
        for (int i=0; i< bgColor.length; i++)
        {
            bgColor[i]=0;
        }


        Intent intent1=getIntent();
        userName=intent1.getExtras().getString("UserName");
        //testBoard = new TextView[18][8];
        SortToTerms();
        setBoard();
/*        int countCol=0;
        int countRow=0;
        for(int i=0; i<144; i++)
        {
            board2[i]=board[countRow][countCol];
            countCol++;
        }*/
        gridAdapter=new GridAdapter(this, board2, arrows, arrowDir, letters, bgColor);
        coursesGV.setAdapter(gridAdapter);

/*        if(!(isActivityTransitionRunning())) {
            timerTask.cancel();
            time=0.0;
            timerStarted=false;
            timerText.setText(formatTime(0,0,0));
        }*/
        if(timerStarted)
            startTimer();

        intent2= new Intent(GameActivity.this, ScoresTable.class);
        //String myName=getIntent().getStringExtra("myName");

/*        for(int i=0; i<144; i++)
        {
            gridAdapter.updateLetter(i, coursesGV, coursesGV);
        }*/

/*        Intent myIntent = new Intent(this , NotifyService.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

//get time from database and initialise the variables.
        int minute=0;
        int hour=5;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.AM_PM, Calendar.PM);    //set accordingly
        calendar.add(Calendar.DAY_OF_YEAR, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);*/

    }

    private void startTimer() {
        timerTask= new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
    private String getTimerText() {
        int rounded= (int)Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours) {
        return String.format("%02d", hours)+" : "+ String.format("%02d", minutes)+" : "+ String.format("%02d", seconds);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        int x = 0;
        if (id == R.id.check) {
            score = 0;
            int position = 0;
            int aLength;
            int aStart;
            String aDir;
            String guess;
            String answer;
            int rStart;

            for (int i = 0; i < terms.length; i++) {
                answer = terms[i].getAnswer();
                aLength = terms[i].getAnswer().length();
                aStart = terms[i].getAnswerStart();
                aDir = terms[i].getAnswerDir();
                rStart = terms[i].getRiddleStart();
                guess = gridAdapter.letters[aStart - 1];
                if (guess == null) {
                    bgColor[rStart - 1] = 0;
                    continue;
                }
                for (int t = 1; t < aLength; t++) {
                    if (aDir == "right") {
                        if (gridAdapter.letters[aStart + t - 1] == null) {
                            bgColor[rStart - 1] = 0;
                            continue;
                        }
                        guess = guess + gridAdapter.letters[aStart + t - 1];
                    } else {
                        if (gridAdapter.letters[aStart + t * numCol - 1] == null) {
                            bgColor[rStart - 1] = 0;
                            continue;
                        }
                        guess = guess + gridAdapter.letters[aStart + t * numCol - 1];
                    }
                }
                guess = guess.toLowerCase();
                if (guess.equals(answer)) {
                    score++;
                    bgColor[rStart - 1] = 1;
                    bgColor[aStart - 1] = 1;
                    for (int t = 1; t < aLength; t++) {
                        if (aDir == "right")

                            bgColor[aStart + t - 1] = 1;
                        else
                            bgColor[aStart + t * numCol - 1] = 1;


                    }
                } else {
                    bgColor[rStart - 1] = 2;
                    bgColor[aStart - 1] = 2;
                    for (int t = 1; t < aLength; t++) {
                        if (aDir == "right")

                            bgColor[aStart + t - 1] = 2;
                        else
                            bgColor[aStart + t * numCol - 1] = 2;


                    }
                }
            }


            gridAdapter.notifyDataSetChanged();
            if(score== terms.length){
            //if (score == 1) {
                gameTime = getTimerText();
                timerTask.cancel();
                timerStarted = false;
            }

        }
        if (id == R.id.scTable) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setTitle("Game will not be saved!");
            builder.setMessage("Are you sure you want to exit game?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intent2.putExtra("UserName", userName);
                    intent2.putExtra("UserTime", gameTime);
                    startActivity(intent2);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(id==R.id.exit2)
        {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
/*        if(id==R.id.update){
            coursesGV.invalidateViews();
            gridAdapter.notifyDataSetChanged();
            coursesGV.setAdapter(gridAdapter);
        }*/
        return true;
    }

    public void SortToTerms()
    {
        rows = new ArrayList<>();
        CSVReader csvReader = new CSVReader(GameActivity.this, "arrowCrossword.csv");
        try {
            rows = csvReader.readCSV();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < rows.size(); i++) {
/*          Log.d(SyncStateContract.Constants.TAG, String.format("row %s: %s, %s", i, rows.get(i)[0], rows.get(i)[1]));
            String.format("row %s: %s, %d, %d, %s, %d, %d, %s", i, rows.get(i)[0], rows.get(i)[1], rows.get(i)[2],rows.get(i)[3],rows.get(i)[4],rows.get(i)[5],rows.get(i)[6]);*/
/*            String riddle1= String.format("row %s: %s", i, rows.get(i)[0]);
            String riddle=  rows.get(i)[0];*/
            Term term1=new Term( rows.get(i)[0], rows.get(i)[2], Integer.valueOf(rows.get(i)[1]),  Integer.valueOf(rows.get(i)[3]), rows.get(i)[4]);
            terms[i]=term1;
        }
    }

    public void setBoard()
    {
        String riddle1;
        String arrowD;
        //String riddle2;
        int asr;
        //   int length=0;
        for(int a=0; a<rows.size(); a++) {
            riddle1 = terms[a].getRiddle();
            asr = terms[a].getRiddleStart();
            board2[asr-1]=riddle1;
  /*        testBoard[asr-1][asc-1]=new TextView(this);
            testBoard[asr-1][asc-1].setText(riddle1);
            testBoard[asr-1][asc-1].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));*/
        }
        for(int i=0; i<rows.size(); i++)
        {
            arrowD=terms[i].getAnswerDir();
            asr = terms[i].getRiddleStart();
            arrowDir[asr-1]=arrowD;
        }
    }

    public static String[][] getBoard()
    {
        return board;
    }


    /*public static TextView[][] getTestBoard()
    {
        return testBoard;
    }*/
    //        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
    //                LinearLayout.LayoutParams.WRAP_CONTENT);
}