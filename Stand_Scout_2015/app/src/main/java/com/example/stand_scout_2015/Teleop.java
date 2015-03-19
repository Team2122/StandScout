package com.example.stand_scout_2015;

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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Teleop extends Activity {

    Button b6HighTote, bUndo6HighTote, b5HighTote, bUndo5HighTote, b4HighTote, bUndo4HighTote, b3HighTote, bUndo3HighTote, b2HighTote,
           bUndo2HighTote, b1HighTote, bUndo1HighTote, b6HighCont, bUndo6HighCont, b5HighCont, bUndo5HighCont, b4HighCont,
           bUndo4HighCont, b3HighCont, bUndo3HighCont, b2HighCont, bUndo2HighCont, b1HighCont, bUndo1HighCont,b4HighCoopTote,
           bUndo4HighCoopTote, b3HighCoopTote, bUndo3HighCoopTote, b2HighCoopTote, bUndo2HighCoopTote, b1HighCoopTote,
           bUndo1HighCoopTote,bLitter, bUndoLitter, bRecycle, bUndoRecycle, bKnockStack, bUndoKnockStack,bKnockCont,bUndoKnockCont,bNext,bBack;
    EditText etName, etMatch;
    Spinner sRobot, sTeam;
    ToggleButton tbLeftStageCont, tbMidStageCont, tbRightStageCont, tbLeftStepCont, tbMidLeftStepCont, tbMidRightStepCont, tbRightStepCont;
    CheckBox cbLeftFeed, cbRightFeed, cbLeftLandfill, cbRightLandfill;
    RadioButton rbLeftTopStack, rbMidTopStack, rbRightTopStack, rbLeftBottomStack, rbMidBottomStack, rbRightBottomStack;
    Intent auto, comments;
    String[] teams, robots = new String[]{"Red1", "Red2", "Red3", "Blue1", "Blue2", "Blue3"};
    FileReader fr;
    int x1,y1,x2,y2;
    long start,end;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teleop);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        b6HighTote = (Button) findViewById(R.id.b6HighTote);
        bUndo6HighTote = (Button) findViewById(R.id.bUndo6HighTote);
        b5HighTote = (Button) findViewById(R.id.b5HighTote);
        bUndo5HighTote = (Button) findViewById(R.id.bUndo5HighTote);
        b4HighTote = (Button) findViewById(R.id.b4HighTote);
        bUndo4HighTote = (Button) findViewById(R.id.bUndo4HighTote);
        b3HighTote = (Button) findViewById(R.id.b3HighTote);
        bUndo3HighTote = (Button) findViewById(R.id.bUndo3HighTote);
        b2HighTote = (Button) findViewById(R.id.b2HighTote);
        bUndo2HighTote = (Button) findViewById(R.id.bUndo2HighTote);
        b1HighTote = (Button) findViewById(R.id.b1HighTote);
        bUndo1HighTote = (Button) findViewById(R.id.bUndo1HighTote);
        b6HighCont = (Button) findViewById(R.id.b6HighCont);
        bUndo6HighCont = (Button) findViewById(R.id.bUndo6HighCont);
        b5HighCont = (Button) findViewById(R.id.b5HighCont);
        bUndo5HighCont = (Button) findViewById(R.id.bUndo5HighCont);
        b4HighCont = (Button) findViewById(R.id.b4HighCont);
        bUndo4HighCont = (Button) findViewById(R.id.bUndo4HighCont);
        b3HighCont = (Button) findViewById(R.id.b3HighCont);
        bUndo3HighCont = (Button) findViewById(R.id.bUndo3HighCont);
        b2HighCont = (Button) findViewById(R.id.b2HighCont);
        bUndo2HighCont = (Button) findViewById(R.id.bUndo2HighCont);
        b1HighCont = (Button) findViewById(R.id.b1HighCont);
        bUndo1HighCont = (Button) findViewById(R.id.bUndo1HighCont);
        b4HighCoopTote = (Button) findViewById(R.id.b4HighCoopTote);
        bUndo4HighCoopTote = (Button) findViewById(R.id.bUndo4HighCoopTote);
        b3HighCoopTote = (Button) findViewById(R.id.b3HighCoopTote);
        bUndo3HighCoopTote = (Button) findViewById(R.id.bUndo3HighCoopTote);
        b2HighCoopTote = (Button) findViewById(R.id.b2HighCoopTote);
        bUndo2HighCoopTote = (Button) findViewById(R.id.bUndo2HighCoopTote);
        b1HighCoopTote = (Button) findViewById(R.id.b1HighCoopTote);
        bUndo1HighCoopTote = (Button) findViewById(R.id.bUndo1HighCoopTote);
        bLitter = (Button) findViewById(R.id.bLitter);
        bUndoLitter = (Button) findViewById(R.id.bUndoLitter);
        bRecycle = (Button) findViewById(R.id.bRecycle);
        bUndoRecycle = (Button) findViewById(R.id.bUndoRecycle);
        bKnockStack = (Button) findViewById(R.id.bKnockStack);
        bUndoKnockStack = (Button) findViewById(R.id.bUndoKnockStack);
        bKnockCont = (Button) findViewById(R.id.bKnockCont);
        bUndoKnockCont = (Button) findViewById(R.id.bUndoKnockCont);
        bNext = (Button) findViewById(R.id.bNext);
        bBack = (Button) findViewById(R.id.bBack);
        etMatch = (EditText) findViewById(R.id.etMatch);
        etName = (EditText) findViewById(R.id.etName);
        sRobot = (Spinner) findViewById(R.id.sRobot);
        sTeam = (Spinner) findViewById(R.id.sTeam);
        tbLeftStageCont = (ToggleButton) findViewById(R.id.tbLeftStageCont);
        tbMidStageCont = (ToggleButton) findViewById(R.id.tbMidStageCont);
        tbRightStageCont = (ToggleButton) findViewById(R.id.tbRightStageCont);
        tbLeftStepCont = (ToggleButton) findViewById(R.id.tbLeftStepCont);
        tbMidLeftStepCont = (ToggleButton) findViewById(R.id.tbMidLeftStepCont);
        tbMidRightStepCont = (ToggleButton) findViewById(R.id.tbMidRightStepCont);
        tbRightStepCont = (ToggleButton) findViewById(R.id.tbRightStepCont);
        cbLeftFeed = (CheckBox) findViewById(R.id.cbLeftFeed);
        cbRightFeed = (CheckBox) findViewById(R.id.cbRightFeed);
        cbLeftLandfill = (CheckBox) findViewById(R.id.cbLeftLandfill);
        cbRightLandfill = (CheckBox) findViewById(R.id.cbRightLandfill);
        rbLeftTopStack = (RadioButton) findViewById(R.id.rbLeftTopStack);
        rbMidTopStack = (RadioButton) findViewById(R.id.rbMidTopStack);
        rbRightTopStack = (RadioButton) findViewById(R.id.rbRightTopStack);
        rbLeftBottomStack = (RadioButton) findViewById(R.id.rbLeftBottomStack);
        rbMidBottomStack = (RadioButton) findViewById(R.id.rbMidBottomStack);
        rbRightBottomStack = (RadioButton) findViewById(R.id.rbRightBottomStack);

        auto = new Intent(this, Autonomous.class);
        comments = new Intent (this, Comments.class);

        b6HighTote.setBackgroundColor(Color.GREEN);
        b5HighTote.setBackgroundColor(Color.GREEN);
        b4HighTote.setBackgroundColor(Color.GREEN);
        b3HighTote.setBackgroundColor(Color.GREEN);
        b2HighTote.setBackgroundColor(Color.GREEN);
        b1HighTote.setBackgroundColor(Color.GREEN);
        b6HighCont.setBackgroundColor(Color.GREEN);
        b5HighCont.setBackgroundColor(Color.GREEN);
        b4HighCont.setBackgroundColor(Color.GREEN);
        b3HighCont.setBackgroundColor(Color.GREEN);
        b2HighCont.setBackgroundColor(Color.GREEN);
        b1HighCont.setBackgroundColor(Color.GREEN);
        b4HighCoopTote.setBackgroundColor(Color.YELLOW);
        b3HighCoopTote.setBackgroundColor(Color.YELLOW);
        b2HighCoopTote.setBackgroundColor(Color.YELLOW);
        b1HighCoopTote.setBackgroundColor(Color.YELLOW);
        bLitter.setBackgroundColor(Color.GREEN);
        bRecycle.setBackgroundColor(Color.GREEN);
        bUndoKnockStack.setBackgroundColor(Color.GREEN);
        bUndoKnockCont.setBackgroundColor(Color.GREEN);
        bUndo6HighTote.setBackgroundColor(Color.RED);
        bUndo5HighTote.setBackgroundColor(Color.RED);
        bUndo4HighTote.setBackgroundColor(Color.RED);
        bUndo3HighTote.setBackgroundColor(Color.RED);
        bUndo2HighTote.setBackgroundColor(Color.RED);
        bUndo1HighTote.setBackgroundColor(Color.RED);
        bUndo6HighCont.setBackgroundColor(Color.RED);
        bUndo5HighCont.setBackgroundColor(Color.RED);
        bUndo4HighCont.setBackgroundColor(Color.RED);
        bUndo3HighCont.setBackgroundColor(Color.RED);
        bUndo2HighCont.setBackgroundColor(Color.RED);
        bUndo1HighCont.setBackgroundColor(Color.RED);
        bUndo4HighCoopTote.setBackgroundColor(Color.RED);
        bUndo3HighCoopTote.setBackgroundColor(Color.RED);
        bUndo2HighCoopTote.setBackgroundColor(Color.RED);
        bUndo1HighCoopTote.setBackgroundColor(Color.RED);
        bUndoLitter.setBackgroundColor(Color.RED);
        bUndoRecycle.setBackgroundColor(Color.RED);
        bKnockStack.setBackgroundColor(Color.RED);
        bKnockCont.setBackgroundColor(Color.RED);
        cbLeftFeed.setBackgroundColor(Color.WHITE);
        cbRightFeed.setBackgroundColor(Color.WHITE);
        cbLeftLandfill.setBackgroundColor(Color.WHITE);
        cbRightLandfill.setBackgroundColor(Color.WHITE);

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
            Toast t = Toast.makeText(Teleop.this,
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
        sTeam.setSelection(java.util.Arrays.asList(teams).indexOf(Global.team));

        b6HighTote.setText("6 high\n" + Global.stats[Global.ioTele6HighTote]);
        b5HighTote.setText("5 high\n" + Global.stats[Global.ioTele5HighTote]);
        b4HighTote.setText("4 high\n" + Global.stats[Global.ioTele4HighTote]);
        b3HighTote.setText("3 high\n" + Global.stats[Global.ioTele3HighTote]);
        b2HighTote.setText("2 high\n" + Global.stats[Global.ioTele2HighTote]);
        b1HighTote.setText("1 high\n" + Global.stats[Global.ioTele1HighTote]);
        b6HighCont.setText("Level 6\n" + Global.stats[Global.ioTele6HighCont]);
        b5HighCont.setText("Level 5\n" + Global.stats[Global.ioTele5HighCont]);
        b4HighCont.setText("Level 4\n" + Global.stats[Global.ioTele4HighCont]);
        b3HighCont.setText("Level 3\n" + Global.stats[Global.ioTele3HighCont]);
        b2HighCont.setText("Level 2\n" + Global.stats[Global.ioTele2HighCont]);
        b1HighCont.setText("Level 1\n" + Global.stats[Global.ioTele1HighCont]);
        b4HighCoopTote.setText("4 high\n" + Global.stats[Global.ioTele4HighCoopTote]);
        b3HighCoopTote.setText("3 high\n" + Global.stats[Global.ioTele3HighCoopTote]);
        b2HighCoopTote.setText("2 high\n" + Global.stats[Global.ioTele2HighCoopTote]);
        b1HighCoopTote.setText("1 high\n" + Global.stats[Global.ioTele1HighCoopTote]);
        bLitter.setText("Litter to\nLandfill " + Global.stats[Global.ioTeleLitter]);
        bRecycle.setText("Litter in\nContainer " + Global.stats[Global.ioTeleRecycle]);
        bKnockStack.setText("Knock Over\nStack " + Global.stats[Global.ioTeleKnockStack]);
        bKnockCont.setText("Knock Over\nContainer " + Global.stats[Global.ioTeleKnockCont]);
        cbLeftFeed.setChecked(Global.boolStats[Global.ioLeftFeed].equals("Right Feeder Station"));
        cbRightFeed.setChecked(Global.boolStats[Global.ioRightFeed].equals("Left Feeder Station"));
        cbLeftLandfill.setChecked(Global.boolStats[Global.ioLeftLandfill].equals("Right Landfill"));
        cbRightLandfill.setChecked(Global.boolStats[Global.ioRightLandfill].equals("Left Landfill"));
        rbLeftTopStack.setChecked(Global.boolStats[Global.ioLeftTopStack].equals("Right Close"));
        rbMidTopStack.setChecked(Global.boolStats[Global.ioMidTopStack].equals("Mid Close"));
        rbRightTopStack.setChecked(Global.boolStats[Global.ioRightTopStack].equals("Left Close"));
        rbLeftBottomStack.setChecked(Global.boolStats[Global.ioLeftBottomStack].equals("Right Far"));
        rbMidBottomStack.setChecked(Global.boolStats[Global.ioMidBottomStack].equals("Mid Far"));
        rbRightBottomStack.setChecked(Global.boolStats[Global.ioRightBottomStack].equals("Left Far"));
        tbLeftStageCont.setChecked(Global.boolStats[Global.ioLeftStageCont].equals("Right Container"));
        tbMidStageCont.setChecked(Global.boolStats[Global.ioMidStageCont].equals("Middle Container"));
        tbRightStageCont.setChecked(Global.boolStats[Global.ioRightStageCont].equals("Left Container"));
        tbLeftStepCont.setChecked(Global.boolStats[Global.ioLeftStepCont].equals("Right Step Container"));
        tbMidLeftStepCont.setChecked(Global.boolStats[Global.ioMidLeftStepCont].equals("Mid Right Step Container"));
        tbMidRightStepCont.setChecked(Global.boolStats[Global.ioMidRightStepCont].equals("Mid Left Step Container"));
        tbRightStepCont.setChecked(Global.boolStats[Global.ioRightStepCont].equals("Left Step Container"));

        View.OnClickListener listen = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.b6HighTote: Global.stats[Global.ioTele6HighTote] += 2;
                    case R.id.bUndo6HighTote: if (Global.stats[Global.ioTele6HighTote] > 0)
                                                Global.stats[Global.ioTele6HighTote] -= 1;
                                              b6HighTote.setText("6 high\n" + Global.stats[Global.ioTele6HighTote]);
                    break;
                    case R.id.b5HighTote: Global.stats[Global.ioTele5HighTote] += 2;
                    case R.id.bUndo5HighTote: if (Global.stats[Global.ioTele5HighTote] > 0)
                                                Global.stats[Global.ioTele5HighTote] -= 1;
                                              b5HighTote.setText("5 high\n" + Global.stats[Global.ioTele5HighTote]);
                    break;
                    case R.id.b4HighTote: Global.stats[Global.ioTele4HighTote] += 2;
                    case R.id.bUndo4HighTote: if (Global.stats[Global.ioTele4HighTote] > 0)
                                                Global.stats[Global.ioTele4HighTote] -= 1;
                                              b4HighTote.setText("4 high\n" + Global.stats[Global.ioTele4HighTote]);
                    break;
                    case R.id.b3HighTote: Global.stats[Global.ioTele3HighTote] += 2;
                    case R.id.bUndo3HighTote: if (Global.stats[Global.ioTele3HighTote] > 0)
                                                Global.stats[Global.ioTele3HighTote] -= 1;
                                              b3HighTote.setText("3 high\n" + Global.stats[Global.ioTele3HighTote]);
                    break;
                    case R.id.b2HighTote: Global.stats[Global.ioTele2HighTote] += 2;
                    case R.id.bUndo2HighTote: if (Global.stats[Global.ioTele2HighTote] > 0)
                                                Global.stats[Global.ioTele2HighTote] -= 1;
                                              b2HighTote.setText("2 high\n" + Global.stats[Global.ioTele2HighTote]);
                    break;
                    case R.id.b1HighTote: Global.stats[Global.ioTele1HighTote] += 2;
                    case R.id.bUndo1HighTote: if (Global.stats[Global.ioTele1HighTote] > 0)
                                                Global.stats[Global.ioTele1HighTote] -= 1;
                                              b1HighTote.setText("1 high\n" + Global.stats[Global.ioTele1HighTote]);
                    break;
                    case R.id.b6HighCont: Global.stats[Global.ioTele6HighCont] += 2;
                    case R.id.bUndo6HighCont: if (Global.stats[Global.ioTele6HighCont] > 0)
                                                Global.stats[Global.ioTele6HighCont] -= 1;
                                              b6HighCont.setText("Level 6\n" + Global.stats[Global.ioTele6HighCont]);
                    break;
                    case R.id.b5HighCont: Global.stats[Global.ioTele5HighCont] += 2;
                    case R.id.bUndo5HighCont: if (Global.stats[Global.ioTele5HighCont] > 0)
                                                Global.stats[Global.ioTele5HighCont] -= 1;
                                              b5HighCont.setText("Level 5\n" + Global.stats[Global.ioTele5HighCont]);
                    break;
                    case R.id.b4HighCont: Global.stats[Global.ioTele4HighCont] += 2;
                    case R.id.bUndo4HighCont: if (Global.stats[Global.ioTele4HighCont] > 0)
                                                Global.stats[Global.ioTele4HighCont] -= 1;
                                              b4HighCont.setText("Level 4\n" + Global.stats[Global.ioTele4HighCont]);
                    break;
                    case R.id.b3HighCont: Global.stats[Global.ioTele3HighCont] += 2;
                    case R.id.bUndo3HighCont: if (Global.stats[Global.ioTele3HighCont] > 0)
                                                Global.stats[Global.ioTele3HighCont] -= 1;
                                              b3HighCont.setText("Level 3\n" + Global.stats[Global.ioTele3HighCont]);
                    break;
                    case R.id.b2HighCont: Global.stats[Global.ioTele2HighCont] += 2;
                    case R.id.bUndo2HighCont: if (Global.stats[Global.ioTele2HighCont] > 0)
                                                Global.stats[Global.ioTele2HighCont] -= 1;
                                              b2HighCont.setText("Level 2\n" + Global.stats[Global.ioTele2HighCont]);
                    break;
                    case R.id.b1HighCont: Global.stats[Global.ioTele1HighCont] += 2;
                    case R.id.bUndo1HighCont: if (Global.stats[Global.ioTele1HighCont] > 0)
                                                Global.stats[Global.ioTele1HighCont] -= 1;
                                              b1HighCont.setText("Level 1\n" + Global.stats[Global.ioTele1HighCont]);
                    break;
                    case R.id.b4HighCoopTote: if (Global.stats[Global.ioTele4HighCoopTote] < 3)
                                                Global.stats[Global.ioTele4HighCoopTote] += 2;
                                              else
                                                Global.stats[Global.ioTele4HighCoopTote]++;
                    case R.id.bUndo4HighCoopTote: if (Global.stats[Global.ioTele4HighCoopTote] > 0)
                                                    Global.stats[Global.ioTele4HighCoopTote] -= 1;
                                                  b4HighCoopTote.setText("4 high\n" + Global.stats[Global.ioTele4HighCoopTote]);
                    break;
                    case R.id.b3HighCoopTote: if (Global.stats[Global.ioTele3HighCoopTote] < 3)
                                                Global.stats[Global.ioTele3HighCoopTote] += 2;
                                              else
                                                Global.stats[Global.ioTele3HighCoopTote]++;
                    case R.id.bUndo3HighCoopTote: if (Global.stats[Global.ioTele3HighCoopTote] > 0)
                                                    Global.stats[Global.ioTele3HighCoopTote] -= 1;
                                                  b3HighCoopTote.setText("3 high\n" + Global.stats[Global.ioTele3HighCoopTote]);
                    break;
                    case R.id.b2HighCoopTote: if (Global.stats[Global.ioTele2HighCoopTote] < 3)
                                                Global.stats[Global.ioTele2HighCoopTote] += 2;
                                              else
                                                Global.stats[Global.ioTele2HighCoopTote]++;
                    case R.id.bUndo2HighCoopTote: if (Global.stats[Global.ioTele2HighCoopTote] > 0)
                                                    Global.stats[Global.ioTele2HighCoopTote] -= 1;
                                                  b2HighCoopTote.setText("2 high\n" + Global.stats[Global.ioTele2HighCoopTote]);
                    break;
                    case R.id.b1HighCoopTote: if (Global.stats[Global.ioTele1HighCoopTote] < 3)
                                                Global.stats[Global.ioTele1HighCoopTote] += 2;
                                              else
                                                Global.stats[Global.ioTele1HighCoopTote]++;
                    case R.id.bUndo1HighCoopTote: if (Global.stats[Global.ioTele1HighCoopTote] > 0)
                                                    Global.stats[Global.ioTele1HighCoopTote] -= 1;
                                                  b1HighCoopTote.setText("1 high\n" + Global.stats[Global.ioTele1HighCoopTote]);
                    break;
                    case R.id.bLitter: Global.stats[Global.ioTeleLitter] += 2;
                    case R.id.bUndoLitter: if (Global.stats[Global.ioTeleLitter] > 0)
                                               Global.stats[Global.ioTeleLitter] -= 1;
                                           bLitter.setText("Litter to\nLandfill " + Global.stats[Global.ioTeleLitter]);
                    break;
                    case R.id.bRecycle: Global.stats[Global.ioTeleRecycle] += 2;
                    case R.id.bUndoRecycle: if (Global.stats[Global.ioTeleRecycle] > 0)
                                                Global.stats[Global.ioTeleRecycle] -= 1;
                        bRecycle.setText("Litter in\nContainer " + Global.stats[Global.ioTeleRecycle]);
                    break;
                    case R.id.bKnockStack: Global.stats[Global.ioTeleKnockStack] += 2;
                    case R.id.bUndoKnockStack: if (Global.stats[Global.ioTeleKnockStack] > 0)
                                                   Global.stats[Global.ioTeleKnockStack] -= 1;
                                               bKnockStack.setText("Knock Over\nStack " + Global.stats[Global.ioTeleKnockStack]);
                    break;
                    case R.id.bKnockCont: Global.stats[Global.ioTeleKnockCont] += 2;
                    case R.id.bUndoKnockCont: if (Global.stats[Global.ioTeleKnockCont] > 0)
                                                  Global.stats[Global.ioTeleKnockCont] -= 1;
                                              bKnockCont.setText("Knock Over\nContainer " + Global.stats[Global.ioTeleKnockCont]);
                    break;
                    case R.id.rbLeftTopStack: Global.boolStats[Global.ioLeftTopStack] = (Global.boolStats[Global.ioLeftTopStack].length() > 0 ? "" : "Right Close");
                                              rbLeftTopStack.setChecked(Global.boolStats[Global.ioLeftTopStack].equals("Right Close"));
                    break;
                    case R.id.rbMidTopStack: Global.boolStats[Global.ioMidTopStack] = (Global.boolStats[Global.ioMidTopStack].length() > 0 ? "" : "Mid Close");
                                             rbMidTopStack.setChecked(Global.boolStats[Global.ioMidTopStack].equals("Mid Close"));
                    break;
                    case R.id.rbRightTopStack: Global.boolStats[Global.ioRightTopStack] = (Global.boolStats[Global.ioRightTopStack].length() > 0 ? "" : "Left Close");
                                               rbRightTopStack.setChecked(Global.boolStats[Global.ioRightTopStack].equals("Left Close"));
                    break;
                    case R.id.rbLeftBottomStack: Global.boolStats[Global.ioLeftBottomStack] = (Global.boolStats[Global.ioLeftBottomStack].length() > 0 ? "" : "Right Far");
                                                 rbLeftBottomStack.setChecked(Global.boolStats[Global.ioLeftBottomStack].equals("Right Far"));
                    break;
                    case R.id.rbMidBottomStack: Global.boolStats[Global.ioMidBottomStack] = (Global.boolStats[Global.ioMidBottomStack].length() > 0 ? "" : "Mid Far");
                                                rbMidBottomStack.setChecked(Global.boolStats[Global.ioMidBottomStack].equals("Mid Far"));
                    break;
                    case R.id.rbRightBottomStack: Global.boolStats[Global.ioRightBottomStack] = (Global.boolStats[Global.ioRightBottomStack].length() > 0 ? "" : "Left Far");
                                                  rbRightBottomStack.setChecked(Global.boolStats[Global.ioRightBottomStack].equals("Left Far"));
                    break;
                    case R.id.bNext: next();
                    break;
                    case R.id.bBack: back();
                    break;
                }
            }
        };

        b6HighTote.setOnClickListener(listen);
        b5HighTote.setOnClickListener(listen);
        b4HighTote.setOnClickListener(listen);
        b3HighTote.setOnClickListener(listen);
        b2HighTote.setOnClickListener(listen);
        b1HighTote.setOnClickListener(listen);
        bUndo6HighTote.setOnClickListener(listen);
        bUndo5HighTote.setOnClickListener(listen);
        bUndo4HighTote.setOnClickListener(listen);
        bUndo3HighTote.setOnClickListener(listen);
        bUndo2HighTote.setOnClickListener(listen);
        bUndo1HighTote.setOnClickListener(listen);
        b6HighCont.setOnClickListener(listen);
        b5HighCont.setOnClickListener(listen);
        b4HighCont.setOnClickListener(listen);
        b3HighCont.setOnClickListener(listen);
        b2HighCont.setOnClickListener(listen);
        b1HighCont.setOnClickListener(listen);
        bUndo6HighCont.setOnClickListener(listen);
        bUndo5HighCont.setOnClickListener(listen);
        bUndo4HighCont.setOnClickListener(listen);
        bUndo3HighCont.setOnClickListener(listen);
        bUndo2HighCont.setOnClickListener(listen);
        bUndo1HighCont.setOnClickListener(listen);
        b4HighCoopTote.setOnClickListener(listen);
        b3HighCoopTote.setOnClickListener(listen);
        b2HighCoopTote.setOnClickListener(listen);
        b1HighCoopTote.setOnClickListener(listen);
        bUndo4HighCoopTote.setOnClickListener(listen);
        bUndo3HighCoopTote.setOnClickListener(listen);
        bUndo2HighCoopTote.setOnClickListener(listen);
        bUndo1HighCoopTote.setOnClickListener(listen);
        bLitter.setOnClickListener(listen);
        bUndoLitter.setOnClickListener(listen);
        bRecycle.setOnClickListener(listen);
        bUndoRecycle.setOnClickListener(listen);
        bKnockStack.setOnClickListener(listen);
        bUndoKnockStack.setOnClickListener(listen);
        bKnockCont.setOnClickListener(listen);
        bUndoKnockCont.setOnClickListener(listen);
        rbLeftTopStack.setOnClickListener(listen);
        rbMidTopStack.setOnClickListener(listen);
        rbRightTopStack.setOnClickListener(listen);
        rbLeftBottomStack.setOnClickListener(listen);
        rbMidBottomStack.setOnClickListener(listen);
        rbRightBottomStack.setOnClickListener(listen);
        bNext.setOnClickListener(listen);
        bBack.setOnClickListener(listen);
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
                    else if (x2 - x1 > 100){
                        back();
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
        else{
            Global.boolStats[Global.ioLeftFeed] = (cbLeftFeed.isChecked() ? "Right Feeder Station" : "");
            Global.boolStats[Global.ioRightFeed] = (cbRightFeed.isChecked() ? "Left Feeder Station" : "").toString();
            Global.boolStats[Global.ioLeftLandfill] = (cbLeftLandfill.isChecked() ? "Right Landfill" : "").toString();
            Global.boolStats[Global.ioRightLandfill] = (cbRightLandfill.isChecked() ? "Left Landfill" : "").toString();
            Global.boolStats[Global.ioLeftStageCont] = (tbLeftStageCont.isChecked() ? "Right Container" : "");
            Global.boolStats[Global.ioMidStageCont] = (tbMidStageCont.isChecked() ? "Middle Container" : "");
            Global.boolStats[Global.ioRightStageCont] = (tbRightStageCont.isChecked() ? "Left Container" : "");
            Global.boolStats[Global.ioLeftStepCont] = (tbLeftStepCont.isChecked() ? "Right Step Container" : "");
            Global.boolStats[Global.ioMidLeftStepCont] = (tbMidLeftStepCont.isChecked() ? "Mid Right Step Container" : "");
            Global.boolStats[Global.ioMidRightStepCont] = (tbMidRightStepCont.isChecked() ? "Mid Left Step Container" : "");
            Global.boolStats[Global.ioRightStepCont] = (tbRightStepCont.isChecked() ? "Left Step Container" : "");
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
            startActivity(comments);
        }

    }

    public void back(){
        if (sTeam.getSelectedItemPosition() == 0){
            makeToast("team number");
        }
        else{
            Global.boolStats[Global.ioLeftFeed] = (cbLeftFeed.isChecked() ? "Right Feeder Station" : "");
            Global.boolStats[Global.ioRightFeed] = (cbRightFeed.isChecked() ? "Left Feeder Station" : "").toString();
            Global.boolStats[Global.ioLeftLandfill] = (cbLeftLandfill.isChecked() ? "Right Landfill" : "").toString();
            Global.boolStats[Global.ioRightLandfill] = (cbRightLandfill.isChecked() ? "Left Landfill" : "").toString();
            Global.boolStats[Global.ioLeftStageCont] = (tbLeftStageCont.isChecked() ? "Right Container" : "");
            Global.boolStats[Global.ioMidStageCont] = (tbMidStageCont.isChecked() ? "Middle Container" : "");
            Global.boolStats[Global.ioRightStageCont] = (tbRightStageCont.isChecked() ? "Left Container" : "");
            Global.boolStats[Global.ioLeftStepCont] = (tbLeftStepCont.isChecked() ? "Right Step Container" : "");
            Global.boolStats[Global.ioMidLeftStepCont] = (tbMidLeftStepCont.isChecked() ? "Mid Right Step Container" : "");
            Global.boolStats[Global.ioMidRightStepCont] = (tbMidRightStepCont.isChecked() ? "Mid Left Step Container" : "");
            Global.boolStats[Global.ioRightStepCont] = (tbRightStepCont.isChecked() ? "Left Step Container" : "");
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
            startActivity(auto);
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
