package com.cgteam.bublesparty.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GamesBDD {
	
	private SQLiteDatabase bdd;
	
	private BubblesPartyBDD bp_bdd;
	
	public GamesBDD(Context context){
		// Création de la base de données et de ses tables
		bp_bdd = new BubblesPartyBDD(context, BubblesPartyBDD.DATABASE_NAME , null, BubblesPartyBDD.VERSION);
	}
	
	public void open(){
		// Ouverture de la mase de données en écriture
		bdd = bp_bdd.getWritableDatabase();
	}
	
	public void close(){
		// Fermeture de la base de données
		bdd.close();
	}
	
	public int insertGame(String game_name){
		ContentValues values = new ContentValues();
		values.put(bp_bdd.COL_GAME_NAME, game_name);
		return (int) bdd.insert(bp_bdd.TABLE_GAMES, null, values);
	}
	
	public int updateGame(int id, String game_name){
		ContentValues values = new ContentValues();
		values.put(bp_bdd.COL_GAME_NAME, game_name);
		return bdd.update(bp_bdd.TABLE_GAMES, values, bp_bdd.COL_ID + " = " +id, null);
	}
	
	public int updateGame(String old_name, String new_name){
		ContentValues values = new ContentValues();
		values.put(bp_bdd.COL_GAME_NAME, new_name);
		return bdd.update(bp_bdd.TABLE_GAMES, values, bp_bdd.COL_GAME_NAME + " = " +old_name, null);
	}
 
	public int removeGame(int id){
		return bdd.delete(bp_bdd.TABLE_GAMES, bp_bdd.COL_ID + " = " +id, null);
	}
	
	public int removeGame(String game_name){
		return bdd.delete(bp_bdd.TABLE_GAMES, bp_bdd.COL_GAME_NAME + " = " +game_name, null);
	}
	
	public int getGameID(String game_name){
		Cursor c = bdd.query(bp_bdd.TABLE_GAMES, new String[] {bp_bdd.COL_ID, bp_bdd.COL_GAME_NAME}, bp_bdd.COL_GAME_NAME + " LIKE \"" + game_name +"\"", null, null, null, null);
		
		/* Aucun résultat */
		if ( c.getCount() == 0 )
			return -1;
		
		c.moveToFirst();
		return (int) c.getInt(bp_bdd.NUM_COL_ID);
	}
	
	public String getGameNAME(int id){
		Cursor c = bdd.query(bp_bdd.TABLE_GAMES, new String[] {bp_bdd.COL_ID, bp_bdd.COL_GAME_NAME}, bp_bdd.COL_ID + " = " + id, null, null, null, null);
		
		/* Aucun résultat */
		if ( c.getCount() == 0 )
			return null;
		
		c.moveToFirst();
		return c.getString(bp_bdd.NUM_COL_GAME_NAME);
	}
	
}
