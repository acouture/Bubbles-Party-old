package com.cgteam.bubblesparty.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.cgteam.bubblesparty.R;


public class MainMenu extends BaseActivity {
	
	/* Slide des modes de jeu */
	private ViewFlipper viewSlide;
	private Slide slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        Button infiniteMode = (Button) findViewById(R.id.buttonInfinite);
		infiniteMode.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO
//				setGameplay(0);
//				Intent intent = new Intent(MainMenuActivity.this, GamePlayActivity.class);
//				startActivity(intent);
			}
		});
		
		Button classicMode = (Button) findViewById(R.id.buttonClassic);
		classicMode.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO
//				setGameplay(1);
//				Intent intent = new Intent(MainMenuActivity.this, GamePlayActivity.class);
//				startActivity(intent);
			}
		});
		viewSlide = (ViewFlipper) findViewById(R.id.slideGameMode);
		slide = new Slide( this, viewSlide );
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
    	slide.onTouchEvent(touchevent);
    	return false;
    }
}
