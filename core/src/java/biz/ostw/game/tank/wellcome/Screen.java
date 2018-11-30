package biz.ostw.game.tank.wellcome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import biz.ostw.game.tank.obj.ObjectFactory;
import biz.ostw.game.tank.obj.Tank;
import biz.ostw.game.tank.obj.TankType;

public class Screen extends ScreenAdapter {

    private OrthographicCamera camera;

    private Viewport viewport;

    private float width = Gdx.graphics.getWidth();

    private float height = Gdx.graphics.getHeight();

    private Tank tank;

    private Stage stage;

    @Override
    public void show() {

        this.camera = new OrthographicCamera();
        this.viewport = new ExtendViewport(1, 1024, this.camera);
        this.stage = new Stage(this.viewport);

        World world = new World(new Vector2(0, 0), true);

        this.tank = ObjectFactory.get(world, TankType.SELF);

        this.stage.addActor(this.tank);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        this.stage.draw();
    }

    @Override
    public void dispose() {
    }
}
