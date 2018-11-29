package biz.ostw.game.tank.obj;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class TankObjectFactory extends ObjectFactory {

    @Override
    public Object[] supportedKeys() {
        return TankType.values();
    }

    @Override
    public <T> T build(World world, Object type) {

        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(def);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(250f, 250f);
        Fixture fixture = body.createFixture(shape, 1f);

        shape.dispose();

        return null;
    }
}
