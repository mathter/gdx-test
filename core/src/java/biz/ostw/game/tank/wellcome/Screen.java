package biz.ostw.game.tank.wellcome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import biz.ostw.game.tank.SideOfLight;
import biz.ostw.game.tank.obj.ObjectFactory;
import biz.ostw.game.tank.obj.Tank;
import biz.ostw.game.tank.obj.TankType;
import biz.ostw.libgdx.DrawUtils;

public class Screen extends ScreenAdapter implements GestureDetector.GestureListener {

    private static final float SPEED = 0.2f;

    private OrthographicCamera camera;

    private Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    private Viewport viewport;

    private float width = Gdx.graphics.getWidth();

    private float height = Gdx.graphics.getHeight();

    private Tank tank;

    private SpriteBatch batch;

    private World world;

    private ShapeRenderer sr;

    {
        this.box2DDebugRenderer.setDrawInactiveBodies(true);
    }

    @Override
    public void show() {

        this.camera = new OrthographicCamera(0.05f * (width / height), 14);
        camera.setToOrtho(false);
        this.viewport = new ExtendViewport(8000, 8000, this.camera);
        this.viewport.update((int) width, (int) height);
        this.viewport.apply(true);
        this.batch = new SpriteBatch();

        this.world = new World(new Vector2(0, 0), false);
        World.setVelocityThreshold(0.005f);

        this.tank = ObjectFactory.get(world, TankType.SELF);
        this.tank.setSideOfLight(SideOfLight.NORTH);
        this.tank.setPosition(new Vector2(0,0));


        Gdx.input.setInputProcessor(new GestureDetector(this));

        sr = new ShapeRenderer();
        this.ramka();
    }

    private Body ramka() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;

        Body body = this.world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5000, 5000);
        Fixture fixture = body.createFixture(shape, 1f);
        shape.dispose();
        body.getTransform().setPosition(new Vector2(0, 0));

        return body;
    }

    @Override
    public void render(float delta) {

        this.camera.update();
        world.step(100, 60, 20);
        Gdx.gl.glClearColor(0.2f, 0.2f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

//        world.step(1/300, 6, 2);

        this.sr.begin(ShapeRenderer.ShapeType.Line);
        this.sr.setProjectionMatrix(this.camera.combined);
        this.sr.line(0, 0, 5000, 5000);
        this.sr.end();


        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        this.tank.draw(this.batch, 1);
        this.batch.end();

        this.box2DDebugRenderer.render(this.world, this.camera.combined.cpy().scale(DrawUtils.PPM, DrawUtils.PPM, 1));
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
