package biz.ostw.game.skin;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Locale;

interface SkinFactory {
    Skin get(Locale locale);
}
