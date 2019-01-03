package biz.ostw.game.tank.wellcome;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import org.apache.commons.lang3.tuple.Pair;

import biz.ostw.game.tank.obj.CollisionConst;
import biz.ostw.game.tank.obj.GameObject;
import biz.ostw.game.tank.obj.Spatial;
import biz.ostw.game.tank.obj.Tank;
import biz.ostw.game.tank.obj.TankType;

public class ContactController implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        GameObject<?> goA = (GameObject<?>) contact.getFixtureA().getBody().getUserData();
        GameObject<?> goB = (GameObject<?>) contact.getFixtureB().getBody().getUserData();
        Pair<GameObject<TankType>, ? extends GameObject<?>> resolved = CollisionConst.getIfOneIs(TankType.class, goA, goB);

        if (resolved != null) {
            Vector2 tank = ((Spatial) resolved.getLeft()).getPosition();
            Vector2 landscape = ((Spatial) resolved.getRight()).getPosition();

            ((Tank) resolved.getLeft()).setSpeed(0);

            contact.setTangentSpeed(0);
            contact.setRestitution(0);

        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
