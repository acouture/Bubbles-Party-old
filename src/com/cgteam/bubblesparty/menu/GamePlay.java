package com.cgteam.bubblesparty.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cgteam.bubblesparty.R;

public class GamePlay extends BaseActivity {
	private EGamePlay id;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        
        Bundle bundle = getIntent().getExtras();
        id = (EGamePlay) bundle.get("id");
        
        // Vérification du mode de jeu
        if (id == EGamePlay.CLASSIC)
        	System.out.println("CLASSIC");
        else if (id == EGamePlay.INFINITE)
        	System.out.println("INFINITE");
        else
        	System.out.println("MODE INCONNU");
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
}

