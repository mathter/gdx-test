package biz.ostw.game.common;

import com.badlogic.gdx.physics.box2d.World;

public abstract class Context {

    private static Context MAIN;

    public static Context getContext() {
        return MAIN;
    }

    public static void setContext(Context MAIN) {
        Context.MAIN = MAIN;
    }

    public abstract World getWorld();

    public abstract void dispose();
}
