package biz.ostw.game.tank.obj.landscape;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;

import biz.ostw.game.tank.SideOfLight;
import biz.ostw.game.tank.obj.AbstractGameObject;
import biz.ostw.game.tank.obj.landscape.LandscapeElement;
import biz.ostw.game.tank.obj.landscape.LandscapeElementFactory;
import biz.ostw.game.tank.obj.landscape.LandscapeType;
import biz.ostw.libgdx.DrawUtils;

class SingleLandscapeElement extends AbstractGameObject<LandscapeType> implements LandscapeElement {

    private final Body body;

    private final Sprite sprite;

    private final Vector2 halfSize;

    protected SingleLandscapeElement(Body body, TextureRegion textureRegion, LandscapeType type) {

        super(type);

        body.setUserData(this);
        this.body = body;
        this.sprite = new Sprite(textureRegion);
        this.halfSize = DrawUtils.screen2d2box2d(new Vector2(LandscapeElementFactory.HALF_SIZE, LandscapeElementFactory.HALF_SIZE), 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector2 p = DrawUtils.box2d2screen2d(this.body.getPosition(), -LandscapeElementFactory.HALF_SIZE);

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
    public float getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(float speed) {
        // By default do nothing.
    }

    @Override
    public Vector2 getHalfSize() {
        return this.halfSize;
    }

    @Override
    public void update(float delta) {
        // By default do nothing.
    }
}
