package biz.ostw.game.tank;

import com.badlogic.gdx.input.GestureDetector;

import biz.ostw.game.common.Scenario;
import biz.ostw.game.tank.obj.Spatial;

public class SimpleInputProcessor extends GestureDetector.GestureAdapter {

    private Spatial object;

    public SimpleInputProcessor(Spatial spatial) {

        this.object = spatial;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        this.object.setSpeed(0);
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                this.object.setSideOfLight(SideOfLight.EAST);
            } else {
                this.object.setSideOfLight(SideOfLight.WEST);
            }
        } else {
            if (velocityY > 0) {
                this.object.setSideOfLight(SideOfLight.SOUTH);
            } else {
                this.object.setSideOfLight(SideOfLight.NORTH);
            }
        }

        this.object.setSpeed(Scenario.<TankScenario>getScenario().getSpeed());

        return false;
    }
}
