package com.example.stand_scout_2015;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;

public class Autonomous extends Activity {

    CheckBox cbRobSet, cbStackedTote;
    Button bTote, bUndoTote, bContainer, bUndoContainer, bStepCont, bUndoStepCont, bKnockCont,
            bUndoKnockCont, bNext;
    RadioButton rbLeftTote, rbMidTote, rbRightTote, rbLandfill, rbMidLandfill, rbRightLandfill, rbNoShow;
    Spinner sRobot, sTeam;
    EditText etName,etMatch;
    Intent intent;
    String[] robots, teams;
    FileReader fr;
    int x1 = 0,y1 = 0,x2,y2;
    long start = 0,end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autonomous);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        cbRobSet = (CheckBox) findViewById(R.id.cbRobSet);
        cbStackedTote = (CheckBox) findViewById(R.id.cbStackedTote);
        bTote = (Button) findViewById(R.id.bTote);
        bUndoTote = (Button) findViewById(R.id.bUndoTote);
        bContainer = (Button) findViewById(R.id.bContainer);
        bUndoContainer = (Button) findViewById(R.id.bUndoContainer);
        bStepCont = (Button) findViewById(R.id.bStepCont);
        bUndoStepCont = (Button) findViewById(R.id.bUndoStepCont);
        bKnockCont = (Button) findViewById(R.id.bKnockCont);
        bUndoKnockCont = (Button) findViewById(R.id.bUndoKnockCont);
        bNext = (Button) findViewById(R.id.bNext);
        sRobot = (Spinner) findViewById(R.id.sRobot);
        etName = (EditText) findViewById(R.id.etName);
        etMatch = (EditText) findViewById(R.id.etMatch);
        rbLeftTote = (RadioButton) findViewById(R.id.rbLeftTote);
        rbMidTote = (RadioButton) findViewById(R.id.rbMidTote);
        rbRightTote = (RadioButton) findViewById(R.id.rbRightTote);
        rbLandfill = (RadioButton) findViewById(R.id.rbLandfill);
        rbMidLandfill = (RadioButton) findViewById(R.id.rbMidLandfill);
        rbRightLandfill = (RadioButton) findViewById(R.id.rbRightLandfill);
        rbNoShow = (RadioButton) findViewById(R.id.rbNoShow);
        sTeam = (Spinner) findViewById(R.id.sTeam);
        intent = new Intent(this, Teleop.class);

        robots = new String[]{"Red 1", "Red 2", "Red 3", "Blue 1", "Blue 2", "Blue 3"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, robots);
        sRobot = (Spinner) findViewById(R.id.sRobot);
        sRobot.setAdapter(adapter);

        teams = new String[]{"Option 1", "Option"};
        try {
            fr= new FileReader(Environment.getExternalStorageDirectory()+"/team_names.csv");
            Scanner in= new Scanner(fr);
            in.useDelimiter("\n");
            ArrayList<String> teamlist = new ArrayList<String>();
            teamlist.add("000");
            while(in.hasNext()){
                String team = in.next().trim();
                teamlist.add(team);}
            in.close();
            teams=teamlist.toArray(teams);
        } catch (FileNotFoundException e) {
            Toast t = Toast.makeText(Autonomous.this,
                    "File not found", Toast.LENGTH_LONG);
            t.show();
        }
        if (teams.length > 0){
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teams);}
        else{
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String []{"Option 1","Option 2"});}
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTeam.setAdapter(adapter);

        etName.setText(Global.name, TextView.BufferType.EDITABLE);
        etMatch.setText(Integer.toString(Global.match), TextView.BufferType.EDITABLE);
        sRobot.setSelection(java.util.Arrays.asList(robots).indexOf(Global.robot));

        bTote.setBackgroundColor(Color.GREEN);
        bContainer.setBackgroundColor(Color.GREEN);
        bStepCont.setBackgroundColor(Color.GREEN);
        bUndoKnockCont.setBackgroundColor(Color.GREEN);
        bUndoTote.setBackgroundColor(Color.RED);
        bUndoContainer.setBackgroundColor(Color.RED);
        bUndoStepCont.setBackgroundColor(Color.RED);
        bKnockCont.setBackgroundColor(Color.RED);
        rbLeftTote.setBackgroundColor(Color.WHITE);
        rbMidTote.setBackgroundColor(Color.WHITE);
        rbRightTote.setBackgroundColor(Color.WHITE);
        rbLandfill.setBackgroundColor(Color.WHITE);

        sTeam.setSelection(java.util.Arrays.asList(teams).indexOf(Global.team));
        cbRobSet.setChecked(Global.boolStats[Global.ioRobSet].equals(cbRobSet.getText()));
        cbStackedTote.setChecked(Global.boolStats[Global.ioStackedTote].equals(cbStackedTote.getText()));
        bTote.setText("Yellow Tote to\nauto zone " + Global.stats[Global.ioAutoTote]);
        bContainer.setText("Container to\nauto zone " + Global.stats[Global.ioAutoContainer]);
        bStepCont.setText("Container\nfrom step " + Global.stats[Global.ioAutoStepCont]);
        bKnockCont.setText("Knocked over\nContainer " + Global.stats[Global.ioAutoKnockCont]);
        if (Global.startPos.equals("Right Staging Zone")){
            rbLeftTote.setChecked(true);
        }
        else if (Global.startPos.equals("Middle Staging Zone")){
            rbMidTote.setChecked(true);
        }
        else if (Global.startPos.equals("Left Staging Zone")){
            rbRightTote.setChecked(true);
        }
        else if (Global.startPos.equals("Right Landfill")){
            rbLandfill.setChecked(true);
        }
        else if (Global.startPos.equals("Middle Landfill")){
            rbMidLandfill.setChecked(true);
        }
        else if (Global.startPos.equals("Left Landfill")){
            rbRightLandfill.setChecked(true);
        }
        else if (Global.startPos.equals("Wasn't there")){
            rbNoShow.setChecked(true);
        }

        View.OnClickListener listen = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switch (v.getId()){
                    case R.id.rbLeftTote: Global.startPos = "Right Staging Zone";
                                          rbMidTote.setChecked(false);
                                          rbRightTote.setChecked(false);
                                          rbLandfill.setChecked(false);
                                          rbRightLandfill.setChecked(false);
                                          rbMidLandfill.setChecked(false);
                                          rbNoShow.setChecked(false);
                    break;
                    case R.id.rbMidTote: Global.startPos = "Middle Staging Zone";
                                         rbLeftTote.setChecked(false);
                                         rbRightTote.setChecked(false);
                                         rbLandfill.setChecked(false);
                                         rbRightLandfill.setChecked(false);
                                         rbMidLandfill.setChecked(false);
                                         rbNoShow.setChecked(false);
                    break;
                    case R.id.rbRightTote: Global.startPos = "Left Staging Zone";
                                           rbMidTote.setChecked(false);
                                           rbLeftTote.setChecked(false);
                                           rbLandfill.setChecked(false);
                                           rbRightLandfill.setChecked(false);
                                           rbMidLandfill.setChecked(false);
                                           rbNoShow.setChecked(false);
                    break;
                    case R.id.rbLandfill: Global.startPos = "Right Landfill";
                                          rbMidTote.setChecked(false);
                                          rbRightTote.setChecked(false);
                                          rbLeftTote.setChecked(false);
                                          rbRightLandfill.setChecked(false);
                                          rbMidLandfill.setChecked(false);
                                          rbNoShow.setChecked(false);
                    break;
                    case R.id.rbMidLandfill: Global.startPos = "Middle Landfill";
                                             rbMidTote.setChecked(false);
                                             rbRightTote.setChecked(false);
                                             rbLeftTote.setChecked(false);
                                             rbLandfill.setChecked(false);
                                             rbRightLandfill.setChecked(false);
                                             rbNoShow.setChecked(false);
                    break;
                    case R.id.rbRightLandfill: Global.startPos = "Left Landfill";
                                               rbMidTote.setChecked(false);
                                               rbRightTote.setChecked(false);
                                               rbLeftTote.setChecked(false);
                                               rbLandfill.setChecked(false);
                                               rbMidLandfill.setChecked(false);
                                               rbNoShow.setChecked(false);
                    break;
                    case R.id.rbNoShow: Global.startPos = "Wasn't there";
                                        rbMidTote.setChecked(false);
                                        rbRightTote.setChecked(false);
                                        rbLandfill.setChecked(false);
                                        rbRightLandfill.setChecked(false);
                                        rbMidLandfill.setChecked(false);
                                        rbLeftTote.setChecked(false);
                    break;
                    case R.id.bTote:if (Global.stats[Global.ioAutoTote] < 3)
                                        Global.stats[Global.ioAutoTote] += 2;
                                    else
                                        Global.stats[Global.ioAutoTote]++;
                    case R.id.bUndoTote:if (Global.stats[Global.ioAutoTote] > 0)
                                            Global.stats[Global.ioAutoTote] -= 1;
                                        bTote.setText("Yellow Tote to\nauto zone " + Global.stats[Global.ioAutoTote]);
                    break;
                    case R.id.bContainer:if (Global.stats[Global.ioAutoContainer] < 7)
                                            Global.stats[Global.ioAutoContainer] += 2;
                                         else
                                            Global.stats[Global.ioAutoContainer]++;
                    case R.id.bUndoContainer:if (Global.stats[Global.ioAutoContainer] > 0)
                                                 Global.stats[Global.ioAutoContainer] -= 1;
                                             bContainer.setText("Container to\nauto zone " + Global.stats[Global.ioAutoContainer]);
                    break;
                    case R.id.bStepCont:if (Global.stats[Global.ioAutoStepCont] < 4)
                                            Global.stats[Global.ioAutoStepCont] += 2;
                                        else
                                            Global.stats[Global.ioAutoStepCont]++;
                    case R.id.bUndoStepCont:if (Global.stats[Global.ioAutoStepCont] > 0)
                                                Global.stats[Global.ioAutoStepCont] -= 1;
                                            bStepCont.setText("Container\nfrom step " + Global.stats[Global.ioAutoStepCont]);
                    break;
                    case R.id.bKnockCont:if (Global.stats[Global.ioAutoKnockCont] < 7)
                                            Global.stats[Global.ioAutoKnockCont] += 2;
                                         else
                                            Global.stats[Global.ioAutoKnockCont]++;
                    case R.id.bUndoKnockCont:if (Global.stats[Global.ioAutoKnockCont] > 0)
                                                Global.stats[Global.ioAutoKnockCont] -= 1;
                                             bKnockCont.setText("Knocked over\nContainer " + Global.stats[Global.ioAutoKnockCont]);
                    break;
                    case R.id.bNext:System.out.println("hi pushed next");
                        next();
                        break;
                }
            }
        };
        bTote.setOnClickListener(listen);
        bUndoTote.setOnClickListener(listen);
        bContainer.setOnClickListener(listen);
        bUndoContainer.setOnClickListener(listen);
        bStepCont.setOnClickListener(listen);
        bUndoStepCont.setOnClickListener(listen);
        bKnockCont.setOnClickListener(listen);
        bUndoKnockCont.setOnClickListener(listen);
        rbLeftTote.setOnClickListener(listen);
        rbMidTote.setOnClickListener(listen);
        rbRightTote.setOnClickListener(listen);
        rbLandfill.setOnClickListener(listen);
        rbRightLandfill.setOnClickListener(listen);
        rbMidLandfill.setOnClickListener(listen);
        rbNoShow.setOnClickListener(listen);
        bNext.setOnClickListener(listen);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = MotionEventCompat.getActionMasked(event);
        if (action == MotionEvent.ACTION_DOWN){
            x1 = (int) event.getX();
            y1 = (int) event.getY();
            start = System.currentTimeMillis();
        }
        else if (action == MotionEvent.ACTION_UP){
            x2 = (int) event.getX();
            y2 = (int) event.getY();
            end = System.currentTimeMillis();
            if(Math.abs((x2-x1) / Math.sqrt(3)) >= Math.abs(y2-y1)){
                if (end - start < 5000){
                    if (x1 - x2 > 100){
                        System.out.println("hi" + (x1 - x2));
                        next();
                    }
                }
            }
        }
        return true;
    }

    public void next(){
        if (sTeam.getSelectedItemPosition() == 0){
            makeToast("team number");
        }
        else if (Global.startPos.equals("")){
            makeToast("starting position");
        }
        else{
            Global.boolStats[Global.ioRobSet] = (cbRobSet.isChecked() ? cbRobSet.getText() : "").toString();
            Global.boolStats[Global.ioStackedTote] = (cbStackedTote.isChecked() ? cbStackedTote.getText() : "").toString();
            //Scanner getTeam = new Scanner((String)sTeam.getSelectedItem());
            //getTeam.useDelimiter(",| |;");
            try{
                //Global.stats[Global.ioTeam] = Integer.parseInt(getTeam.next());
                Global.team = (String)sTeam.getSelectedItem();
            }
            catch (RuntimeException e){}
            Global.name = etName.getText().toString();
            Global.robot = (String) sRobot.getSelectedItem();
            Global.match = Integer.parseInt(etMatch.getText().toString());
            //getTeam.close();
            startActivity(intent);
        }
    }

    public void makeToast(String message){
        Toast t = Toast.makeText(getApplicationContext(), "please enter " + message, Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in, menu);
        return true;
    }
}
