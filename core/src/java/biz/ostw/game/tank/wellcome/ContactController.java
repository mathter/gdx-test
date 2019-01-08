package biz.ostw.game.tank.wellcome;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import org.apache.commons.lang3.tuple.Pair;

import biz.ostw.game.tank.SideOfLight;
import biz.ostw.game.tank.obj.CollisionConst;
import biz.ostw.game.tank.obj.GameObject;
import biz.ostw.game.tank.obj.Spatial;
import biz.ostw.game.tank.obj.TankType;

public class ContactController implements ContactListener {

    private Spatial tank;

    @Override
    public void beginContact(Contact contact) {
        System.out.println(contact);

        GameObject<?> goA = (GameObject<?>) contact.getFixtureA().getBody().getUserData();
        GameObject<?> goB = (GameObject<?>) contact.getFixtureB().getBody().getUserData();
        Pair<GameObject<TankType>, ? extends GameObject<?>> resolved = CollisionConst.getIfOneIs(TankType.class, goA, goB);

        if (resolved != null) {
            tank = (Spatial) resolved.getLeft();
            Vector2 tankPosition = ((Spatial) resolved.getLeft()).getPosition();

            final Spatial landscape = (Spatial) resolved.getRight();
            Vector2 landscapePosition = ((Spatial) resolved.getRight()).getPosition();

            contact.setTangentSpeed(0);
            contact.setRestitution(0);
            tank.setSpeed(0);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setPosition(tank, landscape);
                }
            }).start();
        }
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("End");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        System.out.println("preSolve");
    }

    private void setPosition(Spatial subjected, Spatial base) {

        switch (subjected.getSideOfLight()) {
            case NORTH:
                subjected.setPosition(new Vector2(subjected.getPosition().x, base.getPosition().y - base.getHalfSize().y - subjected.getHalfSize().y));
                break;

            case EAST:
                subjected.setPosition(new Vector2(base.getPosition().x - base.getHalfSize().x - subjected.getHalfSize().x, subjected.getPosition().y));
                break;

            case SOUTH:
                subjected.setPosition(new Vector2(subjected.getPosition().x, base.getPosition().y + base.getHalfSize().y + subjected.getHalfSize().y));
                break;

            case WEST:
                subjected.setPosition(new Vector2(base.getPosition().x + base.getHalfSize().x + subjected.getHalfSize().x, subjected.getPosition().y));
                break;
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        System.out.println("postSolve");
    }
}
