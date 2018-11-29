package biz.ostw.game.tank.obj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tank extends Actor implements Drawable, Updatable {

    private final Body body;

    private final TextureRegion bodySprite;

    private final Animation<TextureRegion> trackAnimation;

    Tank(Body body, TextureRegion textureSprite, Animation<TextureRegion> trackAnimation) {

        this.body = body;
        this.bodySprite = textureSprite;
        this.trackAnimation = trackAnimation;
    }

    @Override
    public void update(float delta) {

        this.act(delta);
    }
}
