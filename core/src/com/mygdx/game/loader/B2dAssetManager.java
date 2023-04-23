package com.mygdx.game.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class B2dAssetManager{
    public final AssetManager manager = new AssetManager();

    public String pingSound = "sounds/ping.wav";
//    public Sound ping2Sound;
//    public Sound boing2Sound;
public final String playingSong = "music/Rolemusic.mp3";
    public String boingSound ="sounds/boing.wav";
    public final String skin = "skin/glassy-ui.json";

    public final String gameImages = "images/game.atlas";
    public final String loadingImages = "images/loading.atlas";


    public void queueAddImages(){

        manager.load(gameImages, TextureAtlas.class);

    }
    public void queueAddLoadingImages(){
        manager.load(loadingImages, TextureAtlas.class);
    }
    public void queueAddSounds(){
        manager.load(pingSound, Sound.class);
        manager.load(boingSound,Sound.class);
//        ping2Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/ping.wav"));
//        boing2Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/boing.wav"));
    }

    public void queueAddMusic(){
;        manager.load(playingSong, Music.class);
    }

    public void queueAddSkin(){
        SkinParameter params = new SkinParameter("skin/glassy-ui.atlas");
        manager.load(skin, Skin.class,params);
    }
    public void queueAddFonts(){

    }
    public void queueAddParticleEffects(){

    }
}
