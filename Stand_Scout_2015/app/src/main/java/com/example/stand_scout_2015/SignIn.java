package com.example.stand_scout_2015;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignIn extends Activity {

	EditText etName;
	Button bNext;
	Intent intent;
	Spinner sRobot;
	String[] robots;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		etName = (EditText) findViewById(R.id.etName);
		bNext = (Button) findViewById(R.id.bNext);
		intent = new Intent(this, Autonomous.class);
		robots = new String[]{"Red 1", "Red 2", "Red 3", "Blue 1", "Blue 2", "Blue 3"};
		ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, robots);
		sRobot = (Spinner) findViewById(R.id.sRobot);
		sRobot.setAdapter(adapter);
		View.OnClickListener listen = new View.OnClickListener() {
			  public void onClick(View v) {
				  if (etName.getText().toString().length()>0){
					  Global.name = etName.getText().toString();
					  Global.robot = (String) sRobot.getSelectedItem();
					  startActivity(intent);
				  }
				  else{
					  Toast toast = Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_LONG);
					  toast.show();
				  }
			  }};
		bNext.setOnClickListener(listen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}

}
