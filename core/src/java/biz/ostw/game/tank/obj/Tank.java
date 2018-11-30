package biz.ostw.game.tank.obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tank extends Actor implements Drawable, Updatable {

    private final Body body;

    private final Sprite bodySprite;

    private final Animation<TextureRegion> trackAnimation;

    private float time = 0;

    Tank(Body body, TextureRegion textureSprite, Animation<TextureRegion> trackAnimation) {

        this.body = body;
        this.bodySprite = new Sprite(textureSprite);

        this.trackAnimation = trackAnimation;

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(250f, 250f);
        Fixture fixture = body.createFixture(shape, 1f);

        shape.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

//        batch.draw(this.bodySprite, this.getX(), this.getY());
        this.bodySprite.draw(batch);


        this.time += Gdx.graphics.getDeltaTime();
        TextureRegion frame = this.trackAnimation.getKeyFrame(this.time);

        batch.draw(frame, 0, 0);
        batch.draw(frame, 390, 0);
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void update(float delta) {

        this.act(delta);
    }
}
