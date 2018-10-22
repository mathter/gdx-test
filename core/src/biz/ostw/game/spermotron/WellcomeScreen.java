package biz.ostw.game.spermotron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

import biz.ostw.libgdx.DrawUtils;
import biz.ostw.libgdx.util.FontBuilder;

public class WellcomeScreen extends ScreenAdapter {

    private OrthographicCamera camera;

    private BitmapFont font;

    private TextureAtlas atlas;

    private Skin skin;

    private Stage stage;

    private Viewport viewport;

    private Texture texture;

    private float width = Gdx.graphics.getWidth();

    private float height = Gdx.graphics.getHeight();

    @Override
    public void show() {

        this.atlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));
        this.skin = new Skin(this.atlas);
        this.skin.load(Gdx.files.internal("pack.skin"));
        this.font = this.skin.getFont("font");

        this.camera = new OrthographicCamera();
        this.viewport = new ExtendViewport(1, 1024, this.camera);
        this.stage = new Stage(viewport);

        this.stage = new Stage(this.viewport);
        this.stage.addActor(new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(skin.getRegion("wellcome/pregnant"), this.getStage().getWidth() * 2 / 5, 0);

                GlyphLayout gl = new GlyphLayout();

                gl.setText(WellcomeScreen.this.font, "S P E R M O T R O N !");

                WellcomeScreen.this.font.draw(batch, gl, (stage.getWidth() - gl.width) / 2, (stage.getHeight() - gl.height) / 2);
            }
        });
        this.stage.addActor(this.buildTextButton("GO!", 0, 0));

        Gdx.input.setInputProcessor(this.stage);

        BitmapFont[] fonts = SkinFactory.bitmapFonts(Locale.CANADA);

        System.out.println(fonts);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        this.stage.draw();
    }

    private TextButton buildTextButton(String text, final int x, final int y) {

        return new TextButton(text, skin, "default");
    }

    @Override
    public void dispose() {
        this.skin.dispose();
    }
}
