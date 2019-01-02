package biz.ostw.game.tank.obj;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

public abstract class ObjectFactory implements Disposable {

    private static final Map<Object, ObjectFactory> MAP = load();

    public static final <T> T get(World world, Object type) {

        ObjectFactory objectFactory = MAP.get(type);

        if (objectFactory != null) {
            return objectFactory.<T>build(world, type);
        } else {
            throw new NoSuchElementException("There is not ObjectFactory for type '" + type + "'!");
        }
    }

    public abstract Object[] supportedKeys();

    public abstract <T> T build(World world, Object type, Object... params);

    private static Map<Object, ObjectFactory> load() {

        final Map<Object, ObjectFactory> map = new HashMap<>();
        ServiceLoader<ObjectFactory> serviceLoader = ServiceLoader.load(ObjectFactory.class);

        for (ObjectFactory objectFactory : serviceLoader) {

            for (Object e : objectFactory.supportedKeys()) {
                map.put(e, objectFactory);
            }
        }

        return map;
    }
}
