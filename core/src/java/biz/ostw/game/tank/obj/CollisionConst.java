package biz.ostw.game.tank.obj;


import org.apache.commons.lang3.tuple.Pair;

public final class CollisionConst {

    public static final short CATEGORY_TANK = 0x0001;

    public static final short CATEGORY_LANDSCAPE_STRICT = 0x0002;

    public static final short CATEGORY_LANDSCAPE_NONE_STRICT = 0x0004;

    public static <T> Pair<GameObject<T>, ? extends GameObject<?>> getIfOneIs(Class<T> typeClass, GameObject<?> left, GameObject<?> right) {
        Object leftType = left.getType();
        Object rightType = right.getType();

        boolean leftTypeIsInstanceOf = typeClass.isInstance(leftType);
        boolean rightTypeIsInstanceOf = typeClass.isInstance(rightType);

        if (leftTypeIsInstanceOf && rightTypeIsInstanceOf) {
            return null;
        } else {
            if (leftTypeIsInstanceOf) {
                return Pair.of((GameObject<T>) left, right);
            } else {
                return Pair.of((GameObject<T>) right, left);
            }
        }
    }

    public static <T> Pair<GameObject<T>, ? extends GameObject<?>> getIfOneIs(T type, GameObject<?> left, GameObject<?> right) {
        Object leftType = left.getType();
        Object rightType = right.getType();

        boolean leftTypeIsInstanceOf = type.equals(leftType);
        boolean rightTypeIsInstanceOf = type.equals(rightType);

        if (leftTypeIsInstanceOf && rightTypeIsInstanceOf) {
            return null;
        } else {
            if (leftTypeIsInstanceOf) {
                return Pair.of((GameObject<T>) left, right);
            } else {
                return Pair.of((GameObject<T>) right, left);
            }
        }
    }

    private CollisionConst() {
    }
}
