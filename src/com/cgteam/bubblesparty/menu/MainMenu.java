package com.cgteam.bubblesparty.menu;

import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.View.OnClickListener;
import com.cgteam.bubblesparty.R;


public class MainMenu extends BaseActivity {

	/* Slide des différents jeux */
	private ViewFlipper viewSlide;
	private Slide slide;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
		
		/* Version du jeu */
        drawVersionOfProject();
        
        /* Changement de la police sur les éléments à écrire (btn)*/
        changeBtnFonts();
        
        /* Création du slide */
        slide();
        
        /* Attribution des fonctions sur les boutons */
        bindButtons();
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
    
    /** 
     * Changement de la police d'écriture sur les boutons
     */
    public void changeBtnFonts(){
    	String fontPath = "fonts/Bambina.ttf";
        TextView btn_quit = (TextView)findViewById(R.id.buttonQuit);
        Typeface type = Typeface.createFromAsset(getAssets(), fontPath);
        btn_quit.setTypeface(type);
    }
    
    /** 
     * Création d'un slide des différents jeux 
     */
    public void slide(){
    	viewSlide = (ViewFlipper) findViewById(R.id.slideGame);
		slide = new Slide( viewSlide.getContext(), viewSlide );
		
		/* Changement police titre */
		String fontPath = "fonts/Bambina.ttf";
        Typeface type = Typeface.createFromAsset(getAssets(), fontPath);
        
        TextView title_game1 = (TextView)findViewById(R.id.titleGame1);
        title_game1.setTypeface(type);
        
        TextView title_game2 = (TextView)findViewById(R.id.titleGame2);
        title_game2.setTypeface(type);
    }
    
    public void bindButtons(){
    	 Button leftSlide = (Button) findViewById(R.id.buttonLeftSlide);
         leftSlide.setOnClickListener(new OnClickListener() {
 			public void onClick(View v) {
 				slide.swapright();
 			}
 		});
         
         Button rightSlide = (Button) findViewById(R.id.buttonRightSlide);
         rightSlide.setOnClickListener(new OnClickListener() {
 			public void onClick(View v) {
 				slide.swapleft();
 			}
 		});
    }
}
