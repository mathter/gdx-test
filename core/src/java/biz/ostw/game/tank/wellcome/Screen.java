package biz.ostw.game.tank.wellcome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import biz.ostw.game.tank.SideOfLight;
import biz.ostw.game.tank.obj.Drawable;
import biz.ostw.game.tank.obj.LandscapeType;
import biz.ostw.game.tank.obj.ObjectFactory;
import biz.ostw.game.tank.obj.Spatial;
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
        this.box2DDebugRenderer.setDrawBodies(true);
        this.box2DDebugRenderer.setDrawVelocities(true);
        this.box2DDebugRenderer.setDrawAABBs(true);
        this.box2DDebugRenderer.setDrawContacts(true);
        this.box2DDebugRenderer.setDrawJoints(true);
    }

    @Override
    public void show() {

        this.camera = new OrthographicCamera(0.05f * (width / height), 14);
        camera.setToOrtho(false);
        this.viewport = new ExtendViewport(6500, 6500, this.camera);
        this.viewport.update((int) width, (int) height);
        this.viewport.apply(true);
        this.batch = new SpriteBatch();

        this.world = new World(new Vector2(0, 0), false);


        Gdx.input.setInputProcessor(new GestureDetector(this));

        this.tank = ObjectFactory.get(world, TankType.SELF);
        this.tank.setSideOfLight(SideOfLight.NORTH);
        this.tank.setPosition(new Vector2(0, 0));

        Spatial element = ObjectFactory.get(this.world, LandscapeType.FOREST);
        element.setPosition(new Vector2(5, 5));

        element = ObjectFactory.get(this.world, LandscapeType.FOREST);
        element.setPosition(new Vector2(5.5f, 5));

        element = ObjectFactory.get(this.world, LandscapeType.BLOCK);
        element.setPosition(new Vector2(6f, 5));
        element = ObjectFactory.get(this.world, LandscapeType.BLOCK);
        element.setPosition(new Vector2(6.5f, 5));
        element = ObjectFactory.get(this.world, LandscapeType.BLOCK);
        element.setPosition(new Vector2(6f, 5.5f));
        element = ObjectFactory.get(this.world, LandscapeType.BLOCK);
        element.setPosition(new Vector2(6.5f, 5.5f));


        this.world.setContactListener(new ContactController());

        sr = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {

        this.camera.update();
        world.step(100, 6, 2);
        Gdx.gl.glClearColor(0.2f, 0.2f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

//        world.step(1/300, 6, 2);

        this.sr.begin(ShapeRenderer.ShapeType.Line);
        this.sr.setProjectionMatrix(this.camera.combined);
        this.sr.line(0, 0, 5000, 5000);
        this.sr.end();


        Array<Body> bodies = new Array<>();
        this.world.getBodies(bodies);


        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        for (Body body : bodies) {
            Object o = body.getUserData();

            if (o instanceof Drawable) {
                ((Drawable) o).draw(this.batch, 1);
            }
        }

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
        this.tank.setSpeed(0);
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
