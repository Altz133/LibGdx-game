package com.mygdx.game.bodies;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class B2dCollisionDetection implements ContactListener{

    private B2dModel parent;
    public B2dCollisionDetection(B2dModel parent){
        this.parent = parent;

    }
    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+fb.getBody().getType());

        if(fa.getBody().getUserData() == "IAMTHESEA"){
            parent.isSwimming = true;

        }else if(fb.getBody().getUserData() == "IAMTHESEA"){
            parent.isSwimming = true;

        }
        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.shootUpInAir(fa,fb);
        }else if(fb.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.shootUpInAir(fb,fa);
        }

    }
    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture){
        System.out.println("Adding force");
        otherFixture.getBody().applyForceToCenter(new Vector2(-10,1000),true);
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("End of Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        if(fa.getBody().getUserData() =="IAMTHESEA"){
            parent.isSwimming = false;
            return;
        }else if(fb.getBody().getUserData() =="IAMTHESEA"){
            parent.isSwimming = false;
            return;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
