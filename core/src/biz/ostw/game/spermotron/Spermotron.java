package biz.ostw.game.spermotron;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

import biz.ostw.game.spermotron.wellcome.Screen;

public class Spermotron extends Game {

    private OrthographicCamera camera;

    @Override
    public void create() {

        Screen screen = new Screen();
        this.setScreen(screen);
    }
}
