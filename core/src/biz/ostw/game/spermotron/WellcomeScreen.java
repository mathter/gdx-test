package biz.ostw.game.spermotron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import biz.ostw.libgdx.DrawUtils;
import biz.ostw.libgdx.util.FontBuilder;

public class WellcomeScreen extends ScreenAdapter {

    private SpriteBatch batch;

    private OrthographicCamera camera;

    private BitmapFont font;

    private Texture texture;

    private Stage stage;

    private float width = Gdx.graphics.getWidth();

    private float height = Gdx.graphics.getHeight();

    @Override
    public void show() {

        this.texture = new Texture(Gdx.files.internal("pregnant.png"));

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, this.width, this.height);
        this.camera.position.set(0, 0, 0);

        this.batch = new SpriteBatch();

        this.stage = new Stage(new ScreenViewport(), this.batch);
        this.stage.addActor(new Actor() {

            {
                this.setPosition(-100, -100);
                this.setBounds(0, 0, 1000, 1000);
            }

            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(texture, 0f, 0f);
            }

            @Override
            public boolean fire(Event event) {
                System.out.println(event);
                return super.fire(event);
            }
        });

        Gdx.input.setInputProcessor(this.stage);

        this.font = new FontBuilder(Gdx.files.internal("typesimp.ttf"))
                .size(150)
                .characters(FreeTypeFontGenerator.DEFAULT_CHARS)
                .borderColor(Color.DARK_GRAY)
                .borderWidth(3f)
                .shadowColor(Color.LIGHT_GRAY)
                .shadowOffsetX(10)
                .shadowOffsetY(10)
                .build();
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

        this.stage.act();
//        this.stage.draw();
    }

    @Override
    public void dispose() {

        this.batch.dispose();
        this.font.dispose();
    }


}
