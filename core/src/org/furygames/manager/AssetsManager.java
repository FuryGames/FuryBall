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
    	
    	// Images
    	//
    	
        manager.load("backgrounds/splash.jpg", Texture.class);
        // Menu Buttons
        manager.load("buttons/start_button.png", Texture.class);
        manager.load("buttons/exit_button.png", Texture.class);
        manager.load("buttons/credits_button.png", Texture.class);
        // Authors
        manager.load("actors/authors/enki.png", Texture.class);
        manager.load("actors/authors/writkas.png", Texture.class);
        manager.load("actors/authors/jrf.png", Texture.class);
        manager.load("actors/authors/guitauricio.png", Texture.class);
        manager.load("actors/authors/danirod.png", Texture.class);
        
        // Musics
        //
        
        manager.load("sounds/music/Mauricio Vera - Ausencia.mp3", Music.class);
        
        // Sound
        //
        
    }
}
