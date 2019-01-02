package biz.ostw.game.tank.obj;

import com.badlogic.gdx.math.Vector2;

import biz.ostw.game.tank.SideOfLight;

public interface Spatial {

    public void setSideOfLight(SideOfLight sideOfLight);

    public SideOfLight getSideOfLight();

    public void setPosition(Vector2 position);

    public Vector2 getPosition();
}
