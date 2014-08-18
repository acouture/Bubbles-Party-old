package com.cgteam.bubblesparty.menu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.cgteam.bubblesparty.R;
import com.cgteam.bublesparty.bdd.BubblesPartyBDD;
import com.cgteam.bublesparty.bdd.ScoresBDD;

public class HighScores extends BaseActivity
{
	
	private int nb_scores;
	private String game_title;
	private String game_bdd;
	
	// Nombre de colonnes
	private static final int NB_COLONNE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivity_high_scores);
		
		// Récupération des informations de bases
		Bundle bundle = getIntent().getExtras();
		game_title = (String) bundle.get("game_title");
		game_bdd = (String) bundle.get("game_bdd");
		nb_scores = this.getResources().getInteger(R.integer.number_of_high_scores);
		
		// Cas de problème
		if( game_title == null || game_bdd == null || nb_scores <= -1 ){
			Log.e("HIGH_SCORES", "Error : Chargement des high-scores");
			Toast.makeText(this.getApplicationContext(), "Une erreur est survenu lors du chargement des high-scores", Toast.LENGTH_SHORT).show();
			finish();
		}
		
		// Création et affichage des scores
		createTableOfScore();
	}
	
	/**
	 * Création du tableau des scores
	 */
	public void createTableOfScore(){
		TableLayout containerTableOfScore = (TableLayout) findViewById(R.id.score_container);
		
		TableRow row = new TableRow(this);
	    TextView cell_left = new TextView(this);
	    TextView cell_right = new TextView(this);
	    
	    cell_left.setText(this.getString(R.string.position));
	    cell_right.setText(this.getString(R.string.score));
	    cell_left.setGravity( Gravity.CENTER );
	    cell_left.setPadding(0, 20, 0, 20);
	    cell_right.setGravity( Gravity.CENTER );
	    cell_right.setPadding(0, 20, 0, 20);
	    
	    
	    row.addView(cell_left);
	    row.addView(cell_right);
	    containerTableOfScore.addView(row);
	    
	    
	    // Affichage des autres high scores
	    int[][] resultat = null;
	    ScoresBDD scores = new ScoresBDD(this.getApplicationContext());
	    scores.open();
	    resultat = scores.getGameSCORE(game_bdd, nb_scores);
	    scores.close();
	    
	    // problème récupération des données
	    if ( resultat == null ){
	    	Log.e("BDD", "Erreur de récupération des données des scores ");
	    }
	    
		for(int pos = 0; pos < nb_scores ; pos++ ){
			row = new TableRow(this);
		    cell_left = new TextView(this);
		    cell_right = new TextView(this);
		    
		    cell_left.setText( ((Integer) (pos+1) ).toString() );
		    cell_right.setText( ((Integer) resultat[pos][1]).toString() );
		    cell_left.setGravity( Gravity.CENTER );
		    cell_right.setGravity( Gravity.CENTER );
		    
		    row.addView(cell_left);
		    row.addView(cell_right);
		    containerTableOfScore.addView(row);
			
		}
		
	}
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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

