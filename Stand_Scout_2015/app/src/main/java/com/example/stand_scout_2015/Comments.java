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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Comments extends Activity {
    SeekBar rateDriver,rateNoodling,rateSpeed,rateCapping;
    Spinner sCapHeight;
    EditText reason,weakness;
    Intent Teleop,Final;
    Button Forward,Back;


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
        rateCapping = (SeekBar) findViewById(R.id.sbRateCap);
        sCapHeight = (Spinner) findViewById(R.id.sCapHeight);
        reason = (EditText) findViewById(R.id.reason);
        weakness = (EditText) findViewById(R.id.etWeakness);
        Teleop = new Intent(this, Teleop.class);
        Final = new Intent(this, Final_Screen.class);
        Forward = (Button) findViewById(R.id.forward_button);
        Back = (Button) findViewById(R.id.back_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"0","1","2","3","4","5","6"});
        sCapHeight.setAdapter(adapter);

        for (int i = 1; i <= Global.comments.length; i++){
            int resourceId = getResources().getIdentifier("checkbox"+i, "id",this.getBaseContext().getPackageName());
            CheckBox m = (CheckBox) findViewById(resourceId);
            m.setChecked(Global.comments[i-1].equals(m.getText()));
        }
        rateDriver.setProgress(Global.stats[Global.ioDriver]);
        rateNoodling.setProgress(Global.stats[Global.ioNoodle]);
        rateSpeed.setProgress(Global.stats[Global.ioSpeed]);
        rateCapping.setProgress(Global.stats[Global.ioCap]);
        sCapHeight.setSelection(Global.stats[Global.ioCapHeight]);
        reason.setText(Global.comment, TextView.BufferType.EDITABLE);
        weakness.setText(Global.weakness, TextView.BufferType.EDITABLE);
        
        View.OnClickListener listen =new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                save_coms();
                switch (v.getId()) {
                    case R.id.forward_button:
                        try{
                            save_data();
                        }
                        catch (NoSuchElementException e){
                            System.out.println("No Such Element");
                            Toast.makeText(getApplicationContext(), "Avoid coming back after the final screen", Toast.LENGTH_LONG).show();
                        }
                        startActivity(Final);
                        break;
                    case R.id.back_button: goToTeleop();
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
            Global.comments[i-1] = (m.isChecked() ? m.getText().toString() : "");
            System.out.println("hello save to array");
        }
        Global.stats[Global.ioDriver] = rateDriver.getProgress();
        Global.stats[Global.ioNoodle] = rateNoodling.getProgress();
        Global.stats[Global.ioSpeed] = rateSpeed.getProgress();
        Global.stats[Global.ioCap] = rateCapping.getProgress();
        Global.stats[Global.ioCapHeight] = Integer.parseInt(sCapHeight.getSelectedItem().toString());
        System.out.println("hello" + Global.stats[Global.ioCapHeight]);
        Global.comment = reason.getText().toString();
        Global.weakness = weakness.getText().toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in, menu);
        return true;
    }

    public void save_data() throws NoSuchElementException{
        StringBuilder b = new StringBuilder();
        Scanner s = new Scanner(Global.team);
        s.useDelimiter(",");
        try {
            b.append(s.next() + ",");
        }
        catch(java.util.InputMismatchException e){
            System.out.println("Not a number");
            b.append("0000,");
        }
        b.append(Global.match +",");
        s.close();
        for(int i:Global.stats){
            b.append(i + ",");
        }
        for(String sid:Global.boolStats){
            b.append (sid + ",");
        }
        for (String bool : Global.comments) {
            b.append(bool + ",");
        }
        b.append(Global.startPos + ",");
        b.append(Global.comment.replace(",",".").replace("\n","").replace("\r","") + ",");
        b.append(Global.weakness.replace(",",".").replace("\n","").replace("\r","") + ",");
        b.append (Global.name);
        b.append(Global.robot);
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(" HHmm");
            String time = sdf.format(cal.getTime());
            FileWriter fw = new FileWriter(Global.f, true);
            System.out.println("hello" + b.toString());
            fw.append(b.toString() + "\n");
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
            Global.boolStats[i] = "";
        }
        for (int i = 0;i < Global.comments.length;i++){
            Global.comments[i] = "";
        }
        Global.team = "";
        Global.startPos = "";
        Global.comment = "";
        Global.weakness = "";
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