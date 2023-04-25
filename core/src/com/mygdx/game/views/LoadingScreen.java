package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
public class LoadingScreen implements Screen {

    private AtlasRegion image;
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
    public static TextureRegion currentFrame;
    private int currentLoadingStage=0;
    public float countDown = 5f;
    public static float stateTime;
    Animation flameAnimation;
    private Image titleImage;
    private Table table;
    private Table loadingTable;
    private Stage stage = new Stage();
    private Image backgroundImage;
    private TextureRegion copyright;
    private Image copyrightImage;
    private TextureRegion background;

    public LoadingScreen(MyGame myGame) {
        this.game = myGame;
        sb = new SpriteBatch();
        stateTime = 0f;

        sb.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE);
    }

    public void show(){
        loadAssets();
        game.assMan.queueAddImages();

        titleImage = new Image(title);
        copyrightImage = new Image(copyright);
        backgroundImage = new Image(background);
        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);

        loadingTable=new Table();
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));
        loadingTable.add(new LoadingBarPart(dash,flameAnimation));

        table.add(titleImage).align(Align.center).pad(10,0,0,0).colspan(10);
        table.row();
        table.add(loadingTable).width(400);
        table.row();
        table.add(copyrightImage).align(Align.center).pad(200,0,0,0).colspan(10);
        table.row();
        table.setBackground(new TiledDrawable(background));
        stage.addActor(table);

        System.out.println("Loading images...");
    }

    @Override
    public void render(float delta) {
        stateTime += delta;
        Gdx.gl.glClearColor(0,0,0,1); //  clear the screen
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (game.assMan.manager.update()) {
            currentLoadingStage+= 1;
            if(currentLoadingStage <= 5){
                loadingTable.getCells().get((currentLoadingStage-1)*2).getActor().setVisible(true);
                loadingTable.getCells().get((currentLoadingStage-1)*2+1).getActor().setVisible(true);
            }
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
        stage.act();
        stage.draw();


    }
    private void drawLoadingBar(int stage, TextureRegion currentFrame){
        for(int i = 0; i < stage;i++){
            sb.draw(currentFrame, 50 + (i * 50), 150, 50, 50);
            sb.draw(dash, 35 + (i * 50), 140, 40, 40);
        }
    }
    private void loadAssets() {
        // load loading images and wait until finished
        game.assMan.queueAddLoadingImages();
        game.assMan.manager.finishLoading();

        // get images used to display loading progress
        atlas = game.assMan.manager.get("images/loading.atlas");
        title = atlas.findRegion("staying-alight-logo");
        dash = atlas.findRegion("loading-dash");
        background = atlas.findRegion("flamebackground");
        copyright = atlas.findRegion("copyright");
        flameAnimation = new Animation(0.07f, atlas.findRegions("flames"), Animation.PlayMode.LOOP);
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
     class LoadingBarPart extends Actor{


        public LoadingBarPart(AtlasRegion ar, Animation an) {
            super();
            image = ar;
            flameAnimation = an;
            this.setWidth(30);
            this.setHeight(25);
            this.setVisible(false);
        }
         @Override
         public void draw(Batch batch, float parentAlpha) {
             super.draw(batch,parentAlpha);
             batch.draw(image, getX(),getY(),30,30);
             batch.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE);
             batch.draw(currentFrame, getX()-5,getY(),40,40);
             batch.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
         }
         @Override
         public void act(float delta){
             super.act(delta);
             stateTime+=delta;
             currentFrame =(TextureRegion) flameAnimation.getKeyFrame(stateTime,true);
         }


     }
}
