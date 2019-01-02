package biz.ostw.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DrawUtils {

    public static final float PPM = 500f;

    public static final float MPP = 1 / PPM;

    public static Vector2 box2d2screen2d(final Vector2 box2dcoordinate, float offset) {
        return box2d2screen2d(box2dcoordinate, offset, offset);
    }

    public static Vector2 box2d2screen2d(final Vector2 box2dcoordinate, float offsetX, float offsetY) {
        Vector2 p = box2dcoordinate.cpy();
        p.x = p.x * DrawUtils.PPM;
        p.y = p.y * DrawUtils.PPM;
        p.add(offsetX, offsetY);

        return p;
    }

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
