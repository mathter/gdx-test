package biz.ostw.game.tank.obj;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

public abstract class ObjectFactory {

    private static final Map<Object, ObjectFactory> MAP = load();

    public static final <T extends Enum<T>> Body get(World world, Enum<T> type) {

        ObjectFactory objectFactory = MAP.get(type);

        if (objectFactory != null) {
            return objectFactory.build(world, type);
        } else {
            throw new NoSuchElementException("There is not ObjectFactory for type '" + type + "'!");
        }
    }

    public abstract Object[] supportedKeys();

    public abstract <T extends Enum<T>> Body build(World world, Enum<T> type);

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
