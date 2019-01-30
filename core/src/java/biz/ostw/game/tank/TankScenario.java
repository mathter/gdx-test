package biz.ostw.game.tank;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;

import biz.ostw.game.common.Scenario;

public class TankScenario extends Scenario {

    private static final float SPEED = 0.2f;

    public float getSpeed(){

        return SPEED;
    }

    @Override
    public Vector2 getWorldSize() {
        return null;
    }

    @Override
    public InputProcessor getInputProcessor() {
        return new Sim;
    }

    @Override
    public ContactListener getContactListener() {
        return null;
    }

    @Override
    public void step(float delta) {
    }

    @Override
    public void pause() {
    }

    private Tank getPlayerTank() {

    }
}
