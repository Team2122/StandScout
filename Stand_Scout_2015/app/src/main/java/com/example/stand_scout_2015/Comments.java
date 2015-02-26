package com.example.stand_scout_2015;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;


public class Comments extends Activity {
    SeekBar rateDriver,rateNoodling,rateSpeed;
    EditText reason;
    Intent Teleop,Final;
    Button Forward,Back;
    int x1,x2,y1,y2;
    long start,end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("well hello there");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        rateDriver = (SeekBar) findViewById(R.id.rate_driver);
        rateNoodling = (SeekBar) findViewById(R.id.rate_noodling);
        rateSpeed = (SeekBar) findViewById(R.id.rate_speed_seekbar);
        reason = (EditText) findViewById(R.id.reason);
        Teleop = new Intent(this, Teleop.class);
        Final = new Intent(this, Final_Screen.class);
        Forward = (Button) findViewById(R.id.forward_button);
        Back = (Button) findViewById(R.id.back_button);

        for (int i = 1; i <= Global.comments.length; i++){
            int resourceId = getResources().getIdentifier("checkbox"+i, "id",this.getBaseContext().getPackageName());
            CheckBox m = (CheckBox) findViewById(resourceId);
            m.setChecked(Global.comments[i-1]);
        }
        
        rateDriver.setProgress(Global.stats[Global.ioDriver]);
        rateNoodling.setProgress(Global.stats[Global.ioNoodle]);
        rateSpeed.setProgress(Global.stats[Global.ioSpeed]);
        reason.setText(Global.comment, TextView.BufferType.EDITABLE);
        
        View.OnClickListener listen =new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.forward_button: save_coms();
                        save_data();
                        startActivity(Final);
                        break;
                    case R.id.back_button: save_coms();
                        goToTeleop();
                        break;
                }
            }
        };

        Forward.setOnClickListener(listen);
        Back.setOnClickListener(listen);
    }

    public void save_coms(){
        for (int i = 1; i <= Global.comments.length; i++){
            System.out.println("hello start loop save_coms");
            int resourceId = getResources().getIdentifier("checkbox"+i, "id",this.getBaseContext().getPackageName());
            System.out.println("hello init resourceId");
            CheckBox m = (CheckBox) findViewById(resourceId);
            System.out.println ("hello init checkbox");
            Global.comments[i-1] = m.isChecked();
            System.out.println("hello save to array");
        }
        Global.stats[Global.ioDriver] = rateDriver.getProgress();
        Global.stats[Global.ioNoodle] = rateNoodling.getProgress();
        Global.stats[Global.ioSpeed] = rateSpeed.getProgress();
        Global.comment = reason.getText().toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in, menu);
        return true;
    }

    public void save_data(){
        StringBuilder b = new StringBuilder();
        Scanner s = new Scanner(Global.team);
        try {
            b.append(s.nextInt() + ",");
        }
        catch(java.util.InputMismatchException e){
            System.out.println("Not a number");
        }
        b.append(Global.match +",");
        s.close();
        for(int i:Global.stats){
            b.append(i + ",");
        }
        for(boolean sid:Global.boolStats){
            b.append (sid ? "1," : "0," );
        }
        for (boolean bool : Global.comments) {
            b.append( bool ? "1," : "0," );
        }
        b.append (Global.startPos + ",");
        b.append(Global.comment);
        b.append (Global.name + "\n" );
        try {
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(" HHmm");
            String time = sdf.format(cal.getTime());
            FileWriter fw = new FileWriter(Global.f, true);
            System.out.println("hello" + Global.f.toString());
            System.out.println("hello writing");
            fw.append(b.toString());
            fw.flush();
            fw.close();
            Global.f.renameTo(new File(Environment.getExternalStorageDirectory() + "/" + Global.robot + time + ".csv"));
            Global.f = new File(Environment.getExternalStorageDirectory() + "/" + Global.robot + time + ".csv");
        } catch (IOException e) {
            System.out.println("not working");
            e.printStackTrace();
        }
        clear_global();
    }

    public void clear_global(){
        for (int i = 0;i < Global.stats.length;i++){
            Global.stats[i] = 0;
        }
        for (int i = 0;i < Global.boolStats.length;i++){
            Global.boolStats[i] = false;
        }
        for (int i = 0;i < Global.comments.length;i++){
            Global.comments[i] = false;
        }
        Global.team = "";
        Global.startPos = "";
        Global.comment = "";
        Global.match++;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToTeleop() {
        startActivity(Teleop);
    }
}
