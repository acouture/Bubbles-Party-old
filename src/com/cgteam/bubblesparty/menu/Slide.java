package com.cgteam.bubblesparty.menu;

import com.cgteam.bubblesparty.R;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class Slide {
	
	/* Slide des modes de jeu */
	private ViewFlipper slide;
    /* Derni�re coordonn�e en x que l'utilisateur a touch� l'�cran */
	private float touchX;
	
	private Context context;
	
	public Slide(Context context, ViewFlipper slide){
		this.slide = slide;
		this.context = context;
	}
	
	public void onTouchEvent(MotionEvent touchevent){
		
		if ( context != null && slide != null ){
	    	switch (touchevent.getAction()) {
	        
	    		// R�cup�ration de la coordonn�e lorsque l'utilisateur touche l'�cran
	    		case MotionEvent.ACTION_DOWN : 
	    			touchX = touchevent.getX();
	                break;
	            
	            // D�termination du mouvement (gauche/droite) de l'utilisateur 
	    		case MotionEvent.ACTION_UP : 
	                         
	    			float currentX = touchevent.getX();
	                
	    			// swap droite
	    			if (touchX < currentX){
	    				// Cas o� il n'y a plus de mode
	                    if (slide.getDisplayedChild() == 0)
	                    	break;
	                    slide.setInAnimation(context, R.anim.in_from_left);
	                    slide.setOutAnimation(context, R.anim.out_to_right);
	                    slide.showNext();
	                 }
	    			// swap gauche
	                 if (touchX > currentX){
	                	// Cas o� il n'y a plus de mode
	                    if (slide.getDisplayedChild() == 1)
	                    	break;  
	                     slide.setInAnimation(context, R.anim.in_from_right);
	                     slide.setOutAnimation(context, R.anim.out_to_left);
	                     slide.showPrevious();
	                 }
	                 
	                 break;
	    	}
		}
		else{
			Log.e("SLIDE", "Un probl�me est survenu lors du chargement du slide.");
		}
    }
	
}
