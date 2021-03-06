package biz.ostw.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DrawUtils {

    public static void drawInscribed(Batch batch, Texture texture, float x, float y, float width, float height) {

        final float textureWidth = texture.getWidth();
        final float textureHeight = texture.getHeight();

        final float widthRatio = textureWidth / width;
        final float heightRatio = textureHeight / height;
        final float ratio;

        if (widthRatio < heightRatio) {
            ratio = heightRatio;
        } else {
            ratio = widthRatio;
        }

        width = textureWidth / ratio;
        height = textureHeight / ratio;

        batch.draw(texture, x, y, width, height);
    }

    private DrawUtils() {
    }
}
