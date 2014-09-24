package org.furygames.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManager {

    public AssetManager manager;

    public AssetsManager() {
        manager = new AssetManager();
    }

    public void cargarAssets() {

        // Images
        //

        manager.load("backgrounds/splash.jpg", Texture.class);
        // Menu Buttons
        manager.load("buttons/start_button.png", Texture.class);
        manager.load("buttons/exit_button.png", Texture.class);
        manager.load("buttons/credits_button.png", Texture.class);
        manager.load("backButton.png", Texture.class);
        manager.load("nextButton.png", Texture.class);
        manager.load("replayButton.png", Texture.class);
        // Authors
        manager.load("actors/authors/enki.png", Texture.class);
        manager.load("actors/authors/writkas.png", Texture.class);
        manager.load("actors/authors/jrf.png", Texture.class);
        manager.load("actors/authors/guitauricio.png", Texture.class);
        manager.load("actors/authors/danirod.png", Texture.class);
        manager.load("actors/authors/seba.png", Texture.class);
        // Figures
        manager.load("actors/figures/ball/alien_1.png", Texture.class);
        manager.load("actors/figures/ball/alien_2.png", Texture.class);
        manager.load("actors/figures/ball/alien_3.png", Texture.class);
        manager.load("actors/figures/ball/alien_4.png", Texture.class);
        manager.load("actors/figures/wall-square.png", Texture.class);
        manager.load("actors/figures/wall-column.png", Texture.class);
        manager.load("actors/figures/elementMetal000.png", Texture.class);
        manager.load("actors/figures/rectangle.png", Texture.class);
        manager.load("actors/figures/Portal/Portal0000.png", Texture.class);
        manager.load("actors/figures/Portal/Portal0001.png", Texture.class);
        manager.load("actors/figures/BlackHole/BlackHole0000.png", Texture.class);
        manager.load("actors/figures/BlackHole/BlackHole0001.png", Texture.class);
        manager.load("actors/figures/BlackHole/BlackHole0002.png", Texture.class);
        manager.load("actors/figures/BlackHole/BlackHole0003.png", Texture.class);


        // Backgrounds
        manager.load("backgrounds/spaceBackground.png", Texture.class);
        manager.load("levelComplete.png", Texture.class);
        // Musics
        //

        manager.load("sounds/music/Mauricio Vera - Ausencia.mp3", Music.class);
        manager.load("sounds/music/prototipe.mp3", Music.class);

        // Sound
        //
        manager.load("sounds/boing.mp3", Sound.class);
    }
}
