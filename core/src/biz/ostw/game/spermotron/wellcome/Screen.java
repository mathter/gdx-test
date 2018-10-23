package biz.ostw.game.spermotron.wellcome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import biz.ostw.game.skin.BaseSkinFactory;

public class Screen extends ScreenAdapter {

    private OrthographicCamera camera;

    private BitmapFont font;

    private TextureAtlas atlas;

    private Skin skin;

    private Stage stage;

    private Viewport viewport;

    private float width = Gdx.graphics.getWidth();

    private float height = Gdx.graphics.getHeight();

    @Override
    public void show() {

        this.skin = new WellcomeSkinFactory().get(null);
        this.font = this.skin.getFont("decor-font-max");

        this.camera = new OrthographicCamera();
        this.viewport = new ExtendViewport(1, 1024, this.camera);
        this.stage = new Stage(viewport);

        this.stage = new Stage(this.viewport);
        this.stage.addActor(new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {

                batch.draw(skin.getRegion("pregnant"), this.getStage().getWidth() * 2 / 5, 0);

                GlyphLayout gl = new GlyphLayout();

                gl.setText(Screen.this.font, "S P E R M O T R O N !");

                Screen.this.font.draw(batch, gl, (stage.getWidth() - gl.width) / 2, (stage.getHeight() - gl.height) / 2);
            }
        });

        this.stage.addActor(this.buildGoButton());

        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        this.stage.draw();
    }

    private TextButton buildGoButton() {

        TextButton button = new TextButton("Go!", skin, "default-decor-max");
        button.setPosition(this.stage.getWidth() / 2, 0, Align.center | Align.bottom);

        return button;
    }

    @Override
    public void dispose() {
        this.skin.dispose();
    }
}
