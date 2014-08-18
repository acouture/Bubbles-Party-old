package com.cgteam.bubblesparty.menu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cgteam.bubblesparty.R;


public class GamePlay extends BaseActivity
{
	private EGamePlay id;
	private String title;
	private String game_bdd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_game_play);
		
		Bundle bundle = getIntent().getExtras();
		id = (EGamePlay) bundle.get("id");
		
		switch(id){
			case CLASSIC:
				title = this.getString(R.string.classic_mode);
				game_bdd = this.getString(R.string.game_classic_mode);
				break;
			case INFINITE:
				title = this.getString(R.string.infinite_mode);
				game_bdd = this.getString(R.string.game_infinite_mode);
				break;
			default:
				// On préviens l'utilisateur
				Toast.makeText(this.getApplicationContext(), "Le mode est inconnu", Toast.LENGTH_SHORT).show();
				Log.e("MODE", "MODE INCONNU");
				// On termine l'activité
				finish();
				break;
		}
		
		/* Changement de la police sur les éléments à écrire (btn)*/
        changeBtnFonts();
        
        /* Changement du titre */
        changeTitle(title);
        
        /* Mise en place des actions sur les boutons */
        bindsButtons();
		
	}
	
	public void bindsButtons(){
		// High scores
		Button highScores = (Button) findViewById(R.id.buttonHighScore);
		highScores.setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	         	Intent intent = new Intent(getApplicationContext(), HighScores.class);
	         	intent.putExtra("game_title", title);
	         	intent.putExtra("game_bdd", game_bdd);
	         	startActivity(intent);
	         }
        });
        
	}
	
	/** 
     * Changement de la police d'écriture sur les boutons
     */
    public void changeBtnFonts(){
    	String fontPath = "fonts/Bambina.ttf";
    	Typeface type = Typeface.createFromAsset(getAssets(), fontPath);
    	
        TextView btn_highScore = (TextView)findViewById(R.id.buttonHighScore);
        btn_highScore.setTypeface(type);
        
        TextView btn_retour = (TextView)findViewById(R.id.buttonRetour);
        btn_retour.setTypeface(type);
    }
    
    /** 
     * Changement de la police d'écriture du titre
     * Changement du titre du mode de jeu
     */
    public void changeTitle(String title){
    	String fontPath = "fonts/Bambina.ttf";
    	Typeface type = Typeface.createFromAsset(getAssets(), fontPath);
    	
    	TextView menu_title = (TextView) findViewById(R.id.sous_menu_title);
    	menu_title.setText(title);
    	menu_title.setTypeface(type);
    	
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
        switch(id){
	        case R.id.about:
	        	Intent intent = new Intent( getApplicationContext(), About.class);
	        	startActivity(intent);
	        	return true;
	        default:
	        	return super.onOptionsItemSelected(item);
        }
	}
}

