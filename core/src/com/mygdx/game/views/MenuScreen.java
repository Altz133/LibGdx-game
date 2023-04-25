package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.mygdx.game.MyGame;

public class MenuScreen implements Screen {
    
    private final MyGame game;
//    private OrthographicCamera camera;
    protected Skin skin;
    private TextureAtlas atlas;
    private Stage stage;
    private TextureAtlas.AtlasRegion background;

    public MenuScreen(MyGame myGame) {
        this.game = myGame;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void show() {
        game.assMan.queueAddSkin();
        game.assMan.manager.finishLoading();
        //imho bad practices again, DRY principle violation
        skin = game.assMan.manager.get("skin/glassy-ui.json");
        stage = new Stage(new ScreenViewport(),game.batch);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
//       table.setDebug(true);
        stage.addActor(table);


        TextButton newGame = new TextButton("New Game", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);

        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();
        skin = game.assMan.manager.get("skin/glassy-ui.json");
        atlas = game.assMan.manager.get("images/loading.atlas");
        background = atlas.findRegion("flamebackground");

        table.setBackground(new TiledDrawable(background));

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.changeScreen(MyGame.MAIN);
                dispose();

            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.changeScreen(MyGame.PREFERENCES);
                dispose();

            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);


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
        stage.dispose();

    }
}
