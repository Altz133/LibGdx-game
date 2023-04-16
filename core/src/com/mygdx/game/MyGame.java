package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.views.*;

import java.awt.*;


public class MyGame extends Game {
	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;

	public SpriteBatch batch;
	public BitmapFont font;

	public final static int MENU = 0;
	public final static int PREFERENCES = 1;

	public final static int MAIN = 2;
	public final static int ENDGAME = 3;

	public final static int LOADING= 5;
	private OrthographicCamera camera;
	private AppPreferences preferences;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		preferences = new AppPreferences();
		loadingScreen = new LoadingScreen(this);
		preferencesScreen = new PreferencesScreen(this);
		loadingScreen = new LoadingScreen(this);
		endScreen = new EndScreen(this);
		mainScreen = new MainScreen(this);
		menuScreen = new MenuScreen(this);
		changeScreen(LOADING);
	}

	@Override
	public void render () {
		super.render();
	}
	


	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case PREFERENCES:
				if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
				this.setScreen(preferencesScreen);
				break;
			case MAIN:
				if(mainScreen == null) mainScreen = new MainScreen(this);
				this.setScreen(mainScreen);
				break;
			case LOADING:
				if(loadingScreen == null) loadingScreen = new LoadingScreen(this);
				this.setScreen(loadingScreen);
				break;
			case ENDGAME:
				if(endScreen == null) endScreen = new EndScreen(this);
				this.setScreen(endScreen);
				break;
		}
	}
	public AppPreferences getPreferences(){
		return this.preferences;
	}

	@Override
	public void dispose () {
		batch.dispose();
		loadingScreen.dispose();
		preferencesScreen.dispose();
		menuScreen.dispose();
		mainScreen.dispose();
		endScreen.dispose();
	}
}
