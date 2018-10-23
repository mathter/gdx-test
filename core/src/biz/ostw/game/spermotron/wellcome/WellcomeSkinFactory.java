package biz.ostw.game.spermotron.wellcome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Locale;

import biz.ostw.game.skin.BaseSkinFactory;

public class WellcomeSkinFactory extends BaseSkinFactory {

    @Override
    public Skin get(Locale locale) {

        final Skin skin = super.get(locale);
        final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("scenes/wellcome/pack.atlas"));

        skin.addRegions(atlas);

        return skin;
    }
}
