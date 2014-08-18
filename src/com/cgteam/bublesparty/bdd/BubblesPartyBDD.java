package com.cgteam.bublesparty.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Cette classe gère la relation avec la base de données SQLITE
 */
public class BubblesPartyBDD extends SQLiteOpenHelper {
	
	/**
	 * Informations général
	 */
	public static final String DATABASE_NAME = "BubblesPartyBDD.db";
	public static final String COL_ID = "ID";
	public static final int NUM_COL_ID = 0;
	public static final int VERSION = 2;
	
	/**
	 * TABLE GAMES
	 */
	public static final String TABLE_GAMES = "bubblesparty_games";
	public static final String COL_GAME_NAME = "game_name";
	public static final int NUM_COL_GAME_NAME = 1;
	private static final String CREATE_TABLE_GAMES = " CREATE TABLE " + TABLE_GAMES 
			+ "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_GAME_NAME + " TEXT NOT NULL );";
	
	/**
	 * TABLE SCORES
	 */
	public static final String TABLE_SCORES = "bubblesparty_score";
	public static final String COL_SCORE_POINTS = "score_points";
	public static final int  NUM_COL_SCORE_POINTS = 1;
	public static final String COL_SCORE_GAME = "score_game";
	public static final int NUM_COL_SCORE_GAME = 2;
	
	private static final String CREATE_TABLE_SCORES = " CREATE TABLE " + TABLE_SCORES
			+ "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_SCORE_POINTS + " INTEGER, "
			+ COL_SCORE_GAME + " INTEGER, "
			+ "FOREIGN KEY (" + COL_SCORE_GAME + ") REFERENCES " 
			+ TABLE_GAMES + "(" + COL_ID + ") );";
	
	// Contrainte de clé étrangère
	private static final String TABLE_SCORE_CONSTRAINT = "ALTER TABLE " + TABLE_SCORES
			+ " ADD FOREIGN KEY " + COL_SCORE_GAME + " " 
			+ TABLE_GAMES + "(" + COL_ID + ")";		
	
	public BubblesPartyBDD(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		/* Création de la table game */
		db.execSQL(CREATE_TABLE_GAMES);
		
		/* Création de la table score et les contraintes */
		db.execSQL(CREATE_TABLE_SCORES);
		//db.execSQL(TABLE_SCORE_CONSTRAINT);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
