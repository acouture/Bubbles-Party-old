package com.cgteam.bubblesparty.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cgteam.bubblesparty.R;


public class GamePlay extends BaseActivity
{
	private EGamePlay id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
		
		Bundle bundle = getIntent().getExtras();
		id = (EGamePlay) bundle.get("id");
		
		switch(id){
			case CLASSIC:
				break;
			case INFINITE:
				break;
			default:
				// On préviens l'utilisateur
				Toast.makeText(this.getApplicationContext(), "Le mode est inconnu", Toast.LENGTH_SHORT).show();
				Log.e("MODE", "MODE INCONNU");
				// On termine l'activité
				finish();
				break;
		}
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

