package com.mygdx.game.bodies;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.bullet.softbody.btSoftBody;


public class BodyFactory {
    public static final int STEEL = 0;
    public static final int WOOD = 1;
    public static final int RUBBER = 2;
    public static final int STONE = 3;
    private final float DEGTORAD = 0.0174533f;
    public static BodyFactory thisInstance;
    private World world;

    private BodyFactory(World world){
        this.world=world;
    }

    //this method makes this class a singleton class
    public static BodyFactory getInstance(World world){
        if(thisInstance == null){
            thisInstance = new BodyFactory(world);
        }
        return thisInstance;
    }

    public static FixtureDef makeFixture(int material, Shape shape){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        switch(material){
            case 0:
                fixtureDef.density = 1f;
                fixtureDef.friction =0.3f;
                fixtureDef.restitution=0.1f;
                break;
            case 1:
                fixtureDef.density = 0.5f;
                fixtureDef.friction =0.7f;
                fixtureDef.restitution=0.3f;
                break;
            case 2:
                fixtureDef.density = 1f;
                fixtureDef.friction =0f;
                fixtureDef.restitution=0.9f;
                break;
            case 3:
                fixtureDef.density = 1f;
                fixtureDef.friction =0.9f;
                fixtureDef.restitution=0.01f;
                break;
            default:
                fixtureDef.density = 7f;
                fixtureDef.friction =0.5f;
                fixtureDef.restitution=0.3f;
                break;

        }
        return fixtureDef;
    }
    public Body makeCirclePolyBody(float posx, float posy, float radius, int material, BodyDef.BodyType bodyType, boolean fixedRotation){
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        Body boxBody = world.createBody(boxBodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius/2);
        boxBody.createFixture(makeFixture(material,circleShape));
        circleShape.dispose();
        return boxBody;

    }
    //overloaded
    public Body makeCirclePolyBody(float posx, float posy, float radius, int material ){
        return makeCirclePolyBody( posx,  posy,  radius,  material, BodyDef.BodyType.DynamicBody,  false);
    }


    public Body makeBoxPolyBody(float posx, float posy, float width, float height,int material, BodyDef.BodyType bodyType, boolean fixedRotation){
        // create a definition
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        //create the body to attach said definition
        Body boxBody = world.createBody(boxBodyDef);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2, height/2);
        boxBody.createFixture(makeFixture(material,poly));
        poly.dispose();

        return boxBody;
    }
    //overloaded
    public Body makeBoxPolyBody(float posx, float posy, float width, float height,int material, BodyDef.BodyType bodyType){
        return makeBoxPolyBody(posx, posy, width, height, material, bodyType, false);
    }

    public Body makePolygonShapeBody(Vector2[] vertices, float posx, float posy, int material, BodyDef.BodyType bodyType){
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type= bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        Body boxBody = world.createBody(boxBodyDef);

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices);
        boxBody.createFixture(makeFixture(material,polygon));
        polygon.dispose();

        return boxBody;
    }

    public void  makeConeSensor(Body body, float size){
        FixtureDef fixtureDef = new FixtureDef();
        //fixtureDef.isSensor = true; //later

        PolygonShape polygon = new PolygonShape();

        float radius = size;
        Vector2[] vertices = new Vector2[5];
        vertices[0] = new Vector2(0,0);
        for (int i =2; i<6;i++){
            float angle = (float) (i / 6.0 * 145 * DEGTORAD); //degrees to radians conversion
            vertices[i-1] = new Vector2(radius*((float)Math.cos(angle)), radius * ((float)Math.sin(angle)));
        }
        polygon.set(vertices);
        fixtureDef.shape = polygon;
        body.createFixture(fixtureDef);
        polygon.dispose();
    }
}