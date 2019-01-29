package biz.ostw.game.common;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;

public abstract class Scenario {

    private static Scenario MAIN;

    public static final <T extends Scenario> T getScenario() {
        return (T) MAIN;
    }

    public static final void setScenario(Scenario scenario) {
        MAIN = scenario;
    }

    public abstract Vector2 getWorldSize();

    public abstract InputProcessor getInputProcessor();

    public abstract ContactListener getContactListener();

    public abstract void step(float delta);

    public abstract void pause();
}
