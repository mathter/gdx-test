package biz.ostw.game.spermotron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.List;
import java.util.Locale;

public class SkinFactory {

    public List<BitmapFont> bitmapFonts(Locale locale) {

        String selector = locale != null ? locale.getCountry() : "default";

        return null;
    }
}
