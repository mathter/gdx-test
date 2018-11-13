package biz.ostw.game.tank;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

import biz.ostw.game.tank.wellcome.Screen;

public class Tank extends Game {

    private OrthographicCamera camera;

    @Override
    public void create() {

        Screen screen = new Screen();
        this.setScreen(screen);
    }
}
