package org.furyfames.assets;

import org.furygames.furyball.FuryBall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class Manager {
	
	public  AssetManager manager;

    
    public Manager(){
    	manager = new AssetManager();
    }
    
    public  void cargarAssets(){
       
        manager.load("Fondo-logo2.png",Texture.class);
        manager.load("sounds/intro/Movie Theater Intro.mp3",Sound.class);
            
    }
    
    
  
    
}
