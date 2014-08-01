package com.cgteam.bubblesparty.menu;

import com.cgteam.bubblesparty.R;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class Slide {
	
	/* Slide des modes de jeu */
	private ViewFlipper slide;
    /* Dernière coordonnée en x que l'utilisateur a touché l'écran */
	private float touchX;
	
	private BaseActivity view;
	
	public Slide(BaseActivity view, ViewFlipper slide){
		if ( slide == null )
			Log.e("SLIDE", "error : le slide n'a pas été créer." );
		if ( view == null )
			Log.e("SLIDE", "error : mauvaise association d'activité." );
		this.slide = slide;
		this.view = view;
	}
	
	public void onTouchEvent(MotionEvent touchevent){
    	switch (touchevent.getAction()) {
        
    		// Récupération de la coordonnée lorsque l'utilisateur touche l'écran
    		case MotionEvent.ACTION_DOWN : 
    			touchX = touchevent.getX();
                break;
            
            // Détermination du mouvement (gauche/droite) de l'utilisateur 
    		case MotionEvent.ACTION_UP : 
                         
    			float currentX = touchevent.getX();
                
    			// swap droite
    			if (touchX < currentX){
    				// Cas où il n'y a plus de mode
                    if (slide.getDisplayedChild() == 0)
                    	break;
                    slide.setInAnimation(view, R.anim.in_from_left);
                    slide.setOutAnimation(view, R.anim.out_to_right);
                    slide.showNext();
                 }
    			// swap gauche
                 if (touchX > currentX){
                	// Cas où il n'y a plus de mode
                    if (slide.getDisplayedChild() == 1)
                    	break;  
                     slide.setInAnimation(view, R.anim.in_from_right);
                     slide.setOutAnimation(view, R.anim.out_to_left);
                     slide.showPrevious();
                 }
                 
                 break;
    	}
    }
	
}
