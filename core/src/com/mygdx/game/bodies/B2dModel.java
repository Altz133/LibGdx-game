package com.mygdx.game.bodies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.controller.KeyboardController;

public class B2dModel {
    public World world;
    private Body bodyDynamic;
    private Body bodyStatic;
    private Body bodyKinetic;
    private Body player;
    private Body water;
    private KeyboardController controller;
    private OrthographicCamera camera;
    Vector3 mousePos = new Vector3();
    public boolean isSwimming = false;
    public B2dModel(KeyboardController cont, OrthographicCamera cam){
        controller = cont;
        camera = cam;
        world = new World(new Vector2(0,-10f),true);
        world.setContactListener(new B2dCollisionDetection(this));

       createFloor();
//        createObject();
//        createMovingObject();

        //Get out body factory singleton and store it in a bodyFactory
        BodyFactory bodyFactory = BodyFactory.getInstance(world);

        //add a new rubber ball at postion 1,1
//        bodyFactory.makeCirclePolyBody(1,1,2,BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody,false);

        //add a new steel bal at postion 4,1
//        bodyFactory.makeCirclePolyBody(4,1,2,BodyFactory.STEEL, BodyDef.BodyType.DynamicBody,false);

        //add a anew stona at position -4,1
//        bodyFactory.makeCirclePolyBody(-4,1,2,BodyFactory.STONE, BodyDef.BodyType.DynamicBody,false);
        //pentagon
//        Vector2[] vert = new Vector2[]{
//                new Vector2(1,5),
//                new Vector2(0,2),
//                new Vector2(2.5f,0),
//                new Vector2(5,2),
//        };
        //trapez
//        Vector2[] vert2 = new Vector2[]{
//                new Vector2(1,5),
//                new Vector2(2,2),
//                new Vector2(4,2),
//                new Vector2(5,5)
//        };

//        bodyFactory.makePolygonShapeBody(vert, -8,1,BodyFactory.STONE, BodyDef.BodyType.DynamicBody);
//        bodyFactory.makePolygonShapeBody(vert2, -8,1,BodyFactory.STONE, BodyDef.BodyType.DynamicBody);
        player = bodyFactory.makeBoxPolyBody(1,1,2,2,BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody,false);

        water = bodyFactory.makeBoxPolyBody(1,-8,40,8,BodyFactory.RUBBER, BodyDef.BodyType.StaticBody, false);
        water.setUserData("IAMTHESEA");

        bodyFactory.makeAllFixturesSensors(water);



    }
    public boolean pointIntersectsBody(Body body, Vector2 mouseLocation){
        mousePos.set(mouseLocation,0);
        camera.unproject(mousePos);
        if(body.getFixtureList().first().testPoint(mousePos.x,mousePos.y)){
            return true;
        }
        return false;
    }
    public void logicStep(float delta){

        if(controller.isMouse1Down && pointIntersectsBody(player,controller.mouseLocation)){
            System.out.println("Player was clicked");
        }

        if(controller.left){
            player.applyForceToCenter(-10,0,true);
        }else if(controller.right){
            player.applyForceToCenter(10,0,true);
        }else if(controller.up){
            player.applyForceToCenter(0,10,true);
        }else if(controller.down){
            player.applyForceToCenter(0,-10,true);
        }
        if(isSwimming){
            player.applyForceToCenter(0,50,true);
        }
        world.step(delta, 3,3);
    }
    //dynamic body
    private void createObject(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);

        bodyDynamic = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        bodyDynamic.createFixture(shape,0.0f);

        shape.dispose();

    }
    //static body
    private void createFloor(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,-10);

        bodyStatic = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50,1);
        bodyStatic.createFixture(shape,0.0f);

        shape.dispose();
    }
    //kinetic body
    private void createMovingObject(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);

        bodyKinetic = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        bodyKinetic.createFixture(shape, 0.0f);

        shape.dispose();
        bodyKinetic.setLinearVelocity(0,0.75f);

    }


}
