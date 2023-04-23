package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGame;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class LoadingScreen implements Screen {
    private MyGame game;
    private TextureAtlas atlas;
    private AtlasRegion title;
    private AtlasRegion dash;
    private SpriteBatch sb;

    public final int IMAGE = 0;		// loading images
    public final int FONT = 1;		// loading fonts
    public final int PARTY = 2;		// loading particle effects
    public final int SOUND = 3;		// loading sounds
    public final int MUSIC = 4;
    private TextureRegion currentFrame;
    private int currentLoadingStage=0;
    public float countDown = 5f;
    public float stateTime;
    Animation flameAnimation;

    public LoadingScreen(MyGame myGame) {
        this.game = myGame;
        sb = new SpriteBatch();
        sb.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE);
    }

    public void show(){
        stateTime = 0f;

        game.assMan.queueAddLoadingImages();
        game.assMan.manager.finishLoading();

        atlas = game.assMan.manager.get("images/loading.atlas");
        title = atlas.findRegion("staying-alight-logo");
        dash = atlas.findRegion("loading-dash");

        flameAnimation = new Animation(0.07f, atlas.findRegions("flames"),Animation.PlayMode.LOOP);


        game.assMan.queueAddImages();
        System.out.println("Loading images...");
    }

    @Override
    public void render(float delta) {
        stateTime += delta;
        currentFrame = (TextureRegion) flameAnimation.getKeyFrame(stateTime, true);
        Gdx.gl.glClearColor(0,0,0,1); //  clear the screen
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        drawLoadingBar(currentLoadingStage * 2, currentFrame);
        sb.draw(title, 135, 250);
        sb.end();

        if(game.assMan.manager.update()){
            currentLoadingStage+=1;
            switch (currentLoadingStage){
                case FONT:
                    System.out.println("Loading fonts...");
                    game.assMan.queueAddFonts();
                    break;
                case PARTY:
                    System.out.println("Loading Particle Effects....");
                    game.assMan.queueAddParticleEffects(); // fonts are done now do party effects
                    break;
                case SOUND:
                    System.out.println("Loading Sounds....");
                   	game.assMan.queueAddSounds();
                   	break;
                case MUSIC:
                    System.out.println("Loading fonts....");
                    game.assMan.queueAddMusic();
                    break;
                case 5:
                    System.out.println("Finished"); // all done
                    break;
            }
            if(currentLoadingStage >5){
                countDown-=delta;
                currentLoadingStage = 5;
                if(countDown <0){
                    game.changeScreen(MyGame.MENU);
                }
            }

        }


    }
    private void drawLoadingBar(int stage, TextureRegion currentFrame){
        for(int i = 0; i < stage;i++){
            sb.draw(currentFrame, 50 + (i * 50), 150, 50, 50);
            sb.draw(dash, 35 + (i * 50), 140, 80, 80);
        }
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
