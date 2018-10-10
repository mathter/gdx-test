package biz.ostw.game.spermotron;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Spermotron extends Game {

    private OrthographicCamera camera;

    @Override
    public void create() {

        WellcomeScreen screen = new WellcomeScreen();
        this.setScreen(screen);
    }
}
