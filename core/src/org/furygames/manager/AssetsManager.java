package org.furygames.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManager {
	
	public  AssetManager manager;
    
    public AssetsManager(){
    	manager = new AssetManager();
    }
    
    public  void cargarAssets(){
       
        manager.load("Fondo-logo2.png",Texture.class);
        manager.load("sounds/intro/Movie Theater Intro.mp3",Sound.class);
            
    }
}
