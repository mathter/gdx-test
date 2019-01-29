package biz.ostw.game.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import biz.ostw.game.tank.obj.Drawable;
import biz.ostw.libgdx.DrawUtils;

public class ScreenProcessor extends ScreenAdapter {

    private OrthographicCamera camera;

    private Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    private Viewport viewport;

    private float width = Gdx.graphics.getWidth();

    private float height = Gdx.graphics.getHeight();

    private SpriteBatch batch;

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
    public void render(float delta) {
        final Scenario scenario = Scenario.getScenario();
        final Context context = Context.getContext();
        final World world = context.getWorld();

        this.camera.update();
        Gdx.gl.glClearColor(0.2f, 0.2f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        scenario.step(delta);

        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        this.renderWorldBodies(world);

        this.batch.end();

        this.box2DDebugRenderer.render(world, this.camera.combined.cpy().scale(DrawUtils.PPM, DrawUtils.PPM, 1));
    }

    private void renderFrame() {
        this.sr.begin(ShapeRenderer.ShapeType.Line);
        this.sr.setProjectionMatrix(this.camera.combined);
        this.sr.line(0, 0, 5000, 5000);
        this.sr.end();
    }

    private void renderWorldBodies(World world) {

        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);
    }

    private void renderBodies(Iterable<Body> bodies) {

        for (Body body : bodies) {
            Object o = body.getUserData();

            if (o instanceof Drawable) {
                ((Drawable) o).draw(this.batch, 1);
            }
        }
    }

    @Override
    public void show() {
        this.camera = new OrthographicCamera(0.05f * (width / height), 14);
        this.camera.setToOrtho(false);

        this.viewport = new ExtendViewport(6500, 6500, this.camera);
        this.viewport.update((int) width, (int) height);
        this.viewport.apply(true);

        this.batch = new SpriteBatch();

        this.sr = new ShapeRenderer();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {

        Scenario.getScenario().pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {

        Context.getContext().dispose();
    }
}
