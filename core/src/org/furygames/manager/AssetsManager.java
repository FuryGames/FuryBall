package org.furygames.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManager {
	
	public  AssetManager manager;
    
    public AssetsManager(){
    	manager = new AssetManager();
    }
    
    public  void cargarAssets(){
        manager.load("backgrounds/splash.jpg", Texture.class);
        manager.load("sounds/music/Mauricio Vera - Ausencia.mp3", Music.class);
    }
}
