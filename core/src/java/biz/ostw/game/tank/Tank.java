package biz.ostw.game.tank;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;

import biz.ostw.game.tank.obj.ObjectFactory;
import biz.ostw.game.tank.obj.TankType;
import biz.ostw.game.tank.wellcome.Screen;

public class Tank extends Game {

    private OrthographicCamera camera;

    @Override
    public void create() {

        Screen screen = new Screen();
        this.setScreen(screen);
    }
}
