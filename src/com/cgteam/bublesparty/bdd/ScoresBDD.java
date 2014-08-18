package com.cgteam.bublesparty.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ScoresBDD {
	private SQLiteDatabase bdd;
	
	private BubblesPartyBDD bp_bdd;
	private GamesBDD game_bdd;

	public ScoresBDD(Context context){
		// Création de la base de données et de ses tables
		bp_bdd = new BubblesPartyBDD(context, BubblesPartyBDD.DATABASE_NAME , null, BubblesPartyBDD.VERSION);
		game_bdd = new GamesBDD( context );
	}
	
	public void open(){
		// Ouverture de la mase de données en écriture
		bdd = bp_bdd.getWritableDatabase();
		game_bdd.open();
	}
	
	public void close(){
		// Fermeture de la base de données
		bdd.close();
		game_bdd.close();
	}
	
	private int checkGameInDataBase( String game_name ){
		int id_game = (int) game_bdd.getGameID(game_name);
		
		/* Cas où le jeu n'existe pas dans la base de données */
		if ( id_game == -1 )
			return (int) game_bdd.insertGame(game_name);
		return id_game;
	}
	
	public int insertSCORE(String game_name, int score){
		/* Vérification que le jeu existe dans la base de données */
		int id_game = (int) checkGameInDataBase(game_name);
		
		// Echec de l'insertion
		if ( id_game == -1 )
			return -1;
		
		ContentValues values = new ContentValues();
		values.put(bp_bdd.COL_SCORE_GAME, id_game);
		values.put(bp_bdd.COL_SCORE_POINTS, score);
		return (int) bdd.insert(bp_bdd.TABLE_SCORES, null, values);
	}
	
	public int updateSCORE(int id, int score){
		
		ContentValues values = new ContentValues();
		values.put(bp_bdd.COL_SCORE_POINTS, score);
		return bdd.update(bp_bdd.TABLE_SCORES, values, bp_bdd.COL_ID + " = " +id, null);
	}
	
	public int removeSCORE(int id){
		return bdd.delete(bp_bdd.TABLE_SCORES, bp_bdd.COL_ID + " = " +id, null);
	}
	
	public int getSCORE(int id){
		Cursor c = bdd.query(bp_bdd.TABLE_SCORES, new String[] {bp_bdd.COL_ID, bp_bdd.COL_SCORE_POINTS, bp_bdd.COL_SCORE_GAME}, bp_bdd.COL_ID + " = " + id, null, null, null, null);
		
		/* Aucun résultat */
		if ( c.getCount() == 0 )
			return -1;
		
		c.moveToFirst();
		return (int) c.getInt(bp_bdd.NUM_COL_SCORE_POINTS);
	}
	
	public int[][] getGameSCORE(String game, int nb_result ){
		/* Vérification que le jeu existe dans la base de données */
		int id_game = (int) checkGameInDataBase(game);
		
		// Echec de l'insertion
		if ( id_game == -1 )
			return null;
		
		Cursor c;
		int[][] result;
		int nbres;
		// Cas où il n'y a pas de limitation
		if ( nb_result <= -1 ){
			c = bdd.query(bp_bdd.TABLE_SCORES, new String[] {bp_bdd.COL_ID, bp_bdd.COL_SCORE_POINTS, bp_bdd.COL_SCORE_GAME}, bp_bdd.COL_SCORE_GAME + " = " + id_game , null, bp_bdd.COL_ID, null, bp_bdd.COL_SCORE_POINTS + " DESC", null );
			nbres = c.getCount();
			if ( nbres == 0 )
				return null;
			result = new int[nbres][2];
		}
		// Cas où il y a une limitation
		else{
			Integer limitation = nb_result;
			c = bdd.query(bp_bdd.TABLE_SCORES, new String[] {bp_bdd.COL_ID, bp_bdd.COL_SCORE_POINTS, bp_bdd.COL_SCORE_GAME}, bp_bdd.COL_SCORE_GAME + " = " + id_game , null, bp_bdd.COL_ID, null, bp_bdd.COL_SCORE_POINTS + " DESC", limitation.toString() );
			result = new int[nb_result][2];
			
			/* Remplissage de la liste avec des 0 */
			for(int j = 0; j < nb_result; j++){
				result[j][0] = -1; // ID
				result[j][1] = 0; // POINTS
			}
			
			nbres = c.getCount();
			
		}
		
		int i = 0;
		/* Remplissage de l'array à l'aide de la base de données */
		
		c.moveToFirst();
		while( i < nbres ){
			result[i][0] = (int) c.getInt(bp_bdd.NUM_COL_ID);
			result[i][1] = (int) c.getInt(bp_bdd.NUM_COL_SCORE_POINTS);
			i++;
			c.moveToNext();
		}
		
		return result;
	}
	
	
}
