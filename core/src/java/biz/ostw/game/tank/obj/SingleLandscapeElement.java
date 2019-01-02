package biz.ostw.game.tank.obj;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;

import biz.ostw.game.tank.SideOfLight;
import biz.ostw.libgdx.DrawUtils;

public class SingleLandscapeElement implements LandscapeElement {

    private final Body body;

    private final Sprite sprite;

    protected SingleLandscapeElement(Body body, TextureRegion textureRegion) {

        body.setUserData(this);
        this.body = body;
        this.sprite = new Sprite(textureRegion);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector2 p = DrawUtils.box2d2screen2d(this.body.getPosition(), -LandscapeFactory.HALF_SIZE);

        this.sprite.setPosition(p.x, p.y);
        this.sprite.draw(batch);
    }

    @Override
    public void setSideOfLight(SideOfLight sideOfLight) {
        // By default do nothing.
    }

    @Override
    public SideOfLight getSideOfLight() {
        // By default do nothing.
        return null;
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

    @Override
    public void update(float delta) {
        // By default do nothing.
    }
}
