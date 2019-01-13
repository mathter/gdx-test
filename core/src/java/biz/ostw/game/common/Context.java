package biz.ostw.game.common;

import com.badlogic.gdx.physics.box2d.World;

import biz.ostw.game.tank.Screen;

public abstract class Context {

    private static Context MAIN;

    public static Context getMAIN() {
        return MAIN;
    }

    public static void setMAIN(Context MAIN) {
        Context.MAIN = MAIN;
    }

    public abstract World getWorld();

    public abstract Screen getScreen();
}
