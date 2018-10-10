package biz.ostw.game.spermotron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WellcomeScreen extends ScreenAdapter {

    private SpriteBatch batch;

    private Texture texture;

    private OrthographicCamera camera;

    @Override
    public void show() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, w, h);
        this.camera.position.set(0, 0, 0);

        this.batch = new SpriteBatch();
        this.texture = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f,0.2f,0f,1f);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        this.camera.rotate(0.2f);
        this.camera.update();

        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();
        this.batch.draw(this.texture, 0,0);
        this.batch.end();
    }

    @Override
    public void dispose() {

        this.batch.dispose();
        this.texture.dispose();
    }
}
