package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGame;
import com.sun.tools.javac.comp.Check;
import org.w3c.dom.Text;

import javax.swing.event.ChangeEvent;
import java.text.DecimalFormat;

public class PreferencesScreen implements Screen {
    private static final DecimalFormat df =new DecimalFormat("0.0");
    private MyGame game;
    protected Skin skin;
    private OrthographicCamera camera;
    private Stage stage;


    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;
    private CheckBox musicCheckBox;
    private CheckBox soundEffectsCheckbox;
    private Slider volumeSoundSlider;
    private Slider volumeMusicSlider;
    private Label musicVolume;
    private Label soundEffectsVolume;

    public PreferencesScreen(MyGame myGame) {
        this.game = myGame;
    }
    @Override
    @SuppressWarnings("Duplicates")
    public void show() {
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        stage = new Stage(new ScreenViewport(),game.batch);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
//        table.setDebug(true);
        stage.addActor(table);
        musicVolume = new Label(String.valueOf(game.getPreferences().getMusicVolume()),skin);
        soundEffectsVolume = new Label(String.valueOf(game.getPreferences().getMusicVolume()),skin);


        volumeMusicSlider = new Slider(0f,1f,0.1f,false,skin);
        volumeMusicSlider.setValue(game.getPreferences().getMusicVolume());
        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                game.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                return false;
            }
        });

        volumeSoundSlider = new Slider (0f,1f,0.1f,false, skin);
        volumeSoundSlider.setValue(game.getPreferences().getSoundVolume());
        volumeSoundSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                game.getPreferences().setSoundVolume(volumeSoundSlider.getValue());
                return false;
            }
        });
        soundEffectsCheckbox = new CheckBox(null,skin);
        soundEffectsCheckbox.setChecked(game.getPreferences().isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean temp = game.getPreferences().isSoundEffectsEnabled();
                game.getPreferences().setSoundEffectsEnabled(!temp);


            }
        });

        musicCheckBox = new CheckBox(null,skin);
        musicCheckBox.setChecked(game.getPreferences().isMusicEnabled());
        musicCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean temp = game.getPreferences().isMusicEnabled();
                game.getPreferences().setMusicEnabled(!temp);
            }
        });



        final TextButton backButton = new TextButton("Back",skin, "small");
        backButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                game.changeScreen(MyGame.MENU);
            }
        });


        titleLabel = new Label("Preferences",skin);
        volumeMusicLabel = new Label("Music volume",skin);
        volumeSoundLabel = new Label("Sound volume", skin);
        musicOnOffLabel = new Label("Toggle music",skin);
        soundOnOffLabel = new Label("Toggle sound",skin);




        table.add(titleLabel).colspan(3);
        table.row().pad(10,0,0,10);
        table.add(volumeMusicLabel).left();
        table.add(volumeMusicSlider);
        table.add(musicVolume).minWidth(30).center();
        table.row().pad(10,0,0,10);
        table.add(musicOnOffLabel).left();
        table.add(musicCheckBox);
        table.row().pad(10,0,0,10);
        table.add(volumeSoundLabel).left();
        table.add(volumeSoundSlider);
        table.add(soundEffectsVolume).minWidth(30).center();
        table.row().pad(10,0,0,10);
        table.add(soundOnOffLabel).left();
        table.add(soundEffectsCheckbox);
        table.row().pad(10,0,0,10);
        table.add(backButton).fillX().uniformX().left();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        musicVolume.setText(String.valueOf(df.format(game.getPreferences().getMusicVolume())));
        soundEffectsVolume.setText(String.valueOf(df.format(game.getPreferences().getSoundVolume())));
        stage.draw();

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
        stage.dispose();

    }

}
