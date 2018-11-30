package biz.ostw.game.tank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class TextureFactory {

    private static final String MAIN_TEXTURE_FILE_PATH = "texture/pack.atlas";

    private static final TextureAtlas MAIN_ATLAS;

    public static AtlasRegion getRegion(String name) {

        return MAIN_ATLAS.findRegion(name);
    }

    public static Array<AtlasRegion> getRegions(String name) {

        return MAIN_ATLAS.findRegions(name);
    }

    static {

        MAIN_ATLAS = new TextureAtlas(MAIN_TEXTURE_FILE_PATH);

        Gdx.app.addLifecycleListener(new LifecycleListener() {
            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
                MAIN_ATLAS.dispose();
            }
        });
    }

    private TextureFactory() {
    }
}
