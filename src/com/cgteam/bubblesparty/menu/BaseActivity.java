package com.cgteam.bubblesparty.menu;

import android.support.v7.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class BaseActivity extends ActionBarActivity {
	
	protected ActionBar menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		/* Cache la barre de titre */
		getSupportActionBar().hide();
		
	}

    
    public void quit(View v) {
    	finish();
    }
}
