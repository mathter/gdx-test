package biz.ostw.game.tank;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Tank extends Game {

    private OrthographicCamera camera;

    @Override
    public void create() {

        Screen screen = new Screen();
        this.setScreen(screen);
    }
}
