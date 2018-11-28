package biz.ostw.game.tank.obj;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TankObjectFactory extends ObjectFactory {

    @Override
    public Object[] supportedKeys() {
        return Tank.values();
    }

    @Override
    public <T extends Enum<T>> Body build(World world, Enum<T> type) {

        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(def);

        return null;
    }
}
