package com.cgteam.bubblesparty.menu;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

import com.cgteam.bubblesparty.R;


public class MainMenu extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
		
		/* Version du jeu */
        drawVersionOfProject();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent touchevent){
    	return false;
    }
    
    /**
     * Récupération de la version du projet
     */
    public void drawVersionOfProject(){
    	String versionName = null;
		try {
			versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			Log.e("VERSION", "Un problème est survenue lors de la récupération de la version du projet.");
		}
		TextView tvVersion = (TextView) findViewById(R.id.textVersion);
		
		if(versionName != null && tvVersion != null)
			tvVersion.setText(versionName);
		else
			Log.e("VERSION", "Un problème est survenue lors de l'éditage de la version");
    }
}
