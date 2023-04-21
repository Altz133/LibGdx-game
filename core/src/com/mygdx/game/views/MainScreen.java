package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.bodies.B2dModel;
import com.mygdx.game.MyGame;
import com.mygdx.game.controller.KeyboardController;

public class MainScreen implements Screen {
    private MyGame parent;
    private B2dModel model;
    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;
    private KeyboardController controller;

    public MainScreen(MyGame myGame) {
        this.parent= myGame;
        controller = new KeyboardController();


    }

    @Override
    public void show() {
        cam = new OrthographicCamera(32,24);
        model =  new B2dModel(controller,cam);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
        Gdx.input.setInputProcessor(controller);

    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f,0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world,cam.combined);

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
        debugRenderer.dispose();

    }
}

