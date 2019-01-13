package biz.ostw.game.tank;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;

import biz.ostw.game.common.Scenario;

public class TankScenario implements Scenario {

    @Override
    public Vector2 getWorldSize() {
        return null;
    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }

    @Override
    public ContactListener getContactListener() {
        return null;
    }
}
