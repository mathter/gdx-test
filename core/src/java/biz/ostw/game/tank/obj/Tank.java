package biz.ostw.game.tank.obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Transform;

import biz.ostw.game.tank.SideOfLight;

public class Tank implements Spatial, Drawable, Updatable {

    private final Body body;

    private final Sprite bodySprite;

    private final Animation<TextureRegion> trackAnimation;

    private float time = 0;

    private float speed = 0;

    private SideOfLight sideOfLight = SideOfLight.NORTH;

    Tank(Body body, TextureRegion textureSprite, Animation<TextureRegion> trackAnimation) {

        this.body = body;
        this.bodySprite = new Sprite(textureSprite);

        this.trackAnimation = trackAnimation;

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(250f, 250f);
        Fixture fixture = body.createFixture(shape, 1f);

//        this.body.setTransform(0, 0, 10);
        shape.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        Vector2 p = this.body.getPosition();
        this.bodySprite.draw(batch);


        if (this.speed != 0) {
            this.time += Gdx.graphics.getDeltaTime();
        }

        TextureRegion frame = this.trackAnimation.getKeyFrame(this.time);
        Transform transform = this.body.getTransform();


        Vector2 v = transform.getPosition();

        batch.draw(frame,
                v.x,
                v.y,
                this.bodySprite.getWidth() / 2,
                this.bodySprite.getHeight() / 2,
                frame.getRegionWidth(),
                frame.getRegionHeight(),
                1,
                1,
                transform.getRotation());

        batch.draw(frame,
                v.x + this.bodySprite.getWidth() - 110,
                v.y,
                -(this.bodySprite.getWidth()) / 2 + 110,
                this.bodySprite.getHeight() / 2,
                frame.getRegionWidth(),
                frame.getRegionHeight(),
                1,
                1,
                transform.getRotation());
    }

    @Override
    public void setSideOfLight(SideOfLight sideOfLight) {
        this.sideOfLight = sideOfLight;

        final Transform transform = this.body.getTransform();

        switch (sideOfLight) {
            case NORTH:

                this.body.setTransform(transform.getPosition(), 0);
                break;

            case EAST:
                this.body.setTransform(transform.getPosition(), MathUtils.PI / 2);
                break;

            case SOUTH:
                this.body.setTransform(transform.getPosition(), MathUtils.PI);
                break;

            case WEST:
                this.body.setTransform(transform.getPosition(), -MathUtils.PI * 2);
                break;
        }
    }

    @Override
    public SideOfLight getSideOfLight() {
        return this.sideOfLight;
    }

    @Override
    public void setPosition(Vector2 position) {
        final Transform transform = this.body.getTransform();

        this.body.setTransform(position, transform.getRotation());
    }

    @Override
    public Vector2 getPosition() {
        return this.body.getPosition();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.trackAnimation.setFrameDuration(12.5f / speed);
        this.speed = speed;
    }

    @Override
    public void update(float delta) {
    }
}
