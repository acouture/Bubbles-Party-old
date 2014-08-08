package com.cgteam.bubblesparty.menu;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cgteam.bubblesparty.R;

public class About extends BaseActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        /* Gestion de boutons */
        bindButtons();
        
        /* version du projet */
        drawVersionOfProject();
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
		TextView tvVersion = (TextView) findViewById(R.id.about_version);
		
		if(versionName != null && tvVersion != null)
			tvVersion.setText( tvVersion.getText() + " "+ versionName);
		else
			Log.e("VERSION", "Un problème est survenue lors de l'éditage de la version");
    }
	
    /**
     * Gestion des boutons
     */
	public void bindButtons(){
		Button facebook = (Button) findViewById(R.id.facebook);
		facebook.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent;
				try {
					/* Recherche de l'application facebook */
					getPackageManager().getPackageInfo("com.facebook.katana", 0);
					/* 404004496409757 -> id de splash bubble via graph search : https://graph.facebook.com/SplashBubble */
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/404004496409757"));
				} catch (Exception e) {
					/* Page internet normal */
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SplashBubble?fref=ts"));
 			   	}
				if ( intent != null ){
					startActivity(intent);
				}
				else{
					Log.e("FACEBOOK", "erreur de redirection facebook");
				}
 			}
 		});
         
    }

}
