package biz.ostw.game.tank.wellcome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import biz.ostw.game.tank.SideOfLight;
import biz.ostw.game.tank.obj.ObjectFactory;
import biz.ostw.game.tank.obj.Tank;
import biz.ostw.game.tank.obj.TankType;

public class Screen extends ScreenAdapter implements GestureDetector.GestureListener {

    private static final float SPEED = 400;

    private OrthographicCamera camera;

    private Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    private Viewport viewport;

    private float width = Gdx.graphics.getWidth();

    private float height = Gdx.graphics.getHeight();

    private Tank tank;

    private World world;

    @Override
    public void show() {

        this.camera = new OrthographicCamera();
        this.viewport = new ExtendViewport(1, 5000, this.camera);

        this.world = new World(new Vector2(0, 0), true);

        this.tank = ObjectFactory.get(world, TankType.SELF);
        this.tank.setSideOfLight(SideOfLight.SOUTH);
        this.tank.setPosition(new Vector2(0, 0));

        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        this.box2DDebugRenderer.render(this.world, this.camera.combined);
    }

    @Override
    public void dispose() {
        this.world.dispose();
        this.box2DDebugRenderer.dispose();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        tank.setSpeed(0);
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                tank.setSideOfLight(SideOfLight.EAST);
            } else {
                tank.setSideOfLight(SideOfLight.WEST);
            }
        } else {
            if (velocityY > 0) {
                tank.setSideOfLight(SideOfLight.SOUTH);
            } else {
                tank.setSideOfLight(SideOfLight.NORTH);
            }
        }

        tank.setSpeed(SPEED);

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
