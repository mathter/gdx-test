package biz.ostw.game.spermotron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import javax.xml.soap.Text;

import biz.ostw.libgdx.DrawUtils;

public class WellcomeScreen extends ScreenAdapter {

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private BitmapFont font;

    private Texture texture;

    private float width = Gdx.graphics.getWidth();

    private float height = Gdx.graphics.getHeight();

    @Override
    public void show() {

        this.texture = new Texture(Gdx.files.internal("pregnant.png"));

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, this.width, this.height);
        this.camera.position.set(0, 0, 0);

        this.batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("typesimp.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;

        this.font = generator.generateFont(parameter);

        generator.dispose();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        this.camera.update();

        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        DrawUtils.drawInscribed(this.batch, this.texture, -this.width / 5, -this.height / 2, this.width, this.height);

        GlyphLayout gl = new GlyphLayout();

        gl.setText(this.font, "S P E R M O T R O N !");

        this.font.draw(this.batch, gl, -gl.width / 2, gl.height / 2);

        this.batch.end();
    }

    @Override
    public void dispose() {

        this.batch.dispose();
        this.font.dispose();
    }


}
