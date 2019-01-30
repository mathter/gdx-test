package biz.ostw.game.tank;

import com.badlogic.gdx.physics.box2d.World;

import biz.ostw.game.common.Context;

public class ContextImpl extends Context {

    final World world;

    final Screen screen;

    ContextImpl(World world, Screen screen) {
        this.world = world;
        this.screen = screen;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public void dispose() {

    }
}
