package com.mygdx.game.views;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGame;

public class LoadingScreen implements Screen {
    private MyGame game;

    public LoadingScreen(MyGame myGame) {
        this.game = myGame;

    }

    public void show(){
    }

    @Override
    public void render(float delta) {
        game.changeScreen(MyGame.MENU);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
