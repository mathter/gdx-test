package biz.ostw.game.tank.obj;

public class AbstractGameObject<T> implements GameObject {

    private final T type;

    protected AbstractGameObject(T type) {
        this.type = type;
    }

    @Override
    public Object getType() {
        return type;
    }
}
